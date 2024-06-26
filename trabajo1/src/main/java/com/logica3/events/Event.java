package com.logica3.events;

public abstract class Event {
    protected final String name;

    public Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void occur();
}