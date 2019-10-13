<template>
  <div class="text-center mb-2">
    <img
      :src="
        `https://image.maps.api.here.com/mia/1.6/routing?app_id=${appId}&app_code=${appCode}&waypoint0=${from}&waypoint1=${to}&lc=1652B4&lw=6&t=0&ppi=320&w=1920&h=1080`
      "
    />
  </div>
</template>

<script>
import { HERE_MAP_APP_ID, HERE_MAP_APP_CODE } from "../constants";

export default {
  name: "RouteMap",
  props: {
    from: { String },
    to: { String }
  },
  data() {
    return {
      appId: HERE_MAP_APP_ID,
      appCode: HERE_MAP_APP_CODE
    };
  }
};
</script>

<style scoped>
img {
  height: calc(100vh - 150px);
}
</style>
