package src.controllers;

import src.models.*;
import src.Main;
import src.views.ClientView;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * Class who represents the Controller of the Client, that inherits from the Controller Class.
 * A ClientController has the Controller Main attribute and a ClientView.
 */

public class ClientController extends Controller{
    private ClientView view;

    /**
    * Constructor of a ClientController from a Main application
    *
    * This also initializes the view
    *
    * @param app the Main application that initializes the controller
    */
    public ClientController(Main app) {
        super(app);
        this.view = new ClientView(app, this);
    }

    /**
    * Method that checks if there's an authentified client, and if not, calls a method to log one in
    */
    public void authMiddleware() {
        if (app.getAuthClient() == null) {
            this.authMenuView();
        }
    }

    /**
    * Method that calls authMiddleware to be sure there's a client logged in, and then calls the menu() method from view
    */
    public void menu() {
        authMiddleware();
        this.view.menu();
    }

    /**
    * Method that calls the login() method from the view
    */
    public void loginView() {
        this.view.login();
    }

    public void authMenuView() {
        this.view.authMenu();
    }

    public void registerView() {
        this.view.register();
    }

    /**
    * Method that logs in a client with his given email address
    *
    * @param email the client email
    *
    * @throws Exception if the method could not find a CLient with the given email address
    */
    public void login(String email) throws Exception {
        try {
            Client client = app.getCompany().getClientByEmail(email);
            app.setAuthClient(client);
        } catch (Exception e) {
            throw new Exception("Client not found");
        }
    }

    public void register(String fname, String lname, String phoneNum, String email, LocalDate dbirth) {
        int id = app.getCompany().getUniqueClientId();
        Client client = new Client(fname, lname, phoneNum, email, dbirth, id, LocalDate.now());
        app.getCompany().addClient(id-1, client);
    }

    /**
    * Method that logs out the currently logged in client
    */
    public void logout() {
        app.clientLogout();
    }

    public void deleteAccountView() {
        this.view.deleteAccount();   
    }

    public void deleteAccount() {
        app.getCompany().deleteClient(app.getAuthClient());
        app.clientLogout();
    }

    /**
    * Method that calls the reserveFlight() method from the view
    *
    * @param flightsFound an array of flights
    */
    public void reserveFlightView(ArrayList<Flight> flightsFound) {
        this.view.reserveFlight(flightsFound);
    }

    /**
     * Method that creates a new reservation for the client, and adds it to the reservations arrays of this Client and reserved Flight
     * 
     * @param flight the reserved flight
     * @param luggages the array of scheduled luggages 
     * @param flightClass the flight class chosen for the flight
     *
     * @throws Exception in case of method access errors due to invalid authentified client, or wrong values
     */
    public void reserveFlight(Flight flight, ArrayList<Luggage> luggages, FlightClass flightClass) throws Exception {
        Reservation reservation = new Reservation(app.getCompany().getUniqueClientId(), flight, LocalDateTime.now(), app.getAuthClient(), luggages, flightClass);
        try {
            flight.addReservation(reservation);
            app.getAuthClient().addReservation(reservation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
    * Method that calls the searchFlight() method from the view
    */
    public void searchFlightView() {
        this.view.searchFlight();
    }


    public void manageReservationsView() {
        ArrayList<Reservation> nextReservations = app.getAuthClient().getNextReservations();
        this.view.manageReservations(nextReservations);
    }

    public void checkInView() {
        this.view.checkIn();
    }

    public void deleteReservation(Reservation reservation) {
        reservation.getFlight().removeReservation(reservation);
    }

    public void checkIn(Reservation reservation, Seat seat) {
        Checkin checkin = new Checkin(LocalDateTime.now(), reservation, seat);
        reservation.setCheckin(checkin);
        reservation.getFlight().addReservedSeat(seat);
    }

    public void deleteReservationView(ArrayList<Reservation> reservations) {
        this.view.deleteReservation(reservations);
    }

    /**
    * Method that calls the displayReservations() method from the view by passing the array of every past flight of this Client
    */
    public void previousReservationsView() {
        ArrayList<Reservation> previousReservations = app.getAuthClient().getPreviousReservations();
        this.view.displayReservations(previousReservations);
    }

    /**
    * Method that searches in the company flight array an available flight for given departure and arrival cities
    *
    * @param departure the departure city
    * @param arrival the arrival city
    *
    * @return an array of all the flights available for this interval
    */
    public ArrayList<Flight> searchFlights(String departure, String arrival) {
        return app.getCompany().searchFlights(departure, arrival);
    }

    /**
    * Method that calls the personalInformation() method from the view
    */
    public void personalInformationView() {
        this.view.personalInfomation();
    }

    /**
    * Method that allows the authentified client to change his first name
    *
    * @param newFirstName the new first name of the client
    */
    public void modifyFirstName(String newFirstName) {
        app.getAuthClient().setFirstName(newFirstName);
    }
    
    /**
    * Method that allows the authentified client to change his last name
    *
    * @param newLastName the new last name of the client
    */
    public void modifyLastName(String newLastName) {
        app.getAuthClient().setFirstName(newLastName);
    }

    /**
    * Method that allows the authentified client to change his phone number
    *
    * @param newPhoneNumber the new phone number of the client
    */
    public void modifyPhoneNumber(String newPhoneNumber) {
        app.getAuthClient().setFirstName(newPhoneNumber);
    }

    /**
    * Method that allows the authentified client to change his email address
    *
    * @param newEmailAddress the new email address of the client
    */
    public void modifyEmailAddress(String newEmailAddress) {
        app.getAuthClient().setFirstName(newEmailAddress);
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
