package src.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

import src.controllers.Controller;
/**
 * Abstract class who represents a Mean of Transport
 * A Mean of Transport is defined by its name, maximum amount of seats,
 * an array of the flights where it is used, an array of its Seats, its annual
 * fuel consumption and emission factor, the maximum amount of checked and hand
 * luggages per client, its maximum speed and an array of its Flight Classes.
 */
public abstract class MeanTransport {
    protected int id;
    protected String name;
    protected int nbSeats;
    protected ArrayList<Flight> flights;
    protected ArrayList<Seat> seats;
    protected double annualFuelConsumption;
    protected double emissionFactor;
    protected int speedMax;
    protected ArrayList<FlightClass> flightClasses;
    protected ArrayList<Integer> nbSeatsPerFlightClass;

    /**
     * Constructor of a MeansTransport from its id, name, maximum amount of seats,
     * annual fuel consumption, emission factor and maximum speed
     * 
     * @param id                         the id of the MeansTransport
     * @param name                       the name of the MeansTransport
     * @param maxNbSeats                 the maximum amount of seats of the
     *                                   MeansTransport
     * @param annualFuelConsumption      the annual fuel consumption of the
     *                                   MeansTransport
     * @param emissionFactor             the emission factor of the MeansTransport
     * @param speedMax                   the maximum speed of the MeansTransport
     */
    public MeanTransport(int id, String name, int maxNbSeats, double annualFuelConsumption, double emissionFactor,
            int speedMax) {
        this.id = id;
        this.name = name;
        this.nbSeats = maxNbSeats;
        this.annualFuelConsumption = annualFuelConsumption;
        this.emissionFactor = emissionFactor;
        this.speedMax = speedMax;
        this.flights = new ArrayList<Flight>();
        this.seats = new ArrayList<Seat>();
        this.flightClasses = new ArrayList<FlightClass>();
        this.nbSeatsPerFlightClass = new ArrayList<Integer>();
    }

    /**
     * Constructor of a MeansTransport from its id, name, maximum amount of seats,
     * annual fuel consumption, emission factor and maximum speed
     * 
     * @param id                         the id of the MeansTransport
     * @param name                       the name of the MeansTransport
     * @param maxNbSeats                 the maximum amount of seats of the
     *                                   MeansTransport
     * @param annualFuelConsumption      the annual fuel consumption of the
     *                                   MeansTransport
     * @param emissionFactor             the emission factor of the MeansTransport
     * @param speedMax                   the maximum speed of the MeansTransport
     * @param flightClasses              the array of this Means of Transport FlightClasses
     * @param nbSeatsPerFlightClass      the corresponding array of the amount of seats per flight class
     */
    public MeanTransport(int id, String name, int maxNbSeats, double annualFuelConsumption, double emissionFactor, int speedMax, ArrayList<FlightClass> flightClasses, ArrayList<Integer> nbSeatsPerFlightClass) {
        this.id = id;
        this.name = name;
        this.nbSeats = maxNbSeats;
        this.annualFuelConsumption = annualFuelConsumption;
        this.emissionFactor = emissionFactor;
        this.speedMax = speedMax;
        this.flightClasses = flightClasses;
        this.nbSeatsPerFlightClass = nbSeatsPerFlightClass;
        this.flights = new ArrayList<Flight>();
        this.seats = new ArrayList<Seat>();
    }

    /**
     * Sets the id of a mean of transport
     * 
     * @param id the id that will be set to this
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the id of a mean of transport
     * 
     * @return this id
     */
    public int getId() {
        return this.id;
    }

    public abstract String getType();

    /**
     * Gets the annual fuel consuption of a MeansTransport
     * 
     * @return this annualFuelConsumption
     */
    public double getAnnualFuelConsumption() {
        return this.annualFuelConsumption;
    }

    /**
     * Sets the annual fuel consumption of a MeansTransport
     * 
     * @param annualFuelConsumption the annual fuel consumption that will be set to
     *                             this
     */
    public void setConsuption(double annualFuelConsumption) {
        this.annualFuelConsumption = annualFuelConsumption;
    }

    /**
     * Gets the emission factor of a MeansTransport
     * 
     * @return this emissionFactor
     */
    public double getEmissionFactor() {
        return this.emissionFactor;
    }

