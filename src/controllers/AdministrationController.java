package src.controllers;

import src.views.AdministrationView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import src.models.*;
import src.Main;
/**
 * Class who represents the Controller of the Administration, that inherits from the Controller Class.
 * An AdministrationController has the Controller Main attribute and an AdministrationView.
 */
public class AdministrationController extends Controller {
    private AdministrationView view;

    /**
    * Constructor of an AdministrationController from a Main application
    *
    * This also initializes the view
    *
    * @param app the Main application that initializes the controller
    */
    public AdministrationController(Main app) {
        super(app);
        this.view = new AdministrationView(app, this);
    }

    /**
    * Method that calls the corresponding menu from this view
    */
    public void menu() {
        this.view.menu();
    }

    /**
    * Method that calls the createFlight method of this view
    */
    public void createFlightView() {
        this.view.createFlight();
    }

    /**
     * Method that creates the flight object from its attributes and adds it to the Company flights array
     * 
     * @param departureTime the departure time of the flight
     * @param arrivalTime   the arrival time of the flight
     * @param basePrice     the base price of the flight
     * @param meanTransport the mean of transport associated with the flight
     * @param departure     the departure airport of the flight
     * @param arrival       the arrival airport of the flight
     *
     *@throws Exception if the mean of transport chosen on the terminal is not available
     */
    public void createFlight(LocalDateTime departureTime, LocalDateTime arrivalTime, double basePrice,
            MeanTransport meanTransport, Airport departure, Airport arrival) throws Exception {
        int number = getUniqueFlightNumber();
        Flight flight = new Flight(number, departureTime, arrivalTime, basePrice, meanTransport, departure, arrival);
        try {
            flight.getMeanTransport().addFlight(flight);
            app.getCompany().addFlight(number-1, flight);
        } catch (Exception e) {
            throw new Exception("Mean of transport chosen is not available for this flight!");
        }
    }

    /**
     * Method that gets an unique unused flight number from the Company flights array
     *
     * @return an unused id in the Company flights array with the lowest possible value
     */
    public int getUniqueFlightNumber() {
        boolean idFound = false;
        int idx = 0;
        while (!idFound && idx < app.getCompany().getFlights().size()) {
            Flight flight = app.getCompany().getFlights().get(idx);
            if (flight.getNumber() != idx + 1) {
                idFound = true;
            } else {
                idx += 1;
            }
        }
        
        return idx+1;
    }

    /**
    * Method that calls the manageCrewMembers method from this view
    */
    public void manageCrewMembersView() {
        this.view.manageCrewMembers();
    }

    /**
    * Method that calls the showCrewMembers method from this view
    */
    public void showCrewMembersView() {
        this.view.showCrewMembers();
    }

    /**
    * Method that calls the assignCrewMember method from this view by passing the array of Company's next flights as parameter
    */
    public void assignCrewMemberToFlightView() {
        ArrayList<Flight> nextFlights = app.getCompany().getNextFlights();
        this.view.assignCrewMemberToFlight(nextFlights);
    }

    /**
    * Method that returns an array of every CrewMember available for a flight
    * 
    * @param flight the flight we want to add Crew Members in
    * 
    * @return the array of available Crew Members
    */
    public ArrayList<CrewMember> getAvailableCrewMembers(Flight flight) {
        ArrayList<CrewMember> availableCrewMembers = new ArrayList<CrewMember>();
        for (CrewMember crewMember: app.getCompany().getCrewMembers()) {
            if (isCrewMemberAvailable(crewMember, flight.getDepartureTime(), flight.getArrivalTime())) {
                availableCrewMembers.add(crewMember);
            }
        }
        return availableCrewMembers;
    }

    /**
    * Method that checks if a Crew Member is available in a given time interval
    * 
    * @param crewMember the Crew Member we want to check disponibity
    * @param depTime the departure time of the interval 
    * @param arrTime the arrival time of the interval 
    * 
    * @return true if the Crew Member is available, false if not
    */
    public boolean isCrewMemberAvailable(CrewMember crewMember, LocalDateTime depTime, LocalDateTime arrTime) {
        for (Flight flight: crewMember.getFlights()) {
            if (intersectsTime(flight.getDepartureTime(), flight.getArrivalTime(), depTime, arrTime)) {
                return false;
            }
        }
        return true;
    }

