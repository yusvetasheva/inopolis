package com.example;

import lombok.Getter;

public class Memento {
    @Getter
    private final String name;
    private final boolean isValid;

    public Memento(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    public boolean isValid() {
        return isValid;
    }
}

