<template>
<div id="app">
  <div class="container">
    <navbar></navbar>
    <div class="columns">
      <div class="column is-2">
        <sidebar></sidebar>
      </div>
      <div class="column">
        <router-view></router-view>
      </div>
    </div>
    <footerbar></footerbar>
  </div>
</div>
</template>
<script>
import navbar from './components/NavBar'
import sidebar from './components/SideBar'
import about from './components/About'
import footerbar from './components/FooterBar'

export default {
  components: {
    navbar,
    sidebar,
    about,
    footerbar
  },
  name: 'app'
}
</script>
<style lang="sass">
  @import '~bulma'
</style>
