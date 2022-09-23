<template>
  <div class="container mx-auto">
    <h4 class="float-left font-medium leading-tight text-2xl mt-0 mb-2">List of all offers:</h4>
  </div>
  <div class="container mx-auto">
    <div class="relative shadow-md sm:rounded-lg">
      <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
        <tr>
          <th scope="col" class="py-3 px-6">
            id #
          </th>
          <th scope="col" class="py-3 px-6">
            <div class="flex items-center">
              Title
              <a href="#">
                <svg xmlns="http://www.w3.org/2000/svg" class="ml-1 w-3 h-3" aria-hidden="true" fill="currentColor"
                     viewBox="0 0 320 512">
                  <path
                      d="M27.66 224h264.7c24.6 0 36.89-29.78 19.54-47.12l-132.3-136.8c-5.406-5.406-12.47-8.107-19.53-8.107c-7.055 0-14.09 2.701-19.45 8.107L8.119 176.9C-9.229 194.2 3.055 224 27.66 224zM292.3 288H27.66c-24.6 0-36.89 29.77-19.54 47.12l132.5 136.8C145.9 477.3 152.1 480 160 480c7.053 0 14.12-2.703 19.53-8.109l132.3-136.8C329.2 317.8 316.9 288 292.3 288z">
                  </path>
                </svg>
              </a>
            </div>
          </th>
          <th scope="col" class="py-3 px-6">
            <div class="flex items-center">
              Selling Date
              <a href="#">
                <svg xmlns="http://www.w3.org/2000/svg" class="ml-1 w-3 h-3" aria-hidden="true" fill="currentColor"
                     viewBox="0 0 320 512">
                  <path
                      d="M27.66 224h264.7c24.6 0 36.89-29.78 19.54-47.12l-132.3-136.8c-5.406-5.406-12.47-8.107-19.53-8.107c-7.055 0-14.09 2.701-19.45 8.107L8.119 176.9C-9.229 194.2 3.055 224 27.66 224zM292.3 288H27.66c-24.6 0-36.89 29.77-19.54 47.12l132.5 136.8C145.9 477.3 152.1 480 160 480c7.053 0 14.12-2.703 19.53-8.109l132.3-136.8C329.2 317.8 316.9 288 292.3 288z">
                  </path>
                </svg>
              </a>
            </div>
          </th>
          <th scope="col" class="py-3 px-6">
            <div class="flex items-center">
              Status
              <a href="#">
                <svg xmlns="http://www.w3.org/2000/svg" class="ml-1 w-3 h-3" aria-hidden="true" fill="currentColor"
                     viewBox="0 0 320 512">
                  <path
                      d="M27.66 224h264.7c24.6 0 36.89-29.78 19.54-47.12l-132.3-136.8c-5.406-5.406-12.47-8.107-19.53-8.107c-7.055 0-14.09 2.701-19.45 8.107L8.119 176.9C-9.229 194.2 3.055 224 27.66 224zM292.3 288H27.66c-24.6 0-36.89 29.77-19.54 47.12l132.5 136.8C145.9 477.3 152.1 480 160 480c7.053 0 14.12-2.703 19.53-8.109l132.3-136.8C329.2 317.8 316.9 288 292.3 288z">
                  </path>
                </svg>
              </a>
            </div>
          </th>
          <th scope="col" class="py-3 px-6">
            <div class="flex items-center">
              Value highest bid
              <a href="#">
                <svg xmlns="http://www.w3.org/2000/svg" class="ml-1 w-3 h-3" aria-hidden="true" fill="currentColor"
                     viewBox="0 0 320 512">
                  <path
                      d="M27.66 224h264.7c24.6 0 36.89-29.78 19.54-47.12l-132.3-136.8c-5.406-5.406-12.47-8.107-19.53-8.107c-7.055 0-14.09 2.701-19.45 8.107L8.119 176.9C-9.229 194.2 3.055 224 27.66 224zM292.3 288H27.66c-24.6 0-36.89 29.77-19.54 47.12l132.5 136.8C145.9 477.3 152.1 480 160 480c7.053 0 14.12-2.703 19.53-8.109l132.3-136.8C329.2 317.8 316.9 288 292.3 288z">
                  </path>
                </svg>
              </a>
            </div>
          </th>
          <th scope="col" class="py-3 px-6">
            <span class="sr-only">Edit</span>
          </th>
        </tr>
        </thead>
        <tbody>

        <tr v-for="(item) in offers" :key="item.id">
          {{ item.message }}
          <th scope="row" class="py-4 px-6 font-medium text-gray-900 whitespace-nowrap dark:text-white">
            {{ item.id }}
          </th>
          <td class="py-4 px-6">
            {{ item.title }}
          </td>
          <td class="py-3 px-6">
            {{ item.sellDate }}
          </td>
          <td class="py-3 px-6">
            {{ item.status }}
          </td>
          <td class="py-3 px-6">
            {{ item.valueHighestBid }}
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div id="newOfferBtn">
      <button v-on:click="onNewOffer" type="button"
              class="float-right text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center mt-3">
        <i class="fa fa-plus"></i> New Offer
      </button>
    </div>
  </div>
</template>

<script>
import { Offer } from "@/models/offer.js";

export default {
  name: "OffersOverview31",

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
    }
  },

  methods: {
    onNewOffer: function () {
      this.offers.push(Offer.createSampleOffer(this.nextId));
      this.nextId = this.nextId + 3;
    }
  },
}
</script>

<style>

</style>