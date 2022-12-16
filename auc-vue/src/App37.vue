<template>
  <AuctionNavbar />
  <router-view/>
<!--  <Footer />-->
</template>

<script>
import AuctionNavbar from './components/Navbar.vue';
import Footer from './components/Footer.vue';
import { OffersAdaptor } from "@/services/offers-adaptor";
import {reactive} from "vue";
import {CachedOffersAdaptor} from "@/services/cached-offers-adaptor";
import {Offer} from "@/models/offer";

export default {
  name: 'App',
  components: {
    AuctionNavbar,
    // Footer,
  },
  provide() {
    return {
      offersService: new OffersAdaptor("http://localhost:8083/offers"),
      cachedOffersService: reactive(new CachedOffersAdaptor("http://localhost:8083/offers", Offer.copyConstructor()))
    }
  }
}
</script>

<style>
  body {
    padding: 0;
    overflow-x: hidden;  
    min-height: 130vh;
    position: relative;
  }

  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    /* margin-top: 60px; */
  }
</style>
