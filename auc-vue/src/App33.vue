<template>
  <AuctionNavbar />
  <router-view/>
  <Footer />
</template>

<script>
import AuctionNavbar from './components/Navbar.vue';
import Footer from './components/Footer.vue';

export default {
  name: 'App',
  components: {
    AuctionNavbar,
    Footer,
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
