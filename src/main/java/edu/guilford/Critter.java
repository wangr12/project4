package edu.guilford;

public abstract class Critter extends Creature{
    protected float foodNeed;
    protected float foodEaten;
    protected final static float FOODRATIO = 0.005f; // ratio of foodNeed to size

    public Critter(float size, float growthRate) {
        super(size, growthRate);
        this.foodNeed = size * FOODRATIO;
        this.foodEaten = 0;
    }

    public float getFoodNeed() {
        return foodNeed;
    }

    public void setFoodNeed(float f) {
        this.foodNeed = f;
    }

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

    public void eat(float food) {
        foodEaten += food;
    }

    public float stillNeed() {
        return foodNeed - foodEaten;
    }
}
