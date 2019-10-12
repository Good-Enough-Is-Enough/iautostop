import Vue from "vue";
import Vuetify from "vuetify";
import "vuetify/dist/vuetify.min.css";
import { VuetifyPreset } from "vuetify/types/presets";

Vue.use(Vuetify);

const opts: VuetifyPreset = {
  icons: {
    iconfont: "mdi"
  }
};

export default new Vuetify(opts);
