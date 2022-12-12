package src.views;

import java.util.Scanner;
import src.models.*;
import java.util.ArrayList;
import src.Main;

/**
 * Abstract class who represents a console View
 * A view uses as attributes a Scanner to read user data on the console, and a Main application
 */
public abstract class View {
    protected Scanner scanner = new Scanner(System.in);
    protected Main app;

    /**
    * Constructor of a View from a Main application
    *
    * @param app the Main application
    */
    public View(Main app) {
        this.app = app;
    }

    /**
    * Method that clears the terminal console
    */
    public static void clearConsole() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }

    /**
    * Method that prints one last message before exiting the console
    */
    public static void exit() {
        System.out.println("Good Bye!");
    }

    /**
    * Method that prints every object from a mean of transport array
    *
    * @param meanTransports an array of every mean of transport that will be printed
    */
    public void displayMeanTransports(ArrayList<MeanTransport> meanTransports) {
        for (MeanTransport meanTransport : meanTransports) {
            System.out.println(meanTransport);
        }
    }

    /**
     * Method that displays the maximum quantity of luggages per client authorized
     * in the given mean of transport
     *
     * @param meanTransport the mean of transport that contains the values we want to display
     */
    public void displayLuggageTypes(MeanTransport meanTransport) {
        int maxH, maxC;
        if (meanTransport instanceof Airplane) {
            maxH = Airplane.getMaxHandLuggagePerClient();
            maxC = Airplane.getMaxCheckedLuggagePerClient(); 
        } else {
            maxH = Helicopter.getMaxHandLuggagePerClient();
            maxC = Helicopter.getMaxCheckedLuggagePerClient();
        }
        System.out.println("1 : Hand Luggage (max quantity:" + maxH + ")");
        System.out.println("2 : Checked Luggage (max quantiy:" + maxC + ")");
    }

    /**
     * Method that displays on terminal the authorized luggages information
     * 
     * @param meanTransport the mean transport whose information we want to display
     */
    public void displayLuggageAllowance(MeanTransport meanTransport) {
        if (meanTransport instanceof Airplane) {
            displayAiplaneLuggageAllowance();
        } else {
            displayHelicopterLuggageAllowance();
        }
    }

    public static void displayAiplaneLuggageAllowance() {
        System.out.println(Airplane.getMaxHandLuggagePerClient() + "x Hand Luggage");
        System.out.println(Airplane.getMaxCheckedLuggagePerClient() + "x Checked Luggage\n");
    }

    public static void displayHelicopterLuggageAllowance() {
        System.out.println(Helicopter.getMaxHandLuggagePerClient() + "x Hand Luggage");
        System.out.println(Helicopter.getMaxCheckedLuggagePerClient() + "x Checked Luggage\n");
    }

    public void displayLuggagesPrice(MeanTransport meanTransport) {
        if (meanTransport instanceof Airplane) {
            displayAirplaneLuggagesPrice();
        } else {
            displayHelicopterLuggagesPrice();
        }
    }

    public static void displayAirplaneLuggagesPrice() {
        System.out.println("Hand Luggage: " + Airplane.getPricePerHandLuggage() + "$");
        System.out.println("Checked Luggage: " + Airplane.getPricePerCheckedLuggage() + "$\n");
    }

    public static void displayHelicopterLuggagesPrice() {
        System.out.println("Hand Luggage: " + Helicopter.getPricePerHandLuggage() + "$");
        System.out.println("Checked Luggage: " + Helicopter.getPricePerCheckedLuggage() + "$\n");
    }

    /**
    * Method that prints every object from a flight array
    *
    * @param flights an array of every flight that will be printed
    */
    public void displayFlights(ArrayList<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }
    
    /**
    * Method that displays the type of the means of transport
    */
    public void displayMeansTransportType(){
        System.out.println("1- Airplane");
        System.out.println("2- Helicopter");
    }

    /**
    * Method that forces the user to enter a key to exit at the end of the program
    * This method will be used at the end of many display methods before clearing the terminal
    */
    public void clickToExit() {
        System.out.println("\nEnter a key to exit...");
        scanner.nextLine();
    }

    /**
    * Method that forces the user to enter a key to continue
    * This method will be used at the end of many display methods before clearing the terminal
    */
    public void clickToContinue() {
        System.out.println("\nEnter a key to continue...");
        scanner.nextLine();
    }

    /**
    * Method that forces the user to enter a key to continue
    * This method will be used at the end of many display methods before clearing the terminal
    */
    public void clickToPay() {
        System.out.println("\nEnter a key to pay...");
        scanner.nextLine();
    }
}
