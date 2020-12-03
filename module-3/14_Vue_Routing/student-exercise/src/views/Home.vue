<template>
<div>
    <bestseller-list/>
    <new-releases-list/>
</div>
  
</template>

<script>
import BestsellerList from '@/components/BestSellerList.vue'
import NewReleasesList from '@/components/NewReleasesList.vue'
export default {
components: {
    BestsellerList,
    NewReleasesList
}
}
</script>

<style>
</style>