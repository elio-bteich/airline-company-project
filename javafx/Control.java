import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import models.*;

public class Control{

    // Variables -----------------------------------------------------------------------
    Company company = new Company("Air France");
    ArrayList<Address> addresses = new ArrayList<Address>();
    Address paris  = new Address("France", "Paris", "75000", 48.85, 2.35);
    Address nantes  = new Address("France", "Nantes", "44000", 47.21, (-1.55));
    Address lyon  = new Address("France", "Lyon", "69000", 45.56, 4.34);
    Address bordeaux  = new Address("France", "Bordeaux", "56000", 45.56, 4.34);

    ArrayList<Airport> airports = new ArrayList<>();
    Airport airport1 = new Airport("Charles De Gaulle", "CDG", paris);
    Airport aiport2 = new Airport("AIrport Nates", "NAN", nantes);
    Airport aiport3 = new Airport("AIrport Lyon", "LYO", lyon);
    Airport aiport4 = new Airport("AIrport Bordeaux", "BOR", bordeaux);

    //Airplane airplane1 = new Airplane("Airbus 342", 90, 30.9, 0.309, 320);
    //Airplane airplane2 = new Airplane("Boing 749", 180, 40.1, 0.289, 380);
    
    //Flight flight = new Flight("00", "2022/12/12", "2022/12/12", 45, airplane1, airport1, aiport2, this.flight.calculateDistanceBetweenDepartureArrival(), this.flight.calculateEmissionCo2());

    public ArrayList<DispF> displayFlights = new ArrayList<DispF>();

    public void addAddress(){
        addresses.add(paris);
        addresses.add(nantes);
        addresses.add(bordeaux);
        addresses.add(lyon);
    }
    public void addAirport(){
        airports.add(aiport2);
        airports.add(airport1);
        airports.add(aiport3);
        airports.add(aiport4);
    }
    /*public void addedAirplane(){
        this.company.getMeanTransport().add(this.company.createAirplane("Airbus 342", 90, 30.9, 0.309, 320));
        this.company.getAirplanes().add(this.company.createAirplane("Boing 749", 180, 40.1, 0.289, 380));
    }*/
    public void addMeanTransports(){
        this.company.addMeanTransport(new Airplane("Boieng 2132", 80, 20.5, 0.6, 450));
        this.company.addMeanTransport(new Airplane("Boieng 512", 120, 43.2, 0.7, 530));
        this.company.addMeanTransport(new Helicopter("Valkyrie", 8, 20, 0.4, 300));
    }
    public void addedFlight(){
       // this.company.getFlights().add(flight);
        this.company.getFlights().add(this.company.createFlight("01", LocalDateTime.of(2022, 12, 14, 13, 32), LocalDateTime.of(2022, 12, 14, 19, 19), 80,this.company.getAirplanes().get(0), airport1, aiport2));
        this.company.getFlights().add(this.company.createFlight("02", LocalDateTime.of(2022, 12, 20, 10, 30), LocalDateTime.of(2022, 12, 20, 12, 10), 70, this.company.getAirplanes().get(1), airport1, aiport2));
    }
    public void addedCrewMember(){
        this.company.getCrewMembers().add(this.company.createCrewMember("Lola","Durant", "08796745", "lg@gmail.com",LocalDate.of(1990,12,3), 1, LocalDate.now(), "copilote"));
        this.company.getCrewMembers().add(this.company.createCrewMember("Steve","Jonhson", "06792245", "sj@gmail.com",LocalDate.of(2001, 11, 23), 2, LocalDate.now(), "stewart"));
    }

    public void added(){
        //addedAirplane();
        addMeanTransports();
        addedFlight();
        addedCrewMember();
        for(int i=0; i<this.company.getFlights().size(); i++){
            displayFlights.add(new DispF(company.getFlights().get(i).getNumber(), company.getFlights().get(i).getDepartureTime().toString(), company.getFlights().get(i).getArrivalTime().toString(), 
            this.company.getFlights().get(i).getBasePrice(), company.getFlights().get(i).getMeanTransport().getName(),
            this.company.getFlights().get(i).getDepartureAirport().getName(), company.getFlights().get(i).getArrivalAirport().getName(), 0, 0, 0));
        }
    }

