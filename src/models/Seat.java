package src.models;

import java.util.ArrayList;
/**
 * Class who represents a Seat
 * A Seat is defined by its number, an array of the checkins its in,
 * the flight class of this seat and the mean of transport its in.
 */
public class Seat {

    private int noSeat;
    private ArrayList<Checkin> checkins;
    private FlightClass flightClass;
    private MeanTransport meanTransport;

    /**
    * Constructor of a Seat from its number, FlightClass and Mean of Transport
    * @param noS the number of the Seat
    * @param fClass the FlightClass of the Seat
    * @param meanTransport the mean of transport of the Seat
    */
    public Seat (int noS, FlightClass fClass, MeanTransport meanTransport) {
        this.noSeat = noS;
        this.checkins = new ArrayList<Checkin>();
        this.flightClass = fClass;
        this.meanTransport = meanTransport;
    }

    /**
    * Gets the number of a Seat
    * 
    * @return this number
    */
    public int getNoSeat() {
        return this.noSeat;
    }

    /**
    * Sets the number of a Seat
    * 
    * @param noS the number that will be set to this
    */
    public void setNoSeat(int noS) {
        this.noSeat = noS;
    }

    /**
    * Gets the checkin array of a Seat
    * 
    * @return this checkin array
    */
    public ArrayList<Checkin> getCheckins() {
        return this.checkins;
    }

    /**
    * Gets the flight class of a Seat
    * 
    * @return this flightClass
    */
    public FlightClass getFlightClass(){
        return flightClass;
    }

    /**
    * Sets the flight class of a Seat
    * 
    * @param fClass the flight class that will be set to this
    */
    public void setFlightClass(FlightClass fClass){
        this.flightClass = fClass;
    }

    /**
    * Gets the mean of transport of a Seat
    * 
    * @return this meanTransport
    */
    public MeanTransport getMeanTransport(){
        return this.meanTransport;
    }

    /**
    * Sets the mean of transport of a Seat
    * 
    * @param meanTransport the mean of transport that will be set to this
    */
    public void setMeanTransport(MeanTransport meanTransport){
        this.meanTransport = meanTransport;
    }

    /**
     * Checks seat availability for a specific flight
     * 
     * @param flight the flight where we want to check the availability of the seat
     * @return true if seat available, false otherwise
     */
    public boolean isAvailable(Flight flight) {
        return !flight.getReservedSeats().contains(this);
    }

    public static Seat getSeatByNumber(ArrayList<Seat> seats, int no) throws Exception {
        for (Seat seat: seats) {
            if (seat.noSeat == no) {
                return seat;
            }
        }
        throw new Exception("Seat not found!");
    }

    @Override
    public String toString() {
        return "\nSeat number: " + this.noSeat + ", Seat flight class: " + this.flightClass.getDescription();
    }
}
