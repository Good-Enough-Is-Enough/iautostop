<template>
  <v-card :loading="loading" class="mx-auto card" max-width="500">
    <v-img class="white--text align-end" height="200px" :src="message.image">
      <v-card-title>
        <strong>{{ message.title }}</strong>
      </v-card-title>
    </v-img>

    <v-card-text class="text--primary">
      <span v-if="message.type === 'success'">
        <driver-info :text="message.text" :driver="driverInfo" />
      </span>
      <span v-else>
        <v-icon v-if="message.icon" v-text="message.icon" />
        {{ message.text }}
      </span>
    </v-card-text>

    <v-card-actions v-if="message.button">
      <v-btn color="orange" text to="/">
        {{ message.button }}
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script lang="js">
import { DriverInfo } from "../components";

export default {
  name: "LiftStatusMessage",
  components: {DriverInfo},
  props: {
    loading: { type: Boolean, default: false },
    message: { type: Object, default: () => {} },
    driverInfo: { type: Object, default: () => {} }
  }
};
</script>

<style lang="scss" scoped>
.card {
  margin-top: 50px;
}
</style>
