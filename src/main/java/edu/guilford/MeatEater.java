package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

/**
 * The MeatEater class represents a critter that eats plant eaters to survive.
 */
public class MeatEater extends Critter {
    private ArrayList<PlantEater> edible;
    private Random rand = new Random();
    private final static float CATCHCHANCE = 0.5f;
    private final static int MINEAT = 1;
    private final static int MAXEAT = 4;

    /**
     * Constructs a MeatEater with the specified size, growth rate, and list of edible plant eaters.
     *
     * @param size the initial size of the meat eater
     * @param growthRate the growth rate of the meat eater
     * @param ediblePlantEaters the list of edible plant eaters
     */
    public MeatEater(float size, float growthRate, ArrayList<PlantEater> ediblePlantEaters) {
        super(size, growthRate);
        this.edible = ediblePlantEaters;
        this.lifespan = 300;
        this.diechance = 0.5;
    }

    /**
     * Simulates the meat eater chasing and eating a plant eater, reducing the plant eater's size and increasing the meat eater's food eaten.
     *
     * @param plantEater the plant eater to chase and eat
     */
    public void chase (PlantEater plantEater) {
        if (rand.nextFloat() < CATCHCHANCE) {
            eat(plantEater.getSize());
            plantEater.die();
        }
    }  

    /**
     * Simulates a day in the life of the meat eater, eating plant eaters and increasing its size and age.
     */
    @Override
    public void simulateDay() {
        int toEat = rand.nextInt(MINEAT, MAXEAT+1);
        if (edible.size() > 0) {
            for (int i = 0; i < toEat; i++) {
                chase(edible.get(rand.nextInt(edible.size())));
            }
        }
        super.simulateDay();
    }

    /**
     * Returns a string representation of the meat eater.
     *
     * @return a string representation of the meat eater
     */
    @Override
    public String toString() {
        return "MeatEater: size=" + size + ", growthRate=" + growthRate + ", alive=" + alive + ", age=" + age + ", foodNeed=" + foodNeed + ", foodEaten=" + foodEaten;
    }
}
