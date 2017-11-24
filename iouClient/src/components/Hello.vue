<template>
  <div id="Hello">
    <h1>{{ msg }}</h1>
    <ul>
      <li v-for="label in labels" :key="label">{{label}}</li>
    </ul>
    <input v-model="message" placeholder="edit me">
    <p>Message is: {{ message }}</p>
    <input type="text" v-model="newLabel">
    <button class="button is-primary" @click="addLabel">Click</button>
    <p>Message is: {{ newLabel }}</p>
    <ul v-if="posts && posts.length">
      <li v-for="post of posts" :key="post">
        <p>
          <strong>{{post.title}}</strong>
        </p>
        <p>{{post.body}}</p>
      </li>
    </ul>

    <ul v-if="errors && errors.length">
      <li v-for="error of errors" :key="error">
        {{error.message}}
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      labels: [
        'test', 'test1', 'test2'
      ],
      newLabel: '',
      message: '',
      posts: [],
      errors: []
    }
  },
  methods: {
    addLabel () {
      this.labels.push(this.newLabel)
    }
  },
  // Fetches posts when the component is created.
  created () {
    axios.get(`http://api.twitter.com/1.1/direct_messages/show.json?id=240136858829479936`)
      .then(response => {
        // JSON responses are automatically parsed.
        this.posts = response.data
      })
      .catch(e => {
        this.errors.push(e)
      })

    // async / await version (created() becomes async created())
    //
    // try {
    //   const response = await axios.get(`http://jsonplaceholder.typicode.com/posts`)
    //   this.posts = response.data
    // } catch (e) {
    //   this.errors.push(e)
    // }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
