<template>
  <div class="container mx-auto flex-col">
    <div>

<!--      <div class="container mx-auto">-->
<!--        <h4 class="float-left font-medium leading-tight text-2xl mt-0 mb-2">Overview of all offered articles</h4>-->
<!--      </div>-->
      <div class="columns-2 h-full gap-2">
        <div class="w-full">
          <table class="text-sm text-left text-gray-500 dark:text-gray-400">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
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
            </tr>
            </thead>
            <tbody>
            <tr v-for="(item) in offers" :key="item.id">
               {{ item.message }}
              <td v-on:click="selectItem(item)" class="py-4 px-6 hover:bg-gray-100 hover:text-black" :class="{'bg-blue-700 text-white':selectedOrder?.id===item.id}">
                {{ item.title }}
              </td>
            </tr>
            </tbody>
          </table>
        </div>
        <div class="w-full">
          <Detail32 v-if="selectedOrder" v-bind:item="selectedOrder"/>
          <div v-else>
            <p>Select an offer at the left</p>
          </div>
        </div>
      </div>

    </div>
  </div>

</template>

<script>
import { Offer } from "@/models/offer";
import Detail32 from "./Detail32.vue";

export default {
  name: "OffersOverview32",
  components: {
    Detail32
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
      selectedOrder: null
    }
  },

  methods: {
    onNewOffer: function () {
      this.offers.push(Offer.createSampleOffer(this.nextId));
      this.nextId = this.nextId + 3;
    },
    selectItem(element) {
      console.log(element);
      if (element === this.selectedOrder) {
        return this.selectedOrder = null;
      }
      this.selectedOrder = element;
    },
  },
}
</script>

<style scoped>

</style>