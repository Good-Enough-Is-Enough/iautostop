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
      path: "/driver",
      name: "driver-view",
      component: () => import("./views/DriverView.vue")
    },
    {
      path: "/passenger",
      name: "passenger-view",
      component: () => import("./views/PassengerView.vue")
    }
  ]
});
