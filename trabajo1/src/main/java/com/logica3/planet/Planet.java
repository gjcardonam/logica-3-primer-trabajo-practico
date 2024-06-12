package com.logica3.planet;

import com.logica3.events.*;

public class Planet {
    private final String name;
    private EventFactory eventFactory;

    public Planet(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setEventProbability(double airPoisonousScapeProbability, double asteroidProbability, double mentalSicknessProbability, double piratesProbability, double solarStormProbability){
        EventProbability eventProbability = new EventProbability(airPoisonousScapeProbability, asteroidProbability, mentalSicknessProbability, piratesProbability, solarStormProbability);
        this.eventFactory = new EventFactory(eventProbability);

    }

    public Event generateEvent(){
        if (this.eventFactory == null){
            throw new IllegalStateException("Event probability not set");
        }
        return eventFactory.generateEvent();
    }

}
