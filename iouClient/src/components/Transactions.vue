<template>
<div id="Transactions">
  <section class="hero">
    <h1 class="title">Transactions</h1>
      <notification v-bind:notifications="notifications"></notification>     
   </section>
<section class="section">
  <div class="field">
    <label class="label">Friend</label>
      <div class="control">
        <div class="select">
          <select v-model="selected">
            <option v-for="friend in friends" :key="friend">{{friend.name}}</option>
          </select>
        </div>
      </div>
    </div>
    <label class="label">Amount</label>
      <div class="control has-icons-left">
        <input class="input is-success" type="text" placeholder="Text input" id="friend_name" required>
          <span class="icon is-small is-left"><i class="fa fa-user"></i></span>
      </div>
</section>
<button id="send-money" v-on:click="sendMoney" class="button is-info" aria-hidden="true"><i class="fa fa-user-plus" aria-hidden="true"> Send Moneyz</i></button>
</div>
</template>

<script>
import Notification from './Notifications'
import axios from 'axios'
export default {
  data () {
    return {
      friends: [],
      notifications: [],
      selected: ['me', 'you', 'we']
    }
  },
  created: function () {
    this.fetchProductData()
  },
  methods: {
    fetchProductData: function () {
      axios.get('http://localhost:3000/friends').then(
        response => {
          this.$root.friendsGlobal = response.data
        }).catch(e => {
          this.notifications.push(e)
        })
    },
    sendMoney: function () {
      axios.post('/addDebt', this.friend, {
        name: 'Fred'
      },
        {headers: {
          'Content-Type': 'application/json'
        }}
      )
  .then(function (response) {
    this.notifications.push(response.data)
    console.log(response)
  })
  .catch(function (error) {
    this.notifications.push(error)
    console.log(error)
  })
    }
  },
  components: {
    'notification': Notification
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
