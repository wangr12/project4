package edu.guilford;

/**
 * This program simulates an ecosystem consisting of plants, herbivores, and carnivores.
 * @author Ray Wang
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Random;

public class CreatureDriver {
    public static void main(String[] args) {
        final int PLANT_SIZE = 300;
        final int PE_SIZE = 1000;
        final int ME_SIZE = 6000;

        final int NPLANTS = 2000;
        Random rand = new Random();

        ArrayList<Plant> plants = new ArrayList<Plant>();
        for (int i = 0; i < NPLANTS; i++) {
            plants.add(new Plant(PLANT_SIZE + rand.nextInt(-50,50), 5));
        }
        PlantEater plantEater = new PlantEater(PE_SIZE + rand.nextInt(-100,100), 3, plants);

        // test chew method. The result should show the the plant's size to decrease by 0-5 and the plantEater's foodEaten to increase by the same amount
        Plant plant = plants.get(0);
        System.out.println("Testing chew method\n");
        System.out.println("\nPlant eater before chew\n" + plantEater);
        System.out.println("\nPlant before chew\n" + plant);
        plantEater.chew(plant);
        System.out.println("\nPlant eater after chew\n" + plantEater);
        System.out.println("\nPlant after chew\n" + plant);

        // test simulateDay method. The plantEater's size should increase by its growthRate (3), and its age should increase by 1 (if still alive)
        System.out.println("\nTesting simulateDay method\n");
        System.out.println("Plant eater before day\n" + plantEater);
        plantEater.simulateDay();
        System.out.println("\nPlant eater after day\n" + plantEater);


        // Test 2
        int nPlantEaters = 300;
        final int MAX_STEPS = 1000;
        final double PLANT_BIRTH_CHANCE = 0.05;
        final double PE_BIRTH_CHANCE = 1;

        plants = new ArrayList<Plant>();
        for (int i = 0; i < NPLANTS; i++) {
            plants.add(new Plant(PLANT_SIZE + rand.nextInt(-50,50), 5));
        }
        ArrayList<PlantEater> plantEaters = new ArrayList<PlantEater>();
        for (int i = 0; i < nPlantEaters; i++) {
            plantEaters.add(new PlantEater(PE_SIZE + rand.nextInt(-100,100), 3, plants));
        }

        int steps = 0;
        while (stillAlive(plantEaters) && steps < MAX_STEPS) {
            System.out.println("Day " + steps + ": " + plantEaters.size() + " plant eaters, " + plants.size() + " plants, total mass=" + totalMass(plantEaters) + ", total mass of plants=" + totalMass(plants));

            for (PlantEater pe : plantEaters) {
                pe.simulateDay();
            }
            for (Plant p : plants) {
                p.simulateDay();
            }

            if (Math.random() < PLANT_BIRTH_CHANCE) {
                plants.add(new Plant(PLANT_SIZE + rand.nextInt(-50,50), 5));
            }
            if (Math.random() < PE_BIRTH_CHANCE) {
                plantEaters.add(new PlantEater(PE_SIZE + rand.nextInt(-100,100), 3, plants));
            }

            int i = 0;  
            while (i < plantEaters.size()) {  // using a for loop would modify the list while iterating, causing an error --> use while loop
                if (!plantEaters.get(i).isAlive()) {  
                    plantEaters.remove(i);  
                } else {  
                    i++;  
                }
            }

            while (i < plants.size()) {
                if (!plants.get(i).isAlive()) {  
                    plants.remove(i);  
                } else {  
                    i++;  
                }
            }

            steps++;
        }
        System.out.println("Day " + steps + ": " + plantEaters.size() + " plant eaters, " + plants.size() + " plants, total mass=" + totalMass(plantEaters) + ", total mass of plants=" + totalMass(plants));


        // test MeatEater class
        final int NPlantEaters = 100;

        plantEaters = new ArrayList<PlantEater>();
        for (int i = 0; i < nPlantEaters; i++) {
            plantEaters.add(new PlantEater(PE_SIZE + rand.nextInt(-100,100), 3, plants));
        }

        MeatEater meatEater = new MeatEater(ME_SIZE + rand.nextInt(-100,100), 3, plantEaters);
        PlantEater PE = plantEaters.get(0);

        // test chase method. The result should show the the plant's size to decrease by 0-5 and the plantEater's foodEaten to increase by the same amount
        System.out.println("\nTesting Meat Eater class");
        System.out.println("\nTesting chase method");
        System.out.println("\nMeat eater before chase\n" + meatEater);
        System.out.println("\nPlant eater before chase\n" + PE);
        meatEater.chase(PE);
        System.out.println("\nMeat eater after chase\n" + meatEater);
        System.out.println("\nPlant eater after chase\n" + PE);

        // test simulateDay method. The plantEater's size should increase by its growthRate (3), and its age should increase by 1 (if still alive)
        System.out.println("\nTesting simulateDay method\n");
        System.out.println("Meat eater before day\n" + meatEater);
        meatEater.simulateDay();
        System.out.println("\nMeat eater after day\n" + meatEater);


        // Simulation with meat eaters

        System.out.println("Simulation with meat eaters");
        final int NMEATEATERS = 400;
        nPlantEaters = 3000;
        // final double ME_BIRTH_CHANCE = 0.3;
        // plant eaters need to be able to reproduce more in order to keep up with the diet of the meat eaters
        double peBirthRate;
        double meBirthRate;
        final double PE_REPRODUCTION_FACTOR = 5.0;
        final double ME_REPRODUCTION_FACTOR = 4.0;

        plants = new ArrayList<Plant>();
        for (int i = 0; i < NPLANTS; i++) {
            plants.add(new Plant(PLANT_SIZE + rand.nextInt(-50,50), 5));
        }

        plantEaters = new ArrayList<PlantEater>();
        for (int i = 0; i < nPlantEaters; i++) {
            plantEaters.add(new PlantEater(PE_SIZE + rand.nextInt(-100,100), 3, plants));
        }

        ArrayList<MeatEater> meatEaters = new ArrayList<MeatEater>();
        for (int i = 0; i < NMEATEATERS; i++) {
            meatEaters.add(new MeatEater(ME_SIZE + rand.nextInt(-100,100), 3, plantEaters));
        }

        steps = 0;
        while (stillAlive(meatEaters) && steps < MAX_STEPS) {
            System.out.println("Day " + steps + ": " + meatEaters.size() + " meat eaters (mass = " + totalMass(meatEaters) + "), " + plantEaters.size() + " plant eaters (mass = " + totalMass(plantEaters) + "), " + plants.size() + " plants (mass = " + totalMass(plants) + ")");

            for (MeatEater me : meatEaters) {
                me.simulateDay();
            }
            for (PlantEater pe : plantEaters) {
                pe.simulateDay();
            }
            for (Plant p : plants) {
                p.simulateDay();
            }

            if (Math.random() < PLANT_BIRTH_CHANCE) {
                plants.add(new Plant(PLANT_SIZE + rand.nextInt(-50,50), 5));
            }

            peBirthRate = plantEaters.size() / PE_REPRODUCTION_FACTOR;
            for (int i = 0; i < (int) peBirthRate; i++) {
                plantEaters.add(new PlantEater(PE_SIZE + rand.nextInt(-100,100), 3, plants));
            }

            meBirthRate = meatEaters.size() / ME_REPRODUCTION_FACTOR;
            for (int i = 0; i < (int) meBirthRate; i++) {
                meatEaters.add(new MeatEater(ME_SIZE + rand.nextInt(-100,100), 3, plantEaters));
            }

            int i = 0;  
            while (i < meatEaters.size()) {
                if (!meatEaters.get(i).isAlive()) {  
                    meatEaters.remove(i);  
                } else {  
                    i++;  
                }
            }

            i = 0;
            while (i < plantEaters.size()) {
                if (!plantEaters.get(i).isAlive()) {  
                    plantEaters.remove(i);  
                } else {  
                    i++;  
                }
            }

            while (i < plants.size()) {
                if (!plants.get(i).isAlive()) {  
                    plants.remove(i);  
                } else {  
                    i++;  
                }
            }
            
            steps++;
        }
        System.out.println("Day " + steps + ": " + meatEaters.size() + " meat eaters (mass = " + totalMass(meatEaters) + "), " + plantEaters.size() + " plant eaters (mass = " + totalMass(plantEaters) + "), " + plants.size() + " plants (mass = " + totalMass(plants) + ")");

        
    }

    public static boolean stillAlive(ArrayList<? extends Creature> creatures) {
        for (Creature c : creatures) {
            if (c.isAlive()) {
                return true;
            }
        }
        return false;
    }

    public static double totalMass(ArrayList<? extends Creature> creatures) {
        double total = 0;
        for (Creature c : creatures) {
            total += c.getSize();
        }
        return total;
    }
}