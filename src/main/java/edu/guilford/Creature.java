package edu.guilford;

/**
 * The Creature class represents a generic creature with basic attributes and behaviors.
 */
public abstract class Creature {
    protected float size;
    protected float growthRate;
    protected boolean alive;
    protected int age;

    protected int lifespan = 1000;
    protected double diechance = 0.5;

    /**
     * Constructs a Creature with the specified size and growth rate.
     *
     * @param size the initial size of the creature
     * @param growthRate the growth rate of the creature
     */
    public Creature(float size, float growthRate) {
        this.size = size;
        this.growthRate = growthRate;
        this.alive = true;
        this.age = 0;
    }

    /**
     * Returns the size of the creature.
     *
     * @return the size of the creature
     */
    public float getSize() {
        return size;
    }

    /**
     * Returns the growth rate of the creature.
     *
     * @return the growth rate of the creature
     */
    public float getGrowthRate() {
        return growthRate;
    }

    /**
     * Returns whether the creature is alive.
     *
     * @return true if the creature is alive, false otherwise
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Returns the age of the creature.
     *
     * @return the age of the creature
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the growth rate of the creature.
     *
     * @param r the new growth rate
     */
    public void setGrowthRate(float r) {
        growthRate = r;
    }

    /**
     * Changes the size of the creature by the specified amount.
     *
     * @param s the amount to change the size by
     */
    public void changeSize(float sizeChange) {
        size += sizeChange;
        if (size <= 0) {
            die();
        }
    }

    /**
     * Simulates a day in the life of the creature, increasing its size and age.
     */
    public void simulateDay() {
        if (this.age >= lifespan) {
            if (Math.random() > diechance) {
                die();
                return;
            }
        }
        changeSize(growthRate);
        age++;
    }

    /**
     * Marks the creature as dead.
     */
    public void die() {
        size = 0;
        growthRate = 0;
        alive = false;
    }
}
