<template>
  <AuctionNavbar />
  <Overview32/>
  <Footer />
</template>

<script>
// import AuctionHeader from './components/Header.vue';
// import AuctionWelcome from './components/Welcome.vue';
import AuctionNavbar from './components/Navbar.vue';
import Footer from './components/Footer.vue';
import Overview32 from "./components/offers/32/Overview32.vue";

export default {
  name: 'App31',
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
