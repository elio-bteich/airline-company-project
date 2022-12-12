package src.controllers;

import java.time.LocalDateTime;

import src.Main;
import src.models.*;
/**
* Class that defines a Controller.
* This class is the base for the Client, CrewMember and Company Administration controllers.
* Controller's only attribute is the Main.
*/
public class Controller {
    protected Main app;

    /** 
    * Constructor of a Controller from a Main application
    *
    * @param app the Main application
    */
    public Controller(Main app) {
        this.app = app;
    }

    /**
     * Method that checks if the luggage quantity of a client is valid
     * in a given mean of transport
     *
     * @param meanTransport the mean of transport we want to check luggages quantity
     * @param lugType a string that describes a luggage type
     * @param lugQty an integer that gives the amount of luggages for corresponding type
     *
     * @return true if the quantity is inferior or equal to the maximum limit, false if not 
     */
    public boolean isLuggageQtyValid(MeanTransport meanTransport, String lugType, int lugQty) {
        if (meanTransport instanceof Airplane) {
            switch (lugType) {
                case "Hand Luggage":
                    if (lugQty > Airplane.getMaxHandLuggagePerClient()) {
                        return false;
                    }
                    break;
                case "Checked Luggage":
                    if (lugQty > Airplane.getMaxCheckedLuggagePerClient()) {
                        return false;
                    }
            }
            return true;
        } else {
            switch (lugType) {
                case "Hand Luggage":
                    if (lugQty > Helicopter.getMaxHandLuggagePerClient()) {
                        return false;
                    }
                    break;
                case "Checked Luggage":
                    if (lugQty > Helicopter.getMaxCheckedLuggagePerClient()) {
                        return false;
                    }
            }
            return true;
        }
    }

    /**
    * Method that checks if two flights happen at the same time 
    *
    * @param first the first flight
    * @param second the second flight
    *
    * @return the boolean value given by intersectsTime(), with first and second departure and arrival times.
    */
    public static boolean intersectsFlight(Flight first, Flight second) {
        return intersectsTime(first.getDepartureTime(), first.getArrivalTime(), second.getDepartureTime(), second.getArrivalTime());
    }

    /**
    * Method called by intersectsFlight() that checks if the departure and arrival time of the two flights intersects
    *
    * @param departureTime1 the departure time of the first flight
    * @param arrivalTime1 the arrival time of the first flight    
    * @param departureTime2 the departure time of the second flight
    * @param arrivalTime2 the arrival time of the second flight
    *
    * @return false if the first flight takes off after the second flight lands, or if the first flight lands before the second flight takes off,
    *         and true if not
    */
    public static boolean intersectsTime(LocalDateTime departureTime1, LocalDateTime arrivalTime1, LocalDateTime departureTime2, LocalDateTime arrivalTime2) {
        return !(departureTime1.isAfter(arrivalTime2) || arrivalTime1.isBefore(departureTime2));
    }
}
