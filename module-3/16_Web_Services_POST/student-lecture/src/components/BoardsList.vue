<template>
  <div id="sideNav">
    <h1>My Kanban Boards</h1>
    <div class="boards">
      <div class="status-message error" v-show="errorMsg !== ''">
        {{ errorMsg }}
      </div>
      <div class="loading" v-if="isLoading">
        <img src="../assets/ping_pong_loader.gif" />
      </div>
      <router-link
        :to="{ name: 'Board', params: { id: board.id } }"
        class="board"
        v-for="board in this.$store.state.boards"
        v-bind:key="board.id"
        v-bind:style="{ 'background-color': board.backgroundColor }"
        v-else
        tag="div"
      >
        {{ board.title }}
      </router-link>
      <button
        class="btn addBoard"
        v-if="!isLoading && !showAddBoard"
        v-on:click="showAddBoard = !showAddBoard"
      >
        Add Board
      </button>
      <form v-if="showAddBoard">
        Board Title:
        <input type="text" class="form-control" v-model="newBoard.title" />
        Background Color:
        <input
          type="text"
          class="form-control"
          v-model="newBoard.backgroundColor"
        />
        <button class="btn btn-submit" v-on:click="saveNewBoard">Save</button>
        <button
          class="btn btn-cancel"
          v-on:click="showAddBoard = !showAddBoard"
        >
          Cancel
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import boardsService from "../services/BoardService";

export default {
  data() {
    return {
      isLoading: true,
      showAddBoard: false,
      newBoard: {
        title: "",
        backgroundColor: this.randomBackgroundColor(),
      },
      errorMsg: "",
    };
  },
  created() {// Before the page is displayed 
    this.retrieveBoards();// go get the board to be displayed
  },
  methods: {
    retrieveBoards() {
      //call teh getBoards() in boardServices, wait for it to complete(.then)
      //when the call completes it calls the SET+_BOARDS mutation in the data store the response data
      //the response data is what the call to getBoards() returned
      // and indicate loadin is complete(isLoading=false)
      boardsService.getBoards().then((response) => {
        this.$store.commit("SET_BOARDS", response.data);
        this.isLoading = false;
        //check to be sure there was no error in teh call to getBoards()
        //if the path we are in is "Home" and the http status code was 200(OK) there was response data
        // push the path /board/id-of-1st-element-in-response to the browser - tell router go to this path
        if (
          this.$route.name == "Home" && response.status === 200 && response.data.length > 0
        ) {
          this.$router.push(`/board/${response.data[0].id}`);
        }
      });
    },
    saveNewBoard() {},
    randomBackgroundColor() {
      return "#" + this.generateHexCode();
    },
    generateHexCode() {
      var bg = Math.floor(Math.random() * 16777215).toString(16);
      if (bg.length !== 6) bg = this.generateHexCode();
      return bg;
    },
  },
};
</script>

<style scoped>
div#sideNav {
  height: 100%;
  width: 20%;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  padding-top: 20px;
  padding-bottom: 20px;
  overflow-x: hidden;
  border-right: solid lightgrey 1px;
}

h1 {
  text-align: center;
}

.boards {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}
.board {
  color: #f7fafc;
  border-radius: 10px;
  padding: 40px;
  flex: 1;
  margin: 10px;
  text-align: center;
  cursor: pointer;
  width: 60%;
}
.addBoard {
  color: #f7fafc;
  border-radius: 10px;
  background-color: #28a745;
  font-size: 16px;
  width: 60%;
  margin: 10px;
  padding: 20px;
  cursor: pointer;
}
.form-control {
  margin-bottom: 10px;
}
.btn {
  margin-bottom: 35px;
}
.loading {
  flex: 3;
}
.board:hover:not(.router-link-active),
.addBoard:hover {
  font-weight: bold;
}
.router-link-active {
  font-weight: bold;
  border: solid blue 5px;
}
</style>
