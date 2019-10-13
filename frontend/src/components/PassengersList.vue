<template>
  <div>
    <p v-if="!passengersList.length" class="text-justify">
      W tym momencie nikt nie czeka na podwózkę
    </p>
    <v-overlay :value="isLoading">
      <v-progress-circular indeterminate size="64" />
    </v-overlay>
    <v-list>
      <v-list-item
        v-for="passenger in passengersList"
        :key="passenger.id"
        @click="selectPassenger"
      >
        <v-list-item-icon>
          <v-icon v-text="'mdi-account'" />
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title v-text="passenger.travelTo" />
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </div>
</template>

<script lang="js">
import isEqual from "lodash/isEqual";
import {getAvailablePassengersUrl} from "../constants";
import {notifyMe} from "../utils/notificationService";


export default {
  name: 'PassengersListView',
  components: {},
  props: {
    id: {String},
  },
  data() {
    return {
      isLoading: false,
      passengersList: [],
      intervalId: null,
    }
  },
  mounted() {
    this.getPassengers();
    this.intervalId = window.setInterval(this.getPassengers, 5000);
  },
  methods: {
    selectPassenger() {

    },
    getPassengers() {
      this.isLoading = true;

      fetch(getAvailablePassengersUrl(this.id),{
        headers: {
          'Content-Type': 'application/json',
        }
      })
      .then((res) => res.json())
      .then((res) => {

        if(res.length && !isEqual(this.passengersList, res)) {
          notifyMe('New passengers!')
        }

        this.passengersList = res;
      })
      .catch((err) => {
        console.error(err);
      })
      .finally(() => (this.isLoading = false));
    }
  }
};
</script>
