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
          <h1 class="title" v-bind:value="debitorFriend.id">Lend money to </h1>
      </div>
    </div>
    <div class="level-right">
      <div class="field">
        <div class="field has-addons">
        <div class="control has-icons-left">
          <div class="select">
            <select v-model="debitorFriend">
              <option v-for="debitorFriend in $root.friendsGlobal" :key="debitorFriend.id" v-bind:value="debitorFriend">{{ debitorFriend.name }}</option>
            </select>
            <span class="icon is-small is-left"><i class="fa fa-user"></i></span>
          </div>
        </div>
          <div class="control has-icons-right">
            <input class="input is-success" type="text" placeholder="Enter amount" id="credit" v-model="credit" required>
            <span class="icon is-small is-right"><i class="fa fa-eur"></i></span>
          </div>
          <div class="control has-icons-right">
            <input class="input is-success" type="text" placeholder="Notice" id="credit" v-model="notice" required>
            <span class="icon is-small is-right"><i class="fa fa-commenting"></i></span>
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
          <h1 class="title">Owe money to {{ friend.name }}</h1>
      </div>
    </div>
    <div class="level-right">
      <div class="field">
        <div class="field has-addons">
        <div class="control has-icons-left">
          <div class="select">
            <select v-model="friend">
              <option v-for="friend in $root.friendsGlobal" :key="friend.id" v-bind:value="friend">{{ friend.name }}</option>
            </select>
            <span class="icon is-small is-left"><i class="fa fa-user"></i></span>
          </div>
        </div>
          <div class="control has-icons-right">
            <input class="input is-success" type="text" placeholder="Enter amount" id="debt" v-model="debt" required>
            <span class="icon is-small is-right"><i class="fa fa-eur"></i></span>
          </div>
          <div class="control">
            <button id="lend-money" @click="oweMoney" class="button is-info is-outlined" aria-hidden="true">
              <span><i class="fa fa-credit-card" aria-hidden="true"> Save Amount</i></span>
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
          <p><div class="title has-text-left">Pay back {{ creditor.name }}</div>
          <div class="subtitle">Details: {{creditor.notice }} {{ creditor.amount }} € from {{ creditor.date | formatDate }}</div></p>
        </div>
    </div>
    <div class="level-right">
        <div class="field has-addons">
        <div class="control has-icons-left">
          <div class="select">
            <select v-model="creditor">
              <option v-for="creditor in friendAndDebt" :key="creditor.id" v-bind:value="creditor">{{ creditor.amount }} € to {{ creditor.name }} </option>
            </select>
            <span class="icon is-small is-left"><i class="fa fa-money"></i></span>
          </div>
        </div>
        <div class="control has-text-centered">
        </div>
          <div class="control">
            <button id="send-money" @click.prevent="paybackMoney(creditor.id, creditor.amount, creditor.date)" class="button is-danger is-outlined" aria-hidden="true">
              <span><i class="fa fa-credit-card" aria-hidden="true"> Payback Debt</i></span>
            </button>
          </div>
        </div>
    </div>
  </div>
</section>

<section class="section">
  <div class="level">
    <p class="subtitle">The same amount in <i class="fa fa-btc" aria-hidden="true"></i> : {{ valueInBitcoin }}</p>
    <p class="subtitle"></p>
  </div>
</section>
</div>
</template>

<script>
import Notification from './Notifications'
import axios from 'axios'
import nullCheck from '../mixins/nullCheck'
import moment from 'moment'

