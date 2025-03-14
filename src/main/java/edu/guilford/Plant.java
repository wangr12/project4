package edu.guilford;

/**
 * The Plant class represents a plant that can be chewed on by plant eaters.
 */
public class Plant extends Creature {
    
    /**
     * Constructs a Plant with the specified size and growth rate.
     *
     * @param size the initial size of the plant
     * @param growthRate the growth rate of the plant
     */
    public Plant(float size, float growthRate) {
        super(size, growthRate);
    }

    /**
     * Simulates the plant being chewed on by reducing its size by the specified amount.
     * If the amount eaten is greater than or equal to the plant's size, the plant dies.
     *
     * @param eaten the amount of the plant that has been eaten
     */
    public void chewedOn(float eaten) {
        if (eaten < size) {
            changeSize(-eaten);
        } else {
            die();
        }
    } 

    /**
     * Returns a string representation of the plant.
     *
     * @return a string representation of the plant
     */
    @Override
    public String toString() {
        return "Plant: size=" + size + ", growthRate=" + growthRate + ", alive=" + alive + ", age=" + age;
    }
}
