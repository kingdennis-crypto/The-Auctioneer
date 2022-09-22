<template>
  <form v-if="selectedOrder">
    <div class="mb-6">
      <label for="title" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Title</label>
      <input type="text" id="title" placeholder="The title of a auction piece" v-model="selectedOrder.title" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
    </div>
    <div class="mb-6">
      <label for="description" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Description</label>
      <input type="text" id="description" placeholder="Description of auction piece" v-model="selectedOrder.description" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
    </div>
    <div class="mb-6">
      <label for="highestBid" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Highest bid</label>
      <input type="text" id="highestBid" placeholder="The highest bid of the auction bid" v-model="selectedOrder.valueHighestBid" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
    </div>
    <div class="mb-6">
      <label for="highestBid" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Highest bid</label>
      <input type="date" id="highestBid" placeholder="The highest bid of the auction bid" v-model="selectedOrder.sellDate" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
    </div>
    <div class="mb-6">
      <label for="status" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-400">Status</label>
      <select id="status" v-model="selectedOrder.status" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
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
    <div id="newOfferBtn">
      <button v-on:click="onNewOffer" type="button" class="float-right text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center mt-3">
        <i class="fa fa-plus"></i> New Offer
      </button>
    </div>
  </form>
</template>

<script>
// import { Offer } from "@/models/offer";

export default {
  name: "OffersDetail32",
  props: ['item'],
  emits: ['delete-selected'],
  data() {
    return {
      selectedOrder: JSON.parse(this.item),
    }
  },
  methods: {
    onDelete() {
    }
  },
  computed: {
    sellDataUpdater: {
      // eslint-disable-next-line vue/return-in-computed-property
      get() {
        return;
      },
      // eslint-disable-next-line no-unused-vars
      set(localDateTime) {

      },
    }
  }
}
</script>

<style scoped>

</style>