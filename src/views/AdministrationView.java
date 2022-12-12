package src.views;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import src.controllers.AdministrationController;
import java.util.ArrayList;
import src.Main;
import src.models.*;

/**
 * Class who represents an AdministrationView, that inherits from View.
 * An AdministrationView is defined by the two attributes from View and the corresponding Controller
 */
public class AdministrationView extends View {

    private AdministrationController controller;

	/**
     * Constructor of an AdministrationView from an application and a controller
     * 
     * @param app the Main application
     * @param controller the AdministrationController
     */
    public AdministrationView(Main app, AdministrationController controller) {
        super(app);
        this.controller = controller;
    }

    /**
    * Method that displays a console menu and allows to chose an action to do 
    * According to the user choice on the terminal, this method will call different methods from the admin controller
    */
    public void menu() {
        clearConsole();
        System.out.println("\nSelect an option:");
        System.out.println("1- Manage flights");
        System.out.println("2- Manage crew members");
        System.out.println("3- Manage means of transport");
        System.out.println("4- Quit");
        System.out.print("Enter your option: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                this.controller.manageFlightsView();
                break;
            case 2:
                this.controller.manageCrewMembersView();
                break;
            case 3:
                this.controller.manageMeansTransportView();
                break;
            case 4:
                app.menu();
        }

        if (option < 1 || option > 4) {
            this.controller.menu();
        }
    }

