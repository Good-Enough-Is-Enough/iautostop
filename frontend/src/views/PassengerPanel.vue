<template>
  <v-content>
    <lift-status-message
      v-if="isLoading && !showError"
      :loading="isLoading"
      :message="message.waiting"
    />
    <lift-status-message
      v-if="!isLoading && !showError"
      :message="message.success"
      :driver-info="availableDriver"
    />
    <lift-status-message
      v-if="!isLoading && showError"
      :message="message.error"
    />
  </v-content>
</template>

<script lang="js">
import { notifyMe } from "../utils/notificationService";
import { getAvailableDriver, MSG } from "../constants";
import { LiftStatusMessage } from "../components";

export default {
  name: "PassengerPanel",
  components: { LiftStatusMessage },
  props: {
    id: {String},
    role: {String}
  },
  data() {
    return {
      availableDriver: {
        name: null,
        phone: null
      },
      isLoading: true,
      intervalId: null,
      showError: false
    }
  },
  computed: {
    message() {
      return MSG.PASSENGER;
    }
  },
  mounted() {
    setTimeout(() => {
        this.getDriver();
    }, 1000);
    this.intervalId = window.setInterval(this.getDriver, 5000);
  },
  destroyed() {
    window.clearInterval(this.intervalId);
  },
  methods: {
    getDriver() {
      this.isLoading = true;

      fetch(getAvailableDriver(this.id), {
        headers: {
          'Content-Type': 'application/json',
        }
      })
      .then((res) => res.json())
      .then((res) => {
        this.availableDriver = res;
        window.clearInterval(this.intervalId);

          this.isLoading = false;
          notifyMe('Podwózka zaakceptowana!');

      })
      .catch((err) => {
        console.log(err);
        // window.clearInterval(this.intervalId);
        // this.isLoading = false;
        // this.showError = true;
      })
    }
  }
};
</script>
