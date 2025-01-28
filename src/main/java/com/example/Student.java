package com.example;

import lombok.AllArgsConstructor;

/**
 * Напишите методы Equals and HashCode для класса Student, который состоит из полей String name и int age
 */
@AllArgsConstructor
public class Student implements Cloneable {
    String name;
    int age;

    @Override
    public String toString() {
        return "name: " + this.name + " , age: " + age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Student o1 = (Student) o;
        return o1.name != null && o1.name.equals(this.name) && o1.age == this.age;
    }

    @Override
    public int hashCode() {
        int result = this.age;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
