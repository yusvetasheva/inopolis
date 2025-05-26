package com.example;

import java.util.Stack;

public class Caretaker {
    private Stack<Memento> history = new Stack<>();

    // Сохраняет снимок состояния
    public void addMemento(Memento memento) {
        history.push(memento);
    }

    // Извлекает последний снимок
    public Memento getMemento() {
        return history.pop();
    }
}
