package src.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * Class who represents a reservation
 * A reservation is defined by its id, date and time, the Client who reserved,
 * an array of the luggages, a flight class, a flight and a checkin
 * 
 */
public class Reservation {
    private int id;
    private LocalDateTime dateTime;
    private Client client;
    private ArrayList<Luggage> luggages;
    private FlightClass flightClass;
    private Flight flight;
    private Checkin checkin;

    /**
    * Constructor of a Reservation from its identifier, date and time 
    * of reservation, client who made the reservation, array of luggages 
    * and flight class
    *
    * @param id the identifier of the Reservation
    * @param flight the flight scheduled for the Reservation
    * @param dateTime the date and time of the Reservation
    * @param client the Client who made the Reservation
    * @param luggages the array of luggages for the Reservation
    * @param flightClass the FlightClass of the Reservation
    */
    public Reservation(int id, Flight flight, LocalDateTime dateTime, Client client, ArrayList<Luggage> luggages, FlightClass flightClass) {
        this.id = id;
        this.flight = flight;
        this.dateTime = dateTime;
        this.client = client;
        this.luggages = luggages;
        this.flightClass = flightClass;
        this.checkin = null;
    }

    /**
    * Gets the identifier of a Reservation
    * 
    * @return this id
     */
    public int getId(){
        return this.id;
    }

    /**
    * Sets the identifier of a Reservation
    * 
    * @param id the identifier that will be set to this
    */
    public void setId(int id){
        this.id = id;
    }

    /**
    * Gets the time and date of a Reservation
    * 
    * @return this dateTime
    */
    public LocalDateTime getDate(){
        return this.dateTime;
    }

    /**
    * Sets the date and time of a Reservation
    * 
    * @param date the date and time that will be set to this
    */
    public void setDate(LocalDateTime date){
        this.dateTime = date;
    }

    /**
    * Gets the Client of a Reservation
    * 
    * @return this client
    */
    public Client getClient(){
        return this.client;
    }

    /**
    * Sets the Client of a Reservation
    * 
    * @param client the Client that will be set to this
    */
    public void setClient(Client client){
        this.client = client;
    }

    /**
    * Gets the Luggage array of a Reservation
    * 
    * @return this luggages array
    */
    public ArrayList<Luggage> getLuggages(){
        return this.luggages;
    }

    /**
    * Gets the FlightClass of a Reservation
    * 
    * @return this FlightClass
    */
    public FlightClass getFlightClass(){
        return flightClass;
    }

    /**
    * Sets the flight class of a Reservation
    * 
    * @param fClass the flight class that will be set to this
    */
    public void setFlightClass(FlightClass fClass){
        this.flightClass = fClass;
    }

    /**
    * Gets the Flight of a Reservation
    * 
    * @return this flight
    */
    public Flight getFlight () {
        return this.flight;
    }

    /**
    * Gets the Checkin of a Reservation
    * 
    * @return this checkin
    */
    public Checkin getCheckin() {
        return this.checkin;
    }

    /**
    * Sets the checkin of a Reservation
    * 
    * @param checkin the Checkin that will be set to this
    */
    public void setCheckin(Checkin checkin) {
        this.checkin = checkin;
    }

    /**
    * Add a Luggage to the Luggage array
    *
    *  @param luggage the Luggage that we are adding
    */
    public void addLuggage(Luggage luggage){
        this.luggages.add(luggage);
    }

    /**
    * Method that displays the amount of luggages planned in the reservation
    * for each type of luggage
    */
    public void displayLuggages() {
        int handLuggageQuantity = 0;
        int checkedLuggageQuantity = 0;
        for (Luggage luggage: this.luggages) {
            if (luggage instanceof HandLuggage) {
                handLuggageQuantity++;
            } else if (luggage instanceof CheckedLuggage){
                checkedLuggageQuantity++;
            }
        }
        System.out.println(handLuggageQuantity + "x Hand Luggage");
        System.out.println(checkedLuggageQuantity + "x Checked Luggage");
    }

    public double getPrice() {
        return this.flight.getPrice(this.flightClass, this.luggages);
    }

    public boolean hasCheckIn() {
        return this.checkin != null;
    }

    public static Reservation getReservationById(ArrayList<Reservation> reservations, int id) throws Exception {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        throw new Exception("Reservation not found!");
    }

    /**
    * Method that displays all the informations about a reservation
    */
    public void display() {
        System.out.println();
        System.out.println("reservation_id: " + this.id);
        System.out.print("Flight: ");
        System.out.println(this.flight);
        System.out.println("reservation_time: " + Flight.timeToString(this.dateTime));
        System.out.println("flight_class: " + this.flightClass.getDescription());
        System.out.println("checked in: " + hasCheckIn());
        if (hasCheckIn()) {
            System.out.println("seat number: " + this.checkin.getSeat().getNoSeat());
        }
        System.out.println("price: " + this.getPrice() + "$");
        displayLuggages();
    }
}
