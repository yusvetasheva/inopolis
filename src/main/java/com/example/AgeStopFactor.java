package com.example;

public class AgeStopFactor implements StopFactor {
    private int age;

    public AgeStopFactor(int age) {
        this.age = age;
    }

    @Override
    public boolean accept(StopFactorVisitor visitor) {
        return visitor.visit(this);
    }

    public int getAge() {
        return age;
    }
}
