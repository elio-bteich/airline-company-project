package src.models;

import java.util.ArrayList;
/**
 * Class who represents an Airport
 * An Airport is defined by its full name, 3 characters code and Address
 */
public class Airport {
    private String name;
    private String code;
    private Address address;

    /**
     * Constructor of an Airport from its attributes
     * 
     * @param aName name of the Airport
     * @param aCode code of the Airport
     * @param aAddress address of the Airport
     */
    public Airport(String aName, String aCode, Address aAddress) {
        this.name = aName;
        this.code = aCode;
        this.address = aAddress;
    }

    //Setters :

    /**
     * Sets the name of an Airport
     * 
     * @param name the name that will be set to this
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the 3 characters code of an Airport
     * 
     * @param code the code that will be set to this
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Sets the Adress of an Airport
     * 
     * @param address the address that will be set to this
     */
    public void setAddress (Address address) {
        this.address = address;
    }

    //Getters :
    
    /**
     * Gets the name of an Airport
     * 
     * @return this name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the 3 characters code of an Airport
     * 
     * @return this code
     */
    public String getCode() {
        return this.code;
    }
    
    /**
     * Gets the Address of an Airport
     * 
     * @return this Address
     */
    public Address getAddress() {
        return this.address;
    }

    /**
     * Gets an Airport from a given array by looking for its code
     * 
     * @param code the code of the airport we're looking for
     * @param airports the array of airports we're looking in
     *
     * @return the airport that has the given code
     *
     * @throws Exception if the method cannot find an airport with the given code
     */
    public static Airport getAirportByCode(String code, ArrayList<Airport> airports) throws Exception {
        for (Airport airport: airports) {
            if (airport.getCode().equals(code)) {
                return airport;
            }
        }
        throw new Exception("Airport not found!");
    }

    /**
     * Method that converts every information of this airport to a string
     * 
     * @return the string with all the informations
     */
    @Override
    public String toString() {
        return "name: " + this.name + ", code: " + this.code + ", city: " + this.address.getCity();
    }

    /**
     * Method that displays every Airport from a given Airport array
     * 
     * @param airports the array of the airports we want to print
     */
    public static void displayList(ArrayList<Airport> airports) {
        for (Airport airport: airports) {
            System.out.println(airport);
        }
    }
}
