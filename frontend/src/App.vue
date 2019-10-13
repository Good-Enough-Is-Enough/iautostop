<template>
  <div id="app">
    <v-app id="inspire">
      <div>
        <v-app-bar color="red accent-4" dark>
          <router-link to="/">
            <img alt="Vue logo" src="./assets/logo.png" height="50px" />
          </router-link>
          <v-toolbar-title>
            <router-link to="/" class="toolbar-title">iAutostop</router-link>
          </v-toolbar-title>
        </v-app-bar>

        <router-view />

        <v-footer absolute class="text-center">
          <v-col class="text-center" cols="12">
            &copy; {{ new Date().getFullYear() }} — <strong>GoodEnough</strong>
          </v-col>
        </v-footer>
      </div>
    </v-app>
  </div>
</template>

<style scoped>
.v-toolbar__title a {
  color: white;
  text-decoration: none;
}
</style>
