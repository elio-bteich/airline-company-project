package src.models;
/**
 * Class who represents a Checked Luggage, that inherits from Luggage
 * This class only implements one Luggage method and creates the CheckedLuggage object with constant values
 */
public class CheckedLuggage extends Luggage {
    /**
     * Creates the checked luggage with the default maximum dimensions and weight 
     * set by the program. 
     * The airline company will have the possibility to change them, only 
     * the checked luggages created after the modification will have the new 
     * dimensions and weight.
     * 
     * @param id the id of the luggage, the next created checked luggage will 
     * have this id incremented by 1
     */
    public CheckedLuggage(int id) {
        super(id, 25, 75, 45, 90);
    }

    @Override
    public String toString() {
        return "Checked Luggage";
    }
}