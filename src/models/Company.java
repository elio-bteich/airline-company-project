package src.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;

/**
 * Class who represents a Company
 * A Company is defined by its name, and arrays of crew members, clients,
 * flights and means of transport
 */
public class Company {
    private String name;
    private ArrayList<CrewMember> crewMembers;
    private ArrayList<Client> clients;
    private ArrayList<Flight> flights;
    private ArrayList<MeanTransport> meansTransports;

    /**
     * Constructor of a Company from its name
     * The constructor initializes the crew members, clients, flights and means of
     * transports array
     * 
     * @param name the name of the Company
     */
    public Company(String name) {
        this.name = name;
        this.crewMembers = new ArrayList<CrewMember>();
        this.clients = new ArrayList<Client>();
        this.flights = new ArrayList<Flight>();
        this.meansTransports = new ArrayList<MeanTransport>();
    }

    // Getters

    /**
     * Gets the name of the Company
     * 
     * @return this name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the CrewMember array of the Company
     * 
     * @return this crew members array
     */
    public ArrayList<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    /**
     * Gets the Client array of the Company
     * 
     * @return this clients array
     */
    public ArrayList<Client> getClients() {
        return clients;
    }

    /**
     * Gets the Flight array of the Company
     * 
     * @return this flights array
     */
    public ArrayList<Flight> getFlights() {
        return flights;
    }

    /**
     * Gets the Airplane array of the Company
     * 
     * @return the company's airplanes array
     */
    public ArrayList<Airplane> getAirplanes() {
        ArrayList<Airplane> airplanes = new ArrayList<Airplane>();
        for (MeanTransport meanTransport : this.meansTransports) {
            if (meanTransport instanceof Airplane) {
                airplanes.add((Airplane) meanTransport);
            }
        }
        return airplanes;
    }

    /**
     * Gets the Helicopters array of the Company
     * 
     * @return the company's helicopters array
     */
    public ArrayList<Helicopter> getHelicopters() {
        ArrayList<Helicopter> helicopters = new ArrayList<Helicopter>();
        for (MeanTransport meanTransport : this.meansTransports) {
            if (meanTransport instanceof Helicopter) {
                helicopters.add((Helicopter) meanTransport);
            }
        }
        return helicopters;
    }

    /**
     * Sets the name of the Company
     * 
     * @param name the company name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets one crew member from the array of the Company using his email
     * 
     * @param email the email of the crew member we are looking for
     * @return the crew member using this email, null if no one is using it
     *
     * @throws Exception if the method cannot find a crew member with a given email
     */
    public CrewMember getCrewMemberByEmail(String email) throws Exception {
        for (CrewMember member : this.crewMembers) {
            if (member.getEmailAddress().equals(email)) {
                return member;
            }
        }
        throw new Exception("Crew member not found");
    }

    /**
     * Gets one crew member from the array of the Company using his id
     * 
     * @param id the id of the crew member we are looking for
     * @return the crew member using this id, null if no one is using it
     *
     * @throws Exception if the method cannot find a crew member with the given id
     */
    public CrewMember getCrewMemberById(int id) throws Exception {
        for (CrewMember member : this.crewMembers) {
            if (member.id == id) {
                return member;
            }
        }
        throw new Exception("Crew member not found");
    }

    /**
     * Gets one Client from the array of the Company using his email
     * 
     * @param email the email of the Client we are looking for
     * @return the Client using this email, null if no one is using it
     *
     * @throws Exception if the method cannot find a Client with the given email
     */
    public Client getClientByEmail(String email) throws Exception {
        for (Client client : this.clients) {
            if (client.getEmailAddress().equals(email)) {
                return client;
            }
        }
        throw new Exception("Client not found!");
    }

    /**
     * Search a flight in the Company whith the departure city and the arrival city
     *
     * @param departure the departure city
     * @param arrival the arrival city
     * @return the flight that matches names passed as parameter or null if there
     *         isn't any
     */
    public ArrayList<Flight> searchFlights(String departure, String arrival) {
        ArrayList<Flight> flightsFound = new ArrayList<Flight>();
        for (Flight flight : this.flights) {
            if (flight.getDepartureAirport().getAddress().getCity().equals(departure)
                    && flight.getArrivalAirport().getAddress().getCity().equals(arrival)
                    && flight.getDepartureTime().compareTo(LocalDateTime.now()) > 0) {
                flightsFound.add(flight);
            }
        }
        return flightsFound;
    }

