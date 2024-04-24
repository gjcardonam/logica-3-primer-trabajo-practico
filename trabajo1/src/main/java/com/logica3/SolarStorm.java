package com.logica3;

import java.util.Random;

public class SolarStorm extends Event{
    public int[] ids;

    public SolarStorm(){
        super("Solar Storm", "Solar storm disruption");
        ids = new int[1000];
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomId = random.nextInt(200);
            ids[i] = randomId - 100;
        }
    }

    @Override
    public void occur() {
        System.out.println("A solar storm has disrupted the planet's atmosphere!");
    }
}