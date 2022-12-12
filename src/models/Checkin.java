package src.models;

import java.time.LocalDateTime;
/**
 * Class who represents a Checkin before a flight
 * A Checkin is defined by its date, flight reservation and reserved seat
 */
public class Checkin {
    private LocalDateTime checkinDate;
    private Reservation reservation;
    private Seat seat;

    /**
     * Constructor of a Chekin from its attributes
     * 
     * @param date date of the reservation
     * @param reservation reservation of a client 
     * @param seat seat attributed to this reservation
     */
    public Checkin(LocalDateTime date, Reservation reservation, Seat seat) {
        this.checkinDate = date;
        this.reservation = reservation;
        this.seat = seat;
    }

    /**
     * Getter of a checkin date
     * 
     * @return this checkin date
     */
    public LocalDateTime getCheckinDate(){
        return checkinDate;
    }
    
    /**
     * Setter of a checkin date
     * 
     * @param date the date that will be set to this
     */
    public void setCheckinDate(LocalDateTime date){
        this.checkinDate = date;
    }

    /**
     * Getter of a reservation
     * 
     * @return this reservation
     */
    public Reservation getReservation(){
        return reservation;
    }

    /**
     * Setter of a reservation
     * 
     * @param reserv the reservation that will be set to this
    */
    public void setReservation(Reservation reserv){
        this.reservation = reserv;
    }

    /**
     * Getter of a Seat
     *
     * @return this seat
     */
    public Seat getSeat(){
        return seat;
    }

    /**
     * Setter of a Seat
     * 
     * @param seat the seat that will be set this
     */
    public void setSeat(Seat seat){
        this.seat = seat;
    }
}