    /**
    * Method that creates a flight with every attribute initialized by the user in the console
    *
    * @throws DateTimeParseException if the user does not respect the date format in the console
    */
    public void createFlight() {
        clearConsole();
        int duration;
        String departureDateString;
        double basePrice;
        String depAirportCode, arrAirportCode;
        Airport depAirport = null, arrAirport = null;
        LocalDateTime depTime = null;
        MeanTransport meanTransport = null;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
        System.out.println("Create a flight:");
        while (depTime == null) {
            try {
                System.out.print("Departure date (dd-mm-yyyy hh:mm): ");
                departureDateString = scanner.nextLine();
                depTime = LocalDateTime.parse(departureDateString, df);
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect date format!");
            }
        }
        
        System.out.print("Duration in minutes: ");
        duration = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Base price: ");
        basePrice = scanner.nextDouble();
        scanner.nextLine();


        
        LocalDateTime arrTime = depTime.plusMinutes(duration);

        while (meanTransport == null) {
            try {
                meanTransport = this.controller.chooseMeanTransport(depTime, arrTime);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        clearConsole();
        System.out.println("You have selected this mean of transport: ");
        System.out.println(meanTransport);
        System.out.println();
        // TODO: search airports by name

        System.out.println("Here's all the airports we have:\n");
        Airport.displayList(app.getAirports());
        System.out.println();

        while (depAirport == null) {
            try {
                System.out.print("Enter departure airport's code: ");
                depAirportCode = scanner.nextLine();
                depAirport = Airport.getAirportByCode(depAirportCode, app.getAirports());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\nYou have selected this airport for departure: ");
        System.out.println(depAirport);
        
        while (arrAirport == null) {
            try {
                System.out.print("\nEnter arrival airport's code: ");
                arrAirportCode = scanner.nextLine();
                arrAirport = Airport.getAirportByCode(arrAirportCode, app.getAirports());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\nYou have selected this airport for arrival: ");
        System.out.println(arrAirport);

        try {
            this.controller.createFlight(depTime, arrTime, basePrice, meanTransport, depAirport, arrAirport);
            clearConsole();
            System.out.println("Flight has been created successfully!");
        } catch (Exception e) {
            System.out.println("\n" + e.getMessage());
        }
        clickToExit();
    }

    /**
    * Method that shows on the interface every mean of transport available for a departure and arrival time.
    * Then the user may chose a mean of transport by its id
    * 
    * @param depTime the departure time
    * @param arrTime the arrival time
    *
    * @return the id choosen by the user
    */
    public int chooseMeanTransport(LocalDateTime depTime, LocalDateTime arrTime) {
        clearConsole();
        int id;
        this.displayMeanTransports(app.getCompany().getAvailableMeanTranports(depTime, arrTime));
        System.out.print("\nEnter the id of the mean of transport: ");
        id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    /**
    * Method that allow to manage flights
    * The user chooses on the console what action he wants to do, and calls the according method
    */
    public void manageFlights() {
        clearConsole();
        int option;
        System.out.println("1) Create a flight");
        System.out.println("2) Display next flights");
        System.out.println("3) Display flights history");
        System.out.println("4) Quit");
        System.out.print("Enter option: ");
        option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                this.controller.createFlightView();
                break;
            case 2:
                this.controller.nextFlightsView();
                break;
            case 3:
                this.controller.flightsHistoryView();
                break;
            case 4:
                this.controller.menu();
                break;
        }
        if (option < 1 || option > 4) {
            this.controller.manageFlightsView();
        }
    }

    /**
    * Method that displays each of the future flights
    * 
    * @param nextFlights an array of all the future flights
    */
    public void nextFlights(ArrayList<Flight> nextFlights) {
        clearConsole();
        System.out.println("\nNext Flights: ");
        displayFlights(nextFlights);
        clickToExit();
    }

    /**
    * Method that displays each of the past flights
    * 
    * @param flightsHistory an array of all the past flights
    */
    public void flightsHistory(ArrayList<Flight> flightsHistory) {
        clearConsole();
        System.out.println("\nFlights History: ");
        displayFlights(flightsHistory);
        clickToExit();
    }

    /**
    * Method that allow to manage crew members
    * The user chooses on the console what action he wants to do, and calls the according method
    */
    public void manageCrewMembers() {
        clearConsole();
        int option;
        System.out.println("Select an option:");
        System.out.println("1- Show crew members");
        System.out.println("2- Assign a crew member to a flight");
        System.out.println("3- Add a crew member");
        System.out.println("4- Remove a crew member");
        System.out.println("5- Quit");
        System.out.print("Enter option: ");
        option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                this.controller.showCrewMembersView();
                break;
            case 2:
                this.controller.assignCrewMemberToFlightView();
                break;
            case 3:
                this.controller.addCrewMemberView();
                break;
            case 4:
                this.controller.removeCrewMemberView();
                break;
            case 5:
                break;
        }

        if (option < 1 || option > 5) {
            this.controller.manageCrewMembersView();
        }
    }

    /**
    * Method that displays every crew member of the Company
    */
    public void showCrewMembers() {
        clearConsole();
        System.out.println("Crew Members:");
        displayCrewMembers(app.getCompany().getCrewMembers());
        clickToExit();
    }

    /**
    * Method that displays every crew member from an array of crew members
    *
    * @param crewMembers an array of crewMembers we want to print
    */
    public static void displayCrewMembers(ArrayList<CrewMember> crewMembers) {
        for (CrewMember crewMember: crewMembers) {
            System.out.println(crewMember);
        }
    }

    /**
    * Method that allows to choose a flight in which we want to add a crew member
    * 
    * @param flights an array of flights
    */
    public void assignCrewMemberToFlight(ArrayList<Flight> flights) {
        clearConsole();
        int flightNumber;
        char option;
        Flight flight = null;
        this.displayFlights(flights);
        while (flight == null) {
            try {
                System.out.print("\nEnter flight number: ");
                flightNumber = scanner.nextInt();
                flight = app.getCompany().getFlightByNumber(flights, flightNumber);
                clearConsole();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        clearConsole();
        System.out.println("\nYou have selected this flight: ");
        System.out.println(flight);
        System.out.print("\nDo you want to assign a crew member for it (y/n)? ");
        option = scanner.next().charAt(0);
        if (Character.compare(option, 'y') == 0) {
            _assignCrewMemberToFlight(flight);
        }
    }

    /**
    * Method called by assignCrewMemberToFlight() that allows the user to choose the crew member that he wants to assign to the choosen flight
    * 
    * @param flight the choosen flight
    */
    public void _assignCrewMemberToFlight(Flight flight) {
        clearConsole();
        int id;
        CrewMember crewMember = null;
        ArrayList<CrewMember> availableMembers = this.controller.getAvailableCrewMembers(flight);
        if (availableMembers.size() > 0) {
            System.out.println(availableMembers.size() + " available Crew Members:");
            displayCrewMembers(this.controller.getAvailableCrewMembers(flight));
            while (crewMember == null) {
                try {
                    System.out.print("\nEnter id of the crew member you want to assign for the flight: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    crewMember = app.getCompany().getCrewMemberById(id);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            flight.addCrewMember(crewMember);
            clearConsole();
            System.out.println("Crew member assigned successfully");
            clickToExit();
        } else {
            System.out.println("No crew members available for this flight!");
        }
        
    }

    /**
    * Method that creates a crew member from informations given by the user in the console
    *
    * @throws DateTimeParseException if the user does not respect the date format
    */
    public void addCrewMember() {
        clearConsole();
        String fname, lname, phoneNum, email, jobDesc, dateBirthString;
        LocalDate dateBirth = null;
        
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        System.out.println("\nAdding a new Crew Member: ");
        System.out.print("Enter first name: ");
        fname = scanner.nextLine();
        System.out.print("Enter last name: ");
        lname = scanner.nextLine();
        System.out.print("Enter phone number: ");
        phoneNum = scanner.nextLine();
        System.out.print("Enter email address: ");
        email = scanner.nextLine();
        while (dateBirth == null) {
            try {
                System.out.print("Enter date of birth (dd-mm-yyyy): ");
                dateBirthString = scanner.nextLine();
                dateBirth = LocalDate.parse(dateBirthString, df);
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect date format!");
            }
        }
        
        System.out.print("Enter job description: ");
        jobDesc = scanner.nextLine();

        this.controller.createCrewMember(fname, lname, phoneNum, email, dateBirth, jobDesc);
        clearConsole();
        System.out.println("Crew member created successfully!");
        clickToExit();
    }

    /**
    * Method that removes a crew member from its id given by the user in the console
    */
    public void removeCrewMember() {
        clearConsole();
        int id;
        CrewMember crewMember = null;
        displayCrewMembers(app.getCompany().getCrewMembers());
        while (crewMember == null) {
            try {
                System.out.print("\nEnter id of crew member that you want to remove: ");
                id = scanner.nextInt();
                crewMember = app.getCompany().getCrewMemberById(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        this.controller.removeCrewMember(crewMember);
        clearConsole();
        System.out.println("Crew member deleted successfully!");
        clickToExit();
    }

    /**
    * Method that asks the user what kind of mean of transport he wants to add, and calls the corresponding method
    */
    public void addMeanTransport() {
        clearConsole();
        int option;
        System.out.println("What do you want to add?");
        displayMeansTransportType();
        System.out.print("Enter option: ");
        option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                this.controller.createAirplaneView();
                break;
            case 2:
                this.controller.createHelicopterView();
                break;
            default:
                addMeanTransport();
                break;
        }
    }

    /**
    * Method that creates an airplane from informations given by the user in the console
    */
    public void createAirplane() {
        clearConsole();
        String name;
        char option = 'y';
        int nbSeats, maxSpeed;
        double annualFuelConsumption, emissionFactor;
        System.out.println("Adding airplane: ");
        System.out.print("Enter airplane name: ");
        name = scanner.nextLine();
        System.out.print("Enter number of seats: ");
        nbSeats = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter annual fuel consumption: ");
        annualFuelConsumption = scanner.nextDouble();
        System.out.print("Enter CO2 emission factor: ");
        emissionFactor = scanner.nextDouble();
        System.out.print("Enter max speed: ");
        maxSpeed = scanner.nextInt();
        scanner.nextLine();
        Airplane airplane = this.controller.createAirplane(name, nbSeats, annualFuelConsumption, emissionFactor, maxSpeed);
        while (airplane.getNbSeatsWithoutFlightClass() > 0 && Character.compare(option, 'y') == 0) {
            this.controller.addFlightClassesView(airplane);
            if (airplane.getNbSeatsWithoutFlightClass() > 0) {
                System.out.print("Want to add more flight classes (y/n)? ");
                option = scanner.next().charAt(0);
                scanner.nextLine();
            }
        }
        clearConsole();
        System.out.println("Airplane created successfully");
        clickToExit();
    }

    /**
    * Method that creates an Helicopter from informations given by the user in the console
    */
    public void createHelicopter() {
        clearConsole();
        String name;
        char option = 'y';
        int nbSeats, maxSpeed;
        double annualFuelConsumption, emissionFactor;
        System.out.println("Adding helicopter: ");
        System.out.print("Enter helicopter name: ");
        name = scanner.nextLine();
        System.out.print("Enter number of seats: ");
        nbSeats = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter annual fuel consumption: ");
        annualFuelConsumption = scanner.nextDouble();
        System.out.print("Enter CO2 emission factor: ");
        emissionFactor = scanner.nextDouble();
        System.out.print("Enter max speed: ");
        maxSpeed = scanner.nextInt();
        scanner.nextLine();
        Helicopter helicopter = this.controller.createHelicopter(name, nbSeats, annualFuelConsumption, emissionFactor, maxSpeed);
        while (helicopter.getNbSeatsWithoutFlightClass() > 0 && Character.compare(option, 'y') == 0) {
            this.controller.addFlightClassesView(helicopter);
            if (helicopter.getNbSeatsWithoutFlightClass() > 0) {
                System.out.print("Want to add more flight classes (y/n)? ");
                option = scanner.next().charAt(0);
                scanner.nextLine();
            }
        }

        clearConsole();
        System.out.println("Airplane created successfully");
        clickToExit();
    }

    /**
    * Method that creates a FlightClass from informations given by the user in the console and adds it to the mean of transport passsed as parameter
    * 
    * @param meanTransport the meanTransport in which we want to add a FlightClass
    */
    public void addFlightClasses(MeanTransport meanTransport) {
        clearConsole();
        String flightClassName;
        double flightClassCoef;
        int flightClassNbSeats;
        System.out.println("Adding flight class: ");
        System.out.print("Enter flight class name: ");
        flightClassName = scanner.nextLine();
        System.out.print("Enter flight class coef: ");
        flightClassCoef = scanner.nextDouble();
        do {
            System.out.print("Enter number of seats for this flight class (" + meanTransport.getNbSeatsWithoutFlightClass() + " seats left): ");
            flightClassNbSeats = scanner.nextInt();
            scanner.nextLine();
        } while (flightClassNbSeats > meanTransport.getNbSeatsWithoutFlightClass());
        
        FlightClass flightClass = new FlightClass(flightClassName, flightClassCoef);
        try {
            meanTransport.addNbSeatsPerFlightClass(flightClassNbSeats, flightClass); 
            meanTransport.addFlightClass(flightClass);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    /**
    * Method that allow to manage Means of Transport : add a new one, show the current ones, remove one etc.
    * The user chooses on the console what action he wants to do, and calls the according method
    */
    public void manageMeansTransport() {
        clearConsole();
        System.out.println("\nSelect an option:");
        System.out.println("1- Add a mean of transport");
        System.out.println("2- Show means of transport");
        System.out.println("3- Remove means of transport");
        System.out.println("4- Modify luggage allowance");
        System.out.println("5- Modify luggage price");
        System.out.println("6- Add a flight class");
        System.out.println("7- Quit");
        System.out.print("Enter your option: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                this.controller.addMeanTransportView();
                break;
            case 2:
                this.controller.showMeansTransportView();
                break;
            case 3:
                this.controller.removeMeanTransportView();
                break;
            case 4:
                this.controller.modifyLuggageAllowanceView();
                break;
            case 5:
                this.controller.modifyLuggagePriceView();
                break;
            case 6:
                this.controller.addFlightClassView();
                break;
        }
        if (option < 1 || option > 7) {
            this.controller.manageMeansTransportView();
        }
    }

    public void modifyLuggagePrice() {
        clearConsole();
        int option;
        System.out.println("\nModifying luggage price! ");
        System.out.println("\n\nSelect a mean of transport type: ");
        displayMeansTransportType();
        System.out.println("3- Quit");
        System.out.print("Enter option: ");
        option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                this.controller.modifyAirplaneLuggagePriceView();
                break;
            case 2:
                this.controller.modifyHelicopterLuggagePriceView();
                break;
            case 3:
                this.controller.manageMeansTransportView();
                break;
        }

        if (option < 1 || option > 3) {
            this.controller.modifyLuggagePriceView();
        }
    }

    /**
    * Method that allows the user to chose a mean of transport of which he wants to change its luggage allowance
    */
    public void modifyLuggageAllowance() {
        clearConsole();
        int option;
        System.out.println("\nModifying luggage allowance! ");
        System.out.println("\n\nSelect a mean of transport type: ");
        displayMeansTransportType();
        System.out.println("3- Quit");
        System.out.print("Enter option: ");
        option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                this.controller.modifyAirplaneLuggageAllowanceView();
                break;
            case 2:
                this.controller.modifyHelicopterLuggageAllowanceView();
                break;
            case 3:
                this.controller.manageMeansTransportView();
                break;
        }
        if (option < 1 || option > 3) {
            this.controller.modifyLuggageAllowanceView();
        }
    }

    public void modifyAirplaneLuggagePrice() { 
        clearConsole();
        int handPrice, checkedPrice;
        System.out.println("Current luggage prices!\n");
        displayAirplaneLuggagesPrice();
        System.out.print("\nEnter new hand luggage price: ");
        handPrice = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\nEnter new checked luggage price: ");
        checkedPrice = scanner.nextInt();
        scanner.nextLine();
        this.controller.modifyAirplaneLuggagePrice(handPrice, checkedPrice);
        clearConsole();
        System.out.println("Airplane luggages price has been successfully modified!");
        clickToExit();
    }

    public void modifyHelicopterLuggagePrice() { 
        clearConsole();
        int handPrice, checkedPrice;
        System.out.println("Current luggage prices!\n");
        displayHelicopterLuggagesPrice();
        System.out.print("\nEnter new hand luggage price: ");
        handPrice = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\nEnter new checked luggage price: ");
        checkedPrice = scanner.nextInt();
        scanner.nextLine();
        this.controller.modifyHelicopterLuggagePrice(handPrice, checkedPrice);
        clearConsole();
        System.out.println("Helicopter luggages price has been successfully modified!");
        clickToExit();
    }

    /**
    * Method called by modifyLuggageAllowance() which allows the user to chose the new luggage allowance of an airplane
    */
    public void modifyAirplaneLuggageAllowance() { 
        clearConsole();
        int handAllowance, checkedAllowance;
        System.out.println("Current luggage allowance!");
        displayAiplaneLuggageAllowance();
        System.out.print("\nEnter new hand luggage quantity allowance: ");
        handAllowance = scanner.nextInt();
        System.out.print("\nEnter new checked luggage quantity allowance: ");
        checkedAllowance = scanner.nextInt();
        this.controller.modifyAirplaneLuggageAllowance(handAllowance, checkedAllowance);
        clearConsole();
        System.out.println("Airplane luggage quantity allowance has been successfully modified!");
        clickToExit();
    }

    /**
    * Method called by modifyLuggageAllowance() which allows the user to chose the new luggage allowance of an helicopter
    */
    public void modifyHelicopterLuggageAllowance() { 
        clearConsole();
        int handAllowance, checkedAllowance;
        System.out.println("Current luggage allowance!");
        displayAiplaneLuggageAllowance();
        System.out.print("\nEnter new hand luggage quantity allowance: ");
        handAllowance = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\nEnter new checked luggage quantity allowance: ");
        checkedAllowance = scanner.nextInt();
        scanner.nextLine();
        this.controller.modifyHelicopterLuggageAllowance(handAllowance, checkedAllowance);
        clearConsole();
        System.out.println("Helicopter luggage quantity allowance has been successfully modified!");
        clickToExit();
    }

    /**
    * Method that displays the means of transport of the Company
    */
    public void showMeansTransport() {
        clearConsole();
        System.out.println(app.getCompany().getMeanTransports());
        clickToExit();
    }

    /**
    * Method that allows to remove a mean of transport
    * The user chooses on the console the id of the mean of transport he wants to remove
    */
    public void removeMeanTransport() {
        clearConsole();
        int id;
        MeanTransport meanTransport = null;
        System.out.println(app.getCompany().getMeanTransports());
        
        while (meanTransport == null) {
            try {
                System.out.print("\nEnter the id of the mean of transport that you want to remove: ");
                id = scanner.nextInt();
                meanTransport = app.getCompany().getMeanTransportById(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        this.controller.removeMeanTransport(meanTransport);
        clearConsole();
        System.out.println("Mean of transport successfully removed!");
        clickToExit();
    }

    /**
    * Method that allows the user to select a mean of transport
    * The user chooses on the console the id of the mean of transport he wants to select
    *
    * @return the selected mean of transport
    */
    public MeanTransport selectMeanTransport() {
        clearConsole();
        int id;
        MeanTransport meanTransport = null;
        System.out.println("Choose a mean of transport: \n");
        System.out.println(app.getCompany().getMeanTransports());
        while (meanTransport == null) {
            try {
                System.out.print("\nEnter mean of transport id: ");
                id = scanner.nextInt();
                scanner.nextLine();
                meanTransport = app.getCompany().getMeanTransportById(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        clearConsole();
        System.out.println("You have selected this mean of transport: \n");
        System.out.println(meanTransport);
        clickToContinue();
        clearConsole();
        return meanTransport;
    }
}
