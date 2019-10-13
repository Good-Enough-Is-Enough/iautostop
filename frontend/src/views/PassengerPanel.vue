<template>
  <v-content>
    <v-card
      v-if="isLoading && !showError"
      :loading="isLoading"
      class="mx-auto card"
      max-width="500"
    >
      <v-img
        class="white--text align-end"
        height="200px"
        src="https://cdn.pixabay.com/photo/2019/08/20/15/32/cialis-4419052_960_720.jpg"
      >
        <v-card-title>
          <strong>Szukamy dla Ciebie podwózki...</strong>
        </v-card-title>
      </v-img>

      <v-card-text class="text--primary">
        <div>Powiadomimy Cię,gdy któryś z dostępnych</div>
        <div>kierowców zaakceptuje przejazd</div>
      </v-card-text>
    </v-card>

    <v-card v-if="!isLoading && !showError" class="mx-auto card" max-width="500">
      <v-img
        class="white--text align-end"
        height="200px"
        src="https://previews.123rf.com/images/tanyalmera/tanyalmera1606/tanyalmera160600005/58296044-hooray-word-cartoon-hand-lettering-positive-saying-for-cards-posters-and-social-media-content-happin.jpg"
      >
        <v-card-title class="text--primary"
          ><strong>Podwózka zaakceptowana!</strong></v-card-title
        >
      </v-img>

      <v-card-text class="text--primary">
        <div class="driver-info"><strong>DANE KIEROWCY:</strong></div>
        <div class="driver-info"><v-icon v-text="'mdi-account'" />Stefan</div>
        <div class="driver-info">
          <v-icon v-text="'mdi-phone'" />555-555-555
        </div>
      </v-card-text>

      <v-card-actions>
        <v-btn color="orange" text>
          Wróć do strony głównej
        </v-btn>
      </v-card-actions>
    </v-card>

    <v-card v-if="!isLoading && showError" class="mx-auto card" max-width="500">
      <v-img
              class="white--text align-end"
              height="200px"
              src="https://previews.123rf.com/images/tanyalmera/tanyalmera1606/tanyalmera160600005/58296044-hooray-word-cartoon-hand-lettering-positive-saying-for-cards-posters-and-social-media-content-happin.jpg"
      >
        <v-card-title class="text--primary">
          <strong>Podwózka zaakceptowana!</strong>
        </v-card-title>
      </v-img>

      <v-card-text class="text--primary">
        <div>
          <v-icon v-text="'mdi-internet'" />
          Sprawdź swoje połączenie sieciowe i spróbuj ponownie
        </div>
      </v-card-text>

      <v-card-actions>
        <v-btn color="orange" text>
          Wróć do strony głównej
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-content>
</template>

<script lang="js">
import { getAvailableDriver } from "../constants";

export default {
  name: "PassengerPanel",
  props: {
    id: {String},
    role: {String}
  },
  data() {
    return {
      availableDriver: {},
      isLoading: false,
      intervalId: null,
      showError: false
    }
  },
  mounted() {
    this.getDriver();
    this.intervalId = window.setInterval(this.getDriver, 5000);
  },
  methods: {
    getDriver() {
      this.isLoading = true;

      fetch(getAvailableDriver(this.id), {
        headers: {
          'Content-Type': 'application/json',
        }
      })
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
        this.isLoading = false;
        this.showError = true;
      })
    }
  }
};
</script>

<style lang="scss" scoped>
.card {
  margin-top: 50px;
}

.driver-info {
  margin-bottom: 12px;
}
</style>
