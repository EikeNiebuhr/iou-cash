// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import router from './router'
import VModal from 'vue-js-modal'

Vue.use(Vuex)
Vue.use(VModal, {dialog: true})

Vue.config.productionTip = false

import 'font-awesome/css/font-awesome.css'
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
  data: {
    friendsGlobal: [],
    searchWordGlobal: ''
  }
})
