<template>
  <div
    class="pl-5 pr-5 pt-2 pb-2 flex flex-row justify-between items-center shadow-md shadow-accent"
  >
    <h2>Docker Container Updater</h2>
    <div class="flex flex-row items-center h-full">
      <router-link class="mr-4" to="/" active-class="text-primary"><h3>Containers</h3></router-link>
      <router-link class="test" to="/logout" active-class="text-primary"><h3>Logout</h3></router-link>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped lang="scss"></style>
