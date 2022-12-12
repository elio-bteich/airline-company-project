package src.models;

/**
 * Class who represents a flight class
 * A FlightClass is defined by its description, the coefficient that changes the price
 * of the seats, and the Airplane where we can find this class
 */
public class FlightClass {
    private String description;
    private double coef;
    private Airplane airplane;

    /**
    * Constructor of a FlightClass from its description and coefficient
    * @param description the description of the FlightClass
    * @param coef the coefficient of the FlightClass
    */
    public FlightClass(String description, double coef) {
        this.description = description;
        this.coef = coef;
    }

    /**
    * Gets the description of a FlightClass
    * 
    * @return this description
    */
    public String getDescription(){
        return description;
    }

    /**
    * Sets the description of a FlightClass
    * 
    * @param description the description that will be set to this
    */
    public void setDescription(String description){
        this.description = description;
    }

    /**
    * Gets the coef of a FlightClass
    * 
    * @return this coef
    */
    public double getCoef(){
        return coef;
    }

    /**
    * Sets the coef of a FlightClass
    * 
    * @param coef the coef that will be set to this
    */
    public void setCoef(double coef){
        this.coef = coef;
    }

    /**
    * Gets the airplane of a FlightClass
    * 
    * @return this airplane
    */
    public Airplane getAirplane(){
        return airplane;
    }

    /**
    * Sets the airplane of a FlightClass
    * 
    * @param airplane the airplane that will be set to this
    */
    public void setAirplane(Airplane airplane){
        this.airplane = airplane;
    }

    /** 
    * Method that checks if this flight class is equal to the other passed as parameter
    *
    * @param other the other flight class we want to compare to
    *
    * @return true if these are the same flight classes, false if not
    */
    @Override
    public boolean equals(Object other) {
 
        // If the object is compared with itself then return true 
        if (other == this) {
            return true;
        }
 
        if (!(other instanceof FlightClass)) {
            return false;
        }
         
        FlightClass castedOther = (FlightClass) other;
         
        return this.description.equals(castedOther.description) && Double.compare(this.coef, castedOther.coef) == 0; 
    }

    /** 
    * Method that turns into a string all the informations about this flight class
    *
    * @return the string with all the informations
    */
    @Override
    public String toString() {
        return   "\nDescription: " + this.description + ", Coefficient: " + this.coef;
    }
}
