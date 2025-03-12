package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

public class PlantEater extends Critter {
    private ArrayList<Plant> edible;
    private Random rand = new Random();
    private double mintoEat = 0.05; // lower bound of random calculation for plants to eat (factor of edible.size())
    private double maxtoEat = 0.15; // upper bound of random calculation for plants to eat (factor of edible.size())
    private final static int LIFESPAN = 40;
    private final static double DIECHANCE = 0.3;

    public PlantEater(float size, float growthRate, ArrayList<Plant> ediblePlants) {
        super(size, growthRate);
        this.edible = ediblePlants;
    }

    public void chew (Plant plant) {
        if (stillNeed() != 0) {
            float maxEat = Math.min(plant.getSize()/2, foodNeed/4);
            float minEat = Math.min(plant.getSize()/50, foodNeed/6);
            float eaten = Math.min(rand.nextFloat(minEat, maxEat), stillNeed());
            eat(eaten); 
            plant.chewedOn(eaten);
        }
    }

    @Override
    public void simulateDay() {
        int plantsToEat = rand.nextInt((int) (edible.size()*mintoEat), (int) (edible.size()*maxtoEat));
        for (int i = 0; i < plantsToEat; i++) {
            chew(edible.get(rand.nextInt(edible.size())));
        }
        super.simulateDay();
    }

    @Override
    public String toString() {
        return "PlantEater: size=" + size + ", growthRate=" + growthRate + ", alive=" + alive + ", age=" + age + ", foodNeed=" + foodNeed + ", foodEaten=" + foodEaten;
    }
}
