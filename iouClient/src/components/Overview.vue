<template>
<div id="Overview">
  <section class="hero">
    <h1 class="title">Overview</h1>
      <notification v-bind:notifications="notifications"></notification>
  </section>
  <section class="section">
    <div class="tile is-ancestor">
      <div class="tile is-parent is-vertical" v-for="sortedFriends in chunkedFriends" :key="sortedFriends.id">
            <div class="content">
          <article class="tile is-child box" v-for="friend in sortedFriends" :key="friend.id">
            <button class="delete is-pulled-right" aria-label="close" @click.prevent="deleteFriend(friend.id)"></button>
            <p class="title">{{ friend.name }}</p>
            <p class="positive">{{ friend.totalpositive }} €</p>
            <p class="negative">{{ friend.totalnegative }} €</p>
          </article>
            </div>
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
import chunk from 'chunk'
import finder from 'array-find-index'

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
  computed: {
    chunkedFriends () {
      return chunk(this.filteredSearch, 4)
    }
  },
  methods: {
    fetchFriendsData: function () {
      axios.get('http://localhost:3000/friends/').then(
        response => {
          this.$root.friendsGlobal = response.data
          this.notifications.push({
            type: 'success',
            message: 'All good! Last succesful update at ' + new Date().toLocaleString()
          })
          console.log(response)
        }).catch(e => {
          this.notifications.push(e)
          console.log(e)
        })
    },
    show: function () {
      this.$modal.show('createFriend')
    },
    deleteFriend: function (id) {
      var index = finder(this.$root.friendsGlobal, x => x.id === id)
      console.log(id)
      console.log(index)
      axios.delete('http://localhost:3000/friends/' + id, {
        headers: {
          'Content-Type': 'application/json'
        }
      }).then(response => {
        console.log(response)
        this.notifications.push({
          type: 'success',
          message: 'Friend ' + response.data + id + index + ' succesfully removed!'})
        this.$root.friendsGlobal.splice(index, 1)
      }).catch(e => {
        this.notifications.push(e)
        console.log(e)
      })
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
 padding-left: 5px;
 padding-right: 5px;
 text-align: center;
 font-weight: 600;
}

.positive {
  background-color: #23D160;
  padding-left: 5px;
  padding-right: 5px;
  text-align: center;
  font-weight: 600;
}

.content {
   max-width: 350px;
}
</style>
