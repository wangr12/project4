package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

public class MeatEater extends Critter {
    private ArrayList<PlantEater> edible;
    private Random rand = new Random();
    private final static float CATCHCHANCE = 0.7f;
    private final static int MINEAT = 1;
    private final static int MAXEAT = 4;
    private final static int LIFESPAN = 50;
    private final static double DIECHANCE = 0.2;

    public MeatEater(float size, float growthRate, ArrayList<PlantEater> ediblePlantEaters) {
        super(size, growthRate);
        this.edible = ediblePlantEaters;
    }

    public void chase (PlantEater plantEater) {
        if (rand.nextFloat() < CATCHCHANCE) {
            eat(plantEater.getSize());
            plantEater.die();
        }
    }

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

    @Override
    public String toString() {
        return "MeatEater: size=" + size + ", growthRate=" + growthRate + ", alive=" + alive + ", age=" + age + ", foodNeed=" + foodNeed + ", foodEaten=" + foodEaten;
    }
}
