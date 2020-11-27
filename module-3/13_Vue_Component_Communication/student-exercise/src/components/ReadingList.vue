<template>
  <div class="book-container">
        <book-card v-for="book in readingList"
        v-bind:key="book.title"
        v-bind:author="book.author"
        v-bind:book="book">
        </book-card>
  </div>
</template>

<script>
import BookCard from './BookCard';
export default {
    name: 'reading-list',
    components: {
        BookCard
    },
    computed: {
        readingList() {
            const bookList = this.$store.state.books;
            return bookList;
        }
    }
}
</script>

<style>
.book-container {
    display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}
</style>