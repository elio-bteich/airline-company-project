package src.models;

import java.util.ArrayList;
/**
 * Class who represents an Airplane, that inherits from MeanTransport
 * An Airplane is defined by MeanTransport attributes, maximum amout of luggages per type and local methods
 */
public class Airplane extends MeanTransport{
    protected static int maxHandLuggagePerClient = 1;
    protected static int maxCheckedLuggagePerClient = 3;
    protected static int pricePerHandLuggage = 10;
    protected static int pricePerCheckedLuggage = 25;
    
    /**
     * Constructor of an Airplane from many of its attributes
     * 
     * @param id the plane id
     * @param name name of the plane
     * @param maxNbSeats maximum number of seats
     * @param annualFuelConsumption annual consumption of the plane
     * @param emissionFactor emission factor of the plane
     * @param speedMax maximum speed of the plane
     * @param flightClasses the array of plane's flight classes
     * @param nbSeatsPerFlightClass the maximum amount of Seats per Flight Class
     */
    public Airplane(int id, String name, int maxNbSeats, double annualFuelConsumption, double emissionFactor, int speedMax, ArrayList<FlightClass> flightClasses, ArrayList<Integer> nbSeatsPerFlightClass) {
        super(id, name, maxNbSeats, annualFuelConsumption, emissionFactor, speedMax, flightClasses, nbSeatsPerFlightClass);
    }

    /**
     * Second constructor of an Airplane, with the same attributes except its arrays
     * 
     * @param id id of the airplane 
     * @param name name of the plane
     * @param maxNbSeats maximum number of seats
     * @param annualFuelConsumption annual consumption of the plane
     * @param emissionFactor emission factor of the plane
     * @param speedMax maximum speed of the plane
     */
    public Airplane(int id, String name, int maxNbSeats, double annualFuelConsumption, double emissionFactor, int speedMax) {
        super(id, name, maxNbSeats, annualFuelConsumption, emissionFactor, speedMax);
    }

    /** 
    * Getter of the maximum checked luggage amount per Client
    *
    * @return this maxCheckedLuggagePerClient
    */
    public static int getMaxCheckedLuggagePerClient() {
        return maxCheckedLuggagePerClient;
    }

    /** 
    * Getter of the maximum Hand luggage amount per Client
    *
    * @return this maxHandLuggagePerClient
    */
    public static int getMaxHandLuggagePerClient() {
        return maxHandLuggagePerClient;
    }

    /** 
    * Setter of the maximum hand luggage amount per Client
    *
    * @param maxHandLuggagePerClient the new maximum Hand Luggage amount Per Client
    */
    public static void setMaxHandLuggagePerClient(int maxHandLuggagePerClient) {
        Airplane.maxHandLuggagePerClient = maxHandLuggagePerClient;
    }

    /** 
    * Setter of the maximum checked luggage amount per Client
    *
    * @param maxCheckedLuggagePerClient the new maximum CheckedLuggage amount PerClient
    */
    public static void setMaxCheckedLuggagePerClient(int maxCheckedLuggagePerClient) {
        Airplane.maxCheckedLuggagePerClient = maxCheckedLuggagePerClient;
    }

    /**
     * Add a flight to the Airplane flights array
     * 
     * @param flight the flight we want to add
     */
    public void addFlight(Flight flight) {
        getFlights().add(flight);
    }

    /**
     * Remove a flight from the Airplane flights array
     * 
     * @param flight the flight we want to remove
     */
    public void removeFlight(Flight flight) {
        getFlights().remove(flight);
    }

    public static int getPricePerCheckedLuggage() {
        return pricePerCheckedLuggage;
    }

    public static int getPricePerHandLuggage() {
        return pricePerHandLuggage;
    }

    public static void setPricePerCheckedLuggage(int pricePerCheckedLuggage) {
        Airplane.pricePerCheckedLuggage = pricePerCheckedLuggage;
    }

    public static void setPricePerHandLuggage(int pricePerHandLuggage) {
        Airplane.pricePerHandLuggage = pricePerHandLuggage;
    }

    /**
     * Method that returns the name of this mean of transport
     * 
     *@return the string containing "Airplane"
     */
    @Override
    public String getType() {
        return "Airplane";
    }
}
