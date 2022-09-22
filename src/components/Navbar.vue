<template>
  <nav class="bg-white border-gray-200 dark:bg-gray-900">
    <div class="flex flex-wrap justify-between items-center mx-auto max-w-screen-xl px-4 md:px-6 py-2.5">
      <div class="w-1/3 align-middle">
        <a href="#" class="flex items-center">
          <img src="../assets/welcome/image2vector.svg" class="mr-3 h-6 sm:h-9" alt="the Auctioneer Logo">
          <span class="self-center text-xl font-semibold whitespace-nowrap dark:text-white">The Auctioneer</span>
        </a>
      </div>
      <div class="w-1/3 align-middle justify-start">
        <p>Hello</p>
      </div>
      <div class="w-1/3 align-middle items-end`">
        <a href="#" class="mr-6 text-sm font-medium text-gray-500 dark:text-white hover:underline">Sign in</a>
        <a href="#"
          class="text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center ">Login</a>
      </div>
    </div>
  </nav>
  <nav class="bg-gray-50 dark:bg-gray-700 mb-6">
    <div class="py-3 px-4 mx-auto max-w-screen-xl md:px-6">
      <div class="flex items-center justify-between">
        <ul class="flex flex-row mt-0 mr-6 space-x-8 text-sm font-medium">
          <li>
            <a href="#" class="text-gray-900 dark:text-white hover:underline" aria-current="page">Home</a>
          </li>
          <li>
            <a href="#" class="text-gray-900 dark:text-white hover:underline">My offers</a>
          </li>
          <li>
            <button id="dropdownNavbarLink" data-dropdown-toggle="dropdownNavbar"
              class="flex justify-between items-center py-2 pr-4 pl-3 w-full font-medium text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 md:w-auto dark:text-gray-400 dark:hover:text-white dark:focus:text-white dark:border-gray-700 dark:hover:bg-gray-700 md:dark:hover:bg-transparent">My
              bids
              <svg class="ml-1 w-5 h-5" aria-hidden="true" fill="currentColor" viewBox="0 0 20 20"
                xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd"
                  d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
                  clip-rule="evenodd"></path>
              </svg></button>
            <!-- Dropdown menu -->
            <div id="dropdownNavbar"
              class="hidden z-10 w-44 font-normal bg-white rounded divide-y divide-gray-100 shadow dark:bg-gray-700 dark:divide-gray-600">
              <ul class="py-1 text-sm text-gray-700 dark:text-gray-400" aria-labelledby="dropdownLargeButton">
                <li>
                  <a href="#"
                    class="block py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Active</a>
                </li>
                <li>
                  <a href="#"
                    class="block py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">History</a>
                </li>
              </ul>
            </div>
          </li>
          <li>
            <a href="#" class="text-gray-900 dark:text-white hover:underline">My account</a>
          </li>
        </ul>
        <ul>
          <div class="mr-2">
            <p>Today is {{ getDate() }}</p>
          </div>
        </ul>
      </div>
    </div>
  </nav>

</template>

<script>
import dateFormat from "dateformat";
const now = new Date();

export default {
  name: 'AuctionNavbar',
  data() {
    return {
      currentDate: dateFormat(now, "dddd mmmm dS, yyyy")
    }
  },
  methods: {
    getDate () {
      return this.currentDate
    }
  }
}
</script>

<style>

</style>