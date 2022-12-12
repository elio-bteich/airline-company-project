package src.models;

import java.util.ArrayList;
/**
 * Class who represents an Helicopter, that inherits from MeanTransport
 * An Helicopter is defined by MeanTransport attributes and local methods
 */
public class Helicopter  extends MeanTransport{
    protected static int maxHandLuggagePerClient = 1;
    protected static int maxCheckedLuggagePerClient = 1;
    protected static int pricePerHandLuggage = 20;
    protected static int pricePerCheckedLuggage = 50;

    /**
     * Constructor of an Helicopter from many of its attributes
     * 
     * @param id the id of the Helicopter
     * @param name name of the Helicopter
     * @param maxNbSeats maximum number of seats
     * @param annualFuelConsumption annual consumption of the Helicopter
     * @param emissionFactor emission factor of the Helicopter
     * @param speedMax maximum speed of the Helicopter
     */
    public Helicopter(int id, String name, int maxNbSeats, double annualFuelConsumption, double emissionFactor, int speedMax) {
        super(id, name, maxNbSeats, annualFuelConsumption, emissionFactor, speedMax);
    }

    /**
     * Second constructor of an Helicopter from many of its attributes
     * 
     * @param id the id of the Helicopter
     * @param name name of the Helicopter
     * @param maxNbSeats maximum number of seats
     * @param annualFuelConsumption annual consumption of the Helicopter
     * @param emissionFactor emission factor of the Helicopter
     * @param speedMax maximum speed of the Helicopter
     * @param flightClasses the array of FLight Classes of the Helicopter
     * @param nbSeatsPerFlightClass the array of the corresponding amount of seats per flight class
     */
    public Helicopter(int id, String name, int maxNbSeats, double annualFuelConsumption, double emissionFactor, int speedMax, ArrayList<FlightClass> flightClasses, ArrayList<Integer> nbSeatsPerFlightClass) {
        super(id, name, maxNbSeats, annualFuelConsumption, emissionFactor, speedMax, flightClasses, nbSeatsPerFlightClass);
    }

    /**
     * Getter of the maximum amount of Checked luggage per client in this Helicopter
     * 
     * @return this maxCheckedLuggagePerClient
     */
    public static int getMaxCheckedLuggagePerClient() {
        return maxCheckedLuggagePerClient;
    }

    /**
     * Getter of the maximum amount of Hand luggage per client in this Helicopter
     * 
     * @return this maxHandLuggagePerClient
     */
    public static int getMaxHandLuggagePerClient() {
        return maxHandLuggagePerClient;
    }

    /**
     * Setter of the maximum amount of Hand luggage per client in this Helicopter
     * 
     * @param maxHandLuggagePerClient the new maximum amout of Hand Luggage per Client
     */
    public static void setMaxHandLuggagePerClient(int maxHandLuggagePerClient) {
        Helicopter.maxHandLuggagePerClient = maxHandLuggagePerClient;
    }

    /**
     * Setter of the maximum amount of Checked luggage per client in this Helicopter
     * 
     * @param maxCheckedLuggagePerClient the new maximum amout of Checked Luggage per Client
     */
    public static void setMaxCheckedLuggagePerClient(int maxCheckedLuggagePerClient) {
        Helicopter.maxCheckedLuggagePerClient = maxCheckedLuggagePerClient;
    }

    public static int getPricePerCheckedLuggage() {
        return pricePerCheckedLuggage;
    }

    public static int getPricePerHandLuggage() {
        return pricePerHandLuggage;
    }

    public static void setPricePerCheckedLuggage(int pricePerCheckedLuggage) {
        Helicopter.pricePerCheckedLuggage = pricePerCheckedLuggage;
    }

    public static void setPricePerHandLuggage(int pricePerHandLuggage) {
        Helicopter.pricePerHandLuggage = pricePerHandLuggage;
    }

    /**
     * Method that returns a string of the type of mean of transport of this
     * 
     * @return the string containing "Helicopter"
     */
    @Override
    public String getType() {
        return "Helicopter";
    }
}
