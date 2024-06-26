package com.logica3.events;

import java.util.Random;

public class SolarStorm extends Event{
    public final int[] ids;

    public SolarStorm(){
        super("Solar Storm");
        ids = new int[1000];
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomId = random.nextInt(99);
            ids[i] = randomId;
        }
    }

    @Override
    public void occur() {
        System.out.println("A solar storm has disrupted the planet's atmosphere!");
    }
}