package com.example;

public class Ellipse extends Figure{

    /**У эллипса много формул на периметр, но все они дают погрешность в вычислении*/
    @Override
    public double getPerimeter(double poluos1, double poluos2){
        double chislitel = 4*(3.14*poluos1*poluos2+(poluos1-poluos2)*(poluos1-poluos2));
        return chislitel/(poluos1+poluos2);
    }

}
