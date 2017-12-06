<template>
 <modal name="createFriend"
 :draggable="true"
 :width="640"
 :height="480"
 :scrollable="true"
 @closed="clearAll"
 >
    <div class="modal-card">
     <header class="modal-card-head">
              <p class="modal-card-title">Add Friend</p>
      <button class="delete" aria-label="close" @click="$modal.hide('createFriend')" v-on:click="clearAll"></button>
    </header>
      <section class="modal-card-body">
      <!-- Content ... -->
      <notification v-bind:notifications="notifications" class="notification"></notification>  
  <label class="label">First name</label>
  <div class="control has-icons-left">
    <input class="input is-success" type="text" placeholder="John" v-model="friend.firstName" :disabled="isDisabled" id="friend_firstname" required>
   <span class="icon is-small is-left">
      <i class="fa fa-user"></i></span>
      </div>
        <label class="label">Last name</label>
     <div class="control has-icons-left">   
          <input class="input is-success" type="text" placeholder="Smith" v-model="friend.lastName" :disabled="isDisabled" id="friend_lastname" required>
   <span class="icon is-small is-left">
      <i class="fa fa-user"></i>
    </span>
      </div>
      </section>
    <footer class="modal-card-foot">
      <button class="button is-success" @click.prevent="addFriend">Save changes</button>
      <button class="button" @click="$modal.hide('createFriend')" v-on:click="clearAll">Close</button>
      <button class="button" @click="$modal.hide('createFriend')" v-on:click="clearAll" :disabled="isDisabled">Cancel</button>
    </footer>
    </div>
 </modal>
</template>
<script>
import Notification from './Notifications'
import axios from 'axios'
import nullCheck from '../mixins/nullCheck'
import search from '../mixins/search'
export default {
  data () {
    return {
      friend: {},
      notifications: [],
      isDisabled: false
    }
  },
  methods: {
    clearAll: function () {
      this.friend = null
      this.notifications.length = 0
      this.isDisabled = false
    },
    addFriend: function () {
      if (this.isNullOrWhitespace(this.friend.firstName)) {
        this.notifications.push({
          type: 'danger',
          message: 'Oops! Please give your friend a first name'
        })
        return false
      }
      if (this.isNullOrWhitespace(this.friend.lastName)) {
        this.notifications.push({
          type: 'danger',
          message: 'Oops! Please give your friend a last name'
        })
        return false
      }
      axios.post('http://localhost:50012/friends/', this.friend, {
        headers: {
          'Content-Type': 'application/json'
        }
      }).then(response => {
        console.log(response)
        let fullName = this.friend.firstName + ' ' + this.friend.lastName
        this.notifications.push({
          type: 'success',
          message: 'New Friend ' + fullName + ' added succesfully!'})
        this.$set(this.$root.friendsGlobal, (this.$root.friendsGlobal.length + 1), this.friend)
        this.isDisabled = true
      }).catch(e => {
        this.notifications.push(e)
        console.log(e)
      })
    }
  },
  components: {
    notification: Notification
  },
  mixins: [
    nullCheck,
    search
  ]
}
</script>