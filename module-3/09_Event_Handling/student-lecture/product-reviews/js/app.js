const name = 'Cigar Parties for Dummies';
let description = 'Host and plan the perfect cigar party for all of your squirrelly friends.';
let reviews = [
  {
    reviewer: 'Malcolm Gladwell',
    title: 'What a book!',
    review:
      "It certainly is a book. I mean, I can see that. Pages kept together with glue (I hope that's glue) and there's writing on it, in some language.",
    rating: 3
  }
];

/**
 * Add our product name to the page title
 * Get our page page title by the id and the query the .name selector
 * once you have the element you can add the product name to the span.
 */
function setPageTitle() {
  const pageTitle = document.getElementById('page-title');
  pageTitle.querySelector('.name').innerHTML = name;
}

/**
 * Add our product description to the page.
 */
function setPageDescription() {
  document.querySelector('.description').innerHTML = description;
}

/**
 * I will display all of the reviews in the reviews array
 */
function displayReviews() {

  //If the html content supports a template  add template to the dom
  if ('content' in document.createElement('template')) {
    reviews.forEach((review) => {
      displayReview(review);
    });
  } else {
    console.error('Your browser does not support templates');
  }
}

/**
 *
 * @param {Object} review The review to display
 */

function displayReview(review) {
  // get a reference to the element with id='main
  const main = document.getElementById('main');
  //clone the template and get a reference to it
  const tmpl = document.getElementById('review-template').content.cloneNode(true);
  tmpl.querySelector('h4').innerText = review.reviewer;
  tmpl.querySelector('h3').innerText = review.title;
  tmpl.querySelector('p').innerText = review.review;
  // there will always be 1 star because it is a part of the template
  for (let i = 1; i < review.rating; ++i) {
    const img = tmpl.querySelector('img').cloneNode();
    tmpl.querySelector('.rating').appendChild(img);
  }
  main.appendChild(tmpl);
}

// LECTURE STARTS HERE ---------------------------------------------------------------
//These functions will execute when the page is loaded into the browser
//Since these functions contains statements that manipulate the DOM
// if the dom has not yet been created when they run they will fail
// so we should wait until the DOM is created before allowing them to run

// luckily the browser will tell us when it has finished creating th DOM 
//by firing the DOMContentLoaded event
// wait until the DOM is loaded/created before manipulating the DOM with these function
// add an event handler to the document to be run when DOMContentLoaded event happens

// .addEventListener(event-to-listen-for, function-to-handle-event)
document.addEventListener('DOMContentLoaded',()=>{

                                                // set the product reviews page title
                                                 setPageTitle();
                                                  // set the product reviews page description
                                                setPageDescription();
                                                // display all of the product reviews on our page
                                                    displayReviews();




/**
 * Take an event on the description and swap out the description for a text box. using the function provided
 * 
 */
const descriptionLine=document.querySelector('.description');// Get a reference to element we want the Event Handler
//add an event handler to the description line passing it a reference to the element clicked(event.target)
// event.target get the target of the event from the event object(the element that was click in this case)
// name of the parameter is not matter 'click',(semih)
descriptionLine.addEventListener('click', (event)=> {//an anon-func that takes the event object as a parameter
  toggleDescriptionEdit(event.target)

});
  // add an eventHandler to the input box to accept the input and change the description when they press enter
const theInputBox=document.getElementById('inputDesc');// get a reference to teh input box

theInputBox.addEventListener('keyup',(event)=>{
  if(event.key==='Enter'){
    exitDescriptionEdit(event.target, true);// run the func provided to accept the input and set to the description

  }
  if(event.key==='Escape'){// if the key they pressed was Escape 
    exitDescriptionEdit(event.target, false);
  }
})

// add an event listener to the button for add a review
const theAddReview = document.getElementById('btnToggleForm');
theAddReview.addEventListener('click', (event)=>{
showHideForm(); // run the function to show/hide the form


})
const theSaveReviewButton=document.getElementById('btnSaveReview');
theSaveReviewButton.addEventListener('click',(event)=>{

  // Due to this being a submit button for a form- we do not want to go the server with an HTTP request
  // we want to handle the button processing
  event.preventDefault(); // tell the button to not perform it's default behavior to submit to API
saveReview();

})



});// end of the event handling code from DOMContentLoaded
 




