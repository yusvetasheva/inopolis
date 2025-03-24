package com.example;


public class Parent {
    private void a() {
        System.out.println("PARENT");
    }

    public static void main(String[] angs) {
        Parent p = new Child();
        p.a();
    }
}

class Child extends Parent {
    public void a() {
        System.out.println("CHILD");
    }
}
