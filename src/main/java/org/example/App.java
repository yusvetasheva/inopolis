package org.example;

public class App {
    public static void main (String[] arg){
         Product pr = new Product();
         Product prInit = new Product(1L, "empty", 1, 1);

         System.out.println(pr.toString());
        System.out.println(prInit.toString());
    }

}
