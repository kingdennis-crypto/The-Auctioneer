<template>
  <div class="row">
    <div class="col-lg-3 col-sm-12 text-start">
      <div>
        <p>Pick a date:</p>
        <input type="date" />
      </div>
      <br />
      <div>
        <div>Pick a category:</div>
        <ul>
          <li><a href="#">Antiques</a></li>
          <li><a href="#">Cars</a></li>
          <li><a href="#">Event Tickets</a></li>
          <li><a href="#">Holidays</a></li>
          <li><a href="#">Collectables</a></li>
        </ul>
      </div>
    </div>
    <div class="col-lg-6 col-sm-12 text-start">
      <h1>Auction expire today:</h1>
      <div>
        <img class="auctionImage" alt="Rally Car 1" src="../assets/welcome/rallyCar1.jpg" />
        <img class="auctionImage" alt="Rally Car 1" src="../assets/welcome/rallyCar2.jpg" />
        <img class="auctionImage" alt="Rally Car 1" src="../assets/welcome/rallyCar4.jpg" />
      </div>
    </div>
    <div class="col-lg-3 col-sm-12 text-start">
      <h1>Original Race Truck Paris-Dakar</h1>
      <p>A unique object for sale! The truck of team 'Gerard De Rooy' that has competed in the Paris-Dakar race of 2017 and finished at 3rd position. The truck will be delivered inclusive of some €10,000 worth of spare parts.</p>
      <br />
      <p><strong>Engine:</strong> IVECO Cusor, 13 liter</p>
      <p><strong>Power:</strong> 900 HP, 4x4 transmission</p>
      <br />
      <p><strong>Higest bid: €87.000</strong></p>
      <p><strong>Auction closes at 18:00 GTMT today!</strong></p>
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
</style>
