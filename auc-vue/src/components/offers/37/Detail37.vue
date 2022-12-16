<template>
  <div v-if="notFound">
    <p>This item was not found</p>
  </div>
  <!-- If on load no offer was selected -->
  <div v-else-if="selectedOffer === null && selectedOfferCopy === null" class="w-full h-full flex flex-col items-center justify-center">
    <div role="status">
      <svg aria-hidden="true" class="mr-2 w-8 h-8 text-gray-200 animate-spin dark:text-gray-600 fill-blue-600" viewBox="0 0 100 101" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z" fill="currentColor"/>
        <path d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z" fill="currentFill"/>
      </svg>
      <span class="sr-only">Loading...</span>
    </div>
    <p>Loading...</p>
    <p>{{ notFound }}</p>
  </div>
  <div v-else>
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
          @click="deleteOffer()"
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
          @click="revertChanges()" :disabled="hasChanged"
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
          @click="saveChanges()" :disabled="hasChanged"
      >
        Save
      </button>
    </div>
  </div>
</template>

<script>
import { Offer } from "@/models/offer";
import dateFormat from "dateformat";

export default {
  name: 'OffersDetail37',
  props: ['item'],
  emits: ['getOffers'],
  inject: ['offersService'],
  data() {
    return {
      selectedOffer: null,
      selectedOfferCopy: null,
      notFound: false,
    }
  },
  created() {
    this.getOffer();
  },
  watch: {
    '$route'() {
      this.getOffer();
    },
  },
  methods: {
    async getOffer() {
      const id = this.$route.params.id;

      const request = await this.offersService.asyncFindById(id);

      if (request === null) {
        console.log("NOT FOUND");
        this.notFound = true;
        this.selectedOffer = null;
        this.selectedOfferCopy = null;

        return;
      }

      this.notFound = false;
      this.selectedOffer = request;
      this.selectedOfferCopy = Offer.copyConstructor(request);

      console.log(this.selectedOffer);
    },

    getStatuses() {
      return Offer.getStatusses()
    },
    deleteOffer() {
      if (!confirm("Are you sure you want to delete this offer?")) {
        return;
      }

      this.offersService.asyncDeleteById(this.selectedOfferCopy.id);
      this.$router.push(this.$route.matched[0].path);

      this.$emit('getOffers');
    },
    saveChanges() {
      if (!Offer.isFilledIn(this.selectedOfferCopy)) {
        return alert("You haven't filled in all the fields");
      }

      if (!confirm("Are you sure you want to save this offer?")) {
        return;
      }

      this.offersService.asyncSave(this.selectedOfferCopy);
      this.$emit('getOffers');
    },
    revertChanges() {
      if (confirm("Are you sure you want to revert this offer?")) {
        this.selectedOfferCopy = Offer.copyConstructor(this.selectedOffer);
      }
    },
    clearChanges() {
      if (confirm("Are you sure you want to clear the form?")) {
        this.selectedOfferCopy.title = "";
        this.selectedOfferCopy.description = "";
        this.selectedOfferCopy.valueHighestBid = 0;
        this.selectedOfferCopy.sellDate = new Date();
        this.selectedOfferCopy.status = "NEW"
      }
    },
    cancelChanges() {
      if (this.hasChanged) {
        this.$router.push(this.$route.matched[0].path);
        return;
      }

      if (confirm("Are you sure you want to cancel?")) {
        this.$router.push(this.$route.matched[0].path);
      }
    },

    leaveRouteConfirm(){
      return (this.hasChanged && !confirm('Changes that you made may not be saved.'))
    },

    beforeWindowUnload(e){
      if (this.leaveRouteConfirm()){
        // cancel event
        e.preventDefault()
        //requires a return value
        e.returnValue =''
      }
    },
  },
  computed: {
    mutableOffer() {
      return this.selectedOffer
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
      return Offer.equals(this.selectedOffer, this.selectedOfferCopy);
    },
  },

  // Unsaved changes on route change
  beforeRouteLeave(){
    return (this && !this.leaveRouteConfirm());
  },
  beforeRouteUpdate(){
    return (this && !this.leaveRouteConfirm());
  },
  mounted(){
    addEventListener('beforeunload', this.beforeWindowUnload)
  },
  beforeUnmount(){
    removeEventListener('beforeunload', this.beforeWindowUnload)
  }
}
</script>

<style scoped>

</style>