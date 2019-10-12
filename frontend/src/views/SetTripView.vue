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
  </v-content>
</template>

<script lang="js">
import RouteSelector from "../components/RouteSelector";

export default {
  name: 'set-trip-view',
  components: {RouteSelector},
  props: {
    id: {String},
    type: {String}
  },
  methods: {
    onRouteSelect(location, direction) {
      console.log(location, direction);
    }
  }
};
</script>

<style scoped></style>
