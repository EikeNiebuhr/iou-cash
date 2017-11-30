import Vue from 'vue'
import Router from 'vue-router'
import Transactions from '@/components/Transactions'
import Overview from '@/components/Overview'
import About from '@/components/About'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/overview',
      name: 'Overview',
      component: Overview
    },
    {
      path: '/transactions',
      name: 'Transactions',
      component: Transactions
    },
    {
      path: '/about',
      name: 'About',
      component: About
    }
  ]
})
