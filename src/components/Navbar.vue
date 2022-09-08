<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light mb-5">
    <div class="container-fluid">
      <!-- <a class="navbar-brand" href="#">Navbar</a> -->
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
        aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav text-start">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">My Offers</a>
          </li>
          
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
              data-bs-toggle="dropdown" aria-expanded="false">
              My bids
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <li><a class="dropdown-item" href="#">Active</a></li>
              <li><a class="dropdown-item" href="#">History</a></li>
            </ul>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">My account</a>
          </li>
        </ul>
        <div class="d-flex" style="margin-left: auto;">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="#">Log in</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Sign in</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'AuctionNavbar',
}
</script>

<style>

</style>