<template>
  <form v-on:submit.prevent="submitForm" class="cardForm">
    <div class="status-message error" v-show="errorMsg !== ''">{{errorMsg}}</div>
    <div class="form-group">
      <label for="title">Title:</label>
      <input id="title" type="text" class="form-control" v-model="card.title" autocomplete="off" />
    </div>
    <div class="form-group">
      <label for="tag">Tag:</label>
      <select id="tag" class="form-control" v-model="card.tag">
        <option value="Feature Request">Feature Request</option>
        <option value="Design">Design</option>
        <option value="Q&A">Q&A</option>
      </select>
      <label for="status">Status:</label>
      <select id="tag" class="form-control" v-model="card.status">
        <option value="Planned">Planned</option>
        <option value="In Progress">In Progress</option>
        <option value="Completed">Completed</option>
      </select>
    </div>
    <div class="form-group">
      <label for="description">Description:</label>
      <textarea id="description" class="form-control" v-model="card.description"></textarea>
    </div>
    <button class="btn btn-submit">Submit</button>
    <button class="btn btn-cancel" v-on:click.prevent="cancelForm" type="cancel">Cancel</button>
  </form>
</template>

<script>
import boardsService from "../services/BoardService";
import moment from "moment";//Access the moment for formatting

export default {
  name: "card-form",
  props: {
    cardID: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      card: {
        title: "",
        description: "",
        status: "Planned",
        tag: "",
        avatar: "",
        date: null
      },
      errorMsg: ""
    };
  },
  methods: {
    submitForm() {
      const newCard = {
        boardId: Number(this.$route.params.boardID),
        title: this.card.title,
        description: this.card.description,
        status: this.card.status,
        tag: this.card.tag,
        avatar: "https://randomuser.me/api/portraits/lego/1.jpg",
        date: moment().format("MMM Do YYYY")// format date as jul 4th 2020
      };

      if (this.cardID === 0) {
        // add
        boardsService
          .addCard(newCard)
          .then(response => {
            if (response.status === 201) {
              this.$router.push(`/board/${newCard.boardId}`);
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, "adding");
          });
      } else {
        // update
        newCard.id = this.cardID;
        newCard.avatar = this.card.avatar;
        newCard.date = this.card.date;
        boardsService
          .updateCard(newCard)
          .then(response => {
            if (response.status === 200) {
              this.$router.push(`/board/${newCard.boardId}`);
            }
          })//catch errrors form API call - an object containing info about the error is passed to the .catch
          //similar to Exception handling in Java
          .catch(error => {// if there was an error in the api call
            this.handleErrorResponse(error, "updating");// call this method in our code with the error object
          });
      }
    },
    cancelForm() {
      this.$router.push(`/board/${this.$route.params.boardID}`);
    },
    // Common API error handling routine 
    //Two types of Api error that may occur
    //request error- meaing something was wrong in typing to get to the server
    //response error- meaning you got to the server, but what there was something wrong with what you asked it to do
    handleErrorResponse(error, verb) {
      if (error.response) {                                     //         if it was response error
        this.errorMsg =                                        // Set our erroMsg attribute to some verbiage
          "Error " + verb + " card. Response received was '" + // and the text teh server sent from the response
          error.response.statusText +
          "'.";                                               // with a '.' at the end
      } else if (error.request) {                             //if it was request error
        this.errorMsg =
          "Error " + verb + " card. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " card. Request could not be created.";
      }
    }
  },
  created() {
    if (this.cardID != 0) {
      boardsService
        .getCard(this.cardID)
        .then(response => {
          this.card = response.data;
        })
        .catch(error => {
          if (error.response && error.response.status === 404) {
            alert(
              "Card not available. This card may have been deleted or you have entered an invalid card ID."
            );
            this.$router.push("/");
          }
        });
    }
  }
};
</script>


<style>
.cardForm {
  padding: 10px;
  margin-bottom: 10px;
}
.form-group {
  margin-bottom: 10px;
  margin-top: 10px;
}
label {
  display: inline-block;
  margin-bottom: 0.5rem;
}
.form-control {
  display: block;
  width: 80%;
  height: 30px;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}
textarea.form-control {
  height: 75px;
  font-family: Arial, Helvetica, sans-serif;
}
select.form-control {
  width: 20%;
  display: inline-block;
  margin: 10px 20px 10px 10px;
}
.btn-submit {
  color: #fff;
  background-color: #0062cc;
  border-color: #005cbf;
}
.btn-cancel {
  color: #fff;
  background-color: #dc3545;
  border-color: #dc3545;
}
.status-message {
  display: block;
  border-radius: 5px;
  font-weight: bold;
  font-size: 1rem;
  text-align: center;
  padding: 10px;
  margin-bottom: 10px;
}
.status-message.success {
  background-color: #90ee90;
}
.status-message.error {
  background-color: #f08080;
}
</style>
