<template>
  <div>
    <form class="new-book-form" v-on:submit.prevent="addNewBook">
            <div>
                <label for="title-input">Title:</label>
                <input class="title-input" type="text" v-model="newBook.title"/>
            </div>

            <div>
                <label for="author-input">Author:</label>
                <input class="author-input" type="text" v-model="newBook.author"/>
            </div>

            <div>
                <label for="isbn-input">ISBN:</label>
                <input class="isbn-input" type="text" v-model="newBook.isbn"/>
            </div>
            <div>
            <input type="submit">
            </div>
    </form>
  </div>
</template>

<script>
export default {
    name: "new-book-form",
    data() {
        return {
            newBook: {
                read: false,
            }
        }
    },
    methods: {
        addNewBook() {
            this.$store.commit("ADD_BOOK", this.newBook);
            this.newBook = {read: false};
        }
    }  
}
</script>

<style>
</style>