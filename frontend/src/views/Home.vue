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
          <v-btn block to="/driver/new-trip">Driver</v-btn>
          <v-btn block to="/passenger/new-trip">Passenger</v-btn>
        </v-row>
      </v-col>
    </v-container>
  </v-content>
</template>

<script lang="js">
export default {
  name: 'Home'
}
</script>
