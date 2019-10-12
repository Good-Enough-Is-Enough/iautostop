<template>
  <v-content>
    <route-selector
      :disabled="isLoading"
      :direction="'From'"
      @route-select="onRouteSelect($event, 'from')"
    />
    <route-selector
      :disabled="isLoading"
      :direction="'To'"
      @route-select="onRouteSelect($event, 'to')"
    />
    <v-btn :loading="isLoading" @click="setUserInfo">Let's go!</v-btn>
  </v-content>
</template>

<script lang="js">
import RouteSelector from "../components/RouteSelector";
import { SET_USER_INFO } from "../constants";

export default {
  name: 'set-trip-view',
  components: {RouteSelector},
  props: {
    id: {String},
    role: {String}
  },
  data() {
    return {
      isLoading: false,
      user: {},
      travelFrom: '',
      travelTo: ''
    }
  },
  methods: {
    onRouteSelect(location, direction) {
      direction === 'from'
        ? this.travelFrom = location
        : this.travelTo = location
    },
    setUserInfo() {
      this.user = {
        id: this.id,
        role: this.role,
        travelFrom: this.travelFrom,
        travelTo: this.travelTo
      };

      this.isLoading = true;

      fetch(SET_USER_INFO, {
        method: "POST",
        body: JSON.stringify(this.user),
        headers: {
          'Content-Type': 'application/json',
        }
      })
      .then(() => {
        this.$router.push(`/${this.role}/${this.id}/passengers-list`)
      })
      .catch(err => {
        console.error(err)
      })
      .finally(() => (this.isLoading = false));
    }
  }
};
</script>

