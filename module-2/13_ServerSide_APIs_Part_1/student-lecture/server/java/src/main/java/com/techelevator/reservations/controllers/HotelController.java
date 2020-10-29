package com.techelevator.reservations.controllers;

import com.techelevator.reservations.dao.HotelDAO;
import com.techelevator.reservations.dao.MemoryHotelDAO;
import com.techelevator.reservations.dao.MemoryReservationDAO;
import com.techelevator.reservations.dao.ReservationDAO;
import com.techelevator.reservations.models.Hotel;
import com.techelevator.reservations.models.Reservation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // tells the server there a methods in here to handle rest api calls
public class HotelController {

	
	// This contains the methods for our API
	// A Controller is a set of methods for an API stored on a server
	// This will handle API calls for Hotel data
	
	//Essentially all a controller does is find data send it back to the API
	
	//MVC is a design pattern(a way of coding and structuring applications
	// MVC Model - the data for application
	//view the interactions with the user
	//Controller - coordinates the View and data the view tells the Controller what is wants the Controller get the data from the Model returns it to the View
	                     // usually as a list of objects or a single object
	// in this application- View the services class
						//- Model - The DAOs
						//- Controller This code that associates paths to methods
	
	
	
	
    private HotelDAO hotelDAO;     			// Define a reference to the hotelDAO
    private ReservationDAO reservationDAO;  // Define a reference to the ReservationDAO
// Define - Create a name for a piece of memory
// instantiate - Reserve memory for an object   - new classname
// initialize - Assign a value to an object		- constructors
    public HotelController() {				// Constructor for the controller class initialize data for this class
        this.hotelDAO = new MemoryHotelDAO(); // instantiate the HotelDAO and assign it to the reference
        this.reservationDAO = new MemoryReservationDAO(hotelDAO); // instantiate the ReservationDAO and assign it to the reference
    }

    /**
     * Return All Hotels
     *
     * @return a list of all hotels in the system
     */
    // @RequestMapping  assigns an API path to a method and http request
    //    server-name:port/path
    // http:localhost:8080/hotels will cause this method to run
    @RequestMapping(path = "/hotels", method = RequestMethod.GET)// This method handle get for path /hotels
    public List<Hotel> list() {
    	// This controller method does is get a list of Hotels form the DAO and return it to API caller
    	System.out.println("Hello from the method that handled your/ hotels path");
        return hotelDAO.list();// call the DAO method to produce a list of Hotels and return it API
    }

    /**
     * Return hotel information
     *
     * @param id the id of the hotel
     * @return all info for a given hotel
     */
    // This method will handle  the path /Hotels/id - for example /hotels/1
    //{id} is a path variable - a value passed in the URL path
    //PathVariable give us access to the path variable in the URL
    @RequestMapping(path = "/hotels/{id}", method = RequestMethod.GET)
    public Hotel get(@PathVariable int id) {// Get the path variable form the URL and as an int call it id
        return hotelDAO.get(id);// use the value from the path  variable to call the get method
        						// 			in the hotelDAO and return int
    }
    //Write a controller to return all the reservations when the path/reservation is used with get request
    
    @RequestMapping(path="/reservations", method=RequestMethod.GET)
    	public List<Reservation>listReservations(){
    	return reservationDAO.findAll();// call the find all method in reservationDAO and return to API called
    	
    }
    
    
    //write a  Controller to add a reservation to our reservation resource
    // using the path/hotels/hotel-id/reservations- 
    // http://localhost:8080/hotels/1/reservations - will add a reservation to hotel id 1
    
  
    // Since we are adding to a resource a POST request will be done by API caller
    
    //The data for the reservation will be in the body of the API POST request
    // We will return the Reservation we added to the Reservation resource
    
    //@PathVariable will give us access to the hotel id
    //@RequestBody will give us access to data in the request body -- create an object from the data
    //		@RequestBody class-name object-name convert the JSON in the Request body to the object of the class
    
    
    @RequestMapping(path="/hotels/{id}/reservations",method=RequestMethod.POST)
    public Reservation addReservation(@PathVariable int id, @RequestBody Reservation aReservation)  {
    	// We have the hotel id in the int variable called id
    	// we have the reservation information in the Reservation object called silence 
    	
    	// call the reservation DAO method to add the the reservation to the resource
    	
    	reservationDAO.create(aReservation,id);
    	
    	return aReservation;
    	
    }
    
    // Write a controller to return all reservation for a given 
    // hotels/id/reservations for a get request
    @RequestMapping(path="/hotels/{id}/reservations",method=RequestMethod.GET)
    public List<Reservation> getAReservation(@PathVariable int id)  {
    	return reservationDAO.findByHotel(id);
    }
    
    
    
    
    
}
