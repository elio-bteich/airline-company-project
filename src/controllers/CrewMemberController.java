package src.controllers;

import src.models.*;
import src.Main;
import src.views.CrewMemberView;
import java.util.ArrayList;

/**
 * Class who represents the Controller of the CrewMember, that inherits from the Controller Class.
 * A CrewMemberController has the Controller Main attribute and a CrewMemberView.
 */
public class CrewMemberController extends Controller{
    private CrewMemberView view;

    /**
    * Constructor of a CrewMemberController from a Main application
    *
    * This also initializes the view
    *
    * @param app the Main application that initializes the controller
    */
    public CrewMemberController(Main app) {
        super(app);
        this.view = new CrewMemberView(app, this);
    }

    /**
    * Method that checks if there's an authentified crew member, and if not, calls a method to log one in
    */
    public void authMiddleware() {
        if (app.getAuthCrewMember() == null) {
            this.view.login();
        }
    }

    /**
    * Method that logs out the currently logged in crew member
    */
    public void logout() {
        app.crewMemberLogout();
    }

    /**
    * Method that calls authMiddleware to be sure there's a crew member logged in, and then calls the menu() method from view
    */
    public void menu() {
        authMiddleware();
        this.view.menu();
    }

    /**
    * Method that logs in a crew member with his given email address
    *
    * @param email the crew member email address
    *
    * @throws Exception if the method could not find a Crew Member for the given email address
    */
    public void login(String email) throws Exception {
        try {
            CrewMember crewMember = app.getCompany().getCrewMemberByEmail(email);
            app.setAuthCrewMember(crewMember);
        } catch (Exception e) {
            throw new Exception("Crew Member not found");
        }
    }

    /**
    * Method that calls the nextFlights() method from the view by passing as parameter the array of this crew member next flights
    */
    public void nextFlightsView() {
        ArrayList<Flight> nextFlights = app.getAuthCrewMember().getNextFlights();
        this.view.nextFlight(nextFlights);
    }

    /**
    * Method that calls the flightsHistory() method from the view by passing as parameter the array of this crew member previous flights
    */
    public void flightsHistoryView() {
        ArrayList<Flight> previousFlights = app.getAuthCrewMember().getPreviousFlights();
        this.view.flightsHistory(previousFlights);
    }

    /**
    * Method that calls the personalInformation() method from the view
    */
    public void personalInformationView() {
        this.view.personalInfomation();
    }

    /**
    * Method that allows the crew member to change his first name by the given one
    *
    * @param newFirstName the new crew member first name
    */
    public void modifyFirstName(String newFirstName) {
        app.getAuthCrewMember().setFirstName(newFirstName);
    }
    
    /**
    * Method that allows the crew member to change his last name by the given one
    *
    * @param newLastName the new crew member last name
    */
    public void modifyLastName(String newLastName) {
        app.getAuthCrewMember().setLastName(newLastName);
    }

    /**
    * Method that allows the crew member to change his phone number by the given one
    *
    * @param newPhoneNumber the new crew member phone number
    */
    public void modifyPhoneNumber(String newPhoneNumber) {
        app.getAuthCrewMember().setPhoneNumber(newPhoneNumber);
    }

    /**
    * Method that allows the crew member to change his email address by the given one
    *
    * @param newEmailAddress the new crew member email address
    */
    public void modifyEmailAddress(String newEmailAddress) {
        app.getAuthCrewMember().setEmailAddress(newEmailAddress);
    }

    /**
    * Method that calls the modifyFirstName() method from the view
    */
    public void modifyFirstNameView() {
        this.view.modifyFirstName();
    }

    /**
    * Method that calls the modifyLastName() method from the view
    */
    public void modifyLastNameView() {
        this.view.modifyLastName();
    }

    /**
    * Method that calls the modifyPhoneNumber() method from the view
    */
    public void modifyPhoneNumberView() {
        this.view.modifyPhoneNumber();
    }

    /**
    * Method that calls the modifyEmailAddress() method from the view
    */
    public void modifyEmailAddressView() {
        this.view.modifyEmailAddress();
    }
}
