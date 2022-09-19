<template>
  <!-- <AuctionHeader /> -->
  <AuctionNavbar />
  <!-- <AuctionWelcome /> -->
  <Overview31/>
  <Footer />
</template>

<script>
// import AuctionHeader from './components/Header.vue';
// import AuctionWelcome from './components/Welcome.vue';
import AuctionNavbar from './components/Navbar.vue';
import Footer from './components/Footer.vue';
import Overview31 from "./components/offers/Overview31.vue";

export default {
  name: 'App',
  components: {
    // AuctionHeader,
    // AuctionWelcome,
    AuctionNavbar,
    Footer,
    Overview31,
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
