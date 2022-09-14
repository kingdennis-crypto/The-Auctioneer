<template>
  <nav class="navbar navbar-expand-lg bg-light py-0 px-5">
    <div class="container-fluid">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav text-start">
          <li class="nav-item">
            <a href="#" aria-current="page" class="nav-link active">The auctioneer</a>
          </li>
        </ul>
        <div class="d-flex" style="margin-left: auto;">
          <ul class="navbar-nav" v-if="loggedIn">
            <li class="nav-item">
              <a class="nav-link" href="#">My account</a>
            </li>
          </ul>
          <ul class="navbar-nav" v-if="!loggedIn">
            <li class="nav-item">
              <a class="nav-link" href="#">Log in</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Sign up</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'AuctionHeader',
  data() {
    return {
      loggedIn: true
    }
  }
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
