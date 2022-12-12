package src;

import java.io.File;
import java.util.ArrayList;
import src.models.*;
import src.controllers.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import src.views.View;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Class who represents the Main program.
 * The Main attributes are a client, crew member or a company that want to use the views, 
 * the controllers to manage these views, a scanner type to read data on the terminal,
 * and two arrays for the Airports and their Addresses.
 *
 * The Main initializes many values for the Addresses, Airports, MeanTransports,
 * CrewMembers, Clients, Flights and Reservations.
 * The Main program also manages allows the user to chose between the company admin view,
 * the crew member view and the client view.
 */
public class Main {
    private Client authClient;
    private CrewMember authCrewMember;
    private ArrayList<Airport> airports;
    private Company company;
    private ArrayList<Address> addresses;
    private ClientController clientController;
    private CrewMemberController crewMemberController;
    private AdministrationController administrationController;
    public static Scanner scanner = new Scanner(System.in);

    /**
    * Constructor of the main class from a company
    * This constructor sets to null the authentified client and crewmember values,
    * and initializes the controllers, plus the Airports and Addresses array
    *
    * @param company the company that initializes the Main
    */
    public Main(Company company) {
        this.authClient = null;
        this.authCrewMember = null;
        this.airports = new ArrayList<Airport>();
        this.company = company;
        this.addresses = new ArrayList<Address>();
        this.clientController = new ClientController(this);
        this.crewMemberController = new CrewMemberController(this);
        this.administrationController = new AdministrationController(this);
    }

    /**
    * Getter of the authentified Client
    *
    * @return the current authentified client
    */
    public Client getAuthClient() {
        return this.authClient;
    }

    /**
    * Getter of the addresses array
    *
    * @return the array of all the addresses
    */
    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    /**
    * Getter of the administration Controller
    *
    * @return the administration controller
    */
    public AdministrationController getAdministrationController() {
        return administrationController;
    }

    /**
    * Getter of the airports array
    *
    * @return the airports array
    */
    public ArrayList<Airport> getAirports() {
        return airports;
    }

    /**
    * Getter of the authentified Crew Member
    *
    * @return the current authentified Crew Member
    */
    public CrewMember getAuthCrewMember() {
        return authCrewMember;
    }

    /**
    * Getter of the Client Controller
    *
    * @return the client controller
    */
    public ClientController getClientController() {
        return clientController;
    }

    /**
    * Getter of the company
    *
    * @return this company
    */
    public Company getCompany() {
        return company;
    }

    /**
    * Getter of the Crew Member Controller
    *
    * @return the crew member controller
    */
    public CrewMemberController getCrewMemberController() {
        return crewMemberController;
    }
    
    /**
    * Setter of the Addresses array
    *
    * @param addresses the new addresses array
    */
    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    /**
    * Setter of the Airports array
    *
    * @param airports the new airports array
    */
    public void setAirports(ArrayList<Airport> airports) {
        this.airports = airports;
    }

    /**
    * Setter of the authentified Client array
    *
    * @param authClient the new authentified client array
    *
    * @throws Exception if the authentified Client value is NULL
    */
    public void setAuthClient(Client authClient) throws Exception {
        if (authClient == null) {
            throw new Exception("Client is null");
        }
        this.authClient = authClient;
    }

    public void clientLogout() {
        this.authClient = null;
    }

    public void crewMemberLogout() {
        this.authCrewMember = null;
    }

    /**
    * Setter of the authentified crew member array
    *
    * @param authCrewMember the new authentified crew member array
    *
    * @throws Exception if the authentified Crew Member value is NULL
    */
    public void setAuthCrewMember(CrewMember authCrewMember) throws Exception {
        if (authCrewMember == null) {
            throw new Exception("Crew member is null");
        }
        this.authCrewMember = authCrewMember;
    }

    /**
    * Setter of the company
    *
    * @param company the new company
    */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
    * Method that adds default Addresses to the addresses array
    * This method reads the Addresses values in the text file Addresses.txt 
    *
    * @throws FileNotFoundException if the file name is incorrect
    */
    public void addDefaultAddresses() throws FileNotFoundException {
        Scanner input = new Scanner(new File("DB/Addresses.txt"));
        input.useDelimiter(",|\n");

        while(input.hasNext()) {
            String cityName = input.next();
            String postalCode = input.next();
            String latitude = input.next();
            String longitude = input.next();

            double lat  = Double.parseDouble(latitude);
            double longi = Double.parseDouble(longitude);

            this.addresses.add(new Address(cityName, postalCode, lat, longi));
        }
    }

