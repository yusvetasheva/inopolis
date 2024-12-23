package com.example;

public class Circle extends Ellipse implements Coordinates {

    double x = 0;
    double y = 0;

    @Override
    public double getPerimeter(double radius) {
        return 2 * 3.14 * radius;
    }

    @Override
    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
