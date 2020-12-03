<template>
<div> {{book.title}} 
        {{book.author}}
</div>
  
</template>

<script>
export default {
    props:{
        book: Object
    }
}
</script>

<style>
</style>