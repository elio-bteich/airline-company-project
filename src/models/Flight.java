package src.models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class who represents a Flight
 * A Flight is defined by its number, departure and arrival date, base price,
 * mean of transport used, departure and arrival Airport, an array of every 
 * Client reservation for this flight,an array of reserved Seats and 
 * an array of CrewMembers who will be there during the flight
 */
public class Flight {
    private int number;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double basePrice;
    private MeanTransport meanTransport;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private ArrayList<Reservation> reservations;
    private ArrayList<Seat> reservedSeats;
    private ArrayList<CrewMember> crewMembers;


    /**
    * Constructor of a Flight from its attributes except the arrays
    * The constructor only initializes the arrays
    * 
    * @param number the number of the Flight
    * @param departureTime the date and time of departure of the Flight
    * @param arrivalTime the date and time of arrival of the Flight
    * @param basePrice the base price of the Flight
    * @param meanTransport the mean of transport used for the Flight
    * @param departureAirport the departure airport of the Flight
    * @param arrivalAirport the arrival airport of the Flight
    */
    public Flight(int number, LocalDateTime departureTime, LocalDateTime arrivalTime, double basePrice,
            MeanTransport meanTransport, Airport departureAirport, Airport arrivalAirport) {
        this.number = number;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.basePrice = basePrice;
        this.meanTransport = meanTransport;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.reservations = new ArrayList<Reservation>();
        this.reservedSeats = new ArrayList<Seat>();
        this.crewMembers = new ArrayList<CrewMember>();
    }

    /**
    * Gets the number of a Flight
    * 
    * @return this number
    */
    public int getNumber() {
        return this.number;
    }

    /**
    * Sets the number of a Flight
    * 
    * @param number the number that will be set to this
    */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
    * Gets the departure time of a Flight
    * 
    * @return this departureTime
    */
    public LocalDateTime getDepartureTime() {
        return this.departureTime;
    }

    /**
    * Sets the departure time of a Flight
    * 
    * @param departureTime the departure time that will be set to this
    */
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    /**
    * Gets the arrival time of a Flight
    * 
    * @return this arrivalTime
    */
    public LocalDateTime getArrivalTime() {
        return this.arrivalTime;
    }

    /**
    * Sets the arrival time of a Flight
    * 
    * @param arrivalTime the arrival time that will be set to this
    */
    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
    * Gets the base price of a Flight
    * 
    * @return this basePrice
    */
    public double getBasePrice() {
        return this.basePrice;
    }

    /**
    * Sets the base price of a Flight
    * 
    * @param basePrice the base price that will be set to this
    */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
    * Gets the mean of transport of a Flight
    * 
    * @return the flight's mean of transport
    */
    public MeanTransport getMeanTransport() {
        return this.meanTransport;
    }

    public double getPrice(FlightClass flightClass, ArrayList<Luggage> luggages) {
        double luggagesPrice = 0;
        if (this.meanTransport instanceof Airplane) {
            for (Luggage luggage : luggages) {
                if (luggage instanceof HandLuggage) {
                    luggagesPrice += Airplane.pricePerHandLuggage;       
                } else {
                    luggagesPrice += Airplane.pricePerCheckedLuggage;
                }
            }
        } else {
            for (Luggage luggage : luggages) {
                if (luggage instanceof HandLuggage) {
                    luggagesPrice += Helicopter.pricePerHandLuggage;       
                } else {
                    luggagesPrice += Helicopter.pricePerCheckedLuggage;
                }
            }
        }
        
        return this.basePrice * flightClass.getCoef() + luggagesPrice;
    }

    /**
    * Sets the mean of transport of the flight
    * 
    * @param meanTransport the mean of transport that will be set to the flight
    */
    public void setMeanTransport(MeanTransport meanTransport) {
        this.meanTransport = meanTransport;
    }

    /**
    * Gets the departure airport of a Flight
    * 
    * @return this departureAirport
    */
    public Airport getDepartureAirport() {
        return this.departureAirport;
    }

    /**
    * Sets the departure airport of a Flight
    * 
    * @param departureAirport the departure airport that will be set to this
    */
    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    /**
    * Gets the arrival airport of a Flight
    * 
    * @return this arrivalAirport
    */
    public Airport getArrivalAirport() {
        return this.arrivalAirport;
    }

