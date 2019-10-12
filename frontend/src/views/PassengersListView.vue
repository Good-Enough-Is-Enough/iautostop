<template>
  <v-content>
    <v-overlay :value="isLoading">
      <v-progress-circular indeterminate size="64"></v-progress-circular>
    </v-overlay>
    <v-list>
      <v-list-item
        v-for="passenger in passengersList"
        :key="passenger.id"
        @click="selectPassenger"
      >
        <v-list-item-icon>
          <v-icon v-text="'mdi-account'"></v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title v-text="passenger.travelTo"></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-content>
</template>

<script lang="js">
import {AVAILABLE_PASSENGERS} from "../constants";

export default {
  name: 'passengers-list-view',
  components: {},
  props: {
    id: {String},
    role: {String}
  },
  data() {
    return {
      isLoading: false,
      passengersList: []
    }
  },
  mounted() {
    this.getPassengers();
  },
  methods: {
    selectPassenger() {

    },
    getPassengers() {

      this.isLoading = true;

      fetch(`/${this.id}${AVAILABLE_PASSENGERS}`,{
        headers: {
          'Content-Type': 'application/json',
        }
      })
      .then(res => res.json())
      .then(res => {
        this.passengersList = res;
      })
      .catch(err => {
        console.error(err)
      })
      .finally(() => (this.isLoading = false));
    }
  }
};
</script>

