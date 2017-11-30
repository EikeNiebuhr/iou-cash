<template>
<div id="sidebar">
<aside class="nav-menu">
  <div class="has-text-centered">
 <p class="menu-label is-hidden-touch">send money to friends</p></div>
  <ul class="menu-list">
      <li>
<router-link to="/overview"> 
        <span class="icon"><i class="fa fa-home"></i></span> Overview
        </router-link>
        </li>
    <li>
<router-link to="/transactions">        
        <span class="icon"><i class="fa fa-table"></i></span> Transactions</router-link>
        </li>
<router-link to="/about">        
        <span class="icon"><i class="fa fa-info"></i></span> About</router-link>
        </li>        
  </ul>
</aside>
</div>
</template>
<script>
export default {
  data () {
    return {
      name: 'sidebar'
    }
  }
}
</script>