    /**
     * Add a flight to the Company flight array at a specified index
     *
     * @param idx the index of the array where we add the flight 
     * @param flight the flight that we are adding
     */
    public void addFlight(int idx, Flight flight) {
        this.flights.add(idx, flight);
    }

    /**
    * Getter of this Means of Transport array
    *
    * @return this meansTransport
    */
    public ArrayList<MeanTransport> getMeanTransports() {
        return this.meansTransports;
    }

    /**
     * Remove a flight from the company flights list
     * 
     * @param flight the flight that we want to remove
     */
    public void deleteFlight(Flight flight) {
        this.flights.remove(flight);
    }

    /**
     * Adds a crew member from the company's crew member's array
     * 
     * @param idx the index in the array
     * @param member the member that we are adding
     */
    public void addCrewMember(int idx, CrewMember member) {
        this.crewMembers.add(idx, member);
    }

    /**
     * Removes a crew member from the company's crew member's list
     * 
     * @param member the member that we are removing
     */
    public void deleteCrewMember(CrewMember member) {
        this.crewMembers.remove(member);
    }

    /**
     * Method that checks whether or not the name of a mean of transport is already used in the MeanTransport array of the company
     *
     * @param transportName the name of a mean of transport we want to check
     *
     * @return true if the name is already used, false if not.
     */
    public boolean isNameUsed(String transportName) {
        for(MeanTransport mean : meansTransports) {
            if (mean.getName().equals(transportName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an mean transport to the company's mean transports list at a specified index
     * 
     * @param idx the index in the array where we add the mean of transport 
     * @param meanTransport the mean of transport that we are adding
     */
    public void addMeanTransport(int idx, MeanTransport meanTransport) {
        this.meansTransports.add(idx, meanTransport);
    }

    /**
     * Removes an mean transport from the company's mean transports list
     * 
     * @param meanTransport the mean of transport that we are removing
     */
    public void deleteMeanTransport(MeanTransport meanTransport) {
        Iterator<Flight> it = this.flights.iterator();
        while (it.hasNext()) {
            Flight flight = it.next();
            if (flight.getMeanTransport().getId() == meanTransport.getId()) {
                it.remove();
            }
        }
        this.meansTransports.remove(meanTransport);
    }

    /**
     * Adds a client to the company's clients list at a specified index
     * 
     * @param idx the index in the array where we add the client 
     * @param client the client that we are adding
     */
    public void addClient(int idx, Client client) {
        this.clients.add(idx, client);
    }


    /**
     * Removes a client from the company's clients list
     * 
     * @param client the client that we are removing
     */
    public void deleteClient(Client client) {
        for (Flight flight: this.flights) {
            Iterator<Reservation> it = flight.getReservations().iterator();
            while (it.hasNext()) {
                Reservation reservation = it.next();
                if (reservation.getClient().getId() == client.getId()) {
                    it.remove();
                }
            }
        }
        this.clients.remove(client);
    }

    /**
     * Gets a flight by its flight number
     * 
     * @param number the number of the flight we are looking for
     * @return the corresponding flight, or null if there is none
     *
     * @throws Exception if the method cannot find a flight with the given number
     */
    public Flight getFlightByNumber(int number) throws Exception {
        for (Flight flight : this.flights) {
            if (flight.getNumber() == number) {
                return flight;
            }
        }
        throw new Exception("Flight not found!");
    }

    /**
     * Getter of a Flight from the company's next flight array
     * 
     * @param flights the array of flights we are looking in 
     * @param number the number of the flight that we are looking for
     *
     * @return the corresponding flight 
     *
     * @throws Exception if the method could not find a flight with the given number
     */
    public Flight getFlightByNumber(ArrayList<Flight> flights, int number) throws Exception {
        for (Flight flight : flights) {
            if (flight.getNumber() == number) {
                return flight;
            }
        }
        throw new Exception("Flight not found!");
    }

    /**
     * Getter of an array of all the future flights of the company
     * 
     * @return an array of all the future flights scheduled
     */
    public ArrayList<Flight> getNextFlights() {
        ArrayList<Flight> nextFlights = new ArrayList<Flight>();
        for (Flight flight: this.flights) {
            if (flight.getDepartureTime().isAfter(LocalDateTime.now())) {
                nextFlights.add(flight);
            }
        }
        return nextFlights;
    }

    /**
     * Getter of an array of all the previous flights of the company
     * 
     * @return an array of all the previous flights
     */
    public ArrayList<Flight> getPreviousFlights() {
        ArrayList<Flight> previousFlights = new ArrayList<Flight>();
        for (Flight flight: this.flights) {
            if (flight.getDepartureTime().isBefore(LocalDateTime.now())) {
                previousFlights.add(flight);
            }
        }
        return previousFlights;
    }

    /**
     * Getter of an array of all the available Means of Transport of the Company for a given time interval
     *
     * @param depTime the departure time of the interval
     * @param arrTime the arrival time of the interval 
     * 
     * @return an array of all the available means of transport
     */
    public ArrayList<MeanTransport> getAvailableMeanTranports(LocalDateTime depTime, LocalDateTime arrTime) {
        ArrayList<MeanTransport> availableMeanTransports = new ArrayList<MeanTransport>(); 
        for (MeanTransport meanTransport: this.meansTransports) {
            if (meanTransport.isAvailable(depTime, arrTime)) {
                availableMeanTransports.add(meanTransport);
            }
        }
        return availableMeanTransports;
    }

    public boolean isEmailUsedByClient(String email) {
        for (Client client: this.clients) {
            if (client.getEmailAddress().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmailUsedByCrewMember(String email) {
        for (CrewMember crewMember: this.crewMembers) {
            if (crewMember.getEmailAddress().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter of a mean of transport with a given id
     *
     * @param id the id of the mean of transport we're looking for
     * 
     * @return the mean of transport with the corresponding id
     *
     * @throws Exception if the method cannot find a mean of transport with the given id
     */
    public MeanTransport getMeanTransportById(int id) throws Exception {
        for (MeanTransport meanTransport : this.meansTransports) {
            if (meanTransport.getId() == id) {
                return meanTransport;
            }
        }
        throw new Exception("Mean of transport not found!");
    }

    /**
     * Getter of an airplane with a given id
     *
     * @param id the id of the airplane we're looking for
     * 
     * @return the airplane with the corresponding id
     *
     * @throws Exception if the method cannot find an airplane for the given id
     */
    public Airplane getAirplaneById(int id) throws Exception {
        for(Airplane airplane : this.getAirplanes()){
            if(airplane.getId() == id){
                return airplane;
            }

        }
        throw new Exception("Airplane not found!");
    }

    /**
     * Getter of an Helicopter with a given id
     *
     * @param id the id of the helicopter we're looking for 
     * 
     * @return the Helicopter with the corresponding id
     *
     * @throws Exception if the method cannot find an helicopter for the given id
     */
    public Helicopter getHelicopterById(int id) throws Exception {
        for(Helicopter helicopter : this.getHelicopters()){
            if(helicopter.getId() == id){
                return helicopter;
            }
        }
        throw new Exception("Helicopter not found!");
    }

    /**
     * Method that gets an unused client id from the Company clients array
     * 
     * @return the lowest available id from the array
     */
    public int getUniqueClientId() {
        boolean idFound = false;
        int idx = 0;
        while (!idFound && idx < this.clients.size()) {
            Client client = this.clients.get(idx);
            if (client.getId() != idx + 1) {
                idFound = true;
            } else {
                idx += 1;
            }
        }
        
        return idx+1;
    }

    /**
     * Method that gets an unused crew member id from the Company crewmembers array
     * 
     * @return the lowest available id from the array
     */
    public int getUniqueCrewMemberId() {
        boolean idFound = false;
        int idx = 0;
        while (!idFound && idx < this.crewMembers.size()) {
            CrewMember crewMember = this.crewMembers.get(idx);
            if (crewMember.getId() != idx + 1) {
                idFound = true;
            } else {
                idx += 1;
            }
        }
        
        return idx+1;
    }

    /**
     * Method that gets an unused mean of transport id from the Company mean of transports array
     * 
     * @return the lowest available id from the array
     */
    public int getUniqueMeanTransportId() {
        boolean idFound = false;
        int idx = 0;
        while (!idFound && idx < this.meansTransports.size()) {
            MeanTransport meanTransport = this.meansTransports.get(idx);
            if (meanTransport.getId() != idx + 1) {
                idFound = true;
            } else {
                idx += 1;
            }
        }
        
        return idx+1;
    }
}
