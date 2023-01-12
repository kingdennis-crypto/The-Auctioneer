<template>
  <div>
    <p>Hallo</p>
  </div>
</template>

<script>
export default {
  name: 'RequestError',
  props: {
    responseCode: Number,
    requestURL: String,
    errorMessage: String
  },
  created() {
    
  }
}
</script>