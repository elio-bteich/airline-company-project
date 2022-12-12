package src.views;

import java.util.ArrayList;

import src.Main;
import src.models.*;
import src.controllers.CrewMemberController;

/**
 * Class who represents a CrewMemberView, that inherits from View.
 * A CrewMemberView is defined by the two attributes from View and the corresponding Controller
 */
public class CrewMemberView extends View{

    private CrewMemberController controller;
	
    /**
     * Constructor of an CrewMemberView from an application and a controller
     * 
     * @param app the Main application
     * @param controller the CrewMemberController
     */
    public CrewMemberView(Main app, CrewMemberController controller) {
        super(app);
        this.controller = controller;
    }

    /**
    * Method that checks if a CrewMember is logged in, and if not calls a method to log in
    */
    public void authMiddleware() {
        if (app.getAuthCrewMember() == null) {
            this.login();
        }
    }

    /**
    * Method that allows a crew member to log himself in by telling his email on the terminal
    */
    public void login() {
        int cpt = 0;
        clearConsole();
        String email;

        System.out.println("Crew Member Login");
        System.out.print("Enter your email: ");
        email = scanner.nextLine();


        while (app.getAuthCrewMember() == null && cpt != 3) {
            try {
                this.controller.login(email);
            } catch (Exception e) {
                System.out.println("\nCrew member not found");
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
            app.menu();
        } else {
            this.controller.menu();
        }
    }

    /**
    * Method that displays a console menu and allows to chose an action to do 
    * According to the user choice on the terminal, this method will call different methods
    */
    public void menu() {
        clearConsole();
        System.out.println("\nSelect an option:");
        System.out.println("1- Check your next flights");
        System.out.println("2- Check flights history");
        System.out.println("3- Check your personal information");
        System.out.println("4- Logout");
        System.out.print("Enter your option: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                this.controller.nextFlightsView();
                break;
            case 2:
                this.controller.flightsHistoryView();
                break;
            case 3:
                this.controller.personalInformationView();
                break;
            case 4:
                this.controller.logout();
                break;
        }
        // if the crew member didn't choose to logout display the menu again
        if (option < 1 || option > 4) {
            this.controller.menu();
        }
    }

    /**
    * Method that prints on the terminal all the future flights of a CrewMember
    * 
    * @param nextFlights the future Flights array
    */
    public void nextFlight(ArrayList<Flight> nextFlights) {
        clearConsole();
        System.out.println("Next Flights:");
        System.out.println(nextFlights);
        clickToExit();
        this.menu();
    }

    /**
    * Method that prints on the terminal all the past flights of a CrewMember
    * 
    * @param previousFlights the past Flights array
    */
    public void flightsHistory(ArrayList<Flight> previousFlights) {
        clearConsole();
        System.out.println("Previous Flights");
        System.out.println(previousFlights);
        clickToExit();
        this.menu();
    }

    /**
    * Method that allows a CrewMember to check his personal informations and possibly call a method to change them
    */
    public void personalInfomation() {
        clearConsole();
        char option;
        System.out.println("\nPersonal info:");
        System.out.println(app.getAuthCrewMember());
        do {
            System.out.print("\nWant to modify your personal info (y/n)? ");
            option = scanner.next().charAt(0);
        } while (Character.compare(option, 'y') != 0 && Character.compare(option, 'n') != 0);
        
        if (Character.compare(option, 'y') == 0) {
            this.modifyCrewMemberInfo();
        } else {
            this.menu();
        }
    }

    /**
    * Method called by personalInformation() to change this CrewMember informations
    * This method prints a menu on the terminal so the CrewMember can choose which info he wants to change, and call the corresponding method
    */
    public void modifyCrewMemberInfo() {
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
                this.menu();
                break;
        }

        if (option < 1 || option > 5) {
            this.modifyCrewMemberInfo();
        }
    }

    /**
    * Method that asks on the terminal a new first name to set to this CrewMember
    */
    public void modifyFirstName() {
        clearConsole();
        System.out.print("Enter new first name: ");
        String newFirstName = scanner.nextLine();
        this.controller.modifyFirstName(newFirstName);
        clearConsole();
        System.out.println("Your first name has been modify with success !");
        clickToExit();

    }

    /**
    * Method that asks on the terminal a new last name to set to this CrewMember
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
    * Method that asks on the terminal a new phone number to set to this CrewMember
    */
    public void modifyPhoneNumber() {
        clearConsole();
        System.out.print("Enter new phone number: ");
        String newPhoneNumber = scanner.nextLine();
        this.controller.modifyPhoneNumber(newPhoneNumber);
        clearConsole();
        System.out.println("Your phone number has been successfully modified !");
        clickToExit();
    }

    /**
    * Method that asks on the terminal a new email address to set to this CrewMember
    */
    public void modifyEmailAddress() {
        clearConsole();
        System.out.print("Enter new email address: ");
        String newEmailAddress = scanner.nextLine();
        this.controller.modifyEmailAddress(newEmailAddress);
        clearConsole();
        System.out.println("Your email has been successfully modified !");
        clickToExit();
    }

}
