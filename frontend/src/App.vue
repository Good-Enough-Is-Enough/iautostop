<template>
  <div id="app">
    <v-app id="inspire">
      <div>
        <v-app-bar color="deep-purple accent-4" dark>
          <v-toolbar-title>Iautostop</v-toolbar-title>
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
