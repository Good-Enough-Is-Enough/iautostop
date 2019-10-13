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
      path: "/:role/:id/new-trip",
      name: "SetTripView",
      props: true,
      component: () => import("./views/SetTripView.vue")
    },
    {
      path: "/driver/:id/panel",
      name: "DriverPanelView",
      props: true,
      component: () => import("./views/DriverPanel.vue")
    },
    {
      path: "/passenger/:id/panel",
      name: "PassengerPanel",
      props: true,
      component: () => import("./views/PassengerPanel.vue")
    }
  ]
});
