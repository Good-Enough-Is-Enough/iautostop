<template>
  <v-autocomplete
    v-model="model"
    :items="items"
    :loading="isLoading"
    :search-input.sync="search"
    color="white"
    hide-no-data
    hide-selected
    item-text="Description"
    item-value="API"
    label="From"
    placeholder="Start typing to Search Street"
    prepend-icon="mdi-database-search"
    return-object
  ></v-autocomplete>
</template>

<script lang="js">
import { HERE_MAP_APP_ID, HERE_MAP_APP_CODE } from "../constants";

const CITY = 'Warsaw';

export default {
    name: "RouteSelector",
    data() {
      return {
        entries: [],
        isLoading: false,
        model: null,
        search: null,
      }
    },

  computed: {
    items () {
      return this.entries.map(entry => {
        const Description = entry.address.street.replace('ulica', 'ul. ');

        return Object.assign({}, entry, { Description })
      })
    },
  },

  watch: {
    model(val) {
      this.$emit('routeSelect', val.locationId);
    },
    search (val) {
      if (val.length < 3) return;

      // Items have already been requested
      if (this.isLoading) return;

      this.isLoading = true;

      // Lazily load input items
      fetch(`http://autocomplete.geocoder.api.here.com/6.2/suggest.json?app_id=${HERE_MAP_APP_ID}&app_code=${HERE_MAP_APP_CODE}&country=POL&query=${CITY}+${val}`)
              .then(res => res.json())
              .then(res => {
                const { suggestions } = res;
                this.entries = suggestions;
              })
              .catch(err => {
                console.log(err)
              })
              .finally(() => (this.isLoading = false))
    },
  },
}
</script>

<style scoped></style>
