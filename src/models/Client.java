package src.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * Class who represents an Client, that inherits from Person
 * A Client is defined by Person's attributes, and an id, inscription date and an array of every reservation he made
 */
public class Client extends Person {
    private ArrayList<Reservation> reservations;
    private LocalDate inscriptionDate;

    /**
     * Constructor of a Client from every attributes except his reservation array
     * The constructor initializes the reservation array
     * 
     * @param fname first name of the Client
     * @param lname last name of the Client
     * @param phoneNum phone number of the Client
     * @param email email of the Client
     * @param dbirth date of birth of the Client
     * @param id id of the Client
     * @param inscrDate inscription date of the Client
     */
    public Client(String fname, String lname, String phoneNum, String email, LocalDate dbirth, int id, LocalDate inscrDate) {
        super(fname, lname, phoneNum, email, dbirth);
        this.id = id;
        this.inscriptionDate = inscrDate;
        this.reservations = new ArrayList<Reservation>();
    }
    
    /**
     * Sets the inscription date of a Client
     * 
     * @param inscDate the inscription date that will be set to this
     */
    public void setDate(LocalDate inscDate) {
        this.inscriptionDate = inscDate;
    }
    /**
     * Gets the inscription date of a Client
     * 
     * @return this inscription date
     */
    public LocalDate getInscriptionDate() {
        return this.inscriptionDate;
    }

    /**
     * Gets the reservation array of a Client
     * 
     * @return this reservations array
     */
    public ArrayList<Reservation> getReservations() {
        return this.reservations;
    }

    public ArrayList<Reservation> getNextReservationsWithoutCheckin() {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        for (Reservation reservation: this.getNextReservations()) {
            if (!reservation.hasCheckIn()){
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    /**
     * Add a new reservation to the reservation array of a Client
     * 
     *@param reservation the reservation that we are adding  
     */
    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
    }

    /**
     * Gets the client's next reservations array
     * 
     * @return an array of all the future reservations
     */
    public ArrayList<Reservation> getNextReservations() {
        ArrayList<Reservation> nextReservations = new ArrayList<Reservation>();
        for (Reservation reservation: this.reservations) {
            if (reservation.getFlight().getDepartureTime().isAfter(LocalDateTime.now())) {
                nextReservations.add(reservation);
            }
        }
        return nextReservations;
    }

    /**
     * Gets the client's next flights array
     * 
     * @return the array of all future flights
     */
    @Override
    public ArrayList<Flight> getNextFlights() {
        ArrayList<Flight> nextFlights = new ArrayList<Flight>();
        for (Reservation reservation: getNextReservations()) {
            nextFlights.add(reservation.getFlight());
        }
        return nextFlights;
    }

    public ArrayList<Flight> getPreviousFlights() {
        ArrayList<Flight> previousFlights = new ArrayList<Flight>();
        for (Reservation reservation: getPreviousReservations()) {
            if (reservation.getFlight().getDepartureTime().isBefore(LocalDateTime.now())) {
                previousFlights.add(reservation.getFlight());
            }
        }
        return previousFlights;
    }

    /**
     * Gets the array of all previous reservations of the client
     * 
     * @return an array of previous reservations
     */
    public ArrayList<Reservation> getPreviousReservations() {
        ArrayList<Reservation> previousReservations = new ArrayList<Reservation>();
        for (Reservation reservation: this.reservations) {
            if (reservation.getFlight().getDepartureTime().isBefore(LocalDateTime.now())) {
                previousReservations.add(reservation);
            }
        }
        return previousReservations;
    }
    
    /**
     * Gets the total duration of all the previous flights 
     * 
     * @return total duration
     */
    @Override
    public Duration getTotalFlightsDuration() {
        Duration totalDuration = Duration.ofSeconds(0);
        for (Reservation reservation : this.getPreviousReservations()) {
            totalDuration.plus(reservation.getFlight().getDuration());
        }
        return totalDuration;
    }
}
