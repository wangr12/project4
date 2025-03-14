package edu.guilford;

/**
 * The Critter class represents a creature that needs to eat food to survive.
 */
public abstract class Critter extends Creature{
    protected float foodNeed;
    protected float foodEaten;
    protected final static float FOODRATIO = 0.005f; // ratio of foodNeed to size

    /**
     * Constructs a Critter with the specified size, growth rate, and food need.
     *
     * @param size the initial size of the critter
     * @param growthRate the growth rate of the critter
     * @param foodNeed the amount of food the critter needs each day
     */
    public Critter(float size, float growthRate) {
        super(size, growthRate);
        this.foodNeed = size * FOODRATIO;
        this.foodEaten = 0;
    }

    /**
     * Returns the amount of food the critter needs each day.
     *
     * @return the amount of food the critter needs
     */
    public float getFoodNeed() {
        return foodNeed;
    }

    /**
     * Sets the amount of food the critter needs each day.
     *
     * @param f the new food need
     */
    public void setFoodNeed(float f) {
        this.foodNeed = f;
    }

    /**
     * Simulates a day in the life of the critter, resetting its food eaten and increasing its size and age.
     */
    @Override
    public void simulateDay() {
        if (foodEaten < foodNeed) {
            die();
        } else {
            foodEaten = 0;
            super.simulateDay();
            foodNeed = size * FOODRATIO;
        }
    }

    /**
     * Increases the amount of food the critter has eaten by the specified amount.
     *
     * @param food the amount of food eaten
     */
    public void eat(float food) {
        foodEaten += food;
    }

    /**
     * Returns whether the critter still needs more food.
     *
     * @return true if the critter still needs more food, false otherwise
     */
    public float stillNeed() {
        return foodNeed - foodEaten;
    }
}