export default {
  data () {
    return {
      notifications: [],
      friendAndDebt: [],
      debitorFriend: {},
      friend: {},
      debt: '',
      debtInBitcoin: {},
      valueInBitcoin: '',
      timeOfDebt: '',
      credit: '',
      notice: '',
      creditor: '2'
    }
  },
  created: function () {
    this.fetchFriendsData()
    this.fetchFriendsAndDebts()
  },
  methods: {
    fetchFriendsData: function () {
      axios.get('http://localhost:50012/friends').then(
        response => {
          this.$root.friendsGlobal = response.data
          this.notifications.push({
            type: 'success',
            message: 'All good! Found all friends at ' + new Date().toLocaleTimeString()
          })
          console.log(response)
        }).catch(e => {
          this.notifications.push(e)
        })
    },
    fetchFriendsAndDebts: function () {
      axios.get('http://localhost:50012/debts/').then(
        response => {
          console.log(response.data)
          this.notifications.push({
            type: 'success',
            message: 'All good! Got all debts from server at ' + new Date().toLocaleTimeString()
          })
          this.friendAndDebt = response.data
        }).catch(e => {
          this.notifications.push(e)
        })
    },
    lendMoney: function () {
      if (this.isNullOrWhitespace(this.credit)) {
        this.notifications.push({
          type: 'danger',
          message: 'Oops! Please enter an amount to lend'
        })
        return false
      }
      if (this.isNullOrWhitespace(this.debitorFriend.name)) {
        this.notifications.push({
          type: 'danger',
          message: 'Oops! Please select a friend'
        })
        return false
      }

      axios.post('http://localhost:50012/assets', {
        'debitor': {
          'id': this.debitorFriend.id
        },
        'creditor': {
          'id': '2'
        },
        'amount': this.credit,
        'notice': this.notice
      },
        {
          headers: {
            'Content-Type': 'application/json'
          }}
      )
  .then(response => {
    this.notifications.push({
      type: 'success',
      message: this.credit + ' € send to friend ' + this.debitorFriend.name
    })
    this.moneyInBitcoin(this.credit, null)
    console.log(response)
  })
  .catch(e => {
    this.notifications.push(e)
    console.log(e)
  })
    },
    oweMoney: function () {
      if (this.isNullOrWhitespace(this.debt)) {
        this.notifications.push({
          type: 'danger',
          message: 'Oops! Please enter an amount you owe'
        })
        return false
      }
      if (this.isNullOrWhitespace(this.friend.name)) {
        this.notifications.push({
          type: 'danger',
          message: 'Oops! Please select a friend'
        })
        return false
      }

      axios.post('http://localhost:50012/debts', {
        'debitor': {
          'id': '2'
        },
        'creditor': {
          'id': this.friend.id
        },
        amount: this.debt
      },
        {
          headers: {
            'Content-Type': 'application/json'
          }}
      )
  .then(response => {
    this.notifications.push({
      type: 'success',
      message: this.debt + ' € send to friend ' + this.friend.name
    })
    this.moneyInBitcoin(this.debt, null)
    console.log(response)
  })
  .catch(e => {
    this.notifications.push(e)
    console.log(e)
  })
    },
    paybackMoney: function (id, amount, dateOfDebt) {
      axios.delete('http://localhost:50012/debts/' + id, {
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Access-Control-Allow-Origin': '*'
        }
      }).then(response => {
        console.log(response)
        this.moneyInBitcoin(amount, dateOfDebt)
        this.notifications.push({
          type: 'success',
          message: 'Debt of ' + amount + ' € succesfully payed!'})
        this.fetchFriendsAndDebts()
      }).catch(e => {
        this.notifications.push(e)
        console.log(e)
      })
    },
    moneyInBitcoin: function (amount, dateOfDebt) {
      let axiosInstance = axios.create({
        baseURL: 'http://localhost:50002/',
        timeout: 30000,
        headers: {'Content-Type': 'application/json'}
      })
      if (dateOfDebt === null) {
        axiosInstance.get('/' + amount)
    .then(response => {
      this.debtInBitcoin = response.data
      console.log(this.debtInBitcoin)
      this.valueInBitcoin = this.debtInBitcoin.bitcoin.value
    }).catch(e => {
      this.notifications.push(e)
      console.log(e)
    })
      } else {
        var timeInSec = dateOfDebt / 1000
        var valid = (new Date(dateOfDebt)).getTime() > 0
        console.log((new Date(dateOfDebt)).getTime())
        console.log(valid)
        if (valid) {
          axiosInstance.get('/' + timeInSec + '/' + amount,
      )
    .then(response => {
      this.debtInBitcoin = response.data
      console.log(this.debtInBitcoin)
      this.valueInBitcoin = this.debtInBitcoin.bitcoin.value
      this.timeOfDebt = this.debtInBitcoin.bitcoin.time
    }).catch(e => {
      this.notifications.push(e)
      console.log(e)
    })
        }
      }
    }
  },
  components: {
    'notification': Notification
  },
  filters: {
    formatDate: function (value) {
      if (value) {
        return moment(value).format('DD.MM.YYYY HH:mm')
      }
    }
  },
  mixins: [
    nullCheck
  ]
}
</script>
<style scoped>
#pay-back-text {
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 5px;
}
</style>
