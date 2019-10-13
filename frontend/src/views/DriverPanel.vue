<template>
  <v-content>
    <v-container fluid>
      <v-row no-gutters>
        <v-col cols="12">
          <div class="text-center mb-2">
            <v-btn text color="deep-purple" @click="showMap = !showMap">
              {{ showMap ? "Ukryj " : "Pokaż " }}mapę trasy
            </v-btn>
          </div>
        </v-col>
        <v-col cols="12">
          <route-map :from="from" :to="to" v-show="showMap" />
        </v-col>
        <v-col cols="12">
          <div class="text-center mb-2">
            <v-btn text color="deep-purple" @click="showRoute = !showRoute">
              {{ showRoute ? "Ukryj " : "Pokaż " }}trasę
            </v-btn>
          </div>
        </v-col>
        <v-col cols="12" v-show="showRoute">
          <p>Przebieg trasy</p>
          <v-list>
            <v-list-item v-for="(street, index) in streetNames" :key="index">
              <v-list-item-icon>
                <v-icon v-text="'mdi-map-marker-path'" />
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title v-text="street" />
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-col>
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
      showRoute: false,
      isLoading: false,
      from: null,
      to: null,
      streetNames: null
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
        this.streetNames = res.streetNames;
      })
      .catch((err) => {
        console.error(err);
      })
      .finally(() => (this.isLoading = false));
    }
  }
};
</script>
