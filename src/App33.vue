<template>
  <AuctionNavbar />
  <router-view/>
  <Footer />
</template>

<script>
  /* eslint-disable vue/no-unused-components */
import AuctionNavbar from './components/Navbar.vue';
import Footer from './components/Footer.vue';
import Overview32 from "./components/offers/Overview32.vue";

export default {
  name: 'App',
  components: {
    AuctionNavbar,
    Footer,
    Overview32
  }
}
</script>

<style>
  body {
    padding: 0px;
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
