<template>
  <v-content>
    <route-selector
      :direction="'From'"
      @route-select="onRouteSelect($event, 'from')"
    />
    <route-selector
      :direction="'To'"
      @route-select="onRouteSelect($event, 'to')"
    />
    <v-btn @click="setUserInfo">Let's go!</v-btn>
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

      fetch(SET_USER_INFO, {method: "POST", body: JSON.stringify(this.user)});
    }
  }
};
</script>

<style scoped></style>
