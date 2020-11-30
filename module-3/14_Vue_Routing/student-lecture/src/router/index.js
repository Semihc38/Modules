import Vue from 'vue'              // get the vue code
import VueRouter from 'vue-router'// get the router code
import Products from '@/views/Products'// get the Products.vue view router code
import ProductDetail from '@/view/ProductDetail'// Get the ProductDetail.vue router view code


Vue.use(VueRouter)// Tell Vue we are using the router

// Array of router routes(like a URL path)
// Like using @RequestMapping in MVC to associate a path to a controller
const routes = [
{       // An object is use to associate a URL path to a route
  path:'/',         // The URL path to associate(root path)
  name:'products',  // name for the path/router-view association
  component:Products// view router component associated with the path(see import above)
},
{
path:'/products/:id',// this path will have a path variable called id
name:'product-detail',// name for the path-router view association
component:ProductDetail// Name of the view in the views folder 

}
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
