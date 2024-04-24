package com.logica3;

public class SolarStorm extends Event{
    public SolarStorm(){
        super("Solar Storm", "Solar storm disruption");
    }

    @Override
    public void occur() {
        System.out.println("A solar storm has disrupted the planet's atmosphere!");
    }
}