    public ArrayList<Seat> getAvailableSeatsByFlightClass(FlightClass flightClass) {
        ArrayList<Seat> availableSeats = new ArrayList<Seat>();
        for (Seat seat: this.meanTransport.getSeats()) {
            if (seat.isAvailable(this) && seat.getFlightClass().equals(flightClass)) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    /**
    * Sets the arrival airport of a Flight
    * 
    * @param arrivalAirport the arrival airport that will be set to this
    */
    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    /**
    * Gets the Reservation array of a Flight
    * 
    * @return this reservations
    */
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    /**
    * Method that adds a new reservation to this FLight array if it's possible
    *
    * @param reservation the reservation we want to add
    *
    * @throws Exception if the chosen flight is already full
    */
    public void addReservation(Reservation reservation) throws Exception {
        // we have to verify that seats for a specific flight class aren't full
        if (this.isFlightFull()) {
            throw new Exception("Flight is full");
        }
        this.reservations.add(reservation);
    }

    public int getUniqueReservationId() {
        boolean idFound = false;
        int idx = 0;
        while (!idFound && idx < this.reservations.size()) {
            Reservation reservation = this.reservations.get(idx);
            if (reservation.getId() != idx + 1) {
                idFound = true;
            } else {
                idx += 1;
            }
        }
        
        return idx+1;
    }

    /**
    * Method that checks if this flight is already fully reserved
    * 
    * @return true if the size of the array is superior or equal 
    *         to the maximum amount of seat
    */
    public boolean isFlightFull() {
        return this.reservations.size() >= this.getMeanTransport().nbSeats; 
    }

    /**
    * Gets the CrewMember array of a Flight
    * 
    * @return this crewMembers
    */
    public ArrayList<CrewMember> getCrewMembers() {
        return this.crewMembers;
    }

    /**
    * Add a CrewMemnber to the crewMembers array
    *
    *  @param crewMember the crew member that we are adding
    */
    public void addCrewMember(CrewMember crewMember) {
        this.crewMembers.add(crewMember);
    }

    /**
    * Remove a Crew Member from the crewMembers array
    * 
    * @param crewMember the Crew Member that we want to remove
    */
    public void removeCrewMember(CrewMember crewMember) {
        this.crewMembers.remove(crewMember);
    }

    /**
    * Gets the reserved seats array of a Flight
    * 
    * @return this reservedSeats
    */
    public ArrayList<Seat> getReservedSeats() {
        return reservedSeats;
    }

    /**
    * Add a seat to the reservedSeats array
    *
    *  @param seat the seat that we are adding
    */
    public void addReservedSeat(Seat seat) {
        this.reservedSeats.add(seat);
    }

    public void removeReservation(Reservation reservation) {
        reservation.getClient().removeReservation(reservation);
        this.reservations.remove(reservation);
    }

    /**
     * Gets the flight's available seats
     * 
     * @return the list of the available seats
     */
    public ArrayList<Seat> getAvailableSeats() {
        ArrayList<Seat> availableSeats = new ArrayList<Seat>();
        for (Seat seat : this.meanTransport.getSeats()) {
            if (!this.reservedSeats.contains(seat)) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    /**
     * Gets the duration of the flight
     * 
     * @return the difference between the arrival time and the departure time
     */
    public Duration getDuration() {
        return Duration.between(departureTime, arrivalTime);
    }

    /**
     * Calculate the distance between the departure and the arrival aiports whith
     * latitudes and longitudes
     * 
     * @return the distance between the arrival and the departure
     */
    public double calculateDistance() {
        double latA, lonA, latB, lonB;
        latA = this.departureAirport.getAddress().getLatitude();
        lonA = this.departureAirport.getAddress().getLongitutude();
        latB = this.arrivalAirport.getAddress().getLatitude();
        lonB = this.arrivalAirport.getAddress().getLongitutude();
        double distance = 6371 * Math.acos(
                Math.sin(Math.toRadians(latB)) * Math.sin(Math.toRadians(latA)) + Math.cos(Math.toRadians(lonB - lonA))
                        * Math.cos(Math.toRadians(latB)) * Math.cos(Math.toRadians(latA)));
        return distance;
    }

    /**
     * Calculate the emission Co2 of the flight
     * 
     * @return the emission Co2 of the flight
     */
    public double calculateCo2Emission() {
        double emission = (calculateDistance() + this.meanTransport.getAnnualFuelConsumption())
                * meanTransport.getEmissionFactor();
        return emission;
    }

    /**
    * Method that converts the LocalDateTime type to a String
    * 
    * @param time the time we want to convert
    * 
    * @return the converted string
    */
    public static String timeToString(LocalDateTime time) {
        return time.getDayOfMonth() + "/" + time.getMonthValue() + "/" + time.getYear() + " at " + time.getHour() + ":"
                + time.getMinute();
    }

    /**
    * Methods that prints on the terminal all the informations of this Flight
    * 
    */
    @Override
    public String toString() {
        return  "{\n    Type: "+ this.getMeanTransport().getType() 
                +"\n    Number: " + this.number 
                +"\n    From: " + this.departureAirport.getAddress().getCity() 
                +"\n    To: " + this.arrivalAirport.getAddress().getCity()
                +"\n    Departure time: " + Flight.timeToString(departureTime) 
                +"\n    ArrivalTime: " + Flight.timeToString(arrivalTime) 
                +"\n    Duration: " + this.getDuration().toHours()+"h"+ this.getDuration().toMinutes()%60 + "min" 
                +"\n    Distance: " + this.calculateDistance()
                +"\n    CO2 emission: " + this.calculateCo2Emission()
                +"\n    Crew Members: " + this.crewMembers
                +"\n    Base price: " + this.basePrice + "$\n}";
    }
}
