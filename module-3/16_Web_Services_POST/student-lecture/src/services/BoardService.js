import axios from 'axios';// access the axios web api framework

const http = axios.create({   // Define an axios project for
  baseURL: "http://localhost:3000"//this server
});

export default {

  getBoards() {
    return http.get('/boards');// do http get for the path and return a Promise
  },

  getCards(boardID) {
    return http.get(`/boards/${boardID}`)
  },

  getCard(cardID) {
    return http.get(`/cards/${cardID}`)
  },

  // We need to do an HTTP Post to add data to the data source
  //an axios post wants the path and the data to be sent
  //axios automagically creates the headers and puts the data in the body of response
  // the data we send to post can either be a JSON object name or the JSON itself
  //post('/somepath',{name: Frank})
  addCard(card) {
    return http.post('/cards', card);// call the API with POST request with the card object passed as data
  },
// We need to do an HTTP Post to update data to the data source
  //an axios post wants the path and the data to be sent
  //axios automagically creates the headers and puts the data in the body of response
  // the data we send to post can either be a JSON object name or the JSON itself
  //an entire object is usually passed and HTTP PUT request not just the attributes to be updated
  updateCard(card) {
    return http.put(`/cards/${card.id}`, card);// update the card with the id passed in the data source
  },


  //we need to do an http delete to delete data from the data source
  //Since not data is expected/sent in the body of an HTTP DELETE request, no parameter specify data is used
  // in the axios .delete()
  deleteCard(cardID) {
    return http.delete(`/cards/${cardID}`);// delete the card with the id passed
  }

}
