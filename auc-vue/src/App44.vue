<template>
  <NavbarSb />
  <router-view/>
</template>

<script>
import Footer from './components/Footer.vue';
import { OffersAdaptor } from "@/services/offers-adaptor";
import { reactive, shallowReactive } from "vue";
import { CachedOffersAdaptor } from "@/services/cached-offers-adaptor";
import { Offer } from "@/models/offer";
import { SessionSbService } from "@/services/session-sb-service";
import NavbarSb from './components/NavbarSb.vue';
import { FetchInterceptor } from './services/fetch-interceptor';

export default {
  name: 'App',
  components: {
    NavbarSb
  },
  provide() {

    this.theSessionService = shallowReactive(
      new SessionSbService("http://localhost:8083/authentication", "IDK"));

    this.theFetchInterceptor = 
      new FetchInterceptor(this.theSessionService, this.$router);

    return {
      offersService: new OffersAdaptor("http://localhost:8083/offers"),
      usersService: new OffersAdaptor("http://localhost:8083/users"),
      
      cachedOffersService: reactive(new CachedOffersAdaptor("http://localhost:8083/offers", Offer.copyConstructor())),
      sessionSbService: shallowReactive(new SessionSbService("http://localhost:8083/authentication", "token"))
    }
  },
  unmounted() {
    console.log("App.unmounted() has been called.");
    this.theFetchInterceptor.unregister();
  }
}
</script>

<style>
body {
  padding: 0;
  overflow-x: hidden;
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