    /**
     * Sets the emission factor of a MeansTransport
     * 
     * @param emissionFactor the emission factor that will be set to this
     */
    public void setEmissionFactor(double emissionFactor) {
        this.emissionFactor = emissionFactor;
    }

    /**
     * Gets the name of a MeansTransport
     * 
     * @return this name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of a MeansTransport
     * 
     * @param name the name that will be set to this
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the maximum amount of seats of a MeansTransport
     * 
     * @return this maxNbSeats
     */
    public int getNbSeats() {
        return this.nbSeats;
    }

    /**
     * Sets the maximum amount of seats of a MeansTransport
     * 
     * @param nbSeats the nbSeats that will be set to this
     */
    public void setNbSeats(int nbSeats) {
        this.nbSeats = nbSeats;
    }

    /**
     * Gets the flights array of a MeansTransport
     * 
     * @return this flights array
     */
    public ArrayList<Flight> getFlights() {
        return this.flights;
    }

    /**
     * Gets the seat array of a MeansTransport
     * 
     * @return this seats array
     */
    public ArrayList<Seat> getSeats() {
        return this.seats;
    }

    /**
     * Gets the maximum speed of a MeansTransport
     * 
     * @return this speedMax
     */
    public int getspeedMax() {
        return this.speedMax;
    }

    /**
     * Sets the maximum speed of a MeansTransport
     * 
     * @param speedMax the maximum speed that will be set to this
     */
    public void setSpeedMax(int speedMax) {
        this.speedMax = speedMax;
    }

    /**
     * Method that adds a flight to this mean of transport flight array
     * 
     * @param flight the flight we want to add
     *
     * @throws Exception if the method cannot add a flight because the mean of transport used is unavailable
     */
    public void addFlight(Flight flight) throws Exception {
        if (!this.isAvailable(flight.getDepartureTime(), flight.getArrivalTime())) {
            throw new Exception("Mean of transport in unavailable!");
        } else {
            this.flights.add(flight);
        }
    }

