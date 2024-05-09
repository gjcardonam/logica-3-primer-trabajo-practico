package com.logica3.events;

import java.util.Random;

public class AirPoisonousScape extends Event{
    public final int[] ids;

    public AirPoisonousScape(){
        super("Air Poisonous Scape");
        ids = new int[100000];
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            int randomId = random.nextInt(99);
            ids[i] = randomId;
        }
    }

    @Override
    public void occur() {
        System.out.println("The air has become poisonous on the planet!");
    }
    
}
