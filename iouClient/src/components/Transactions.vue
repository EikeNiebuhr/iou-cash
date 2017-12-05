<template>
<div id="Transactions">
  <section class="hero">
    <h1 class="title">Transactions</h1>
      <notification v-bind:notifications="notifications"></notification>     
   </section>
<section class="section">
  <div class="level">
    <div class="level-left">
      <div class="field">
          <h1 class="title">Lend money to {{ debitorFriend }}</h1>
      </div>
    </div>
    <div class="level-right">
      <div class="field">
        <div class="field has-addons">
        <div class="control has-icons-left">
          <div class="select">
            <select v-model="debitorFriend">
              <option v-for="friend in allFriends" :key="friend.name">{{ friend.name }}</option>
            </select>
            <span class="icon is-small is-left"><i class="fa fa-user"></i></span>
          </div>
        </div>
          <div class="control has-icons-right">
            <input class="input is-success" type="text" placeholder="Enter amount" id="amount" v-model="credit" required>
            <span class="icon is-small is-right"><i class="fa fa-eur"></i></span>
          </div>
          <div class="control">
            <button id="lend-money" @click.prevent="lendMoney" class="button is-info is-outlined" aria-hidden="true">
              <span><i class="fa fa-credit-card" aria-hidden="true"> Send Money</i></span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<section class="section">
  <div class="level">
    <div class="level-left">
           <div class="field">
       <div class="has-text-centered">
          <h1 class="title">Pay back: </h1>
        </div>
      </div>
    </div>
    <div class="level-item">
    </div>
    <div class="level-left">
        <div class="field has-addons">
        <div class="control has-icons-left">
          <div class="select">
            <select v-model="creditorFriend">
              <option v-for="friend in friendAndDebt" :key="friend.name">{{ friend.name }}</option>
            </select>
            <span class="icon is-small is-left"><i class="fa fa-user"></i></span>
          </div>
        </div>
        <div class="control has-text-centered">
          <p class="subtitle" id="pay-back-text">Pay debt + lalala {{ creditorFriend }} + ?</p>
        </div>

          <div class="control">
            <button id="send-money" @click.prevent="paybackMoney" class="button is-danger is-outlined" aria-hidden="true">
              <span><i class="fa fa-credit-card" aria-hidden="true"> Payback Money</i></span>
            </button>
          </div>
                </div>
    </div>
  </div>
</section>

<section class="section">
  <div class="level">
    <p>Payed amount in <i class="fa fa-btc" aria-hidden="true"></i> : {{ debtInBitcoin }}</p>
  </div>
</section>
</div>
</template>

<script>
import Notification from './Notifications'
import axios from 'axios'
export default {
  data () {
    return {
      notifications: [],
      allFriends: [],
      friendAndDebt: [],
      debitorFriend: '',
      debt: '',
      debtInBitcoin: '',
      credit: '',
      creditorFriend: ''
    }
  },
  created: function () {
    this.fetchFriendsData()
  },
  methods: {
    fetchFriendsData: function () {
      axios.get('http://localhost:50012/friends').then(
        response => {
          this.$root.friendsGlobal = response.data
          this.allFriends = this.$root.friendsGlobal
          this.notifications.push({
            type: 'success',
            message: 'All good! Last succesful update at ' + new Date().toLocaleTimeString()
          })
          console.log(response)
        }).catch(e => {
          this.notifications.push(e)
        })
    },
    fetchFriendsAndDebts: function () {
      axios.get('http://localhost:3000/friendsAndDebts/').then(
        response => {
          this.friendAndDebt = response.data
        }).catch(e => {
          this.notifications.push(e)
        })
    },
    lendMoney: function () {
      axios.post('/addDebt', this.friend, {
        id: this.friend.id,
        name: this.friend.name,
        amount: this.amount
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
    },
    paybackMoney: function () {
    },
    moneyInBitcoin: function () {
      axios.post('http://locahost:50002/', {
        debt: this.debt.amount,
        date: new Date().now()
      },
        {headers: {
          'Content-Type': 'application/json'
        }}
      )
  .then(function (response) {
    console.log(response)
    this.debtInBitcoin = response.data
  })
    }
  },
  components: {
    'notification': Notification
  }
}
</script>
<style scoped>
#pay-back-text {
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 5px;
}
</style>
