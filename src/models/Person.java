package src.models;

import java.util.ArrayList;
import java.time.Duration;
import java.time.LocalDate;
/**
 * Abstract class who represents a Person
 * A Person is defined by his first name, last name, phone number, email address
 * and date of birth.
 */
public abstract class Person {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String emailAddress;
    protected LocalDate dateOfBirth;

    /**
    * Constructor of a Person from his first name, last name, phone number, email address and date of birth
    * @param fname the first name of the Person
    * @param lname the last name of the Person
    * @param phoneNum the phone number of the Person
    * @param email the email address of the Person
    * @param dbirth the date of birth of the Person
    */
    protected Person(String fname, String lname, String phoneNum, String email, LocalDate dbirth){
        this.firstName = fname;
        this.lastName = lname;
        this.phoneNumber = phoneNum;
        this.emailAddress = email;
        this.dateOfBirth = dbirth;
    }

    /**
     * Sets the id of a Client
     * 
     * @param id the id that will be set to this
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the id of a Client
     * 
     * @return this id
     */
    public int getId() {
        return this.id;
    }

    /**
    * Gets the first name of a Person
    * 
    * @return this firstName
    */
    public String getFirstName(){
        return this.firstName;
    }

    /**
    * Gets the last name of a Person
    * 
    * @return this lastName
    */
    public String getLastName(){
        return this.lastName;
    }

    /**
    * Gets the phone number of a Person
    * 
    * @return this phoneNumber
    */
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    /**
    * Gets the email adress of a Person
    * 
    * @return this emailAdress
    */
    public String getEmailAddress(){
        return this.emailAddress;
    }

    /**
    * Gets the date of birth of a Person
    * 
    * @return this dateOfBirth
    */
    public LocalDate getDateOfBirth(){
        return this.dateOfBirth;
    }

    /**
    * Sets the first name of a MeansTransport
    * 
    * @param firstName the first name that will be set to this
    */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /**
    * Sets the last name of a Person
    * 
    * @param lastName thast name that will be set to this
    */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
    * Sets the phone number of a Person
    * 
    * @param phoneNumber the phone number that will be set to this
    */
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    /**
    * Sets the email address of a Person
    * 
    * @param emailAddress the email address that will be set to this
    */
    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }

    /**
    * Sets the date of birth of a Person
    * 
    * @param dateOfBirth the date of birth that will be set to this
    */
    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    /**
    * Abstract method that will be implemented inside subclasses
    *
    * @return the duration of every flight cumulated to others  
    */
    public abstract Duration getTotalFlightsDuration();

    /**
    * Abstract method that will be implemented inside subclasses
    *
    * @return an array of the future flights 
    */
    public abstract ArrayList<Flight> getNextFlights();
    
    /**
    * Abstract method that will be implemented inside subclasses
    *
    * @return an array of the previous flights 
    */
    public abstract ArrayList<Flight> getPreviousFlights();

    /**
    * Method that converts every information about a Person to a string
    *
    * @return the string that contains all the informations
    */
    @Override
    public String toString() {
        return   "\nFirst name: " + this.firstName
                +"\nLast name: " + this.lastName
                +"\nPhone number: " + this.phoneNumber
                +"\nEmail address: " + this.emailAddress
                +"\nDate of birth: " + this.dateOfBirth;
    }
}
