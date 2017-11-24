<template>
 <modal name="createFriend">
    <div class="modal-card">
     <header class="modal-card-head">
              <p class="modal-card-title">Add Friend</p>
      <button class="delete" aria-label="close" @click="$modal.hide('createFriend')"></button>
    </header>
      <section class="modal-card-body">
      <!-- Content ... -->
      <div class="field">
      <notification v-bind:notifications="notifications" class="notification"></notification>  
  <label class="label">Name</label>
  <div class="control has-icons-left">
    <input class="input is-success" type="text" placeholder="Text input" v-model="friend.name" id="friend_name" required>
    <span class="icon is-small is-left">
      <i class="fa fa-user"></i>
    </span>
  </div>
      </div>
      </section>
    <footer class="modal-card-foot">
      <button class="button is-success" v-on:click="addFriend">Save changes</button>
      <button class="button" @click="$modal.hide('createFriend')">Cancel</button>
    </footer>
    </div>
 </modal>
</template>
<script>
import Notification from './Notifications'
import axios from 'axios'
export default {
  data () {
    return {
      friend: {},
      notifications: []
    }
  },
  methods: {
    addFriend: function () {
      axios.post('http://localhost:3000/api/friend/create', this.friend, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(
          response => {
            this.notifications.push({
              type: 'success',
              message: 'Product created successfully'
            })
          })
        .catch(e => {
          this.notifications.push(e)
        })
    }
  },
  components: {
    notification: Notification
  }
}
</script>