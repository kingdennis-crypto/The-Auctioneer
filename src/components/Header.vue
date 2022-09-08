<template>
  <div class="row">
    <div class="col-lg-3 col-sm-12">
      <img class="headerImage" alt="Left image" src="../assets/header/leftHeaderImg.jpg" />
    </div>
    <div class="col-lg-6 col-sm-12 alignCenter">
      <div class="headerText">The auctioneer</div>
      <div class="headerSubtext">Offered to you by hva.nl</div>
    </div>
    <div class="col-lg-3 col-sm-12">
      <img class="headerImage" alt="Right image" src="../assets/header/rightHeaderImg.jpg" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'AuctionHeader',
}
</script>

<style>
  .headerImage {
    width: 20vw;
    object-fit: contain;
  }

  .headerText {
    margin: auto;
    font-size: 48px;
    font-weight: 500;
  }

  .headerSubtext {
    margin-left: auto;
    margin-right: 20px;
  }

  .alignCenter {
    display: inline-flex;
    align-items: center;
    flex-direction: column;
  }
</style>
