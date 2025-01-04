<script setup lang="ts">
import { RouterView } from 'vue-router';
import Navbar from '@/components/NavbarComponent.vue';
</script>

<template>
  <div class="min-h-screen">
    <header>
      <Navbar />
    </header>

    <main class="ml-[25%] w-[50%] mt-4">
      <RouterView />
    </main>
  </div>
</template>

<style scoped lang="scss"></style>
