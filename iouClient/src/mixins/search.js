export default {
  computed: {
    filteredSearch: function () {
      return this.$root.friendsGlobal.filter((friend) => {
        return friend.name.toUpperCase().match(this.$root.searchWordGlobal.toUpperCase())
      })
    }
  }
}
