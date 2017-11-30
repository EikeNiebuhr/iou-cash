<template>
<div id='navbar'>
  <nav class="level">
    <div class="level-left">
    <a id="logo" class="navbar-item" href="">
      <img src="../assets/logo.png" alt="IOU-Cash: a quick money transfer app">
    </a>
  </div>
  <div class="level-item">
        <h1 id="main-title" class="has-text-weight-semibold">IOU CASH</h1>
      </div>
    <div class="navbar-end">
          <div class="level-item has-text-centered">
      <div class="field has-addons">
  <div class="control">
    <input class="input" type="text" v-model="$root.searchWordGlobal" placeholder="Search friends">
  </div>
  </div>
  </div>
    <div class="level-item has-text-centered">
        <a class="link is-info" href="#">
           <span class="icon"><i class="fa fa-user"></i></span>
        </a>
        <a class="link is-info" href="#">
           <span class="icon"><i class="fa fa-sign-out"></i></span>
        </a>
      </div>
</div>
  </nav>
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
  font-weight: bolder;
}
#main-title {
  font-size: 40px;
}
#navbar {
  background-color: #F5F5F5;
}
</style>
