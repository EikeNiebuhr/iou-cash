import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Transactions from '@/components/Transactions'
import Overview from '@/components/Overview'

Vue.use(Router)

export default new Router({
  routes: [
    {
      name: 'Home',
      path: '/home',
      component: Hello
    },
    {
      path: '/overview',
      name: 'Overview',
      component: Overview
    },
    {
      path: '/transactions',
      name: 'Transactions',
      component: Transactions
    }
  ]
})
