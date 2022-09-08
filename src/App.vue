<template>
  <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
  <div class="container">
    <AuctionHeader />
    <AuctionNavbar />
    <AuctionWelcome />
  </div>
</template>

<script>
import AuctionHeader from './components/Header.vue';
import AuctionNavbar from './components/Navbar.vue';
import AuctionWelcome from './components/Welcome.vue';

export default {
  name: 'App',
  components: {
    AuctionHeader,
    AuctionNavbar,
    AuctionWelcome,
}
}
</script>

<style>
  body {
    padding: 0px;
    overflow-x: hidden;  
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
