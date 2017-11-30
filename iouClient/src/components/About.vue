<template>
<div id="about">
  <section class="hero">
    <h1 class="title">About</h1>
      <notification v-bind:notifications="notifications"></notification>
  </section>
  <section class="section">
    <p>A lot of great things about this app</p>
  </section>
</div>
</template>
<script>
import Notification from './Notifications'
export default {
  data () {
    return {
      name: 'about',
      notifications: []
    }
  },
  components: {
    notification: Notification
  }
}
</script>
