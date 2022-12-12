package src.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class who represents a Crew Member, that inherits from Person
 * A Crew Member is defined by Person's attributes, and an id, hiring date, 
 * a string that describes his job and  an array of every flight he made
 */
public class CrewMember extends Person {
    private LocalDate hiringDate;
    private String jobDescription;
    private ArrayList<Flight> flights;

     /**
     * Constructor of a CrewMember from its attributes except the flight array
     * The constructor only initializes the Flight array
     *
     * @param fname first name of the crew member
     * @param lname last name of the crew memeber
     * @param phoneNum phone number of the crew member
     * @param email email of the crew member
     * @param dBirth birth date of the crew member
     * @param id identifiant of the crew member
     * @param hDate hiring date of the crew memnber
     * @param jobDesc job description of the crew member
     */
    public CrewMember(String fname, String lname, String phoneNum, String email, LocalDate dBirth, int id, LocalDate hDate, String jobDesc) {
        super(fname, lname, phoneNum, email, dBirth);
        this.id = id;
        this.hiringDate = hDate;
        this.jobDescription = jobDesc;
        this.flights = new ArrayList<Flight>();
    }


    /**
     * Getter of this Crew Member Hiring Date
     * 
     * @return this CrewMember hiringDate
     */
    public LocalDate getHiringDate() {
        return this.hiringDate;
    }

    /**
     * Getter of this Crew Member job description
     * 
     * @return this CrewMember job description
     */
    public String getJobDescription() {
        return this.jobDescription;
    }

    /**
     * Setter of this Crew Member hiring date
     * 
     * @param hiringDate the hiringDate that will be set to this
     */
    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    /**
     * Setter of this Crew Member job description
     * 
     * @param jobDesc the job description that will be set to this
     */
    public void setJobDescription(String jobDesc) {
        this.jobDescription = jobDesc;
    }

    /**
     * Getter of this Crew Member Flights array
     * 
     * @return thisCrewMemnber Flight array
     */
    public ArrayList<Flight> getFlights() {
        return this.flights;
    }

    /**
     * Adds a flight to the crew member's flights array
     * 
     * @param flight the Flight that we are adding
     */
    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    /**
     * Removes a flight to the crew member's flights array
     * 
     * @param flight the Flight that we are removing
     */
    public void removeFlight(Flight flight) {
        this.flights.remove(flight);
    }

    /**
     * Gets the crew member's next flights
     * 
     * @return the array of the crew member's next flights
     */
    @Override
    public ArrayList<Flight> getNextFlights() {
        ArrayList<Flight> nextFlights = new ArrayList<Flight>();
        for (Flight flight: this.flights) {
            if (flight.getDepartureTime().isAfter(LocalDateTime.now())) {
                nextFlights.add(flight);
            }
        }
        return nextFlights;
    }

    /**
     * Getter of all the previous flights made by this Crew Member
     * 
     * @return an array of all the previous flights
     */
    @Override
    public ArrayList<Flight> getPreviousFlights() {
        ArrayList<Flight> nextFlights = new ArrayList<Flight>();
        for (Flight flight: this.flights) {
            if (flight.getDepartureTime().isBefore(LocalDateTime.now())) {
                nextFlights.add(flight);
            }
        }
        return nextFlights;
    }

    /**
     * Gets the total duration of all the previous flights 
     * 
     * @return total duration
     */
    @Override
    public Duration getTotalFlightsDuration() {
        Duration totalDuration = Duration.ofSeconds(0);
        for (Flight flight : this.flights) {
            if (flight.getDepartureTime().compareTo(LocalDateTime.now()) < 0) {
                totalDuration.plus(flight.getDuration());
            }
        }
        return totalDuration;
    }

    /**
     * Method that converts all the informations of this Crew Member
     * 
     * @return an array of all the available means of transport
     */
    @Override
    public String toString() {
        return   "\n\nId: " + this.id
                +"\nFirst name: " + this.firstName
                +"\nLast name: " + this.lastName
                +"\nPhone number: " + this.phoneNumber
                +"\nEmail address: " + this.emailAddress
                +"\nHiring date: " + this.hiringDate
                +"\nJob description: " + this.jobDescription;
    }
}
