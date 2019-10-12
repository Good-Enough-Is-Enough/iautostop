<template>
  <div id="app">
    <v-app id="inspire">
      <div>
        <v-app-bar color="red accent-4" dark>
          <img alt="Vue logo" src="./assets/logo.png" height="50px"/>
          <v-toolbar-title>iAutostop</v-toolbar-title>
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
