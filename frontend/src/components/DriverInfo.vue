<template>
  <div>
    <div class="driver-info">
      <strong>{{ text }}</strong>
    </div>
    <div class="driver-info">
      <v-icon v-text="'mdi-account'" />
      {{ driver.name }}
    </div>
    <div class="driver-info">
      <v-icon v-text="'mdi-phone'" />
      {{ driver.phone }}
    </div>
  </div>
</template>

<script lang="js">
export default {
  name: "DriverInfo",
  props: {
    text: { type: String, default: "" },
    driver: { type: Object, default: () => {} }
  }
};
</script>

<style scoped>
.driver-info {
  margin-bottom: 12px;
}
</style>
