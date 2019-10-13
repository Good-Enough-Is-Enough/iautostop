<template>
  <v-content>
    <v-container fluid>
      <v-col cols="12">
        <v-row
          align="center"
          justify="center"
          class="grey lighten-5"
          style="height: 300px;"
        >
          <v-btn block x-large :to="`/driver/${generateUserId()}/new-trip`">
            <v-icon class="home-page-icon" v-text="'mdi-car'" />
            Chcę zabrać pasażerów
          </v-btn>
          <v-btn block x-large :to="`/passenger/${generateUserId()}/new-trip`">
            <v-icon class="home-page-icon" v-text="'mdi-account-clock'" />
            Potrzebuję podwózki
          </v-btn>
        </v-row>
      </v-col>
    </v-container>
  </v-content>
</template>

<script lang="js">
const shortid = require("shortid");

export default {
  name: "Home",
  methods: {
    generateUserId() {
      return shortid.generate();
    }
  }
}
</script>

<style lang="scss">
.home-page-icon {
  margin-right: 12px;
}
</style>
