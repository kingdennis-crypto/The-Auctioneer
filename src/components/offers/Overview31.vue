<template>
  <div class="container mx-auto">
    <div class="relative shadow-md sm:rounded-lg">
      <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
          <tr>
            <th scope="col" class="py-3 px-6">
              #
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
          <!-- <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
          <th scope="row" class="py-4 px-6 font-medium text-gray-900 whitespace-nowrap dark:text-white">
            Apple MacBook Pro 17"
          </th>
          <td class="py-4 px-6">
            Sliver
          </td>
          <td class="py-4 px-6">
            Laptop
          </td>
          <td class="py-4 px-6">
            $2999
          </td>
          <td class="py-4 px-6 text-right">
            <a href="#" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Edit</a>
          </td>
        </tr> -->

          <tr v-for="(item, index) in offers" :key="item.id">
            {{ item.message }}
            <th scope="row" class="py-4 px-6 font-medium text-gray-900 whitespace-nowrap dark:text-white">
              {{ index + 1 }}
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

  methods: {},
}
</script>

<style>

</style>