    /**
    * Method that calls the removeCrewMember method from this view
    */
    public void removeCrewMemberView() {
        this.view.removeCrewMember();
    }

    /**
    * Method that returns a Mean of Transport available for a date and time interval
    *
    * @param depTime the departure time of the interval
    * @param arrTime the arrival time of the interval
    * @return the choosen available MeanTransport
    *
    * @throws Exception if the chosen id is incorrect
    */
    public MeanTransport chooseMeanTransport(LocalDateTime depTime, LocalDateTime arrTime) throws Exception{
        int id = this.view.chooseMeanTransport(depTime, arrTime);
        try {
            return app.getCompany().getMeanTransportById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
    * Method that displays the manageFlights view
    */
    public void manageFlightsView() {
        this.view.manageFlights();
    }

    /**
    * Method that displays the nextFlights view
    */
    public void nextFlightsView() {
        ArrayList<Flight> nextFlights = app.getCompany().getNextFlights();
        this.view.nextFlights(nextFlights);
    }

    /**
    * Method that displays the flightsHistory view
    */
    public void flightsHistoryView() {
        ArrayList<Flight> previousFlights = app.getCompany().getPreviousFlights();
        this.view.flightsHistory(previousFlights);
    }

    /**
    * Method that searches every flight the Company scheduled and returns an array of every flight between a departure and arrival interval
    * 
    * @param departure the departure city
    * @param arrival the arrival city
    * 
    * @return the array of available flights
    */
    public ArrayList<Flight> searchFlights(String departure, String arrival) {
        return app.getCompany().searchFlights(departure, arrival);
    }

    /**
     * Changes the maximum amount of hand luggages for a client.
     * 
     * @param meansTransportType a string of the type of MeanTransport to use the
     *                           right methods
     * @param newMax             the new amount of hand luggages
     */
    public void modifyMaxHLugPerClient(String meansTransportType, int newMax) {
        switch (meansTransportType) {
            case "Airplane":
                Airplane.setMaxHandLuggagePerClient(newMax);
                break;
            case "Helicopter":
                Helicopter.setMaxHandLuggagePerClient(newMax);
                break;
        }
    }

    /**
     * Changes the maximum amount of checked luggages for a client.
     * 
     * @param meansTransportType a string of the type of MeanTransport to use the
     *                           right methods
     * @param newMax             the new amount of checked luggages
     */
    public void modifyMaxCLugPerClient(String meansTransportType, int newMax) {
        switch (meansTransportType) {
            case "Airplane":
                Airplane.setMaxCheckedLuggagePerClient(newMax);
                break;
            case "Helicopter":
                Helicopter.setMaxCheckedLuggagePerClient(newMax);
                break;
        }
    }

    /**
     * Calculates the ticket price for a specific flight
     * 
     * @param flight      the flight that we want to get its ticket price
     * @param flightClass the flight class of the flight that we want to get its
     *                    ticket price
     * @return the ticket price
     */
    public double calculateTicketPrice(Flight flight, FlightClass flightClass) {
        return flight.getBasePrice() * flightClass.getCoef();
    }

    /**
     * Creates a crew member and adds it to the company's crew members list
     * 
     * @param fName the first name of the crew member
     * @param lName the last name of the crew member
     * @param phoneNum the phone number of the crew member 
     * @param email the email address of the crew member 
     * @param dateBirth the date of birth of the crew member 
     * @param jobDesc the job description of the crew member 
     */
    public void createCrewMember(String fName, String lName, String phoneNum, String email,
    LocalDate dateBirth, String jobDesc) {
        int id = app.getCompany().getUniqueCrewMemberId();
        CrewMember member = new CrewMember(fName, lName, phoneNum, email, dateBirth, id, dateBirth, jobDesc);
        app.getCompany().addCrewMember(id-1, member);
    }

    /**
     * Creates a client and adds it to the company's client list
     * 
     * @param fName the first name of the client
     * @param lName the last name of the client
     * @param phoneNum the phone number of the client 
     * @param email the email address of the client 
     * @param dateBirth the date of birth of the client 
     */
    public void createClient(String fName, String lName, String phoneNum, String email,
    LocalDate dateBirth) {
        int id = app.getCompany().getUniqueClientId();
        Client client = new Client(fName, lName, phoneNum, email, dateBirth, id, dateBirth);
        app.getCompany().addClient(id-1, client);
    }

    /**
     * Method that adds a Crew Member to the view
     */
    public void addCrewMemberView() {
        this.view.addCrewMember();
    }

    /**
     * Method that adds a Mean of Transport to the view
     */
    public void addMeanTransportView() {
        this.view.addMeanTransport();
    }


    /**
     * Method that creates an Airplane in the view
     */
    public void createAirplaneView() {
        this.view.createAirplane();
    }

    /**
     * Method that creates an Helicopter in the view
     */
    public void createHelicopterView() {
        this.view.createHelicopter();
    }

    /**
     * First creator of the airplane object
     * 
     * @param name                  the airplane name
     * @param maxNbSeats            the maximum number of seats an airplane can have
     * @param annualFuelConsumption the airplane annual consumption
     * @param emissionFactor        the airplane emission factor
     * @param speedMax              the airplane maximum speed
     * @param flightClasses         the airplane array of flightClass
     * @param nbSeatsPerFlightClass the array of the amount of seats per Flight Class
     */
    public void createAirplane(String name, int maxNbSeats, double annualFuelConsumption, double emissionFactor,int speedMax, ArrayList<FlightClass> flightClasses, ArrayList<Integer> nbSeatsPerFlightClass) {
        int id = app.getCompany().getUniqueMeanTransportId();
        Airplane airplane = new Airplane(id, name, maxNbSeats, annualFuelConsumption, emissionFactor, speedMax, flightClasses, nbSeatsPerFlightClass);
        app.getCompany().addMeanTransport(id-1, airplane);
    }

    /**
     * Second creator of the airplane object
     * This method does not use the two arrays used by the first one, plus it returns the created airplane
     * 
     * @param name                  the airplane name
     * @param maxNbSeats            the maximum number of seats an airplane can have
     * @param annualFuelConsumption the airplane annual consumption
     * @param emissionFactor        the airplane emission factor
     * @param speedMax              the airplane maximum speed
     *
     * @return the created airplane
     */
    public Airplane createAirplane(String name, int maxNbSeats, double annualFuelConsumption, double emissionFactor,int speedMax) {
        int id = app.getCompany().getUniqueMeanTransportId();
        Airplane airplane = new Airplane(id, name, maxNbSeats, annualFuelConsumption, emissionFactor, speedMax);
        app.getCompany().addMeanTransport(id-1, airplane);
        return airplane;
    }

    /**
     * First creator of the helicopter object
     * 
     * @param name                  the helicopter name
     * @param maxNbSeats            the maximum number of seats an helicopter can
     *                              have
     * @param annualFuelConsumption the helicopter annual consumption
     * @param emissionFactor        the helicopter emission factor
     * @param speedMax              the helicopter maximum speed
     * @param flightClasses         the helicopter list of flightClass
     * @param nbSeatsPerFlightClass the array of the amount of seats per Flight Class
     */
    public void createHelicopter(String name, int maxNbSeats, double annualFuelConsumption, double emissionFactor,int speedMax, ArrayList<FlightClass> flightClasses, ArrayList<Integer> nbSeatsPerFlightClass) {
        int id = app.getCompany().getUniqueMeanTransportId();
        Helicopter helicopter = new Helicopter(id, name, maxNbSeats, annualFuelConsumption, emissionFactor, speedMax, flightClasses, nbSeatsPerFlightClass);
        app.getCompany().addMeanTransport(id-1, helicopter);
    }
    /**
     * Second creator of the airplane object
     * This method does not use the two arrays used by the first one, plus it returns the created helicopter
     * 
     * @param name                  the helicopter name
     * @param maxNbSeats            the maximum number of seats an helicopter can have
     * @param annualFuelConsumption the helicopter annual consumption
     * @param emissionFactor        the helicopter emission factor
     * @param speedMax              the helicopter maximum speed
     *
     * @return the created helicopter
     */
    public Helicopter createHelicopter(String name, int maxNbSeats, double annualFuelConsumption, double emissionFactor,int speedMax) {
        int id = app.getCompany().getUniqueMeanTransportId();
        Helicopter helicopter = new Helicopter(id, name, maxNbSeats, annualFuelConsumption, emissionFactor, speedMax);
        app.getCompany().addMeanTransport(id-1, helicopter);
        return helicopter;
    }

    /**
    * Method that calls the addFlightClasses method from this view by passing a mean of transport as parameter
    * @param meanTransport the mean of transport passed as parameter of the called method
    */
    public void addFlightClassesView(MeanTransport meanTransport) {
        this.view.addFlightClasses(meanTransport);
    }

    /**
    * Method that calls the manageMeansTransport method from this view
    */
    public void manageMeansTransportView() {
        this.view.manageMeansTransport();
    }

    /**
    * Method that calls the showMeansTransport method from this view
    */
    public void showMeansTransportView() {
        this.view.showMeansTransport();
    }

    /**
    * Method that calls the removeMeansTransport method from this view
    */
    public void removeMeanTransportView() {
        this.view.removeMeanTransport();
    }

    /**
    * Method that calls the deleteMeanTransport method from this Company by passing a Mean of transport as parameter
    * @param meanTransport the mean of transport passed as parameter of the second method
    */
    public void removeMeanTransport(MeanTransport meanTransport) {
        app.getCompany().deleteMeanTransport(meanTransport);
    }

    /**
    * Method that calls the removeCrewMember method from this Company by passing the given crew member as parameter
    * @param crewMember the crew member passed as parameter of the second method
    */
    public void removeCrewMember(CrewMember crewMember) {
        app.getCompany().deleteCrewMember(crewMember);
    }

    /**
    * Method that calls the modifyLuggageAllowance method from this view
    */
    public void modifyLuggageAllowanceView() {
        this.view.modifyLuggageAllowance();
    }

    /**
    * Method that calls the modifyAirplaneLuggageAllowance method from this view
    */
    public void modifyAirplaneLuggageAllowanceView() {
        this.view.modifyAirplaneLuggageAllowance();
    }

    /**
    * Method that calls the modifyHelicopterLuggageAllowance method from this view
    */
    public void modifyHelicopterLuggageAllowanceView() {
        this.view.modifyHelicopterLuggageAllowance();
    }

    public void modifyAirplaneLuggagePriceView() {
        this.view.modifyAirplaneLuggagePrice();
    }

    public void modifyHelicopterLuggagePriceView() {
        this.view.modifyHelicopterLuggagePrice();
    }

    public void modifyAirplaneLuggagePrice(int handPrice, int checkedPrice) {
        Airplane.setPricePerHandLuggage(handPrice);
        Airplane.setPricePerCheckedLuggage(checkedPrice);
    }

    public void modifyHelicopterLuggagePrice(int handPrice, int checkedPrice) {
        Helicopter.setPricePerHandLuggage(handPrice);
        Helicopter.setPricePerCheckedLuggage(checkedPrice);
    }

    /**
    * Method that modify the airplane maximum luggage allowance with given values
    *
    * @param handAllowance the new maximum hand luggage allowance
    * @param checkedAllowance the new maximum checked luggage allowance
    */
    public void modifyAirplaneLuggageAllowance(int handAllowance, int checkedAllowance) {
        Airplane.setMaxHandLuggagePerClient(handAllowance);
        Airplane.setMaxCheckedLuggagePerClient(checkedAllowance);
    }

    /**
    * Method that modify the helicopter maximum luggage allowance with given values
    *
    * @param handAllowance the new maximum hand luggage allowance
    * @param checkedAllowance the new maximum checked luggage allowance
    */
    public void modifyHelicopterLuggageAllowance(int handAllowance, int checkedAllowance) {
        Helicopter.setMaxHandLuggagePerClient(handAllowance);
        Helicopter.setMaxCheckedLuggagePerClient(checkedAllowance);
    }

    public void addFlightClassView() {
        MeanTransport meanTransport = this.view.selectMeanTransport();
        this.view.addFlightClasses(meanTransport);
    }

    public void modifyLuggagePriceView() {
        this.view.modifyLuggagePrice();
    }
}
