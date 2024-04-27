package com.logica3;

import java.util.Random;

public class Asteroid extends Event {
    public final int[] ids;

    public Asteroid() {
        super("Asteroid");
        ids = new int[1000];
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomId = random.nextInt(100);
            ids[i] = randomId;
        }
    }

    @Override
    public void occur() {
        System.out.println("Critical alert! A barrage of asteroids has struck the ship");
    }
}