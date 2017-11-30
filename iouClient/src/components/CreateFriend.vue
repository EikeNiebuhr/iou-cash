<template>
 <modal name="createFriend"
 :draggble=true
 :width="640"
 :height="400"
 :closed="clearAll"
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
    <input class="input is-success" type="text" placeholder="John Smith" v-model="friend.name" id="friend_name" required>
   <span class="icon is-small is-left">
      <i class="fa fa-user"></i>
    </span>
      </div>
      </section>
    <footer class="modal-card-foot">
      <button class="button is-success" v-on:click="addFriend">Save changes</button>
      <button class="button" @click="$modal.hide('createFriend')" v-on:click="clearAll">Cancel</button>
    </footer>
    </div>
 </modal>
</template>
<script>
import Notification from './Notifications'
import axios from 'axios'
import nullCheck from '../mixins/nullCheck'
export default {
  data () {
    return {
      friend: {},
      notifications: [],
      friends: []
    }
  },
  methods: {
    clearAll: function () {
      this.friend.name = ''
      this.notifications.length = 0
    },
    addFriend: function () {
      if (this.isNullOrWhitespace(this.friend.name)) {
        this.notifications.push({
          type: 'danger',
          message: 'Oops! Please give your friend a name'
        })
        return false
      } else {
        this.friend.name = this.friend.name
      }

      axios.post('http://localhost:3000/friends', this.friend, {
        headers: {
          'Content-Type': 'application/json'
        }
      }).then(response => {
        console.log(response)
        this.notifications.push({
          type: 'success',
          message: 'New Friend ' + this.friend.name + ' added succesfully!'})
        this.$root.friendsGlobal.push(this.friend)
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
    nullCheck
  ]
}
</script>