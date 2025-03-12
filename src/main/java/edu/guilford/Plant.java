package edu.guilford;

public class Plant extends Creature {
    private final static int LIFESPAN = 100;
    private final static double DIECHANCE = 0.5;
    
    public Plant(float size, float growthRate) {
        super(size, growthRate);
    }

    public void chewedOn(float eaten) {
        if (eaten < size) {
            changeSize(-eaten);
        } else {
            die();
        }
    }

    @Override
    public String toString() {
        return "Plant: size=" + size + ", growthRate=" + growthRate + ", alive=" + alive + ", age=" + age;
    }
}