    public ObservableList<CrewMember> dataCM = FXCollections.observableArrayList();
    public ObservableList<Airplane> dataA = FXCollections.observableArrayList();
    public ObservableList<DispF> data = FXCollections.observableArrayList();

    public void addedDataList(){
        added();
        for(CrewMember member : this.company.getCrewMembers()){
            dataCM.add(member);
        }
        for(Airplane airplane : this.company.getAirplanes()){
            dataA.add(airplane);
        }
        for(DispF flight : displayFlights){
            data.add(flight);
        }
    }

    // Home Page -----------------------------------------------
    @FXML
    private AnchorPane VBox;

    @FXML
    void btnClickedAdmin(ActionEvent event) {
        System.out.println("Hello Admin !");
        Address ad  = new Address("France", "Paris", "75000", 45.56, 4.34);
        //addresses.add(ad);
        System.out.println(ad.getCity());

        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("views/AdminHome.fxml"));
            VBox.getChildren().removeAll();
            VBox.getChildren().setAll(root2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnClickedClientLogin() {
        System.out.println("Hello Client !");
        Parent root1;
        try {
            root1 = FXMLLoader.load(getClass().getResource("views/loginClient.fxml"));
            VBox.getChildren().removeAll();
            VBox.getChildren().setAll(root1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnClickedCrewMemberLogin(ActionEvent event) {
        System.out.println("Hello Crew Member !");
        Parent root3;
        try {
            root3 = FXMLLoader.load(getClass().getResource("views/AdminCrewMember.fxml"));
            VBox.getChildren().removeAll();
            VBox.getChildren().setAll(root3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Login Client -----------------------------------------------
    /*@FXML
    private TextField emailClientLogin;

    @FXML
    private PasswordField passClientLogin;

    @FXML
    void btnClickedCurrentClient(ActionEvent event) {
        System.out.println("Welcome client");

    }

    @FXML
    void btnClickedInscriptionClient(ActionEvent event) {

    }*/

    // Control Login Crew Member -----------------------------------------------
    
    @FXML
    private AnchorPane ancre;

    @FXML
    private TextField emailMemberLogin;

    @FXML
    private Label id;

    @FXML
    private PasswordField passMemberLogin;

    @FXML
    void btnClickedCurrentMember(ActionEvent event) {
        //id.setText(nantes.getCity());
    }

    // Control Inscription Client -----------------------------------------------
    /*@FXML
    private DatePicker birthClient;

    @FXML
    private TextField emailClient;

    @FXML
    private TextField firstNameClient;

    @FXML
    private TextField idUser;

    @FXML
    private TextField lastNameClient;

    @FXML
    private TextField phoneClient;

    @FXML
    void btnClickedInscription(ActionEvent event) {
        String fName = firstNameClient.getText();
        String lName = lastNameClient.getText();
        String phone = phoneClient.getText();
        String email = emailClient.getText();
        //String birth = birthClient.getValue();
        // LocalDate inscription = inscriptionDateClient.getValue();
        //this.company.addClient(this.company.createClient(fName, lName, phone, email, birth));
        System.out.println(this.company.getClients().get(0).getLastName());
    }*/

    // Control Administration Company -----------------------------------------------
   @FXML
   private AnchorPane fond;

    @FXML
    void btnAirplanes(ActionEvent event) {
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("views/airplanesAdmin.fxml"));
            fond.getChildren().removeAll();
            fond.getChildren().setAll(root2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnCrewMembers(ActionEvent event) {
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("views/AdminCrewMembers.fxml"));
            fond.getChildren().removeAll();
            fond.getChildren().setAll(root2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnFlights(ActionEvent event) {
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("views/Flights.fxml"));
            fond.getChildren().removeAll();
            fond.getChildren().setAll(root2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnHomePage(ActionEvent event) {
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("views/home.fxml"));
            fond.getChildren().removeAll();
            fond.getChildren().setAll(root2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnLoginCM(ActionEvent event) {
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("views/loginCrewMember.fxml"));
            fond.getChildren().removeAll();
            fond.getChildren().setAll(root2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnLoginClient(ActionEvent event) {
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("views/loginClient.fxml"));
            fond.getChildren().removeAll();
            fond.getChildren().setAll(root2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Controller Flights ----------------------------------------
    @FXML
    private TextField airplaneName;

    @FXML
    private TextField airportArrival;

    @FXML
    private TextField airportDeparture;

    @FXML
    private DatePicker arrivalDate;

    @FXML
    private TextField basePrice;

    @FXML
    private TableColumn<Flight, String> cln_Airplane;

    @FXML
    private TableColumn<Flight, String> cln_AirportA;

    @FXML
    private TableColumn<Flight, String> cln_AirportD;

    @FXML
    private TableColumn<Flight, String> cln_DateA;

    @FXML
    private TableColumn<Flight, String> cln_DateD;

    @FXML
    private TableColumn<Flight, Integer> cln_Id;

    @FXML
    private TableColumn<Flight, Double> cln_Price;

    @FXML
    private TableColumn<Flight, Integer> timeDep;

    @FXML
    private TableColumn<Flight, Integer> timeArr;

    @FXML
    private DatePicker departureDate;

    @FXML
    private TextField hourD;

    @FXML
    private TextField minutesD;

    @FXML
    private TextField hourA;

    @FXML
    private TextField minutesA;

    @FXML
    private TableView<DispF> tableFlights;

    @FXML
    private TableColumn<Flight, Double> cln_Co2;


    @FXML
    private TableColumn<Flight, Double> cln_Distance;

    @FXML
    private TableColumn<Flight, Double> cln_Duration;

    @FXML
    private TextField deleteFlight;


    public double calculateDistanceBetweenDepartureArrival(double latA, double latB, double lonA, double lonB){
        double distance = 6371 * Math.acos(Math.sin(Math.toRadians(latA)) * Math.sin(Math.toRadians(latB)) + Math.cos(Math.toRadians(lonA-lonB)) * Math.cos(Math.toRadians(latA)) * Math.cos(Math.toRadians(latB)));
        return distance;
    }

    public double searchLon(String airportD){
        addAddress();
        double lonA = 0;
        for(Address address : addresses){
            System.out.println(airportD+" "+address.getCity());
            if(airportD.equals(address.getCity())){
                lonA = address.getLongitutude();
                //latA = address.getLatitude();
                return lonA;
            }else{
                //System.out.println("Error the city doesn't exist in the airport list.");
            }
        }
        return lonA;
    }

    public double searchLat(String airportD){
        addAddress();
        double latA = 0;
        for(Address address : addresses){
            if(airportD.equals(address.getCity())){
                System.out.println("if");
                //double lonA = address.getLongitutude();
                latA = address.getLatitude();
                return latA;
            }else{
               // System.out.println(address.getCity());
            }
        }
        return latA;
    }

    public double calculateCo2Emission(double distance, double fuel, double emission) {
        double Co2 = (distance + fuel) * emission;
        return Co2;
    }

    public double searchFuelValue(String transport){
        addMeanTransports();
        double fuel = 0;
        for(MeanTransport trans : this.company.getAirplanes()){
            if(transport.equals(trans.getName())){
                System.out.println("if");
                fuel = trans.getAnnualFuelConsumption();
                return fuel;
            }else{
            }
        }
        return fuel;
    }

    public double searchEmissionValue(String transport){
        addMeanTransports();
        double emission = 0;
        for(MeanTransport trans : this.company.getAirplanes()){
            System.out.println(trans.getName());
            if(transport.equals(trans.getName())){
                System.out.println("if");
                emission = trans.getEmissionFactor();
                return emission;
            }else{
            }
        }
        return emission;
    }

    public double searchSpeedValue(String transport){
        addMeanTransports();
        double speed = 0;
        for(MeanTransport trans : this.company.getAirplanes()){
            System.out.println(trans.getspeedMax());
            if(transport.equals(trans.getName())){
                System.out.println("if");
                speed = trans.getspeedMax();
                return speed;
            }else{
            }
        }
        return speed;
    }
   
    public int iDFlight = data.size();

    @FXML
    void btnClickedCreateFlight() {
        LocalDate dF = departureDate.getValue();
        String dD = dF.toString();
        LocalDate aF = arrivalDate.getValue();
        String aA = aF.toString();
        String price = basePrice.getText();
        int pce = Integer.parseInt(price);
        String airplane = airplaneName.getText();
        String airportD = airportDeparture.getText();
        String airportA = airportArrival.getText();
        String depHour = hourD.getText();
        int depHourInt = Integer.parseInt(depHour);
        String arrHHour = hourA.getText();
        int arrHourInt = Integer.parseInt(arrHHour);
        String depMin = minutesD.getText();
        int depMinInt = Integer.parseInt(depMin);
        String arrMin = minutesA.getText();
        int arrMinInt = Integer.parseInt(arrMin);
        LocalTime timeDep = LocalTime.of(depHourInt, depMinInt);
        LocalTime timeArr = LocalTime.of(arrHourInt, arrMinInt);
        LocalDateTime dateTimeDep= LocalDateTime.of(dF, timeDep);
        LocalDateTime dateTimeArr = LocalDateTime.of(aF, timeArr);
        Duration getDuration = Duration.between(dateTimeDep, dateTimeArr);
        System.out.println(getDuration);
        double distance = calculateDistanceBetweenDepartureArrival(searchLat(airportA), searchLat(airportD), searchLon(airportA), searchLon(airportD));
        double emission = calculateCo2Emission(distance, searchFuelValue(airplane), searchEmissionValue(airplane));
        double time = searchSpeedValue(airplane) / distance;

        iDFlight++;
        String iDtoString = Integer.toString(iDFlight);
        DispF f = new DispF(iDtoString, dD, aA, pce, airplane, airportD, airportA, time, distance, emission);
        this.data.add(f);

        System.out.println(searchFuelValue(airplane));
        System.out.println(searchEmissionValue(airplane));
        System.out.println("Distance : "+distance);
        System.out.println("carbone : "+emission);

        departureDate.setValue(null);
        arrivalDate.setValue(null);
        basePrice.setText(" ");
        airplaneName.setText(" ");
        airportDeparture.setText(" ");
        airportArrival.setText(" ");
    }

    @FXML
    void btnClickedDeleteFlight() {
        System.out.println("delete !");
        String id = this.deleteFlight.getText();
        for(int i=0; i<this.displayFlights.size(); i++){
            System.out.println(id+" "+displayFlights.get(i).getNumber());
            if(id.equals(this.data.get(i).getNumber())){
                this.data.remove(i);
                i = this.displayFlights.size();
                this.deleteFlight.setText(" ");
            }
        }
    }

    @FXML
    void btnDisplayFlight(ActionEvent event) {
        addedDataList();
        display();
    }

    public void display(){
        cln_Id.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("number"));
        cln_DateD.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureTime"));
        cln_DateA.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalTime"));
        cln_Price.setCellValueFactory(new PropertyValueFactory<Flight, Double>("basePrice"));
        cln_Airplane.setCellValueFactory(new PropertyValueFactory<Flight, String>("airplane"));
        cln_AirportD.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureAirport"));
        cln_AirportA.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrivalAirport")); 
        cln_Duration.setCellValueFactory(new PropertyValueFactory<Flight, Double>("duration"));
        cln_Distance.setCellValueFactory(new PropertyValueFactory<Flight, Double>("distance"));
        cln_Co2.setCellValueFactory(new PropertyValueFactory<Flight, Double>("emissionCarbone"));
        this.tableFlights.setItems(this.data);
    }

    // Admin CrewMembers -----------------------------------
    @FXML
    private DatePicker birthCM;

    @FXML
    private TextField emailCM;

    @FXML
    private TableColumn<CrewMember, String> emailM;

    @FXML
    private TextField fNameCM;

    @FXML
    private TableColumn<CrewMember, String> fNameM;

    @FXML
    private TextField lNameCM;

    @FXML
    private TableColumn<CrewMember, String> lNameM;

    @FXML
    private TextField numCM;

    @FXML
    private TableColumn<CrewMember, String> numberM;

    @FXML
    private TextField statutCM;

    @FXML
    private TextField searchCM;

    @FXML
    private TableColumn<CrewMember, String> statutM;

    @FXML
    private TableColumn<CrewMember, Integer> iDCM;

    @FXML
    private TableView<CrewMember> table_adminMember;

    public int idCM = 0;
    
    @FXML
    public void btnClickedCreateMember() {
        String fName = fNameCM.getText();
        String lName = lNameCM.getText();
        String phone = numCM.getText();
        String email = emailCM.getText();
        String finalEmail = email + "@compagnie.com";
        String job = statutCM.getText();
        LocalDate birth = birthCM.getValue();
        for(CrewMember member : dataCM){
            if(phone.equals(member.getPhoneNumber()) && email.equals(member.getEmailAddress())){
                System.out.println("error phone number or email member");
                Alert alert = new Alert(AlertType.ERROR, "Email ou numero déjà existant", javafx.scene.control.ButtonType.OK);
                alert.showAndWait();
            } else {
                idCM++;
                dataCM.add(this.company.createCrewMember(fName,lName, phone, email,birth, idCM, LocalDate.now(), job));
                this.company.createCrewMember(fName,lName, phone, finalEmail,birth, idCM, LocalDate.now(), job);
                /*for(CrewMember member : this.company.getCrewMembers()){
                    System.out.println(member.getFirstName());
                }*/
                fNameCM.setText(" ");
                lNameCM.setText(" ");
                numCM.setText(" ");
                emailCM.setText(" ");
                statutCM.setText(" ");
            }
        }
        //company.createCrawMember("Lola","Durant", "08796745", "lg@gmail.com","22/12/2002", 01, "3/12/2022", "copilote");
        //dataCM.add(this.company.createCrawMember("Lola","Durant", "08796745", "lg@gmail.com","22/12/2002", 01, "3/12/2022", "copilote"));
        //dataCM.add(this.company.createCrawMember("Lol","Durant", "08796745", "l@gmail.com","22/12/2002", 01, "3/12/2022", "copilote"));

    }

    @FXML
    public void btnClickedDeleteCM() {
        String idMember = searchCM.getText();
        int memberId = Integer.parseInt(idMember);
        for(int i=0; i<this.company.getCrewMembers().size(); i++){
            if(memberId == this.dataCM.get(i).getId()){
                System.out.println("if");
                this.dataCM.remove(i);
                i = this.company.getCrewMembers().size();
            }
        }
        
    }

    @FXML
    public int btnClickedSearchCM() {
        String idMember = searchCM.getText();
        int memberId = Integer.parseInt(idMember);
        for(int i=0; i<company.getCrewMembers().size(); i++){
            if(memberId == this.dataCM.get(i).getId()){
                fNameCM.setText(dataCM.get(i).getFirstName());
                lNameCM.setText(dataCM.get(i).getLastName());
                numCM.setText(dataCM.get(i).getPhoneNumber());
                emailCM.setText(dataCM.get(i).getEmailAddress());
                statutCM.setText(dataCM.get(i).getJobDescription());
                i = this.company.getCrewMembers().size();
                return i-1;
            }
        }
        return 0;
    }

    @FXML
    public void btnClickedModifyCM() {
        btnClickedDeleteCM();
        btnClickedCreateMember();
    }

    @FXML
    public void btnDisplayCM(ActionEvent event) {
        addedDataList();
        showListCM();
    }

    public void showListCM(){
        //table_adminMember.getItems().clear();
        iDCM.setCellValueFactory(new PropertyValueFactory<CrewMember, Integer>("id"));
        fNameM.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("firstName"));
        lNameM.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("lastName"));
        numberM.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("phoneNumber"));
        emailM.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("emailAddress"));
        statutM.setCellValueFactory(new PropertyValueFactory<CrewMember, String>("jobDescription"));
        table_adminMember.setItems(dataCM);
    }

    @FXML
    public void btnHome(ActionEvent event) {
        Stage home = new Stage();
        Parent rooot;
        try {
            rooot = FXMLLoader.load(getClass().getResource("views/home.fxml"));
            Scene scene = new Scene(rooot);
            home.setTitle("Air France");
            home.setScene(scene);
            home.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* 
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("views/home.fxml"));
            birthDateCM.getChildren().removeAll();
            birthDateCM.getChildren().setAll(root2);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    // Airplanes Admin -----------------------------------------------------
    @FXML
    private TextField MarkDelete;

    @FXML
    private TextField annualFuel;

    @FXML
    private TableColumn<Airplane, Double> c_emission;

    @FXML
    private TableColumn<Airplane, Double> c_fuel;

    @FXML
    private TableColumn<Airplane, String> c_mark;

    @FXML
    private TableColumn<Airplane, Integer> c_seats;

    @FXML
    private TableColumn<Airplane, Integer> c_speed;

    @FXML
    private TextField emissionFactor;

    @FXML
    private TextField nbSeats;

    @FXML
    private TextField markA;

    @FXML
    private TextField speed;

    @FXML
    private TableView<Airplane> table_airplanes;


    @FXML
    public ObservableList<Airplane> btnCreateAirplane() {
        String mark = markA.getText();
        String seats = nbSeats.getText();
        int s = Integer.parseInt(seats);
        String fuelAnnual = annualFuel.getText();
        double fA = Integer.parseInt(fuelAnnual);
        String emission = emissionFactor.getText();
        double e = Integer.parseInt(emission);
        String speedAirplane = speed.getText();
        int sp = Integer.parseInt(speedAirplane);
        this.dataA.add(new Airplane(mark, s, fA, e, sp));
        this.company.addMeanTransport(new Airplane(mark, s, fA, e, sp));
        markA.setText(" "); 
        nbSeats.setText(" ");
        annualFuel.setText(" ");
        emissionFactor.setText(" ");
        speed.setText(" ");
        //Alert alert = new Alert(AlertType.CONFIRMATION, "Airplane added with success !", javafx.scene.control.ButtonType.OK);
        //alert.showAndWait();
        return dataA;
    }

    @FXML
    void btnDeleteAirplane() {
        String mark = MarkDelete.getText();
        for(int i=0; i<this.dataA.size(); i++){
            if(mark.equals(dataA.get(i).getName())){
                System.out.println("if");
                this.dataA.remove(i);
                MarkDelete.setText(" ");
                i = this.company.getAirplanes().size();
            }
            System.out.println(this.company.getAirplanes().get(i).getName());
        }
    }

    @FXML
    void btnDsplayAirplane() {
        showListAirplanes();
    }

    public void showListAirplanes(){
       // table_airplanes.getItems().clear();
        c_mark.setCellValueFactory(new PropertyValueFactory<Airplane, String>("name"));
        c_seats.setCellValueFactory(new PropertyValueFactory<Airplane, Integer>("maxNbSeats"));
        c_fuel.setCellValueFactory(new PropertyValueFactory<Airplane, Double>("annualFuelConsumption"));
        c_emission.setCellValueFactory(new PropertyValueFactory<Airplane, Double>("emissionFactor"));
        //c_speed.setCellValueFactory(new PropertyValueFactory<Airplane, Integer>("speed"));
        table_airplanes.setItems(dataA);
    }
