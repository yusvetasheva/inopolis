package com.example;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/figures.txt"))) {
            String line;
            List<String> writeToFile = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(" ");
                String result = null;
                if (array[0].equals("Ellipse:")) {
                    Ellipse ellipse = new Ellipse();
                    result = "Периметр эллипса = " + ellipse.getPerimeter(Double.parseDouble(array[1]), Double.parseDouble(array[2]));
                } else if (array[0].equals("Rectangle:")) {
                    Rectangle rectangle = new Rectangle();
                    result = "Периметр прямоугольника = " + rectangle.getPerimeter(Double.parseDouble(array[1]), Double.parseDouble(array[2]));
                } else if (array[0].equals("Circle:")) {
                    Circle circle = new Circle();
                    result = "Периметр круга = " + circle.getPerimeter(Double.parseDouble(array[1]));
                    System.out.println("Начальные координаты круга (x, y) :" + circle.x + ", " + circle.y);
                    circle.move(1, 6);
                    System.out.println("Координаты круга (x, y) после перемещения :" + circle.x + ", " + circle.y);
                } else if (array[0].equals("Square:")) {
                    Square square = new Square();
                    result = "Периметр квадрата = " + square.getPerimeter(Double.parseDouble(array[1]));
                    System.out.println("Начальные координаты квадрата (x, y) :" + square.x + ", " + square.y);
                    square.move(5, 9);
                    System.out.println("Координаты квадрата (x, y) после перемещения :" + square.x + ", " + square.y);
                }

                System.out.println(result);
                writeToFile.add(result);
            }

            Files.write(Path.of("src/main/resources/result.txt"), writeToFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
