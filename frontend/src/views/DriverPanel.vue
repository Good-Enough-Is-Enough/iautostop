<template>
  <v-content>
    <v-container class="fill-height" fluid>
      <v-row no-gutters>
        <v-col cols="12">
          <div class="text-center mb-2">
            <v-btn text color="deep-purple" @click="showMap = !showMap">
              Pokaż mapę trasy
            </v-btn>
          </div>
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col cols="12">
          <route-map :from="from" :to="to" v-show="showMap" />
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col cols="12">
          <passengers-list-view :id="id" />
        </v-col>
      </v-row>
    </v-container>
  </v-content>
</template>

<script lang="js">
import RouteMap from "../components/RouteMap";
import PassengersListView from "../components/PassengersList";
import {getRouteStreet} from "../constants";

export default {
  name: 'driver-panel-view',
  components: {RouteMap, PassengersListView},
  props: {
    id: {String},
  },
  data() {
    return {
      showMap: false,
      isLoading: false,
      from: null,
      to: null
    }
  },
  mounted() {
    this.getRoute();
  },
  methods: {
    getRoute() {
      this.isLoading = true;

      fetch(getRouteStreet(this.id),{
        headers: {
          'Content-Type': 'application/json',
        }
      })
      .then((res) => res.json())
      .then((res) => {
        this.from = res.startCoords;
        this.to = res.endCoords;
      })
      .catch((err) => {
        console.error(err);
      })
      .finally(() => (this.isLoading = false));
    }
  }
};
</script>
