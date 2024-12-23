package com.example;

public class Rectangle extends Figure {

    double x =0;
    double y =0;


    @Override
    public double getPerimeter(double size1, double size2){
        return 2*size1+2*size2;
    }
}
