<template>
  <v-content>
    <v-container class="fill-height" fluid>
      <v-col cols="12">
        <v-text-field placeholder="Podaj swoje imię" v-model="name" />
        <v-text-field
          placeholder="Numer telefonu"
          v-model="phone"
          v-mask="phoneMask"
        />
        <location-selector
          :disabled="isLoading"
          :direction="'From'"
          @route-select="onRouteSelect($event, 'from')"
        />
        <location-selector
          :disabled="isLoading"
          :direction="'To'"
          @route-select="onRouteSelect($event, 'to')"
        />
        <v-row align="center" justify="center">
          <v-btn :loading="isLoading" @click="setUserInfo">Jedźmy!</v-btn>
        </v-row>
      </v-col>
    </v-container>
  </v-content>
</template>

<script lang="js">
import { mask } from "vue-the-mask";

import { LocationSelector } from "../components";
import { SET_USER_INFO } from "../constants";

export default {
  name: 'SetTripView',
  components: {LocationSelector},
  props: {
    id: {String},
    role: {String}
  },
  directives: {
    mask,
  },
  data() {
    return {
      isLoading: false,
      user: {},
      name: null,
      phone: null,
      phoneMask: '###-###-###',
      travelFrom: null,
      travelTo: null
    }
  },
  methods: {
    onRouteSelect(location, direction) {
      direction === 'from'
        ? this.travelFrom = location
        : this.travelTo = location
    },
    setUserInfo() {
      this.user = {
        id: this.id,
        role: this.role,
        name: this.name,
        phone: this.phone,
        travelFrom: this.travelFrom,
        travelTo: this.travelTo
      };

      if (!this.travelFrom || !this.travelTo || !this.name || !this.name.length) {
        return;
      }

      this.isLoading = true;

      fetch(SET_USER_INFO, {
        method: "POST",
        body: JSON.stringify(this.user),
        headers: {
          'Content-Type': 'application/json',
        }
      })
      .then(() => {
        if (this.role === 'driver') {
          this.$router.push(`/${this.role}/${this.id}/panel`);
        } else if (this.role === 'passenger') {
          this.$router.push(`/${this.role}/${this.id}/panel`);
        }
      })
      .catch((err) => {
        console.error(err);
      })
      .finally(() => (this.isLoading = false));
    }
  }
};
</script>