    /**
     * Method that checks if this mean of transport is available for a given time interval
     * 
     * @param depTime the departure time of the interval
     * @param arrTime the arrival time of the interval
     *
     * @return false if there is one flight that intersects with the given interval
     */
    public boolean isAvailable(LocalDateTime depTime, LocalDateTime arrTime) {
        for (Flight flight: this.flights) {
            if (Controller.intersectsTime(flight.getDepartureTime(), flight.getArrivalTime(), depTime, arrTime)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Getter of this Flight Class array
     * 
     * @return this flight class array
     */
    public ArrayList<FlightClass> getFlightClasses() {
        return flightClasses;
    }

    /**
     * Gets a FlightClass from the FlightClass array of this using
     * the name of the class
     * 
     * @param name the name of the flight class that we want to get
     * @return the flight class that matches the name passed as parameter or null if
     *         there isn't any
     *
     * @throws Exception if the method cannot find any flight class with the given name
     */
    public FlightClass getFlightClassByName(String name) throws Exception {
        for (FlightClass flightClass : flightClasses) {
            if (flightClass.getDescription().equals(name)) {
                return flightClass;
            }
        }
        throw new Exception("Flight class not found!");
    }

    /**
     * Gets a FlightClass from the FlightClass array of this using
     * its index in the array
     * 
     * @param index the index in the array of the flight class that we want to get
     * @return the flight class that matches the index passed as parameter,
     *         or null if the index has wrong values (negative or superior to array size)
     */
    public FlightClass getFlightClassByIndex(int index) {
        if (index >= this.flightClasses.size()) {
            return null;
        }
        else {
            return this.flightClasses.get(index);
        }
    }

    /**
     * Gets a FlightClass index from the FlightClass array of this using
     * its name
     * 
     * @param name the name of the flight class of the searched index
     * @return the index that matches the name passed as parameter
     *
     * @throws Exception if the method catches an Exception throwed by the called methods
     */ 
    public int getFlightClassIndex(String name) throws Exception {
        try {
            return this.flightClasses.indexOf(getFlightClassByName(name));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Add a flight class to this Airplane FlightClass array
     *
     * @param flightClass the flight class that we want to add
     */
    public void addFlightClass(FlightClass flightClass) {
        this.flightClasses.add(flightClass);
    }

    /**
     * Modify the coefficient of a FlightClass of the Airplane
     * 
     * @param flightClass the flight class we want to modify its coefficient
     * @param coef        the new coefficient
     */
    public void modifyFlightClassCoef(FlightClass flightClass, double coef) {
        flightClass.setCoef(coef);
    }

    /**
     * Method that returns a string of a luggage type that corresponds to an index
     * 
     * @param index the index of the luggage type
     * @return a string that describes the luggage type
     */
    public String getLuggageTypeByIndex(int index) {
        switch (index) {
            case 1:
                return "Hand Luggage";
            case 2:
                return "Checked Luggage";
        }
        return null;
    }

    /**
     * Gets a Seat array of every Seat of a corresponding FlightClass
     * 
     * @param flightClass the flight class of the seats we are looking for
     * @return an array of every seat of the corresponding flight class
     */
    public ArrayList<Seat> getSeatsByFlightClass(FlightClass flightClass) {
        ArrayList<Seat> seatsList = new ArrayList<Seat>();
        for (Seat seat : this.seats) {
            if (seat.getFlightClass().equals(flightClass)) {
                seatsList.add(seat);
            }
        }
        return seatsList;
    }

    /**
     * Gets a Seat array of every Seat of a corresponding FlightClass
     * 
     * @param name the name of the flight class of the seats we are looking for
     * @return an array of every seat of the corresponding flight class name
     *
     * @throws Exception if the method catches an Exception throwed by the called methods
     */
    public ArrayList<Seat> getSeatsByFlightClassName(String name) throws Exception {
        try {
            return this.getSeatsByFlightClass(this.getFlightClassByName(name));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Gets the amount of Seats for a corresponding FlightClass name
     * 
     * @param name the name of the flight class of the seats we are looking for
     * @return the amount of seats of this flight class name
     *
     * @throws Exception if the method catches an Exception throwed by the called methods
     */
    public int getNumberOfSeatsByFlightClassName(String name) throws Exception {
        try {
            return this.getSeatsByFlightClassName(name).size();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Gets the amount of Seats for a corresponding FlightClass
     * 
     * @param flightClass the flight class of the seats we are looking for
     * @return the amount of seats of this flight class
     */
    public int getNumberOfSeatsByFlightClass(FlightClass flightClass) {
        return this.getSeatsByFlightClass(flightClass).size();
    }

    /**
     * Method that returns a string containing every information of this Mean of Transport
     * 
     * @return a string with every information about this mean of transport 
     */
    @Override
    public String toString() {
        return  "\n\nid: " + this.id 
                +"\ntype: " + this.getType() 
                +"\nname: " + this.name
                +"\nnumber of seats: " + this.nbSeats
                +"\nC02 emission factor: " + this.emissionFactor
                +"\nAnnual fuel consuption: " + this.annualFuelConsumption
                +"\nmax speed: " + this.speedMax
                +"\nflight classes:" + this.flightClasses;
    }

    /**
     * Method that gets the number of seats of this mean of transport not assigned to a flight class
     * 
     * @return the amount of seats unassigned
     */
    public int getNbSeatsWithoutFlightClass() {
        int nbSeatsWithoutFlightClass = this.nbSeats;
        for (int i : nbSeatsPerFlightClass) {
            nbSeatsWithoutFlightClass -= i;
        }
        return nbSeatsWithoutFlightClass;
    }

    /**
     * Method that adds the number of seats unassigned to a flight class
     * 
     * @param newFlightClassNbSeats the amount of seats that can be added
     *
     * @throws Exception if the method cannot get the number of seats
     */
    public void addNbSeatsPerFlightClass(int newFlightClassNbSeats, FlightClass flightClass) throws Exception {
        if (this.getNbSeatsWithoutFlightClass() - newFlightClassNbSeats < 0) {
            throw new Exception("Could not find this number of seats for this flight class");
        }
        for(int i = 1; i <= newFlightClassNbSeats; i++){
            seats.add(new Seat(seats.size()+1, flightClass, this));
        }
        nbSeatsPerFlightClass.add(newFlightClassNbSeats);
    }
}
