<template>
  <div class="text-xs mb-3 text-gray-700 uppercase bg-gray-100 dark:bg-gray-700 dark:text-gray-400">
    <p class="py-5 px-6 font-bold">Offer detail (id={{ selectedOfferCopy.id }})</p>
  </div>
  <form class="text-left">
    <div class="mb-6">
      <label for="title" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Title</label>
      <input type="text" id="title" placeholder="The title of a auction piece" v-model="selectedOfferCopy.title" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
    </div>
    <div class="mb-6">
      <label for="description" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Description</label>
      <input type="text" id="description" placeholder="Description of auction piece" v-model="selectedOfferCopy.description" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
    </div>
    <div class="mb-6">
      <label for="highestBid" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Highest bid</label>
      <input type="text" id="highestBid" placeholder="The highest bid of the auction bid" v-model="selectedOfferCopy.valueHighestBid" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
    </div>
    <div class="mb-6">
      <label for="highestBid" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Sell date</label>
      <input type="date" id="sellDate" placeholder="The highest bid of the auction bid" v-model="sellDateUpdater" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
    </div>
    <div class="mb-6">
      <label for="status" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-400">Status</label>
      <select id="status" v-model="selectedOfferCopy.status" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
        <option v-for="(status, index) in getStatuses()" :key="index">{{ status }}</option>
      </select>
    </div>
  </form>
  <div class="w-full flex justify-end gap-4">
    <button
        class="bg-red-500 p-3 rounded-md text-white cursor-pointer font-medium hover:bg-red-600"
        @click="$emit('delete-selected')"
    >
      Delete
    </button>
    <button
        class="bg-red-500 p-3 rounded-md text-white cursor-pointer font-medium hover:bg-red-600"
        @click="clearChanges()"
    >
      Clear
    </button>
    <button
        class="bg-yellow-400 p-3 rounded-md text-white cursor-pointer font-medium hover:bg-yellow-600 disabled:cursor-not-allowed disabled:bg-yellow-200"
        @click="revertChanges()" :disabled="setDisabled"
    >
      Revert
    </button>
    <button
        class="bg-red-500 p-3 rounded-md text-white cursor-pointer font-medium hover:bg-red-600"
        @click="cancelChanges()"
    >
      Cancel
    </button>
    <button
        class="bg-green-500 p-3 rounded-md text-white cursor-pointer font-medium hover:bg-green-600 disabled:cursor-not-allowed disabled:bg-green-200"
        @click="saveChanges()"
    >
      Save
    </button>
  </div>
</template>

<script>
import { Offer } from "@/models/offer";
import dateFormat from "dateformat";

export default {
  name: 'OffersDetail34',
  props: ['item'],
  emits: ['delete-selected', 'save-selected'],
  data() {
    return {
      selectedOfferCopy: null,
      setDisabled: true
    }
  },
  created() {
    this.selectedOfferCopy = Offer.copyConstructor(this.item);
  },  
  watch: {
    '$route'() {
      this.selectedOfferCopy = Offer.copyConstructor(this.item);
    },
  },
  methods: {
    onDelete() {},
    getStatuses() {
      return Offer.getStatusses()
    },
    offerChanged() {
      return this.item;
    },
    saveChanges() {
      this.$emit('save-selected', this.selectedOfferCopy);
      this.$router.push(this.$route.matched[0].path);
    },
    revertChanges() {
      this.selectedOfferCopy = Offer.copyConstructor(this.item);
    },
    clearChanges() {
      this.selectedOfferCopy.title = "";
      this.selectedOfferCopy.description = "";
      this.selectedOfferCopy.valueHighestBid = 0;
      this.selectedOfferCopy.sellDate = new Date();
      this.selectedOfferCopy.status = "NEW"
    },
    cancelChanges() {
      this.$router.push(this.$route.matched[0].path);
    },
  },
  computed: {
    mutableOffer() {
      return this.item.selectedOffer
    },
    sellDateUpdater: {
      // eslint-disable-next-line vue/return-in-computed-property
      get() {
        const sellDate = dateFormat(this.selectedOfferCopy.sellDate, "yyyy-mm-dd")
        return sellDate;
      },
      // eslint-disable-next-line no-unused-vars
      set(localDateTime) {
        const newDate = new Date(localDateTime);

        // eslint-disable-next-line
        this.selectedOfferCopy.sellDate = newDate;
      },
    },
    hasChanged() {
      // Use event listener on input fields
      const equals = Offer.equals(this.selectedOfferCopy);
      console.log(equals);
      return equals;
    }
  }
}
</script>

<style scoped>

</style>