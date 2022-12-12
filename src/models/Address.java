package src.models;

import java.util.ArrayList;

/**
 * Class who represents the address and localisation of an Airport
 * An address is defined by its country, city, postal code, latitude and longitude
 */
public class Address {
    private String city;
    private String postalCode;
    private double latitude;
    private double longitude;

    /**
     * Constructor of an Address by instantiating every attribute
     * 
     * @param cityName name of the city
     * @param postCode postal code of the city
     * @param lat latitude of the city
     * @param longitude longitude of the city  
     *
     */
    public Address(String cityName, String postCode, double lat, double longitude){
        this.city = cityName;
        this.postalCode = postCode;
        this.latitude = lat;
        this.longitude = longitude;
    }

    /**
     * Gets the city of an Address
     * 
     * @return this city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Gets the postal code of an Address
     * 
     * @return this postalCode
     */
    public String getPostalCode() {
        return this.postalCode;
    }

    /**
     * Gets the latitude of an Address
     * 
     * @return this latitude
     */
    public double getLatitude(){
        return this.latitude;
    }

    /**
     * Gets the longitude of an Address
     * 
     * @return this longitude
     */
    public double getLongitutude(){
        return this.longitude;
    }

    /** 
    * Method that converts to a string every information concerning an Address
    *
    * @return a string with the information of every attribute of this address
    */
    @Override 
    public String toString() {
        return "\nCity: " + this.city + ", Postal Code: " + this.postalCode;
    }

    /** 
    * Method that gets an Address in an array by its postal code
    *
    * @param postalCode the postal code of the address we're looking for
    * @param addresses the array we are looking in
    *
    * @return the first address with this postal code in the array
    *
    * @throws Exception if this method could not find an address with the given postal code
    */
    public static Address getAddressByPostalCode(String postalCode, ArrayList<Address> addresses) throws Exception {
        for (Address address : addresses) {
            if (address.postalCode.equals(postalCode)) {
                return address;
            }
        }
        throw new Exception("Address not found");
    }
}
