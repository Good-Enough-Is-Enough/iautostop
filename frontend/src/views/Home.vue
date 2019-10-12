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
          <v-btn block :to="`/driver/${generateUserId()}/new-trip`">
            I'm a Driver
          </v-btn>
          <v-btn block :to="`/passenger/${generateUserId()}/new-trip`">
            I'm a Passenger
          </v-btn>
        </v-row>
      </v-col>
    </v-container>
  </v-content>
</template>

<script lang="js">
const shortid = require("shortid");

export default {
  name: 'Home',
  methods: {
    generateUserId() {
      return shortid.generate();
    }
  }
}
</script>
