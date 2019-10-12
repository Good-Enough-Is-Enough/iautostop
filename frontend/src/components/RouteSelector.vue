<template>
  <v-autocomplete
    v-model="model"
    :items="items"
    :loading="isLoading"
    :disabled="disabled"
    :search-input.sync="search"
    color="white"
    hide-no-data
    hide-selected
    item-text="Description"
    item-value="API"
    :label="direction"
    placeholder="Start typing to search street"
    prepend-icon="mdi-magnify"
    return-object
  />
</template>

<script lang="js">
import { HERE_MAP_APP_ID, HERE_MAP_APP_CODE } from "../constants";

const CITY = 'Warsaw';

export default {
    name: "RouteSelector",
    props: {
      direction: {String},
      disabled: {
        type: Boolean,
        default: false,
      }
    },
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
      return this.entries.map((entry) => {
        const Description = entry.address.street.replace('ulica', 'ul. ');

        return Object.assign({}, entry, { Description })
      })
    },
  },

  watch: {
    model(val) {
      this.$emit('route-select', val.locationId);
    },
    search (val) {
      if (val.length < 3) return;

      // Items have already been requested
      if (this.isLoading) return;

      this.isLoading = true;

      // Lazily load input items
      fetch(`http://autocomplete.geocoder.api.here.com/6.2/suggest.json?app_id=${HERE_MAP_APP_ID}&app_code=${HERE_MAP_APP_CODE}&country=POL&query=${CITY}+${val}`)
      .then((res) => res.json())
      .then((res) => {
        const { suggestions } = res;
        this.entries = suggestions;
      })
      .catch((err) => {
        console.error(err)
      })
      .finally(() => (this.isLoading = false))
    },
  },
}
</script>
