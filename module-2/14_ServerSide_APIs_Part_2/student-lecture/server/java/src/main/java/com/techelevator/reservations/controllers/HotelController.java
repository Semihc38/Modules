package com.techelevator.reservations.controllers;

import com.techelevator.reservations.dao.HotelDAO;
import com.techelevator.reservations.dao.ReservationDAO;
import com.techelevator.reservations.exception.HotelNotFoundException;
import com.techelevator.reservations.exception.ReservationNotFoundException;
import com.techelevator.reservations.models.Hotel;
import com.techelevator.reservations.models.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelController {

    private HotelDAO hotelDAO;	//
    private ReservationDAO reservationDAO;

    
    
  //the ctor assign object to the hotelDAo and reservation dao reference using 
    // parameters passed to it
    // where do the object for the parameters come from
    //normally the parameters are passed when the class is instantiated
    // we are using spring DI to instantiate DAO objects
    //and pass them to this ctor
    // Spring MVC instantiate the controller for us.
    // The DAOs require the @Component annotation to tell Spring they should be dependency injected when used
    public HotelController(HotelDAO hotelDAO, ReservationDAO reservationDAO) {
        this.hotelDAO = hotelDAO;		//Assign the parameter passed
        this.reservationDAO = reservationDAO;//Assign the parameter passed
        
        
    }

    /**
     * Return All Hotels
     *
     * @return a list of all hotels in the system
     */
    @RequestMapping(path = "/hotels", method = RequestMethod.GET)
    public List<Hotel> list() {
        return hotelDAO.list();
    }

    /**
     * Return hotel information
     *
     * @param id the id of the hotel
     * @return all info for a given hotel
     */
    @RequestMapping(path = "/hotels/{id}", method = RequestMethod.GET)
    public Hotel get(@PathVariable int id) {
        return hotelDAO.get(id);
    }

    /**
     * Returns all reservations in the system
     *
     * @return all reservations
     */
    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<Reservation> listReservations() {
        return reservationDAO.findAll();
    }

    /**
     * Get a reservation by its id
     *
     * @param id
     * @return a single reservation
     */
    @RequestMapping(path = "reservations/{id}", method = RequestMethod.GET)
    public Reservation getReservation(@PathVariable int id) throws ReservationNotFoundException {
        return reservationDAO.get(id);
    }

    /**
     * List of reservations by hotel
     *
     * @param hotelID
     * @return all reservations for a given hotel
     */
    @RequestMapping(path = "/hotels/{id}/reservations", method = RequestMethod.GET)
    public List<Reservation> listReservationsByHotel(@PathVariable("id") int hotelID) throws HotelNotFoundException {
        return reservationDAO.findByHotel(hotelID);
    }

    /**
     * Create a new reservation for a given hotel
     *
     * @param reservation
     * @param hotelID
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/hotels/{id}/reservations", method = RequestMethod.POST)
    
    //Add Valid tell String  to use the validation annotation in the class when assgingin data to the object
    public Reservation addReservation(@Valid @RequestBody Reservation reservation, @PathVariable("id") int hotelID)
            throws HotelNotFoundException {
        return reservationDAO.create(reservation, hotelID);
    }

    /**
     * /hotels/filter?state=oh&city=cleveland
     *
     *
     *@RequestParam - access the data in the query String
     * @param state the state to filter by
     * @param city  the city to filter by
     * @return a list of hotels that match the city & state
     */
    // This method handle  the path/hotels/filter
    @RequestMapping(path = "/hotels/filter", method = RequestMethod.GET)
    														//value is optional or required
    														//if value is same with variable name it is not mandetory
    public List<Hotel> filterByStateAndCity(@RequestParam (value="district",defaultValue="ohio")String state, 
    										@RequestParam(required = false) String city) {
    	//@RequestParam - go get the variable listed form the query string &What follows in? question mark
    	//              - get the value in state from the query string and store it in the variable state in the method
     						//@RequestParam - go get the variable listed form the query string &What follows in? question mark
    						//              - get the value in city from the query string and store it in the variable city in the method
    						// Required = false - the parameter may be missing ( it is not required)
        List<Hotel> filteredHotels = new ArrayList<>();
        List<Hotel> hotels = list();

        // return hotels that match state
        for (Hotel hotel : hotels) {

            // if city was passed we don't care about the state filter
            if (city != null) {
                if (hotel.getAddress().getCity().toLowerCase().equals(city.toLowerCase())) {
                    filteredHotels.add(hotel);
                }
            } else {
                if (hotel.getAddress().getState().toLowerCase().equals(state.toLowerCase())) {
                    filteredHotels.add(hotel);
                }

            }
        }

        return filteredHotels;
    }

}
