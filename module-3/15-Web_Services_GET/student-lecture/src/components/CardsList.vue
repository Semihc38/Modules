<!--This will display in the CardList.vue component-->
<template>
  <div>
    <h1>{{ title }}</h1>
    <div class="loading" v-if="isLoading">
      <img src="../assets/squirrel.gif" />
    </div>
    <div class="boards" v-else>

      <!--the word "planned" for the cards props in BoardColumn.vue-->
      <!--the value in the id parameter from the path for boardID props in BoardColumn.vue-->
      <board-column
        title="Planned"
        v-bind:cards="planned"
        v-bind:boardID="this.$route.params.id"
      />
      <board-column
        title="In Progress"
        v-bind:cards="inProgress"
        v-bind:boardID="this.$route.params.id"
      />
      <board-column
        title="Completed"
        v-bind:cards="completed"
        v-bind:boardID="this.$route.params.id"
      />
    </div>
    <div class="board-actions" v-if="!isLoading">
      <router-link to="/">Back to Boards</router-link>
    </div>
  </div>
</template>

<script>
import boardsService from '../services/BoardService';
import BoardColumn from '@/components/BoardColumn';

export default {
  name: 'cards-list',
  components: {
    BoardColumn
  },
  data() {
    return {
      title: '', //title for the card
      cards: [],// data displayed in the page - loaded from API call
      isLoading: true // switch to indicate is page is loading or not
    };
  },
  created() {// tell vue to do these things before the page is displayed
  // call the getCards boardsService with the id from the path and wait for the API call to complete(.then)
    boardsService.getCards(this.$route.params.id).then(response => {// when API is done pass a function the response data
      this.title = response.data.title;//Assing title the title from the response
      this.cards = response.data.cards;//Assing cards the cards from the response
      this.isLoading = false;// indicate we are done loading the data from teh API
    });
  },
  computed: {
    planned() {
      return this.cards.filter(card => card.status === 'Planned');
    },
    inProgress() {
      return this.cards.filter(card => card.status === 'In Progress');
    },
    completed() {
      return this.cards.filter(card => card.status === 'Completed');
    }
  }
};
</script>

<style>
.boards {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-gap: 20px;
}
.board-actions {
  text-align: center;
  padding: 20px 0;
}
.board-actions a:link,
.board-actions a:visited {
  color: blue;
  text-decoration: none;
}
.board-actions a:hover {
  text-decoration: underline;
}
</style>
