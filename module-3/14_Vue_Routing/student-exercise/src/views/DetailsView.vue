<template>
  <book-details v-bind:book="bookByIsbn"/>
</template>

<script>
import BookDetails from '@/components/BookDetails'
export default {
    components: {
        BookDetails
    },
    computed:{
        bookByIsbn(){
            return this.$store.state.books.find(book => book.isbn === this.$route.params.isbn)
        }
    }
}
</script>

<style>
</style>