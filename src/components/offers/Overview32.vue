/* vue/no-mutating-props */
<template>
  <div class="container mx-auto">
    <div class="flex flex-row gap-5">
      <div class="w-1/4">
        <!-- TODO add a scroll to the table of it exceeds max height -->
        <table class="w-full mb-3 text-sm text-left text-gray-500 dark:text-gray-400">
          <thead class="text-xs text-gray-700 uppercase bg-gray-100 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              <th class="py-5 px-6">Offer title</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item) in offers" :key="item.id">
              <td @click="selectItem(item)"
                class="py-4 px-6 font-medium whitespace-nowrap dark:text-white cursor-pointer rounded-sm hover:bg-gray-100"
                :class="[ selectedOffer?.id === item.id ? selectedRowStyle : notSelectedRowStyle ]">
                {{ item.title }}
              </td>
            </tr>
          </tbody>
        </table>
        <button class="bg-blue-500 w-full p-3 rounded-md text-white cursor-pointer font-medium hover:bg-blue-600"
          @click="onNewOffer">
          Add offer
        </button>
      </div>
      <div class="w-3/4">
        <div class="relative h-full">
          <Detail32 v-if="selectedOffer" v-bind:item="selectedOffer" @delete-selected="deleteOffer()" />
          <div v-else class="bg-violet-500 h-full w-full absolute flex rounded-md">
            <p class="text-white text-5xl font-bold m-auto">No offer was selected</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
/* eslint-disable vue/no-unused-components */

import { Offer } from "@/models/offer";
import Detail32 from "./Detail32.vue";

export default {
  name: "OffersOverview32",
  components: {
    Detail32,
  },
  created() {
    this.lastId = 30000;
    for (let i = 0; i < 8; i++) {
      this.offers.push(Offer.createSampleOffer(this.nextId));
      this.nextId = this.nextId + 3;
    }
  },

  data() {
    return {
      nextId: 30000,
      offers: [],
      selectedOffer: null,

      selectedRowStyle: "bg-blue-500 text-white",
      notSelectedRowStyle: "text-gray-900"
    }
  },

  methods: {
    onNewOffer: function () {
      const newOffer = Offer.createSampleOffer(this.nextId);
      this.selectedOffer = newOffer

      this.offers.push(newOffer);
      this.nextId = this.nextId + 3;
    },

    selectItem(element) {
      if (element === this.selectedOffer) {
        return null;
      }

      this.selectedOffer = element;
    },

    deleteOffer() {
      if (confirm("Are you sure you want to delete this offer?")) {
        this.offers = this.offers.filter(element => element.id !== this.selectedOffer.id)
        this.selectedOffer = null;
      }
    },

    getSelectedOffer() {
      if (!this.selectedOffer) {
        return new Offer(0, 'No title', 'CLOSED', 'No description', new Date(), 'No highest bid');
      }

      return this.selectedOffer;
    }
  },
}
</script>

<style scoped>

</style>