/** 
 * This function is used when th description line is clicked
 *  
 @param {Element-click} the element the user clicked which caused this function to run
 */
function toggleDescriptionEdit(desc) {
  const textBox = desc.nextElementSibling;// get a reference to the next sibling of the element clicked
                                            // allow us to access the input element following the description
  textBox.value = description;               // set the initial value of the input box to the default description
  //.classlist represents the list of classes currently assigned to element- use it to add/remove class
  textBox.classList.remove('d-none');       // un-hide the input box by removing the d-none class
  desc.classList.add('d-none');               // hide the element that was clicked by adding the d-none class
  textBox.focus();                            //put the cursor(focus) in the input box
}

/**
 * Take an event on the text box and set the description to the contents
 * of the text box and then hide the text box and show the description.
 *
 * @param {Event} event the event object
 * @param {Boolean} save should we save the description text
 */
function exitDescriptionEdit(textBox, save) { // receive the element where Enter or Escape was pressed 
                                              // The second parameter is a boolean indicating should the content be saved or not
  let desc = textBox.previousElementSibling;  // Get a reference to element that is a previous sibling to this one (description)
  if (save) {                     // save was requested (true)
    desc.innerText = textBox.value;// set the  description content to the content of the input box
  }
  textBox.classList.add('d-none');// hide the input box(add d-none to it's class)
  desc.classList.remove('d-none');// display the description boc(remove d-none from class)
}

/**
 * I will show / hide the add review form
 */
function showHideForm() {                           // This will hide of show the form for a review
  const form = document.querySelector('form');      // get a reference to the form in the html
  const btn = document.getElementById('btnToggleForm'); // Get a reference to 'Add review' button in the html

  if (form.classList.contains('d-none')) {    // if the form is currently hidden (class has d-none)
    form.classList.remove('d-none');          // remove the d-none to display the form
    btn.innerText = 'Hide Form';              // change the button text to say 'Hide Form'
    document.getElementById('name').focus();  //find the element with the id='name' and put the cursor in it
  } else {                                    // if the form is currently displayed 
    resetFormValues();                        // reset the form values using function provided
    form.classList.add('d-none');             // add the d-none class to the form to hide 
    btn.innerText = 'Add Review';             // make the button say 'add review'

  }
}

/**
 * I will reset all of the values in the form.
 */
function resetFormValues() {
  const form = document.querySelector('form');
  const inputs = form.querySelectorAll('input');
  inputs.forEach((input) => {
    input.value = '';
  });
  document.getElementById('rating').value = 1;
  document.getElementById('review').value = '';
}

/**
 * I will save the review that was added using the add review from
 */
function saveReview() {
// We need to add an element to our reviews array with the values from the form

// get a reference to input box in the form
const nameBox=document.getElementById('name');
const titleBox=document.getElementById('title');
const reviewBox=document.getElementById('review');
const ratingBox=document.getElementById('rating');


// get teh values from the form and create a new element to add to the reveiws array

const newReview ={
  reviewer:nameBox.value,  // create a reviewer in the new review with the value from the name input box
  title:titleBox.value,  // create a title in the new review with the value from the title input box
  review:reviewBox.value,  // create a review in the new review with the value from the review input box
  rating:ratingBox.value  // create a rating in the new review with the value from the rating input box
}
// add the new review object to the reviews array 

reviews.push(newReview); // add the new review to the array in memory
displayReview(newReview);// add the new review to the DOM/HTML for display
showHideForm(); // hide the form now that we have save the data

}
