<template>
  <div class="text-xs mb-3 text-gray-700 uppercase bg-gray-100 dark:bg-gray-700 dark:text-gray-400">
    <p class="py-5 px-6 font-bold">Offer detail (id={{ item.id }})</p>
  </div>
  <form class="text-left">
    <div class="mb-6">
      <label for="title" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Title</label>
      <!-- eslint-disable-next-line -->
      <input type="text" id="title" placeholder="The title of a auction piece" v-model="item.title" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
    </div>
    <div class="mb-6">
      <label for="description" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Description</label>
      <!-- eslint-disable-next-line -->
      <input type="text" id="description" placeholder="Description of auction piece" v-model="item.description" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
    </div>
    <div class="mb-6">
      <label for="highestBid" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Highest bid</label>
      <!-- eslint-disable-next-line -->
      <input type="text" id="highestBid" placeholder="The highest bid of the auction bid" v-model="item.valueHighestBid" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
    </div>
    <div class="mb-6">
      <label for="highestBid" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Sell date</label>
      <!-- eslint-disable-next-line -->
      <input type="date" id="sellDate" placeholder="The highest bid of the auction bid" v-model="sellDataUpdater" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
    </div>
    <div class="mb-6">
      <label for="status" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-400">Status</label>
      <!-- eslint-disable-next-line -->
      <select id="status" v-model="item.status" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
        <option>NEW</option>
        <option>FOR_SALE</option>
        <option>SOLD</option>
        <option>PAID</option>
        <option>DELIVERED</option>
        <option>CLOSED</option>
        <option>EXPIRED</option>
        <option>WITHDRAWN</option>
      </select>
    </div>
  </form>
  <div class="w-full flex justify-end">
    <button 
      class="bg-red-500 p-3 rounded-md text-white cursor-pointer font-medium hover:bg-red-600" 
      @click="$emit('delete-selected')"
    >
      Delete offer
    </button>
  </div>
</template>

<script>
// import { Offer } from "@/models/offer";
import dateFormat from "dateformat";

export default {
  name: "OffersDetail32",
  props: ['item'],
  emits: ['delete-selected'],
  data() {
    return {}
  },
  methods: {
    onDelete() {
    },
  },
  computed: {
    sellDataUpdater: {
      // eslint-disable-next-line vue/return-in-computed-property
      get() {
        const sellDate = dateFormat(this.item.sellDate, "yyyy-mm-dd")
        return sellDate;
      },
      // eslint-disable-next-line no-unused-vars
      set(localDateTime) {
        const newDate = new Date(localDateTime);

        // eslint-disable-next-line
        this.item.sellDate = newDate;
      },
    }
  }
}
</script>

<style scoped>

</style>