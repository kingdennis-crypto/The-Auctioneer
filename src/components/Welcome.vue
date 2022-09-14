<!-- TODO: Add tabs for multi view pages -->

<template>
  <div class="container mx-auto">
    <div class="flex gap-4 flex-row">
      <div class="w-1/5 text-start">
        <div class="mb-4">
          <p class="text-lg font-medium">Pick a date</p>
          <input class="w-full" type="date" name="" id="">
        </div>
        <div>
          <p class="text-lg font-medium">Pick a category</p>
          <ul>
            <li><a href="#" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Antiques</a></li>
            <li><a href="#" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Cars</a></li>
            <li><a href="#" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Event Tickets</a></li>
            <li><a href="#" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Holidays</a></li>
            <li><a href="#" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Collectables</a></li>
          </ul>
        </div>
      </div>
      <div class="w-4/5">
        <div class="w-4/5 mx-auto">
          <div id="default-carousel" class="relative" data-carousel="static">
            <!-- Carousel wrapper -->
            <div class="relative h-56 overflow-hidden rounded-lg md:h-96">
              <!-- Item 1 -->
              <div class="duration-700 ease-in-out absolute inset-0 transition-all transform translate-x-0 z-20"
                data-carousel-item="">
                <span
                  class="absolute text-2xl font-semibold text-white -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2 sm:text-3xl dark:text-gray-800">First
                  Slide</span>
                <img src="../assets/welcome/rallyCar1.jpg"
                  class="absolute block w-full -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2" alt="...">
              </div>
              <!-- Item 2 -->
              <div class="duration-700 ease-in-out absolute inset-0 transition-all transform translate-x-full z-10"
                data-carousel-item="">
                <img src="../assets/welcome/rallyCar2.jpg"
                  class="absolute block w-full -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2" alt="...">
              </div>
              <!-- Item 3 -->
              <div class="duration-700 ease-in-out absolute inset-0 transition-all transform -translate-x-full z-10"
                data-carousel-item="">
                <img src="../assets/welcome/rallyCar3.jpg"
                  class="h-full absolute block w-full -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2" alt="...">
              </div>
            </div>
            <!-- Slider indicators -->
            <div class="absolute z-30 flex space-x-3 -translate-x-1/2 bottom-5 left-1/2">
              <button type="button" class="w-3 h-3 rounded-full bg-white dark:bg-gray-800" aria-current="true"
                aria-label="Slide 1" data-carousel-slide-to="0"></button>
              <button type="button"
                class="w-3 h-3 rounded-full bg-white/50 dark:bg-gray-800/50 hover:bg-white dark:hover:bg-gray-800"
                aria-current="false" aria-label="Slide 2" data-carousel-slide-to="1"></button>
              <button type="button"
                class="w-3 h-3 rounded-full bg-white/50 dark:bg-gray-800/50 hover:bg-white dark:hover:bg-gray-800"
                aria-current="false" aria-label="Slide 3" data-carousel-slide-to="2"></button>
            </div>
            <!-- Slider controls -->
            <button type="button"
              class="absolute top-0 left-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none"
              data-carousel-prev="">
              <span
                class="inline-flex items-center justify-center w-8 h-8 rounded-full sm:w-10 sm:h-10 bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
                <svg aria-hidden="true" class="w-5 h-5 text-white sm:w-6 sm:h-6 dark:text-gray-800" fill="none"
                  stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"></path>
                </svg>
                <span class="sr-only">Previous</span>
              </span>
            </button>
            <button type="button"
              class="absolute top-0 right-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none"
              data-carousel-next="">
              <span
                class="inline-flex items-center justify-center w-8 h-8 rounded-full sm:w-10 sm:h-10 bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
                <svg aria-hidden="true" class="w-5 h-5 text-white sm:w-6 sm:h-6 dark:text-gray-800" fill="none"
                  stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
                </svg>
                <span class="sr-only">Next</span>
              </span>
            </button>
          </div>
          <hr class="my-4 h-px bg-gray-200 border-0 dark:bg-gray-700" />
          <div class="text-start">
            <p class="text-lg font-bold">Original Race Truck Paris-Dakar</p>
            <p>A unique object for sale! The truck of team 'Gerard De Rooy' that has competed in the Paris-Dakar race of 2017 and finished at 3rd position. The truck will be delivered inclusive of some €10,000 worth of spare parts.</p>
            <br />
            <p><strong>Engine:</strong> IVECO Cusor, 13 liter</p>
            <p><strong>Power:</strong> 900 HP, 4x4 transmission</p>
            <br />
            <p><strong>Higest bid: €87.000</strong></p>
            <p><strong>Auction closes at 18:00 GTMT today!</strong></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AuctionWelcome',
}
</script>

<style>
.auctionImage {
  height: 200px;
  object-fit: cover;
  margin: 10px;
}

.container {
  height: 100px !important;
}
</style>
