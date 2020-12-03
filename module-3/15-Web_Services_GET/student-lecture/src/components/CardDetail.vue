<template>
  <div>
    <h1>{{ card.title }}</h1>
    <p>{{ card.description }}</p>

    <router-link v-bind:to="{name:'Board', params:{id:$route.params.boardID }}">
      Back to board
    </router-link>
  </div>
</template>

<script>
//@services.BoardService' is good too
import theBoardServices from "../services/BoardService";
export default {
  name: "card-detail",
  data() {
    // this contains data used in the page
    return {
      card: {
        //hold the data for the card being displayed
        title: "",
        description: "",
        status: "",
        comments: [],
      },
    };
  },
  created() {
    // we need to be suer we have the data before the page is displayed
    // call the getCard service with the board id and

    theBoardServices
      .getCard(this.$route.params.boardID, this.$route.params.cardID)
      .then((apiData) => {
        this.card = apiData;
      }); //wait for API call to complete and pass the response to a function
  },
};
</script>
