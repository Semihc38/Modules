# Server Side APIs: Part 1 - .NET Lecture Notes

## Preparing for lecture

The lecture project is configured to use HTTPS because it's considered best practice to use HTTPS in development if HTTPS is used in production. The lecture project is also configured to run in IIS Express, so the development HTTPS certificate is trusted. The tutorial used IIS Express, so the students should be familiar with it.

Note that the Postman screenshots use HTTP and port 8080; however, the results of each request should be the same, though the dates you get will vary.

## Running the application

To run this application, open `HotelReservations.sln` in Visual Studio and run it using IIS Express. You'll test the API in Postman, and connect a client application as the last step. The client application is the end result of the previous day's lecture.

## Student starting code

The students' code has a similar structure to the `lecture-final` code that you have. The only difference is that the students' code is missing the methods to:

- List all reservations in the system
- Get a reservation by its ID
- List all reservations by a hotel
- Create a new reservation
- Filter hotels by city and state

## Lecture code walkthrough

**Overview**

- Project Walkthrough
  - MVC Structure
  - Models
    - Hotel
    - Address
    - Reservation
  - DAOs
- HotelsController
  - GET
    - all reservations
    - single reservation by ID
    - all reservations by hotel
  - POST
    - create new reservation
  - Filter hotels by state and city
- Connect the client to the API

### Project walkthrough

Before you write any code, walk through the structure of a ASP.NET Core MVC Web API project and answer any questions the students might have.

There is some existing code that you can walk through before you create methods in the controller:

- `Models/Hotel.cs`: Represents a Hotel in the API
- `Models/Address.cs`: Represents an Address in the API
- `Models/Reservation.cs`: Represents a Reservation in the API
- `DAO/IHotelDao.cs`: Interface that all hotel dao objects must implement
- `DAO/HotelDao.cs`: A class that handles in memory storage and hotel data access
- `DAO/IReservationDao.cs`: Interface that all reservation dao objects must implement
- `DAO/ReservationDao.cs`: A class that handles in memory storage and reservation data access

## HotelsController

In the application, you'll find the "Controllers" folder and a class called `HotelsController.cs`:

```csharp
namespace HotelReservations.Controllers
{
    [Route("/")]
    [ApiController]
    public class HotelsController : ControllerBase
    {
        private static IHotelDao _hotelDao;
        private static IReservationDao _reservationDao;

        public HotelsController()
        {
            _hotelDao = new HotelDao();
            _reservationDao = new ReservationDao();
        }

    // ...

}
```

Walk through what the constructor does. They haven't learned about dependency injection yet, so for the time being, the controller just creates a new instance of the DAOs.

### Testing the API with Postman

This is a great opportunity to show the students good practices when it comes to building APIs. A careful programmer won't worry about hooking up the front-end code until they know their API does what they want.

You can take the opportunity to use tools like Postman, Chrome Developer Tools (Network Tab), and debugger break points to step through the process of handling a request.

Run the application and test the existing request mappings:

- GET: /hotels
- GET: /hotels/{id} (1-7)

![List Hotels](./img/list_hotels.png)

### Adding new methods

At this point, you can map out on the board what methods you need in your controller based on the requirements that the front end team gave to you:

- list all reservations
  - path: `/reservations`
  - request method: `GET`
  - return: list of all reservations in the system
- get reservation by ID
  - path: `/reservation/{id}`
  - request method: `GET`
  - return: reservation info for given id using path variable
- list all reservations by hotel
  - path: `/hotels/{id}/reservations`
  - request method: `GET`
  - return: list of all reservations in the system by hotel
- add new reservation
  - path: `/reservations`
  - request method: `POST`
  - add a new reservation to the given hotel based on the request body
- filter hotels
  - path: `/hotels/filter?state={state}&city={city}`
  - request method: `GET`
  - find hotels by state and city (optional)

#### List reservations

The first method you'll write returns all the reservations in the list:

```csharp
[HttpGet("reservations")]
public List<Reservation> ListReservations()
{
    return _reservationDao.List();
}
```

![List Reservations](./img/list_reservations.png)

#### Get a reservation by ID

Next, you need the ability to get a single reservation using the reservation ID. The first thing to point out is the use of a path variable—`{id}`. The reservation ID is a `int`, so you'll map whatever comes through in the path to a int named `id`:

```csharp
[HttpGet("reservations/{id}")]
public Reservation GetReservation(int id)
{
    Reservation reservation = _reservationDao.Get(id);

    if (reservation != null)
    {
        return reservation;
    }

    return null;
}
```

![Get Reservation](./img/get_reservation.png)

#### List reservations by hotel

Next, you'll list all reservations by hotel. This is similar to the previous method, but with one difference: the path variable doesn't always need to be `id`. It can be something different as long as it's the same between the path template (`hotels/{hotelId}/reservations`) and the method parameter (`int hotelId`):

```csharp
[HttpGet("hotels/{hotelId}/reservations")]
public List<Reservation> ListReservationsByHotel(int hotelId)
{
    return _reservationDao.FindByHotel(hotelId);
}
```

![List Reservations by Hotel](./img/list_reservations_by_hotel.png)

#### Add a reservation

Last but not least, there is a way to add a new hotel in the application. Be sure to point out that the `reservation` parameter takes the JSON body and converts it over to a `Reservation` object:

```csharp
[HttpPost("reservations")]
public Reservation AddReservation(Reservation reservation)
{
    return _reservationDao.Create(reservation);
}
```

![Create Reservation](./img/create_reservation.png)

#### Filter hotels by city and state

The client has come back to you and said that they want to filter hotels by state and optionally, city. Until now, all of the variables you've used have been path variables. This method is a good example of how to use query string parameters—they don't have to be defined in the route template, but they're automatically mapped to method parameters. If any are omitted, they're `null`:

```csharp
[HttpGet("hotels/filter")]
public List<Hotel> FilterByStateAndCity(string state, string city)
{
    List<Hotel> filteredHotels = new List<Hotel>();

    List<Hotel> hotels = ListHotels();
    // return hotels that match state
    foreach (Hotel hotel in hotels)
    {
        if (city != null)
        {
            // if city was passed we don't care about the state filter
            if (hotel.Address.City.ToLower().Equals(city.ToLower()))
            {
                filteredHotels.Add(hotel);
            }
        }
        else
        {
            if (hotel.Address.State.ToLower().Equals(state.ToLower()))
            {
                filteredHotels.Add(hotel);
            }
        }
    }
    return filteredHotels;
}
```

### Connecting the client application

The client application from the previous lecture is located in `/client/dotnet`. The API that you built out today modeled the fake API that was used. The only code that changed was the URL of the API in `Program.cs`:

```csharp
class Program
{
    private static readonly APIService apiService = new APIService("https://localhost:44322/");

    // ...

}
```

If the API that you worked on today is running, run the command line application and verify that everything works.

> The previous client application has the ability to update and delete a reservation which is covered in a future lecture. The client also doesn't have the ability to filter by state and city, but that could be something the students can work on if they have time.

At this point, you'll see the two applications communicating with each other.
