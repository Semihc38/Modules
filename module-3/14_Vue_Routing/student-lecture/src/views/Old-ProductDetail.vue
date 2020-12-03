<!-- ProductDetail router view component-->
<template>
    <div id="app" class="main">
        <h1>Product Name:</h1>
       
    </div>
</template>

<script>                   

// We need to get the product id  to be displayed in this page
// We need to get the product data from the vuex data store for the id we are given in the path
//The product id is coming from the url path: product/:id
// Vue router creates the page when it gets a path it knows about
//since we need to data before the page is created in order to place the data in page
// a way to intercept the page create process and get the data is needed

export default {
    created(){// Define code to be run for the created hook in the vue processing
    // this code will run before vue compiles the template which generates the html for the page
       // We need to get id from the URL  and set the activeProductId in the data store to it
    const currentProductId = this.$route.params.id;// get the value of id from the URL
    this.$store.commit("SET_ACTIVE_PRODUCT",currentProductId);// Tell data store to set the active project
    },
    // We need to get the product data from the vuex data store for the id we are given in the path
     computed:{
         product(){ // this function will get the current product's data from the data store
         // go to the data store products array and  find the product with the id in the path
         // we can use the array find() mehtod to locate the element we want
         // like most array function find() loops though an array sending the current element
         // to function provided to it - the function is usually one statement and anonymous
         //         .find((element-name)=>{})
         //         .find((element-name)=>statement-that uses the element)
         //         $store.products give us access to the products in the data store
         // the product id of the product data we want we stored in $store.state.activeProduct

         //Loop though the array of products in the data store and find the one that matches the active prduct id

           const theProducts = this.$store.state.products.find(aProduct => aProduct.id===this.$store.state.activeProduct);
            return theProducts;
         }
     }
}
</script>

<style
ProductsListle scoped>

</style>