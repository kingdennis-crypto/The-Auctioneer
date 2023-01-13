/* vue/no-mutating-props */
<template>
  <div class="container mx-auto">
    <div class="flex flex-row gap-5">
      <div class="w-1/3">
        <table class="w-full mb-3 text-sm text-left text-gray-500">
          <thead class="text-xs text-gray-700 uppercase bg-gray-100">
            <tr>
              <th class="py-5 px-6">Offer title</th>
              <th class="py-5 px-6">Highest Bid</th>
              <th class="py-5 px-6">Made by:</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item) in offers" :key="item.id">
              <td
                @click="selectItem(item)"
                class="py-4 px-6 font-medium whitespace-nowrap dark:text-white cursor-pointer rounded-sm [&:not(.bg-blue-500)]:hover:bg-gray-100"
                :class="[ selectedOfferId === item.id ? selectedRowStyle : notSelectedRowStyle ]">
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
      <div class="w-2/3">
        <div class="relative h-full">
          <router-view v-if="offerIsSelected" v-bind:item="selectedOffer" @getOffers="getAllOffers"/>
          <div v-else class="h-full w-full absolute flex rounded-md">
            <p class="text-black text-2xl m-auto">No offer was selected</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Offer } from "@/models/offer";

export default {
  name: "OffersOverview45",
  inject: ["offersService"],

  created() {
    this.getAllOffers();
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

  computed: {
    offerIsSelected() {
      return this.$route.params.id !== null;
    },
    selectedOfferId() {
      return parseInt(this.$route.params.id);
    }
  },

  methods: {
    async getAllOffers() {
      this.offers = await this.offersService.asyncFindAllSummary();
      this.nextId = this.offers[this.offers.length - 1].id;
    },

    onNewOffer: function () {
      this.nextId += 3;

      const newOffer = Offer.createSampleOffer(this.nextId);
      this.selectedOffer = newOffer

      this.offersService.asyncSave(newOffer);

      this.offers.push(newOffer);
      this.selectItem(newOffer)
    },

    selectItem(element) {
      if (element === this.selectedOffer) {
        return null;
      }

      this.selectedOffer = element;

      // Checks if the selected offer is null
      if (this.selectedOffer !== null) {
        // Set the id of the offer in the route param
        this.$router.push(this.$route.matched[0].path + "/" + element.id);
      }
    },

    // deleteOffer() {
    //   if (confirm("Are you sure you want to delete this offer?")) {
    //     this.offersService.asyncDeleteById(this.selectedOffer.id);
    //     this.offers = this.offers.filter(element => element.id !== this.selectedOffer.id)
    //
    //     this.selectedOffer = null;
    //     this.$router.push(this.$route.matched[0].path);
    //   }
    // },
    //
    // saveOffer(item) {
    //   let offerIndex = this.findSelectedOfferIndex(item.id);
    //
    //   if (Offer.isFilledIn(item)) {
    //     this.offers.splice(offerIndex, 1, item);
    //     const savedOffer = this.offersService.asyncSave(item);
    //
    //     this.offers.map((element) => element.id === savedOffer.id ? savedOffer : element)
    //
    //     this.$router.push(this.$route.matched[0].path);
    //   } else {
    //     alert("You haven't filled in all fields!")
    //   }
    // },

    findSelectedRouteFromRouteParam(id) {
      return this.offers.find(element => element.id === parseInt(id));
    },
    findSelectedOfferIndex(id) {
      return this.offers.findIndex(element => element.id === id)
    }
  },
}
</script>

<style scoped>

</style>