<template>
<div id='navbar'>
<section class="section">
  <div class="container">
    <nav class="navbar columns">
      <p class="column has-text-centered">
        <h1 class="title">IOU CASH</h1>
      </p>
            <p class="column has-text-centered">
      <div class="field has-addons">
  <div class="control">
    <input class="input" type="text" v-model="productSearch" placeholder="Search friends" v-on:keyup="searchProducts">
  </div>
  <div class="control">
    <a class="button is-info">Search</a>
  </div>
</div> 
</p>
      <p class="column has-text-centered">
        <a class="link is-info" href="#">
           <span class="icon"><i class="fa fa-user"></i></span>
        </a>
        <a class="link is-info" href="#">
           <span class="icon"><i class="fa fa-sign-out"></i></span>
        </a>
      </p>
    </nav>
  </div>
</section>
</div>
</template>
<script>
export default {
  data () {
    return {
      name: 'navbar'
    }
  },
  methods: {
    searchProducts: function () {
      if (this.productSearch === '') {
        this.friends = this.originalFriends
        return
      }

      var searchedProducts = []
      for (var i = 0; i < this.originalFriends.length; i++) {
        var productName = this.originalFriends[i]['name'].toLowerCase()
        if (productName.indexOf(this.productSearch.toLowerCase()) >= 0) {
          searchedProducts.push(this.originalFriends[i])
        }
      }

      this.friends = searchedProducts
    }
  }
}
</script>
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
.container {
  background-color: #42b983;
}
</style>
