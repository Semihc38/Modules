/* Steps in solving the problem */

const PROBLEM_IN_A_SET=10;
let problemSet=[]; // This is gonna hold the problems to be displayed

let score=0;
let currentProblem=1;

document.addEventListener('DOMContentLoaded', ()=>{

    generateProblemSet();// Randomly generate the problem and answers
    displayProblemSet();

});// End of DOMContentLoaded event listener


function generateProblemSet(){

    for(let i=0; i<PROBLEM_IN_A_SET; i++ ){

 const problem={
     leftValue :  getRandomNumber(10),
     rightValue :getRandomNumber(10),
     answer :null // right now we don't know the answer
 }
const correctAnswer = problem.leftValue*problem.rightValue;
//getRandomNumber(82) will return a random number between 0 and 81
// 81 is the max answer when multiple single digit decimal values
const answerChoices= shuffleArray([correctAnswer,getRandomNumber(82),getRandomNumber(82),getRandomNumber(82)])
    // we now have an array with the correct answer and 3 wrong answers randomly stored in an array
    // add the array of answer choices to the problem
    problem.answer=answerChoices;// put the answers in the problem object
    problemSet.push(problem);// add the problem to the problem set

}
}
function displayProblemSet(){
    displayProblem();
    displayAnswers();
}
function displayProblem(){
    const problemContainer=document.getElementById('problem')// find the element in the html that holds the problem
    const expression=`${problemSet[currentProblem-1].leftValue} * ${problemSet[currentProblem-1].rightValue}`// expression to display in the page
   problemContainer.querySelector('div.expression').innerText=expression; //assign the current problem to the html element

}

function displayAnswers(){
 const answerContainer= document.getElementById('answers');// get a reference to the answers in the html
 const listOfAnswers = answerContainer.querySelector('ul');// get a reference to the ul that holds the answer
 const theListItems = listOfAnswers.querySelectorAll('li');// get an array of the list items to hold each answer

 const theAnswers = problemSet[currentProblem-1].answer; // copy the answers out of problem for coding conveniece
 for(let i=0; i<theAnswers.length; i++){// loop thru the answers one at a time 

    theListItems[i].innerText=theAnswers[i];// assign the answer to the corresponding li item
 }



}

// Utility/Helper function 
/**
 * Utility function to generate a random number based on max
 * @param {number} max
 */

function getRandomNumber(max) {// max value requested +1
//Passing a 10 - return a random number between 0 and 9
// math.random generates a number between 0 and 1
// multiple by the power of 10 you want if you want whole numbers(int)
// math.random*10 gives 0-9
//math.random*100 gives 0-99
//math.floor - return the closest integer rounded down value
    return Math.floor(Math.random() * Math.floor(max));
   }
   

   /**
 * Utility function to shuffle the items in an array
 * @param {object} arr
 */
function shuffleArray(arr) {
    return arr.sort(function (a, b) { return Math.random() - 0.5 })
   }