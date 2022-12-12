package src.views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import src.Main;
import src.models.*;
import src.controllers.ClientController;
/**
 * Class who represents an ClientView, that inherits from View.
 * An ClientView is defined by the two attributes from View and the corresponding Controller
 */
public class ClientView extends View{

    private ClientController controller;

	/**
     * Constructor of an ClientView from an application and a controller
     * 
     * @param app the Main application
     * @param controller the ClientController
     */
    public ClientView(Main app, ClientController controller) {
        super(app);
        this.controller = controller;
    }

    /**
    * Method that displays a console menu and allows to chose an action to do 
    * According to the user choice on the terminal, this method will call different methods
    */
    public void menu() {
        clearConsole();
        System.out.println("\nSelect an option:");
        System.out.println("1- Reserve a flight");
        System.out.println("2- Manage your reservations");
        System.out.println("3- Check flights history");
        System.out.println("4- Check your personal information");
        System.out.println("5- Delete account");
        System.out.println("6- Logout");
        System.out.print("Enter your option: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                this.controller.searchFlightView();
                break;
            case 2:
                this.controller.manageReservationsView();
                break;
            case 3:
                this.controller.previousReservationsView();
                break;
            case 4:
                this.controller.personalInformationView();
                break;
            case 5:
                this.controller.deleteAccountView();
                break;
            case 6:
                this.controller.logout();
                break;
        }
        
        if (option < 1 || option > 6) {
            this.controller.menu();
        }
    }

    public void deleteAccount() {
        clearConsole();
        char option;
        System.out.print("Are you sure you want to delete your account (y/n)? ");
        option = scanner.next().charAt(0);
        scanner.nextLine();
        if (Character.compare(option, 'y') == 0) {
            this.controller.deleteAccount();
            clearConsole();
            System.out.println("Account has been deleted successfully!");
            clickToExit();
            this.controller.authMiddleware();
        }
    }

    public void authMenu() {
        clearConsole();
        int option;
        System.out.println("Select an option: ");
        System.out.println("\n1- Sign In");
        System.out.println("2- Register");
        System.out.println("3- Quit");
        System.out.print("\nEnter option: ");
        option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                this.controller.loginView();
                break;
            case 2:
                this.controller.registerView();
                break;
            case 3:
                this.app.menu();
                break;
        }

