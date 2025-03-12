package edu.guilford;

public abstract class Creature {
    protected float size;
    protected float growthRate;
    protected boolean alive;
    protected int age;
    public static double THRESHOLD = 0.000001;

    public Creature(float size, float growthRate) {
        this.size = size;
        this.growthRate = growthRate;
        this.alive = true;
        this.age = 0;
    }

    public float getSize() {
        return size;
    }

    public float getGrowthRate() {
        return growthRate;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getAge() {
        return age;
    }

    public void setGrowthRate(float r) {
        growthRate = r;
    }

    public void changeSize(float sizeChange) {
        size += sizeChange;
        if (size <= 0) {
            die();
        }
    }

    public void simulateDay() {
        changeSize(growthRate);
        age++;
    }

    public void die() {
        size = 0;
        growthRate = 0;
        alive = false;
    }
}