    /**
    * Method that adds default Airports to the airports array
    * This method reads the Airports values in the text file Airports.txt 
    *
    * @throws FileNotFoundException if the file name is incorrect
    */
    public void addDefaultAirports() throws FileNotFoundException {
        Scanner input = new Scanner(new File("DB/Airports.txt"));
        Address address;
        input.useDelimiter(",|\n");

        while(input.hasNext()) {
            String airportName = input.next();
            String airportCode = input.next();
            String addressPostalCode = input.next();

            try {
                address = Address.getAddressByPostalCode(addressPostalCode, this.addresses);
                this.airports.add(new Airport(airportName, airportCode, address));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
    * Method that adds default Means of Transport to the means of transport array
    * This method calls the methods to add default means of transport of each type
    *
    * @throws FileNotFoundException if the file name is incorrect
    */
    public void addDefaultMeanTransport() throws FileNotFoundException {
        
        addDefaultAirplane();
        addDefaultHelicopter();
        addDefaultFlighClassAirplane();
        addDefaultFlightClassHelico();
    }

    /**
    * Method that adds default Airplanes to the Means of Transport array
    * This method reads the Airplanes values in the text file Airplanes.txt 
    *
    * @throws FileNotFoundException if the file name is incorrect
    */
    public void addDefaultAirplane() throws FileNotFoundException {
        Scanner input = new Scanner(new File("DB/Airplanes.txt"));
        input.useDelimiter(",|\n");

        while(input.hasNext()) {
            String airplaneName = input.next();
            String nbSeats = input.next();
            String annualFuelConsumption = input.next();
            String emissionFactor = input.next();
            String speedMax = input.next();

            int seats = Integer.parseInt(nbSeats);
            double fuel = Double.parseDouble(annualFuelConsumption);
            double emssion = Double.parseDouble(emissionFactor);
            int speed = Integer.parseInt(speedMax);

            try {
                this.administrationController.createAirplane(airplaneName, seats, fuel, emssion, speed);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
    * Method that adds default Helicopter to the Means of Transport array
    * This method reads the Helicopter values in the text file Helicopter.txt 
    *
    * @throws FileNotFoundException if the file name is incorrect
    */
    public void addDefaultHelicopter() throws FileNotFoundException {
        Scanner input = new Scanner(new File("DB/Helicopter.txt"));
        input.useDelimiter(",|\n");

        while(input.hasNext()) {
            String airplaneName = input.next();
            String nbSeats = input.next();
            String annualFuelConsumption = input.next();
            String emissionFactor = input.next();
            String speedMax = input.next();

            int seats = Integer.parseInt(nbSeats);
            double fuel = Double.parseDouble(annualFuelConsumption);
            double emssion = Double.parseDouble(emissionFactor);
            int speed = Integer.parseInt(speedMax);

            try {
                this.administrationController.createHelicopter(airplaneName, seats, fuel, emssion, speed);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
    * Method that adds default FlightClasses for the Airplanes
    * This method reads the FlightClasses values in the text file FlightClassAirplanes.txt 
    *
    * @throws FileNotFoundException if the file name is incorrect
    */
    public void addDefaultFlighClassAirplane() throws FileNotFoundException{
        Airplane airplane;
        Scanner input = new Scanner(new File("DB/FlightClassAirplane.txt"));
        input.useDelimiter(",|\n");
        while(input.hasNext()) {
            String idAirplane = input.next();
            String flightClassName = input.next();
            String flightClassCoef = input.next();
            int id = Integer.parseInt(idAirplane);
            String nbSeatFlightClassStr = input.next();

            int nbSeatFlightClass = Integer.parseInt(nbSeatFlightClassStr);
            double coef = Double.parseDouble(flightClassCoef);
            try{
            airplane = this.company.getAirplaneById(id);
            FlightClass flightClass = new FlightClass(flightClassName, coef);
            airplane.addFlightClass(flightClass);
            airplane.addNbSeatsPerFlightClass(nbSeatFlightClass, flightClass);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
    * Method that adds default FlightClasses for the Helicopters
    * This method reads the FlightClasses values in the text file FlightClassHelico.txt 
    *
    * @throws FileNotFoundException if the file name is incorrect
    */
    public void addDefaultFlightClassHelico() throws FileNotFoundException{   
        Helicopter helicopter;      
        Scanner input = new Scanner(new File("DB/FlightClassHelico.txt"));
        input.useDelimiter(",|\n");
        while(input.hasNext()) {
            String idHelicoper = input.next();
            String flightClassName = input.next();
            String flightClassCoef = input.next();
            int id = Integer.parseInt(idHelicoper);
            double coef = Double.parseDouble(flightClassCoef);
            String nbSeatFlightClassStr = input.next();
            int nbSeatFlightClass = Integer.parseInt(nbSeatFlightClassStr);
            try{
            helicopter = this.company.getHelicopterById(id);
            FlightClass flightClass = new FlightClass(flightClassName, coef);
            helicopter.addFlightClass(flightClass);
            helicopter.addNbSeatsPerFlightClass(nbSeatFlightClass, flightClass);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
    * Method that adds default CrewMembers to the CrewMembers array
    * This method reads the CrewMember values in the text file CrewMembers.txt 
    *
    * @throws FileNotFoundException if the file name is incorrect
    */
    public void addDefaultCrewMembers() throws FileNotFoundException {
        Scanner input = new Scanner(new File("DB/CrewMembers.txt"));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        input.useDelimiter(",|\n");

        while(input.hasNext()) {
            String fName = input.next();
            String lName = input.next();
            String phoneNum = input.next();
            String email = input.next();
            String dateBirth = input.next();
            String jobDesc = input.next();

            try {
                this.administrationController.createCrewMember(fName, lName, phoneNum, email,
                LocalDate.parse(dateBirth, df), jobDesc);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
    * Method that adds default Clients to the Clients array
    * This method reads the Clients values in the text file Clientss.txt 
    *
    * @throws FileNotFoundException if the file name is incorrect
    */
    public void addDefaultClients() throws FileNotFoundException {
        Scanner input = new Scanner(new File("DB/Clients.txt"));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        input.useDelimiter(",|\n");

        while(input.hasNext()) {
            String fName = input.next();
            String lName = input.next();
            String phoneNum = input.next();
            String email = input.next();
            String dateBirth = input.next();
            try {
                this.administrationController.createClient(fName, lName, phoneNum, email,
                LocalDate.parse(dateBirth, df));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
    * Method that adds default Flights to the Flights array
    * This method reads the Flights values in the text file Flights.txt 
    *
    * @throws FileNotFoundException if the file name is incorrect
    */
    public void addDefaultFlights() throws FileNotFoundException {
        Scanner input = new Scanner(new File("DB/Flights.txt"));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
        input.useDelimiter(",|\n");
        MeanTransport meanTransport;
        Airport departureAirport;
        Airport arrivalAirport;
        while(input.hasNext()) {
            String depTime = input.next();
            String arrTime = input.next();
            String basePrice  = input.next();
            int idTransport  =input.nextInt();
            String codeDepartureAirport = input.next();
            String codeArrivalAirport = input.next();

            Double price = Double.parseDouble(basePrice);
            try {
                LocalDateTime time1 = LocalDateTime.parse(depTime, df);
                LocalDateTime time2 = LocalDateTime.parse(arrTime, df);
                departureAirport = Airport.getAirportByCode(codeDepartureAirport, airports);
                arrivalAirport = Airport.getAirportByCode(codeArrivalAirport, airports);
                meanTransport = this.company.getMeanTransportById(idTransport);
                this.administrationController.createFlight(time1, time2, price, meanTransport, departureAirport, arrivalAirport);
        
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
    * Method that creates a value for a Reservation for the main to work
    * and add this Reservation to the reservations array of the corresponding 
    * Client in the clients array of this Company
    *
    * @throws FileNotFoundException if the file name is incorrect
    */
    public void addDefaultReservations() throws FileNotFoundException{
        Scanner input = new Scanner(new File("DB/Reservations.txt"));
        input.useDelimiter(",|\n");
        while(input.hasNext()) {
            String clientEmail = input.next();
            int flightId =input.nextInt();
            String flightClassName = input.next();
            
            try {
                Client client = company.getClientByEmail(clientEmail);
                Flight flight = company.getFlightByNumber(flightId);
                FlightClass flightClass = flight.getMeanTransport().getFlightClassByName(flightClassName);
                Reservation reservation = new Reservation(flight.getUniqueReservationId(), flight, LocalDateTime.now(), client, new ArrayList<Luggage>(), flightClass);
                flight.addReservation(reservation);
                client.addReservation(reservation);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
    * Method that sets all the default values for the main to work
    */
    public void setDefaultState() {
        try {
            this.addDefaultAddresses();
            this.addDefaultAirports();
            this.addDefaultMeanTransport();
            this.addDefaultClients();
            this.addDefaultCrewMembers();
            this.addDefaultFlights();
            this.addDefaultReservations();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
    * Method that manages the console menu on the terminal
    * This method allows the user to type on the terminal to select
    * his role and call the corresponding Controller menu
    */
    public void menu() {
        int option;
        View.clearConsole();
        System.out.println("Choose your role: ");
        System.out.println("1- Administration");
        System.out.println("2- Client");
        System.out.println("3- Crew Member ");
        System.out.println("4- Exit the program ");
        System.out.println();
        System.out.print("Enter option: ");
        option = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        switch (option) {
            case 1:
                administrationController.menu();
                break;
            case 2:
                clientController.menu();
                break;
            case 3:
                crewMemberController.menu();
                break;
            case 4:
                View.exit();
                break;
            default:
                System.out.println();
                System.out.println("Option is not valid. Please try again!");
                System.out.println();
                break;
        }
        // if the user didn't choose to exit the program display the main menu again
        if (option != 4) {
            this.menu();
        }
    }

    /**
    * Sets the default states and launch the main menu
    *
    * @param args default argument for the main to work properly
    */
    public static void main(String[] args) {
        Company company = new Company("Air France");
        Main app = new Main(company);
        
        app.setDefaultState();
        app.menu();
        

        scanner.close();
    }
}
