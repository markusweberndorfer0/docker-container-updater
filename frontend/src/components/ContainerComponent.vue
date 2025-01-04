<template>
  <h1 class="text-center mb-3">Containers</h1>

  <div class="flex flex-col">
    <div
      v-for="container of containers"
      :key="container.id"
      class="border-2 border-accent rounded-lg mb-4"
    >
      <div class="flex flex-col">
        <div class="border-b-2 border-accent flex flex-row justify-between p-1 px-2 items-center">
          <h2 :class="container.updateAvailable ? 'text-primary' : ''">{{ container.name }}</h2>
          <button
            disabled
            v-if="!container.updateAvailable"
            class="px-1 bg-secondary rounded-lg h-max py-[0.25em]"
          >
            Up-to-date
          </button>
          <button
            disabled
            v-else
            class="px-1 bg-primary rounded-lg h-max py-[0.25em] cursor-pointer"
          >
            Update now
          </button>
        </div>
        <div class="p-2">
          <table class="w-full">
            <tr>
              <td class="font-bold">Image name</td>
              <td>{{ container.image }}</td>
            </tr>
            <tr>
              <td class="font-bold">Local Etag (Current version)</td>
              <td class="break-all">{{ container.localEtag }}</td>
            </tr>
            <tr>
              <td class="font-bold">Remote Etag (Available version)</td>
              <td class="break-all">{{ container.remoteEtag }}</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import { ContainerApi, type ContainerModel } from '@/api';

const containerApi = new ContainerApi();
const containers: Ref<ContainerModel[]> = ref([]);

onMounted(async () => {
  containers.value = await containerApi.getAllContainers();
});
</script>

<style scoped lang="scss"></style>
