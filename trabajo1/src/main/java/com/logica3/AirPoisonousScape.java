package com.logica3;

import java.util.Random;

public class AirPoisonousScape extends Event{
    public int[] ids;

    public AirPoisonousScape(){
        super("Air Poisonous Scape", "The air has become poisonous on the planet!");
        ids = new int[100000];
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            int randomId = random.nextInt(100); // Genera un número aleatorio entre 0 y 100
            ids[i] = randomId;
        }
    }

    @Override
    public void occur() {
        System.out.println("The air has become poisonous on the planet!");
    }
    
}
