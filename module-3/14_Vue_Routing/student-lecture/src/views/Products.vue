<!-- Products view-->
<!-- this view will be managed by the Vue Router-->

<template>
    <div class="products">
        <h1>Products</h1>
        <products-list/><!-- include the ProductList.vue component-->

    </div>
</template>

<script>                   //@ is a short-hand for the root folder of the app
import ProductsList from '@/components/ProductsList.vue'// go get the ProductsList.vue code
export default {
    components:{
        ProductsList

    }
}
</script>

<style
ProductsListle scoped>

</style>