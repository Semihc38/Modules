import Vue from 'vue'                   // Access the code for Vue
import VueRouter from 'vue-router'      // Access the code for Vue router
import Home from '../views/Home.vue'    // Access the code for Home.vue
import Board from '../views/Board.vue'  // Access the code for Board.vue
import Card from '../views/Card.vue'    // Access the code for Card.vue

Vue.use(VueRouter)

const routes = [      // Associate paths to components
  {
    path: '/',        // the root path
    name: 'Home',     // name for the association
    component: Home   // router view to load when the path is referenced
  },
  // this path will be used  when the board title is clicked in BoardList
  {
    path: '/board/:id', // the path /board/id- parameter
    name: 'Board',      //named this
    component: Board    //router view to load when the path referenced
  },
  {// This path will be used when the path /board/board-id/card/card is referenced
    path: '/board/:boardID/card/:cardID',// the path /board/param/card/param
    name: 'Card',
    component: Card
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
