<template>
<div id="Transactions">
    <section class="hero">
        <h1 class="title">Transactions</h1>
  <p class="notification">Current status:
<notification v-bind:notifications="notifications"></notification>                    
  </p>
    </section>
    <section class="section">
  <label class="label">Name</label>
  <div class="control has-icons-left">
    <input class="input is-success" type="text" placeholder="Text input" id="friend_name" required>
    <span class="icon is-small is-left">
      <i class="fa fa-user"></i>
    </span>
  </div>
    <label class="label">Amount</label>
  <div class="control has-icons-left">
    <input class="input is-success" type="text" placeholder="Text input" id="friend_name" required>
    <span class="icon is-small is-left">
      <i class="fa fa-user"></i>
    </span>
  </div>
</section>
<button id="add-friend" v-on:click="show" class="button is-info" aria-hidden="true"><i class="fa fa-user-plus" aria-hidden="true"> Send Moneyz</i></button>
      </container>
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
