package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

/**
 * The PlantEater class represents a critter that eats plants to survive.
 */
public class PlantEater extends Critter {
    private ArrayList<Plant> edible;
    private Random rand = new Random();
    private final double MINTOEAT = 0.05; // lower bound of random calculation for plants to eat (factor of edible.size())
    private final double MAXTOEAT = 0.1; // upper bound of random calculation for plants to eat (factor of edible.size())

    /**
     * Constructs a PlantEater with the specified size, growth rate, and list of edible plants.
     *
     * @param size the initial size of the plant eater
     * @param growthRate the growth rate of the plant eater
     * @param ediblePlants the list of edible plants
     */
    public PlantEater(float size, float growthRate, ArrayList<Plant> ediblePlants) {
        super(size, growthRate);
        this.edible = ediblePlants;
        this.lifespan = 200;
        this.diechance = 0.3;
    }

    /**
     * Simulates the plant eater chewing on a plant, reducing the plant's size and increasing the plant eater's food eaten.
     *
     * @param plant the plant to chew on
     */
    public void chew (Plant plant) {
        if (plant.isAlive() && stillNeed() != 0) {
            float maxEat = Math.min(plant.getSize()/2, foodNeed/4);
            //float minEat = Math.min(plant.getSize()/50, foodNeed/6);
            float minEat = maxEat * 0.2f;
            float eaten = Math.min(rand.nextFloat(minEat, maxEat), stillNeed());
            eat(eaten); 
            plant.chewedOn(eaten);
        }
    }

    /**
     * Simulates a day in the life of the plant eater, eating plants and increasing its size and age.
     */
    @Override
    public void simulateDay() {
        int plantsToEat = rand.nextInt((int) (edible.size()*MINTOEAT), (int) (edible.size()*MAXTOEAT));
        for (int i = 0; i < plantsToEat; i++) {
            chew(edible.get(rand.nextInt(edible.size())));
        }
        super.simulateDay();
    }

    /**
     * Returns a string representation of the plant eater.
     *
     * @return a string representation of the plant eater
     */
    @Override
    public String toString() {
        return "PlantEater: size=" + size + ", growthRate=" + growthRate + ", alive=" + alive + ", age=" + age + ", foodNeed=" + foodNeed + ", foodEaten=" + foodEaten;
    }
}
