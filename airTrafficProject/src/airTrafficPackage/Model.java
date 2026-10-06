/*

*/
package airTrafficPackage;

/**
 * Model.java
 * This class represents a Model of an Airplane
 * @finished true
 * @author Uriel Neves Silva
 * @since Feb 2026
 */
class Model {

    // Create the attributes for the constructor of the class Model
    private final String modelName;
    private final int capacity;
    private final double range;

/*
=========================================
CONSTRUCTORS 
=========================================
*/  
    
    public Model(String modelName, int capacity, double range) {
        this.modelName = modelName;
        this.capacity = capacity;
        this.range = range;
    }

/*
=========================================
METHODS 
=========================================
*/
    public String getModelName() {
        return modelName;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getRange() {
        return range;
    }
}






