package src.models;

import java.util.ArrayList;

/**
 * Abstract class who represents a Luggage
 * A Luggage is defined by its id and maximum dimensions (weight, lenght, width and height)
 */
public abstract class Luggage {
    private int id;
    private double maxWeight;
    private double maxLength;
    private double maxWidth;
    private double maxHeight;

    /**
    * Constructor of a Lugagge from its id, maximum weight, maximum lenght, maximum width and maximum height
    * @param id the identifier of the Luggage
    * @param maxWeight the maximum weight of the Luggage
    * @param maxLength the maximum lenght of the Luggage
    * @param maxWidth the maximum width of the Luggage
    * @param maxHeight the maximum height of the Luggage
    */
    protected Luggage(int id, double maxWeight, double maxLength, double maxWidth, double maxHeight) {
        this.id = id;
        this.maxWeight = maxWeight; // kg
        this.maxLength = maxLength; // cm
        this.maxWidth = maxWidth;   // cm
        this.maxHeight = maxHeight; // cm
    }

    /**
    * Gets the identifier of a Luggage
    * 
    * @return this id
    */
    public int getId() {
        return this.id;
    }
    /**
    * Sets the identifier of a Luggage
    * 
    * @param id the id that will be set to this
    */
    public void setId(int id) {
        this.id = id;
    }

    /**
    * Gets the maximum weight of a Luggage
    * 
    * @return this maxWeight
    */
    public double getMaxWeight() {
        return this.maxWeight;
    }

    /**
    * Sets the maximum weight of a Luggage
    * 
    * @param maxWeight the maximum weight that will be set to this
    */
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
    * Gets the maximum lenght of a Luggage
    * 
    * @return this maxLenght
    */
    public double getMaxLength() {
        return this.maxLength;
    }

    /**
    * Sets the maximum lenght of a Luggage
    * 
    * @param maxLength the maximum length that will be set to this
    */
    public void setMaxLength(double maxLength) {
        this.maxLength = maxLength;
    }

    /**
    * Gets the maximum width of a Luggage
    * 
    * @return this maxWidth
    */
    public double getMaxWidth() {
        return this.maxWidth;
    }
    
    /**
    * Sets the maximum width of a Luggage
    * 
    * @param maxWidth the maximum width that will be set to this
    */
    public void setMaxWidth(double maxWidth) {
        this.maxWidth = maxWidth;
    }

    /**
    * Gets the maximum height of a Luggage
    * 
    * @return this maxHeight
    */
    public double getMaxHeight() {
        return this.maxHeight;
    }

    /**
    * Sets the maximum height of a Luggage
    * 
    * @param maxHeight the maximum height that will be set to this
    */
    public void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }

    /**
     * Getter of the number of luggages of a given type in a given array of Luggages
     * 
     * @param luggageType the type of luggage we want the amount
     * @param luggages the array of luggages
     *
     * @return the amount of luggages of the specified type
     */
    public static int getNumberOfLuggages(String luggageType, ArrayList<Luggage> luggages) {
        int result = 0;
        if (luggageType.equals("Hand Luggage")) {
            for (Luggage lug : luggages) {
                if (lug instanceof HandLuggage) {
                    result++;
                }
            }
        } else if (luggageType.equals("Checked Luggage")) {
            for (Luggage lug : luggages) {
                if (lug instanceof CheckedLuggage) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * Method that creates a new instance of a specified luggage type
     *
     * @param luggageType the type of luggage we want to create
     *
     * @return the created Luggage 
     */
    public static Luggage createLuggage(String luggageType) {
        switch (luggageType) {
            case "Hand Luggage":
                return new HandLuggage(0);
            case "Checked Luggage":
                return new CheckedLuggage(0);
        }
        return null;
    }

    public abstract String toString();
}
