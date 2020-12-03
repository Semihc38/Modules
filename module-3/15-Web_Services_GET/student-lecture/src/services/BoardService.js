//This contains the service used by the app components
//axios is an API framework to do API calls from JavaScript
import axios from 'axios';// Access the axios code

const http = axios.create({// instantiate an axios object for our server connection
  baseURL: "http://localhost:3000"// This axios object is connected to this server
});

export default {

  getBoards() {
    return http.get('/boards');// use the axios object to call API server with a GET request to the path '/boards'
  },

  getCards(boardID) {
    return http.get(`/boards/${boardID}`)
  },

  getCard(boardID, cardID) {// will return the card for given board and card id 
    return http.get(`/boards/${boardID}`).then((response) => {
      const cards = response.data.cards;
      return cards.find(card => card.id == cardID);
    })
  }

}
