package src.models;

/**
 * Class who represents a Hand Luggage, that inherits from Luggage
 * This class only implements one Luggage method and creates the HandLuggage object 
 * with constant values
 */
public class HandLuggage extends Luggage {
    /**
     * Creates the hand luggage with the default maximum dimensions and weight 
     * set by the program. 
     * The airline company will have the possibility to change them, only 
     * the hand luggages created after the modification will have the new 
     * dimensions and weight.
     * 
     * @param id the id of the luggage, the next created hand luggage will 
     * have this id incremented by 1
     */
    public HandLuggage(int id) {
        super(id, 10, 35, 25, 55);
    }

    @Override
    public String toString() {
        return "Hand Luggage";
    }
}
