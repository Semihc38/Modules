package com.techelevator.hotels;

import java.util.Scanner;

import org.springframework.web.client.RestTemplate;

public class App {
	// This code will call an API to retrieve hotel and review data from that API
	// and display it on the screen
	/**********************************************************************************************************************
	Data members for the class - available to every method in the class
	**********************************************************************************************************************/
	// Define a constant to represent the base/starting part of the API URL we are using
	
	private static final String API_BASE_URL = "http://localhost:3000/";// we will add to this URL as needed
	
	
	// REST -REpresentational State Template	-	protocol for calling APIs
	// 												standard way to comminicate with a API
	//RestTemplate - framework to make API calls easier
	//				 it's a set of classes and methods to handle API Calls
	//
	
	//Define a RestTemplate object to handle our API calls
	private static RestTemplate callAPI= new RestTemplate();// use this to make API calls
	
	/**********************************************************************************************************************
	Methods to the class - our Application
	**********************************************************************************************************************/
	
    public static void main(String[] args) {
        run();// main processing code for the application
    }
    // method to Call the API to get all the hotels 
    public static Hotel[] requestHotels(){
    	// use our restTemplate to call the API and store the data in an array of hotels
    	// to get the hotels form the API we need the URL;" http://localhost:3000/hotels"
    	// The getForObject methods needs the API URL and the format in which you want the data
    	// Hotel [] class indicates you want an array of Hotel objects and Hotel is a Class
    	// The API will use the HOTEL POJO to create the objects - using the setters for POJO
    	// Match the data member names to the JSON  element names to populate the POJO object
    	// if the name dont match  between the POJO and JSON no data is stored in the POJO field
    	Hotel[] theHotels = callAPI.getForObject(API_BASE_URL +"hotels", Hotel[].class);
    	
    	
    	return theHotels; // return the array of hotels
    }
    // method to call the API to get all teh reviews it has as and an array of Reviews
    public static Review[] requestReviews() {
    	
    	Review[] theReviews = callAPI.getForObject(API_BASE_URL +"reviews", Review[].class);
    	return theReviews;
    	
    }
    //Method to call the API to get info on hotel with the id 1
    public static Hotel hotel1() {
    	
    	Hotel hotel1= callAPI.getForObject(API_BASE_URL + "hotels/1", Hotel.class);
    	
    	return hotel1;
    }
  public static Review[] reviewForHotelId1(int hotelID) {
    	
    	Review[] reviewsForhotel1= callAPI.getForObject(API_BASE_URL + "hotels/"+hotelID+ "/reviews", Review[].class);
    	
    	return reviewsForhotel1;
    }
    
    
    
    // we are outside of main() but still inside the application class
	// methods called by main() to help it process
    //These methods must be a static because they are called from a static method
    private static void run() { // primary processing for the Application
    	
        Hotel[] hotels = null;
        Scanner scanner = new Scanner(System.in);
        int menuSelection = 999;

        printGreeting();

        // Loop while the menu selection is not zero
        // Display the menu and get the choice 
        // depending on the choice -call a method to handle the menu option
        // (initially each option just displays not implements
        while(menuSelection != 0) {
            try {
                menuSelection = Integer.parseInt(scanner.nextLine());// get input from the user and convert into a int
            } catch (NumberFormatException exception) {				//handle the error if does not enter a number
                System.out.println("Error parsing the input for menu selection.");
            }
            System.out.println("");
            if (menuSelection == 1) {	// This option will list all the hotels
            	printHotels(requestHotels()); // Use the method to get the hotels from the API
            									// and give that to printHotels method      
            } else if (menuSelection == 2) {// This option will all the reviews
            	printReviews(requestReviews());
                
            } else if (menuSelection == 3) {// Show Details for Hotel Id 1
               printHotel(hotel1());
            } else if (menuSelection == 4) {
                printReviews(reviewForHotelId1(1));
            } else if (menuSelection == 5) {
                System.out.println("Not implemented");
            } else if (menuSelection == 6) {
                System.out.println("Not implemented - Create a custom Web API query here");
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            menuSelection = 999;
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            printGreeting();
        }
        scanner.close();
        System.exit(0);
    }
   
    private static void printGreeting() {
        System.out.println("");
        System.out.println("Welcome to Tech Elevator Hotels. Please make a selection: ");
        System.out.println("1: List Hotels");
        System.out.println("2: List Reviews");
        System.out.println("3: Show Details for Hotel ID 1");
        System.out.println("4: List Reviews for Hotel ID 1");
        System.out.println("5: List Hotels with star rating 3");
        System.out.println("6: Custom Query");
        System.out.println("0: Exit");
        System.out.println("");
        System.out.print("Please choose an option: ");
    }
    
    // List all the hotels in the array of Hotels passed to it
    private static void printHotels(Hotel[] hotels) {
        System.out.println("--------------------------------------------");
        System.out.println("Hotels");
        System.out.println("--------------------------------------------");
        for (Hotel hotel : hotels) {
            System.out.println(hotel.getId() + ": " + hotel.getName());
        }
    }
    // list a single hotel passed to it
    private static void printHotel(Hotel hotel) {
        System.out.println(hotel.toString());
    }
    // List all the reviews in the array of Reviews passed to it
    private static void printReviews(Review[] reviews) {
        for (Review review : reviews) {
            System.out.println(review.toString());
        }
    }

}
