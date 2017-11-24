<template>
<div id="Overview">
    <section class="hero">
        <h1 class="title">Overview</h1>
  <p class="notification">Current status:
<notification v-bind:notifications="notifications"></notification>                    
  </p>
    </section>
    <section class="section">
<div class="tile is-ancestor">
  <div class="tile is-parent">
    <article class="tile is-child box" v-for="friend in friends">
      <p class="title">{{ friend.name }}</p>
      <p class="asd">{{ friend.totalpositive }}</p>
      <p class="negative">{{ friend.totalnegativ }}</p>
    </article>
  </div>
  <div class="tile is-parent">
    <article class="tile is-child box">
      <p class="title">Two</p>
      <div class="has-text-centered">
      <p class="positive">1000</p>
      <p class="negative">100</p>
      </div>
    </article>
  </div>
  <div class="tile is-parent">
    <article class="tile is-child box">
      <p class="title">Three</p>
      <p class="subtitle">Subtitle</p>
    </article>
  </div>
  <div class="tile is-parent">
    <article class="tile is-child box">
      <p class="title">Four</p>
      <p class="subtitle">Subtitle</p>
    </article>
  </div>
      </div>
</section>
<button id="add-friend" v-on:click="show" class="button is-info" aria-hidden="true"><i class="fa fa-user-plus" aria-hidden="true"> Friend</i></button>
      </container>
<createFriend></createFriend>
</section>
</div>
</template>

<script>
import Notification from './Notifications'
import CreateFriend from './CreateFriend'
import axios from 'axios'
export default {
  data () {
    return {
      friends: [],
      productSearch: '',
      notifications: []
    }
  },
  created: function () {
    this.fetchProductData()
  },
  methods: {
    fetchProductData: function () {
      axios.get('localhst').then(
        response => {
          this.friends = response.body
          this.originalFriends = this.friends
        }).catch(e => {
          this.notifications.push(e)
        })
    },
    addFriend: function () {
      axios.post('/friend', {
        name: 'Fred'
      })
  .then(function (response) {
    this.notifications.push(response)
    console.log(response)
  })
  .catch(function (error) {
    this.notifications.push(error)
    console.log(error)
  })
    },
    show: function () {
      this.$modal.show('createFriend')
    }
  },
  components: {
    'notification': Notification,
    'createFriend': CreateFriend
  }
}
</script>
<style scoped>
.negative {
 background-color: #FF3860
}

.positive {
  background-color: #42b983
}
</style>
