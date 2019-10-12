<template>
  <div id="app">
    <v-app id="inspire">
      <div>
        <v-app-bar color="red accent-4" dark>
          <img alt="Vue logo" src="./assets/logo.png" height="50px" />
          <v-toolbar-title>iAutostop</v-toolbar-title>
        </v-app-bar>

        <router-view />

        <v-footer>
          <div class="flex-grow-1"></div>
          <div>&copy; {{ new Date().getFullYear() }}</div>
        </v-footer>
      </div>
    </v-app>
  </div>
</template>
