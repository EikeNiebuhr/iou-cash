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
  <label class="label">Name</label>
  <div class="control has-icons-left">
    <input class="input is-success" type="text" placeholder="John Smith" v-model="friend.name" :disabled="isDisabled" id="friend_name" required>
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
      this.friend.name = null
      this.notifications.length = 0
      this.isDisabled = false
    },
    addFriend: function () {
      if (this.isNullOrWhitespace(this.friend.name)) {
        this.notifications.push({
          type: 'danger',
          message: 'Oops! Please give your friend a name'
        })
        return false
      }
      axios.post('http://localhost:50012/friends/', {name: this.friend.name}, {
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
          'Access-Control-Allow-Origin': '*'
        }
      }).then(response => {
        console.log(response)
        this.notifications.push({
          type: 'success',
          message: 'New Friend ' + this.friend.name + ' added succesfully!'})
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