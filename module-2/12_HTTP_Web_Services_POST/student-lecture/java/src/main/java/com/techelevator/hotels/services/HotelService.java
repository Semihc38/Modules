package com.techelevator.hotels.services;

import com.techelevator.hotels.models.Hotel;
import com.techelevator.hotels.models.Reservation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class HotelService {

  private final String BASE_URL;
  private final RestTemplate restTemplate = new RestTemplate();
  private final ConsoleService console = new ConsoleService();

  public HotelService(String url) {
    BASE_URL = url;
  }

  /**
   * Create a new reservation in the hotel reservation system
   *
   * @param newReservation
   * @return Reservation
   */
  public Reservation addReservation(String newReservation) {
    //add a reservation to the API - we need to use HTTP post request 
	  // when we do a post we need to put the data in request body 
	  
	  // create a reservation object from the STring passed into this method
	  // using the helper method makeReservation defined below
    Reservation aReservation = makeReservation(newReservation);
    if(aReservation==null) {
    	console.printError("Invalid data for a reservation ");
    	return null;
    }
    // now that we have a reservation object we need to send to server via API
    // using an HTTP POSt request
    // a post request requires headers to tell the server about the request
    // and the data for the request has to be in the body of request
    HttpHeaders theHeader = new HttpHeaders(); // instantiate an object for request headers
 // telling the server we sending a JSON data in the request body
    theHeader.setContentType(MediaType.APPLICATION_JSON);// mediaType is a group of constants
    													// for datatypes you can send to server
    // so we have the object to send to the server and the headers to describe the data
    //all that's left is to combine them into an http request using httpEntity class
    
    HttpEntity anEntity= new HttpEntity(aReservation, theHeader);
    
    // call the API with a POST request to add a reservation to the server
    
    // the API will return the Reservation object that was added to the server
    //use the PostForObject method to issue a POST request with restTemplate
    
    
    aReservation = restTemplate.postForObject(BASE_URL+ "reservations",anEntity,  Reservation.class);
    
    
    return aReservation;
  }

  /**
   * Updates an existing reservation by replacing the old one with a new
   * reservation
   *
   * @param CSV
   * @return
   */
  public Reservation updateReservation(String CSV) {
    
	  Reservation aReservation= makeReservation(CSV); // create a new reservation from the parameter
	  if(aReservation==null) {
		  console.printError("Invalid data for reservation ");
		  return null;
	  }
	  HttpHeaders theHeaders= new HttpHeaders();
	  theHeaders.setContentType(MediaType.APPLICATION_JSON);
	  HttpEntity anEntity =new HttpEntity(aReservation, theHeaders);
	  
	  // Http put does not return any data - so there is nothing to store when it returns
	   restTemplate.put(BASE_URL+ "reservations/"+ aReservation.getId(),anEntity,  anEntity);
	  
	  
	   
    return aReservation;
  }

  /**
   * Delete an existing reservation
   *
   * @param id
   */
  public void deleteReservation(int id) {
	  
	  /// delete does not require and data in the body of the request nor does it return antuynig\
	  // all we do is call API with URL required to identify what you want to delete
	  restTemplate.delete(BASE_URL+"reservations/"+ id);

  }

  /* DON'T MODIFY ANY METHODS BELOW */

  /**
   * List all hotels in the system
   *
   * @return
   */
  public Hotel[] listHotels() {
    Hotel[] hotels = null;
    try {
      hotels = restTemplate.getForObject(BASE_URL + "hotels", Hotel[].class);
    } catch (RestClientResponseException ex) {
      // handles exceptions thrown by rest template and contains status codes
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } catch (ResourceAccessException ex) {
      // i/o error, ex: the server isn't running
      console.printError(ex.getMessage());
    }
    return hotels;
  }

  /**
   * Get the details for a single hotel by id
   *
   * @param id
   * @return Hotel
   */
  public Hotel getHotel(int id) {
    Hotel hotel = null;
    try {
      hotel = restTemplate.getForObject(BASE_URL + "hotels/" + id, Hotel.class);
    } catch (RestClientResponseException ex) {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } catch (ResourceAccessException ex) {
      console.printError(ex.getMessage());
    }
    return hotel;
  }

  /**
   * List all reservations in the hotel reservation system
   *
   * @return Reservation[]
   */
  public Reservation[] listReservations() {
    Reservation[] reservations = null;
    try {
      reservations = restTemplate.getForObject(BASE_URL + "reservations", Reservation[].class);
    } catch (RestClientResponseException ex) {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } catch (ResourceAccessException ex) {
      console.printError(ex.getMessage());
    }
    return reservations;
  }

  /**
   * List all reservations by hotel id.
   *
   * @param hotelId
   * @return Reservation[]
   */
  public Reservation[] listReservationsByHotel(int hotelId) {
    Reservation[] reservations = null;
    try {
      reservations = restTemplate.getForObject(BASE_URL + "hotels/" + hotelId + "/reservations", Reservation[].class);
    } catch (RestClientResponseException ex) {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } catch (ResourceAccessException ex) {
      console.printError(ex.getMessage());
    }
    return reservations;
  }

  /**
   * Find a single reservation by the reservationId
   *
   * @param reservationId
   * @return Reservation
   */
  public Reservation getReservation(int reservationId) {
    Reservation reservation = null;
    try {
      reservation = restTemplate.getForObject(BASE_URL + "reservations/" + reservationId, Reservation.class);
    } catch (RestClientResponseException ex) {
      console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
    } catch (ResourceAccessException ex) {
      console.printError(ex.getMessage());
    }
    return reservation;
  }

  private Reservation makeReservation(String CSV) {
    String[] parsed = CSV.split(",");

    // invalid input (
    if (parsed.length < 5 || parsed.length > 6) {
      return null;
    }

    // Add method does not include an id and only has 5 strings
    if (parsed.length == 5) {
      // Create a string version of the id and place into an array to be concatenated
      String[] withId = new String[6];
      String[] idArray = new String[] { new Random().nextInt(1000) + "" };
      // place the id into the first position of the data array
      System.arraycopy(idArray, 0, withId, 0, 1);
      System.arraycopy(parsed, 0, withId, 1, 5);
      parsed = withId;
    }

    return new Reservation(Integer.parseInt(parsed[0].trim()), Integer.parseInt(parsed[1].trim()), parsed[2].trim(),
        parsed[3].trim(), parsed[4].trim(), Integer.parseInt(parsed[5].trim()));
  }

}
