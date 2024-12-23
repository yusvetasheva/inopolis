package com.example;

public class Square extends Rectangle implements Coordinates {

    double x = 0;
    double y = 0;

    @Override
    public double getPerimeter(double size) {
        return size * 4;
    }

    @Override
    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }


}
