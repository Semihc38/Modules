/*
    Example of a multi-line comment just like in C#/Java
*/

// Single line comment

/**
 * Functions start with the word function.
 * They don't have a return type and the naming convention is camel-case.
 */
function variables() {
  // Declares a variable where the value cannot be changed
  const numDaysInWeek=7; //const defines a variable whose value cannot be changed
  console.log("There are " + numDaysInWeek+"days in a week");
  //we can enclose the line display in tick marks/accent marks and 
  //place variables to be substituted in an EL expression--${variable}
  console.log(`There are  ${numDaysInWeek} days in a week`);
  // Declares a variable those value can be changed
//always use let not var to define a variable that can be changed 
//use of var to define variables can lead to confusing and hard to debug code  
let daysInMonth=30;
console.log(`There are ${daysInMonth} days in November`);
  // Declares a variable that will always be an array

  const weekdays=[
"Monday"
,"Tuesday"
,"Wednesday"
,"Thursday"
,"Friday"
,"Saturday"
,"Sunday"
  ];
  console.table(weekdays);
}

/**
 * Functions can also accept parameters.
 * Notice the parameters do not have types.
 * @param {Number} param1 The first number to display
 * @param {Number} param2 The second number to display
 */
function printParameters(param1, param2) {
  console.log(`The value of param1 is ${param1}`);
  console.log(`The value of param2 is ${param2}`);
}

/**
 * Compares two values x and y.
 * == is loose equality -values are the same, data types may be different
 * === is strict equality -values and data types must be the same
 * @param {Object} x
 * @param {Object} y
 */
function equality(x, y) {
  console.log(`x is ${typeof x}`);
  console.log(`y is ${typeof y}`);

  console.log(`x == y : ${x == y}`); // true
  console.log(`x === y : ${x === y}`); // false
}

/**
 * Each value is inherently truthy or falsy.
 * false, 0, '', null, undefined, and NaN are always falsy
 * everything else is always truthy
 * @param {Object} x The object to check for truthy or falsy,
 */
function falsy(x) {
  if (x) {
    console.log(`${x} is truthy`);
  } else {
    console.log(`${x} is falsy`);
  }
}

/**
 * js may use JSON objects to hold related data 
 * Objects are simple key-value pairs
    - values can be primitive data types
    - values can be arrays
    - or they can be functions
*/
function objects() {
  const person = {          //js object
    firstName: "Bill",      // element in the object key value pair
    lastName: "Lumbergh",   // element in the object key value pair
    age: 42,                // element in the object key value pair
    employees: [            // element in the object that's an array
      "Peter Gibbons",      // element in the array
      
      "Milton Waddams",     // element in the array
      "Samir Nagheenanajar",// element in the array
      "Michael Bolton"      // element in the array
    ],
    //define a toString function for the object so js knows how to convert it a String
    toString:function(){
      return `${this.lastName},${this.firstName},${this.age}`
    }
  };

  // Log the object
  console.log(person);
  console.table(person);
  console.log(`Display using console.log: ${person}`);//treat person as string not an object
  console.table(`Display using console.table: ${person}`);

  // Log the first and last name
  console.log(`${person.firstName}`)

  // Log each employee - each employee is an element in an array in the object
  //use object.array name to access the array use index inside[] to access an element
  for(let i=0;i<person.employees.length; i++){
    console.log(`Employee #${i+1} is ${person.employees[i]}`)
  }
  // we can execute/run and function in the object using object.function()
  console.log(person.toString())// run the toString function in the object

}

/*
########################
Function Overloading
########################

Function Overloading is not available in Javascript. If you declare a
function with the same name, more than one time in a script file, the
earlier ones are overridden and the most recent one will be used.
*/

function Add(num1, num2) {
  return num1 + num2;
}

function Add(num1, num2, num3) {
  return num1 + num2 + num3;
}

/*
########################
Math Library
########################

A built-in `Math` object has properties and methods for mathematical constants and functions.
*/

function mathFunctions() {
  console.log("Math.PI : " + Math.PI);
  console.log("Math.LOG10E : " + Math.LOG10E);
  console.log("Math.abs(-10) : " + Math.abs(-10));
  console.log("Math.floor(1.99) : " + Math.floor(1.99));
  console.log("Math.ceil(1.01) : " + Math.ceil(1.01));
  console.log("Math.random() : " + Math.random());
}

/*
########################
String Methods
########################

The string data type has a lot of properties and methods similar to strings in Java/C#
*/

function stringFunctions(value) {
  console.log(`.length -  ${value.length}`);
  console.log(`.endsWith('World') - ${value.endsWith("World")}`);
  console.log(`.startsWith('Hello') - ${value.startsWith("Hello")}`);
  console.log(`.indexOf('Hello') - ${value.indexOf("Hello")}`);//first occurrence of the String
  console.log(`.indexOf('l') - ${value.indexOf("l")}`);
  console.log(`.lastIndexOf('l') - ${value.lastIndexOf("l")}`);

  console.log(`.substr(value,2,3) - ${value.substr(2,3)}`);//substr (string starting index, number of characters)
  console.log(`.substring(value,2,3) - ${value.substring(2,3)}`);//substring (string ,starting index and ending index)
                                                                    //return the chars starting at start-index
                                                                      //up to by not including the char at end index
  /*
    Other Methods
        - split(string)
        - substr(number, number)
        - substring(number, number)
        - toLowerCase()
        - trim()
        - https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String
    */
}

    /*
    Array manipulation examples
    */
   function arrayFunction(){
     let stooges=[
       "Moe",
       "Larry",
       "Curly"
     ]
     console.table(stooges);
     stooges.push("Shemp");
     console.table(stooges);
     stooges.unshift("Curly Joe") // add an element to start of the array
     stooges.splice(3,0,"Groucho","Chico","Harpo")//insert elment starting ate index 3 and delete zero elements
     console.table(stooges);
     stooges.splice(3,1)//delete 1 element starting at element 3
     console.table(stooges)
     stooges.splice(3,2)//remove 2 elements staring ate index 3
     console.table(stooges);

     stooges.shift() // remove at the top array
     console.table(stooges)

     stooges.pop() // remove at the bottom array
     console.table(stooges)

     let marxBros =[
       "Groucho",
       "Chico",
       "Harpo"

     ]
     let oldFunnyGuys = stooges.concat(marxBros)// combine two arrays
     console.table(stooges)
          
   }

