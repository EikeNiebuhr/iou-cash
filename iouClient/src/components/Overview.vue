<template>
<div id="Overview">
  <section class="hero">
    <h1 class="title">Overview</h1>
      <notification v-bind:notifications="notifications"></notification>
  </section>
  <section class="section">
    <div class="tile is-ancestor">
      <div class="tile is-parent" v-for="friend in filteredSearch" :key="friend.id">
          <article class="tile is-child box">
            <p class="title">{{ friend.name }}</p>
            <p class="positive">{{ friend.totalpositive }} €</p>
            <p class="negative">{{ friend.totalnegative }} €</p>
          </article>
      </div>
    </div>
  </section>
  <button id="add-friend" @click="show" class="button is-info" aria-hidden="true"><i class="fa fa-user-plus" aria-hidden="true"> Friend</i></button>
  <createFriend></createFriend>
</div>
</template>

<script>
import Notification from './Notifications'
import CreateFriend from './CreateFriend'
import axios from 'axios'
import search from '../mixins/search'
export default {
  data () {
    return {
      friend: {},
      notifications: []
    }
  },
  created: function () {
    this.fetchFriendsData()
  },
  methods: {
    fetchFriendsData: function () {
      axios.get('http://localhost:3000/friends').then(
        response => {
          this.$root.friendsGlobal = response.data
          this.notifications.push({
            type: 'success',
            message: 'All good! Last succesful update at ' + new Date().toLocaleTimeString()
          })
          console.log(response)
        }).catch(e => {
          this.notifications.push(e)
          console.log(e)
        })
    },
    show: function () {
      this.$modal.show('createFriend')
    }
  },
  components: {
    notification: Notification,
    createFriend: CreateFriend
  },
  mixins: [
    search
  ]
}
</script>
<style scoped>
.negative {
 background-color: #FF3860;
 text-align: center;
}

.positive {
  background-color: #42b983;
  text-align: center;
}
</style>
