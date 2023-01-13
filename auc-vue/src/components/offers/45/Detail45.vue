<template>
  <div v-if="notFound">
    <p>This item was not found</p>
  </div>
  <!-- If on load no offer was selected -->
  <div v-else-if="offer === null && offerCopy === null" class="w-full h-full flex flex-col items-center justify-center">
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
      <p class="py-5 px-6 font-bold">Offer detail (id={{ offerCopy.id }})</p>
    </div>
    <form class="text-left">
      <div class="mb-6">
        <label for="title" class="block text-sm font-medium text-gray-900 dark:text-gray-300">Title</label>
        <input type="text" id="title" placeholder="The title of a auction piece" v-model="offerCopy.title" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" disabled>
      </div>
      <div class="mb-6">
        <label for="description" class="block text-sm font-medium text-gray-900 dark:text-gray-300">Description</label>
        <input type="text" id="description" placeholder="Description of auction piece" v-model="offerCopy.description" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" disabled>
      </div>
      <div class="mb-6">
        <label for="highestBid" class="block text-sm font-medium text-gray-900 dark:text-gray-300">Highest bid</label>
        <input type="text" id="highestBid" placeholder="The highest bid of the auction bid" v-model="offerCopy.valueHighestBid" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" disabled>
      </div>
      <div class="mb-6">
        <label for="highestBid" class="block text-sm font-medium text-gray-900 dark:text-gray-300">Sell date</label>
        <input type="date" id="sellDate" placeholder="The highest bid of the auction bid" v-model="sellDateUpdater" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" disabled>
      </div>
      <div class="mb-6">
        <label for="status" class="block text-sm font-medium text-gray-900 dark:text-gray-400">Status</label>
        <select id="status" v-model="offerCopy.status" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" disabled>
          <option v-for="(status, index) in getStatuses()" :key="index">{{ status }}</option>
        </select>
      </div>
      <div class="mb-6">
        <label for="latestBid" class="block text-sm font-medium text-gray-900 dark:text-gray-300">Latest Bid</label>
        <input type="text" id="title" placeholder="The title of a auction piece" :value="JSON.stringify(offerCopy.latestBid)" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" disabled>
      </div>
      <div class="mb-6">
        <label for="highestBid" class="block text-sm font-medium text-gray-900 dark:text-gray-300">New Bid</label>
        <input type="text" id="highestBid" placeholder="The highest bid of the auction bid" v-model="newBidValue" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" required>
      </div>
      <div class="flex flex-row space-x-2">
        <button @click="submitBid" :disabled="!newBidIsHigher" type="button" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mb-2 disabled:bg-blue-200 disabled:cursor-not-allowed">Submit</button>
        <button @click="cancelChanges" type="button" class="py-2.5 px-5 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200">Cancel</button>
      </div>
    </form>
  </div>
</template>

<script>
import { Offer } from "@/models/offer";
import dateFormat from "dateformat";

export default {
  name: 'OffersDetail45',
  props: ['item'],
  emits: ['getOffers'],
  inject: ['offersService', 'restAdaptor', 'sessionSbService'],
  data() {
    return {
      offer: null,
      offerCopy: null,
      notFound: false,
      newBidValue: 0
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
      console.log("OFFER: ", request)

      if (request === null) {
        this.notFound = true;
        this.offer = null;
        this.offerCopy = null;

        return;
      }

      this.notFound = false;
      this.offer = request;
      this.offerCopy = Offer.copyConstructor(request);
      this.newBidValue = request.latestBid.bidValue + 1;
    },

    submitBid() {
      console.log("Submitting bid");
      const user = this.sessionSbService.getCurrentUser();

      delete user.bids;
      delete user.id;

      console.log("BID", {
        bidValue: parseInt(this.newBidValue),
        offerId: this.offerCopy.id,
        user: user
      })

      this.restAdaptor.asyncSave({
        bidValue: this.newBidValue,
        offerId: this.offerCopy.id,
        user: user
      }, `/${this.offerCopy.id}/bids`);

      alert("Bid created");
      this.getOffer();
      this.$emit("updated");
    },

    latestBid() {
      return this.offer.bids.reduce((prev, current) => (prev.value > current.value) ? prev : current);
    },

    getStatuses() {
      return Offer.getStatusses()
    },
    deleteOffer() {
      if (!confirm("Are you sure you want to delete this offer?")) {
        return;
      }

      this.offersService.asyncDeleteById(this.offerCopy.id);
      this.$router.push(this.$route.matched[0].path);

      this.$emit('getOffers');
    },
    saveChanges() {
      if (!Offer.isFilledIn(this.offerCopy)) {
        return alert("You haven't filled in all the fields");
      }

      if (!confirm("Are you sure you want to save this offer?")) {
        return;
      }

      this.offersService.asyncSave(this.offerCopy);
      this.$emit('getOffers');
    },
    revertChanges() {
      if (confirm("Are you sure you want to revert this offer?")) {
        this.offerCopy = Offer.copyConstructor(this.offer);
      }
    },
    clearChanges() {
      if (confirm("Are you sure you want to clear the form?")) {
        this.offerCopy.title = "";
        this.offerCopy.description = "";
        this.offerCopy.valueHighestBid = 0;
        this.offerCopy.sellDate = new Date();
        this.offerCopy.status = "NEW"
      }
    },
    cancelChanges() {
      if (this.hasChanged) {
        this.$router.push(this.$route.matched[0].path);
        this.$emit('canceled');
        return;
      }

      if (confirm("Are you sure you want to cancel?")) {
        this.$router.push(this.$route.matched[0].path);
        this.$emit('canceled');
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
      return this.offer
    },
    sellDateUpdater: {
      // eslint-disable-next-line vue/return-in-computed-property
      get() {
        const sellDate = dateFormat(this.offerCopy.sellDate, "yyyy-mm-dd")
        return sellDate;
      },
      // eslint-disable-next-line no-unused-vars
      set(localDateTime) {
        const newDate = new Date(localDateTime);

        // eslint-disable-next-line
        this.offerCopy.sellDate = newDate;
      },
    },
    hasChanged() {
      return Offer.equals(this.offer, this.offerCopy);
    },
    newBidIsHigher() {
      return this.newBidValue > this.latestBid().bidValue;
    }
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