        if (option < 1 || option > 3) {
            this.controller.authMenuView();
        }
    }

    /**
    * Method that allows a client to log himself in by telling his email on the terminal
    */
    public void login() {
        int cpt = 0;
        clearConsole();
        String email;

        System.out.println("Client Login");
        System.out.print("Enter your email: ");
        email = scanner.nextLine();


        while (app.getAuthClient() == null && cpt != 3) {
            try {
                this.controller.login(email);
            } catch (Exception e) {
                System.out.println("\nClient not found");
                System.out.print("\nEnter your email again: ");
                email = scanner.nextLine();
                System.out.println();
                cpt++;
            }
        }
        if (cpt == 3) {
            clearConsole();
            System.out.println("You have reached the maximum number of login requests!");
            clickToExit();
        } else {
            this.controller.menu();
        }
    }

    public void register() {
        clearConsole();
        String email, fname, lname, dBirthString, phoneNum;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        LocalDate dbirth = null;
        System.out.println("Client Registration");
        System.out.print("\nEnter first name: ");
        fname = scanner.nextLine();
        System.out.print("Enter last name: ");
        lname = scanner.nextLine();
        while (dbirth == null) {
            try {
                System.out.print("Enter date of birth (dd-mm-yyyy): ");
                dBirthString = scanner.nextLine();
                dbirth = LocalDate.parse(dBirthString, df);
            } catch (Exception e) {
                System.out.println("Format incorrect try again...\n");
            }
        }
        
        System.out.print("Enter an email: ");
        email = scanner.nextLine();

        while (app.getCompany().isEmailUsedByClient(email)) {
            System.out.println("Email already used, try a different one... ");
            System.out.print("\nEnter new email: ");
            email = scanner.nextLine();
        }

        System.out.print("Enter phone number: ");
        phoneNum = scanner.nextLine();

        

        this.controller.register(fname, lname, phoneNum, email, dbirth);

        clearConsole();
        System.out.println("Registration successful!");
        clickToContinue();
        this.controller.menu();
    }

    /**
    * Method that allows the client to make a reservation
    * On the terminal, the client is able to select a flight for a reservation
    *
    * @param flightsFound an array of flights available for reservation 
    */
    public void reserveFlight(ArrayList<Flight> flightsFound) {
        int flightNum;
        char option;
        Flight flight = null;
        do {
            try {
                System.out.print("\nEnter flight number: ");
                flightNum = scanner.nextInt();
                scanner.nextLine();
                flight = app.getCompany().getFlightByNumber(flightsFound, flightNum);
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        } while (flight == null);
        clearConsole();
        System.out.println("\nYou have selected this flight for reservation: ");
        System.out.println(flight);
        
        do {
            System.out.print("\nAre you sure you want to reserve it?(y/n) ");
            option = scanner.next().charAt(0);
            scanner.nextLine();
        } while (Character.compare(option, 'y') != 0 && Character.compare(option, 'n') != 0);
        
        if (Character.compare(option, 'y') == 0) {
            this._reserveFlight(flight);
        }
    }

    /**
    * Method called by reserveFlight() that allows the client to continue his reservation
    * On the terminal, the client will have to choose his flight class from the array
    *
    * @param flight the flight concerned by the reservation 
    */
    public void _reserveFlight(Flight flight) {
        clearConsole();
        char option;
        ArrayList<Luggage> luggages = new ArrayList<Luggage>();
        FlightClass flightClass = null;
        MeanTransport meanTransport = flight.getMeanTransport();
        String flightClassName;

        System.out.println("Choose your flight class: ");
        System.out.println(meanTransport.getFlightClasses());

        while (flightClass == null) {
            try {
                System.out.print("\n\nEnter flight class name: ");
                flightClassName = scanner.nextLine();
                flightClass = meanTransport.getFlightClassByName(flightClassName);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        do {
            clearConsole();
            System.out.println("\nDo you want to add a luggage to your reservation?(y/n)");
            option = scanner.next().charAt(0);
            scanner.nextLine();
        } while (Character.compare(option, 'y') != 0 && Character.compare(option, 'n') != 0);
        
        // want to add a luggage
        if (Character.compare(option, 'y') == 0) {
            clearConsole();
            addLuggages(flight, luggages);
            clearConsole();
        }
        System.out.println("Total flight price: " + flight.getPrice(flightClass, luggages) + "$\n");
        clickToPay();
        try {
            this.controller.reserveFlight(flight, luggages, flightClass);
            clearConsole();
            System.out.println("Your payment has been accepted!");
            System.out.println();
            System.out.println("Your flight has been successfully reserved.");
            clickToExit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
    * Method called by _reserveFlight() that allows the client to continue his reservation
    * On the terminal, the client will be able to choose what type and the number of luggages he wants to add to his reservation
    *
    * @param flight the flight concerned by the reservation
    * @param luggages the array of luggages for the reservation  
    */
    public void addLuggages(Flight flight, ArrayList<Luggage> luggages) {
        clearConsole();
        int option, luggageQuantity;
        String luggageType;
        char moreLuggages;
        System.out.println("\nChoose the luggage type you want to add: ");
        this.displayLuggageTypes(flight.getMeanTransport());
        System.out.print("Enter type number: ");
        option = scanner.nextInt();
        scanner.nextLine();
        luggageType = flight.getMeanTransport().getLuggageTypeByIndex(option);
        clearConsole();
        System.out.print("How many " + luggageType + " do you want to add? ");
        luggageQuantity = scanner.nextInt();
        scanner.nextLine();
        clearConsole();

        if (this.controller.isLuggageQtyValid(flight.getMeanTransport(), luggageType, Luggage.getNumberOfLuggages(luggageType, luggages) + luggageQuantity)){
            for (int i = 0; i < luggageQuantity; i++) {
                luggages.add(Luggage.createLuggage(luggageType));
            }
        }else {
            System.out.println("\nYou are only allowed to take with you: ");
            this.displayLuggageAllowance(flight.getMeanTransport());
        }

        do {
            System.out.print("Want to add" + (luggages.size()>0 ? " more" : "") +" luggages?(y/n) ");
            moreLuggages = scanner.next().charAt(0);
        } while (Character.compare(moreLuggages, 'y') != 0 && Character.compare(moreLuggages, 'n') != 0);
        
        if (Character.compare(moreLuggages, 'y') == 0) {
            clearConsole();
            this.addLuggages(flight, luggages);
            clearConsole();
        } else {
            clearConsole();
            System.out.println("Your flight has been successfuly reserved.");
            clickToExit();
        }
    }

    /**
    * Method that allows the client to search for a flight by telling on the terminal the departure and arrival cities
    * This method will then print on the terminal any upcoming flight between those cities. It can print none.
    */
    public void searchFlight() {
        clearConsole();
        String depCity, arrCity;
        System.out.println("\nThis is the available addresses: \n");
        System.out.println(app.getAddresses());
        System.out.print("\nDeparture (city): ");
        depCity = scanner.nextLine();
        System.out.print("Arrival (city): ");
        arrCity = scanner.nextLine();
        ArrayList<Flight> flightsFound = this.controller.searchFlights(depCity, arrCity);
        clearConsole();
        if (flightsFound.size() > 0) {
            System.out.println("\n" + flightsFound.size() + " flight"+ (flightsFound.size()>1 ? "s" : "") + " found: ");
            this.displayFlights(flightsFound);
            this.controller.reserveFlightView(flightsFound);
        } else {
            System.out.println("No flights found");
            clickToExit();
        }
    }

    public void manageReservations(ArrayList<Reservation> reservations) {
        clearConsole();
        System.out.println("Here's your reservations!\n");
        displayReservations(reservations);

        if (reservations.size() > 0) {
            int option;
            System.out.println("\nSelect an option:");
            System.out.println("\n1- Check In ");
            System.out.println("2- Delete reservation");
            System.out.println("3- Quit");
            System.out.print("\nEnter option: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    this.controller.checkInView();
                    break;
                case 2:
                    this.controller.deleteReservationView(reservations);
                    break;
                case 3:
                    this.controller.menu();
                    break;    
            }

            while (option < 1 && option > 3) {
                this.controller.manageReservationsView();
            }

        }
    }

    public void checkIn() {
        clearConsole();
        int id;
        Reservation reservation = null;
        ArrayList<Reservation> reservations = app.getAuthClient().getNextReservationsWithoutCheckin();
        displayReservations(reservations);
        if (reservations.size() > 0) {
            while (reservation == null) {
                try {
                    System.out.print("\nEnter id of the reservation: ");
                    id = scanner.nextInt();
                    reservation = Reservation.getReservationById(reservations, id);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            clearConsole();
            System.out.println("You have selected this reservation for checkin: ");
            reservation.display();
            clickToContinue();
            clearConsole();
    
            System.out.println("Here is the available seats for you!\n");
            ArrayList<Seat> availableSeats = reservation.getFlight().getAvailableSeatsByFlightClass(reservation.getFlightClass());
            System.out.println(availableSeats);
    
            Seat seat = null;
            int number;
            while (seat == null) {
                try {
                    System.out.print("\nChoose your seat (number): ");
                    number = scanner.nextInt();
                    scanner.nextLine();
                    seat = Seat.getSeatByNumber(availableSeats, number);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
    
            this.controller.checkIn(reservation, seat);
            clearConsole();
            System.out.println("Your checkin to this flight has been accepted successfully!");
        } 

        clickToExit();
        
    }

    public void deleteReservation(ArrayList<Reservation> reservations) {
        clearConsole();
        displayReservations(reservations);
        int id;
        Reservation reservation = null;
        while (reservation == null) {
            try {
                System.out.print("\nEnter reservation id: ");
                id = scanner.nextInt();
                scanner.nextLine();
                reservation = Reservation.getReservationById(reservations, id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        this.controller.deleteReservation(reservation);
        clearConsole();
        System.out.println("Reservation has been deleted successfully!");
        clickToExit();
    }

    /**
    * Method that displays every reservation found in the given array
    * 
    * @param reservations the Reservation array
    */
    public void displayReservations(ArrayList<Reservation> reservations) {
        if(reservations.size() == 0){
            System.out.println("No reservations have been found!");
        } else {
            for (Reservation reservation: reservations) {
                reservation.display();
            }
        }
    }   

    /**
    * Method that allows a Client to check his personal informations and possibly call a method to change them
    */
    public void personalInfomation() {
        clearConsole();
        char option;
        System.out.println("\nPersonal info:");
        System.out.println(app.getAuthClient());
        do {
            System.out.print("\nWant to modify your personal info (y/n)? ");
            option = scanner.next().charAt(0);
        } while (Character.compare(option, 'y') != 0 && Character.compare(option, 'n') != 0);
        
        if (Character.compare(option, 'y') == 0) {
            this.modifyClientInfo();
        }
    }

    /**
    * Method called by personalInformation() to change this Client informations
    * This method prints a menu on the terminal so the Client can choose which info he wants to change, and call the corresponding method
    */
    public void modifyClientInfo() {
        clearConsole();
        int option;
        System.out.println("\nWhat do you want to modify? ");
        System.out.println("1) First Name");
        System.out.println("2) Last Name");
        System.out.println("3) Phone number");
        System.out.println("4) Email address");
        System.out.println("5) Quit");
        do {
            System.out.print("\nEnter number: ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option < 1 || option > 5);
        
        switch (option) {
            case 1:
                this.controller.modifyFirstNameView();
                break;
            case 2:
                this.controller.modifyLastNameView();
                break;
            case 3:
                this.controller.modifyPhoneNumberView();
                break;
            case 4:
                this.controller.modifyEmailAddressView();
                break;
            case 5:
                this.controller.menu();
                break;
        }
        if (option < 1 || option > 5) {
            this.modifyClientInfo();
        }
        
    }

    /**
    * Method that asks on the terminal a new first name to set to this Client
    */
    public void modifyFirstName() {
        clearConsole();
        System.out.print("Enter new first name: ");
        String newFirstName = scanner.nextLine();
        this.controller.modifyFirstName(newFirstName);
        clearConsole();
        System.out.println("Your first name has been successfully modified !");
        clickToExit();

    }

    /**
    * Method that asks on the terminal a new last name to set to this Client
    */
    public void modifyLastName() {
        clearConsole();
        System.out.print("Enter new last name: ");
        String newLastName = scanner.nextLine();
        this.controller.modifyLastName(newLastName);
        clearConsole();
        System.out.println("Your last name has been successfully modified !");
        clickToExit();

    }

    /**
    * Method that asks on the terminal a new phone number to set to this Client
    */
    public void modifyPhoneNumber() {
        clearConsole();
        System.out.print("Enter new phone number: ");
        String newPhoneNumber = scanner.nextLine();
        this.controller.modifyPhoneNumber(newPhoneNumber);
        clearConsole();
        System.out.println("Your phone number name has been successfully modified !");
        clickToExit();

    }

    /**
    * Method that asks on the terminal a new email address to set to this Client
    */
    public void modifyEmailAddress() {
        clearConsole();
        System.out.print("Enter new email address: ");
        String newEmailAddress = scanner.nextLine();
        this.controller.modifyEmailAddress(newEmailAddress);
        clearConsole();
        System.out.println("Your email name has been successfully modified !");
        clickToExit();

    }
}
