<template>
  <div class="navbar-container">
    <div class="navbar-header">Docker Container Updater</div>

    <div class="navbar-clickable" v-if="navbarVisible">
      <div class="navbar-elements">
        <RouterLink class="navbar-element" to="/">Home</RouterLink>
      </div>

      <div class="navbar-options navbar-element">
        <v-menu>
          <template v-slot:activator="{ props }">
            <div v-bind="props">More</div>
          </template>
          <v-list>
            <v-list-item v-for="(item, index) in ['1', '2']" :key="index" :value="index">
              <v-list-item-title>{{ item }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
    </div>

    <div class="navbar-icon" @click="navbarVisible = !navbarVisible">≡</div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';

const navbarVisible = ref(true);

onMounted(() => {
  if (window.outerWidth < 1000) {
    navbarVisible.value = false;
  }
});
</script>

<style scoped lang="scss">
@use '../assets/palette';

.navbar-container {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  height: palette.$navbar-height;

  color: white;
  font-size: 20px;
  background-color: black;

  .navbar-clickable {
    height: 100%;
    display: flex;
    align-items: center;
  }

  .navbar-element {
    text-decoration: none;
    color: white;
  }

  .navbar-element:not(:last-child) {
    margin-right: 25px;
  }

  .navbar-header {
    position: absolute;
    left: 25px;
    user-select: none;
  }

  .navbar-options {
    position: absolute;
    right: 25px;
    user-select: none;
    cursor: pointer;
  }

  .navbar-icon {
    display: none;
    transform: scale(1.75);

    user-select: none;
    cursor: pointer;
  }
}

.router-link-active {
  text-decoration: underline !important;
}

@keyframes mobile-navbar-slide-in {
  0% {
    transform: translateX(-100%);
    opacity: 0;
  }

  100% {
    transform: translateY(0%);
    opacity: 1;
  }
}

@media screen and (max-width: 1000px) {
  .navbar-container {
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    width: auto;
    padding: 0 25px;

    .navbar-header {
      position: static;
    }

    .navbar-clickable {
      position: absolute;
      top: palette.$navbar-height;
      left: 0;

      background-color: rgba(0, 0, 0, 0.75);

      color: white;
      width: 100%;
      height: auto;

      display: flex;
      flex-direction: column;
      align-items: center;

      animation: mobile-navbar-slide-in 0.2s;
      animation-timing-function: ease-in-out;
    }

    .navbar-elements {
      display: flex;
      flex-direction: column;
      align-items: center;
      width: auto;
    }

    .navbar-element {
      width: auto;
      margin: 5px 0;
    }

    .navbar-element:not(:last-child) {
      margin-right: 0;
    }

    .navbar-options {
      position: static;
    }

    .navbar-icon {
      position: static;
      display: block;
    }
  }
}
</style>
