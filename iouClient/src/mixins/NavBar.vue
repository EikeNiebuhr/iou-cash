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
    <input class="input" type="text" v-model="$root.searchWordGlobal" placeholder="Search friends">
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
import search from '../mixins/search'
export default {
  data () {
    return {
      name: 'navbar'
    }
  },
  methods: {
  },
  mixins: [
    search
  ]
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
