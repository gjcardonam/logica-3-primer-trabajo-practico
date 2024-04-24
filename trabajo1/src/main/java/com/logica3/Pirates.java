package com.logica3;

public class Pirates extends Event {
    public int[] ids;

    public Pirates() {
        super("Pirates", "Space pirates invasion");
        
        ids = new int[10000];

        int valorActual = 1;
        int posicion = 0;

        while (posicion < ids.length) {
            for (int i = 0; i < 1000; i++) {
                ids[posicion] = valorActual;
                posicion++;
            }
            valorActual++;
        }
        
    }

    @Override
    public void occur() {
        System.out.println("Space pirates have invaded the planet!");
    }
}