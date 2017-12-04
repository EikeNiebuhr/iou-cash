<template>
  <div id="notifications">
    <div class="notification">
      <p>Status:</p>
        <div v-for="notification in notifications" :key="notification.type">
            {{ notification.message }}
        </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  },
  methods: {
    getNotificationClass: function (notification) {
      return 'alert alert-' + notification.type
    }
  },
  props: ['notifications']
}
</script>
