import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home
    },
    {
      path: "/:type/:id/new-trip",
      name: "SetTripView",
      props: true,
      component: () => import("./views/SetTripView.vue")
    }
  ]
});
