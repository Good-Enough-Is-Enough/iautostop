<template>
  <div>
    <v-overlay :value="isLoading">
      <v-progress-circular indeterminate size="64" />
    </v-overlay>
    <p v-if="!passengersList.length" class="text-center">
      W tym momencie nikt nie czeka na podwózkę
    </p>
    <p v-if="passengersList.length" class="text-center">
      Dostępni pasażerowie
    </p>
    <v-list>
      <v-list-item
        v-for="passenger in passengersList"
        :key="passenger.id"
        @click="selectPassenger($event, passenger.id)"
      >
        <v-list-item-icon>
          <v-icon v-text="'mdi-account'" />
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title v-text="passenger.name" />
          <v-list-item-subtitle
            v-html="`Z: ${passenger.travelFrom}, do: ${passenger.travelTo}`"
          ></v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </v-list>
    <p v-if="connectPassengers.length" class="text-center">
      Twoi pasażerowie
    </p>
    <v-list>
      <v-list-item v-for="passenger in connectPassengers" :key="passenger.id">
        <v-list-item-icon>
          <v-icon v-text="'mdi-account'" />
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title v-text="passenger.name" />
          <v-list-item-subtitle
            v-html="`Z: ${passenger.travelFrom}, do: ${passenger.travelTo}`"
          ></v-list-item-subtitle>
          <v-list-item-subtitle
            v-html="`Tel: ${passenger.phone}`"
          ></v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </div>
</template>

<script lang="js">
import isEqual from "lodash/isEqual";
import {getAvailablePassengersUrl, connectTo, getConnectedPassengers} from "../constants";
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
      connectPassengers: []
    }
  },
  mounted() {
    this.getPassengers();
    this.intervalId = window.setInterval(this.getPassengers, 5000);
    this.getConnectedPassengers();
  },
  methods: {
    selectPassenger($event, passengerId) {

      fetch(connectTo(passengerId, this.id), {
        method: "POST",
        headers: {
          'Content-Type': 'application/json',
        }
      })
      .then(() => {
        this.getPassengers();
        this.getConnectedPassengers();
      })
      .catch((err) => {
        console.error(err);
      });
    },
    getConnectedPassengers() {
      fetch(getConnectedPassengers(this.id))
      .then((res) => res.json())
      .then((res) => {

        this.connectPassengers = res;
      })
      .catch((err) => {
        console.error(err);
      });
    },
    getPassengers() {
      this.isLoading = true;

      fetch(getAvailablePassengersUrl(this.id), {
        headers: {
          'Content-Type': 'application/json',
        }
      })
      .then((res) => res.json())
      .then((res) => {
        if (res.length && !isEqual(this.passengersList, res)) {
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
