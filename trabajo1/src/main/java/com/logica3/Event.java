package com.logica3;

public abstract class Event {
    protected String description;
    protected String name;

    public Event(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public abstract void occur();
}