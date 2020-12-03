<template>
  <div>
    <h1>My Kanban Boards</h1>
    <div class="boards">
      <div class="loading" v-if="isLoading">  <!-- show this element if isloading is true-->
        <img src="../assets/squirrel.gif" /><!-- image to display while page is loading-->
      </div>
      <div
        class="board"
        v-for="board in boards"
        v-bind:key="board.id"
        v-bind:style="{ 'background-color': board.backgroundColor }"
        v-else
      >
      <!--Link the board title to a router path which includes the board id is a parameter-->
      <!--teh board title is connected to the router path named "board"-->
        <router-link v-bind:to="{ name: 'Board', params: { id: board.id } }">
          {{ board.title }}
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import boardsService from '../services/BoardService';// Access the code in BoardService

export default {
  data() {
    return {
      boards: [], // data to be used in this page - loaded by an API call
      isLoading: true // switch to indicate if the page is being loaded
    };
  },
  created() {// tell vue to do teh processing in created before the page is displayed
    // calls to an api are asyncronous calls- the program continues after making the call rather than waiting for it to complete
    // We need to wait for the api call to finish before we can process the data from the api
    // .tehn  - wait for the api call to complete and then do this processing
    //how it works
    //1 The api call is done and creates a promise which is returned to the caller
    //2 a promise is an object that will automaticaly updated whent the api call is complete
    //3 .then waits for the promise to be updated as complete
    // when the promise is fulfill, the respnse data is retugrned
    // When we call the boardsService.getBoards it starts to api call and returns us the promise for the call
    // we use .then to wait for the promise to be fulfilled
    // aPormise,then
    boardsService.getBoards().then(response => {// call the boardsService getBoards() function
      this.boards = response.data; // when the api call ompletes , assign the data from the response to boards
      this.isLoading = false;// indicate we are done loading the data
    });
  }
};
</script>

<style scoped>
.boards {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.board {
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 40px;
  flex: 1;
  margin: 0 20px;
  text-align: center;
}
.loading {
  flex: 3;
}
.board a:link,
.board a:visited {
  color: rgb(243, 243, 243);
  text-decoration: none;
}
</style>
