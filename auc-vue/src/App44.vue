<template>
  <NavbarSb />
  <router-view/>
  <!--  <Footer />-->
</template>

<script>
// import AuctionNavbar from './components/Navbar.vue';
import NavbarSb from "@/components/NavbarSb";
import Footer from './components/Footer.vue';
import { OffersAdaptor } from "@/services/offers-adaptor";
import {reactive, shallowReactive} from "vue";
import {CachedOffersAdaptor} from "@/services/cached-offers-adaptor";
import {Offer} from "@/models/offer";
import {SessionSbService} from "@/services/session-sb-service";

export default {
  name: 'App',
  components: {
    NavbarSb,
    // AuctionNavbar,
    // Footer,
  },
  provide() {

    return {
      offersService: new OffersAdaptor("http://localhost:8083/offers"),
      cachedOffersService: reactive(new CachedOffersAdaptor("http://localhost:8083/offers", Offer.copyConstructor())),
      sessionSbService: shallowReactive(new SessionSbService("http://localhost:8083/authentication", "token"))
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
