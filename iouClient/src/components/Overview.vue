<template>
<div id="Overview">
  <section class="hero">
    <h1 class="title">Overview</h1>
      <p class="subtitle has-text-right">My total assets are {{ totalAssets }} € and my total debts are {{ totalDebts }} €</p>
      <notification v-bind:notifications="notifications"></notification>
  </section>
  <section class="section">
    <div class="tile is-ancestor">
      <div class="tile is-parent is-vertical" v-for="sortedFriends in chunkedFriends" :key="sortedFriends.id">
            <div class="content">
          <article class="tile is-child box" v-for="friend in sortedFriends" :key="friend.id">
            <button class="delete is-pulled-right" aria-label="close" @click.prevent="deleteFriend(friend.id)"></button>
            <p class="title">{{ friend.name }}</p>
            <p class="positive">{{ friend.totalDebt }} €</p>
            <p class="negative">{{ friend.totalAsset }} €</p>
          </article>
            </div>
      </div>
    </div>
  </section>
  <button id="add-friend" @click="show" class="button is-large is-info is-pulled-right" aria-hidden="true"><i class="fa fa-user-plus" aria-hidden="true"> Friend</i></button>
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
      notifications: [],
      totalDebts: '',
      totalAssets: ''
    }
  },
  created: function () {
    this.fetchFriendsData()
    this.fetchTotalDebts()
    this.fetchTotalAssets()
  },
  computed: {
    chunkedFriends () {
      return chunk(this.filteredSearch, 4)
    }
  },
  methods: {
    fetchTotalDebts: function () {
      axios.get('http://localhost:50012/debts/total', {
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Access-Control-Allow-Origin': '*'
        }
      }).then(
        response => {
          this.totalDebts = response.data
          console.log(response)
        }).catch(e => {
          this.notifications.push(e)
          console.log(e)
        })
    },
    fetchTotalAssets: function () {
      axios.get('http://localhost:50012/assets/total', {
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Access-Control-Allow-Origin': '*'
        }
      }).then(
        response => {
          this.totalAssets = response.data
          console.log(response)
        }).catch(e => {
          this.notifications.push(e)
          console.log(e)
        })
    },
    fetchFriendsData: function () {
      axios.get('http://localhost:50012/friends/', {
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Access-Control-Allow-Origin': '*'
        }
      }).then(
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
    },
    deleteFriend: function (id) {
      let index = finder(this.$root.friendsGlobal, x => x.id === id)
      axios.delete('http://localhost:50012/friends/' + id, {
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Access-Control-Allow-Origin': '*'
        }
      }).then(response => {
        console.log(response)
        this.notifications.push({
          type: 'success',
          message: 'Friend nr ' + id + ' succesfully removed!'})
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
