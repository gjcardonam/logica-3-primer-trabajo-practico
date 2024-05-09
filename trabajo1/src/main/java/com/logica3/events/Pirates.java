package com.logica3.events;

public class Pirates extends Event {
    public final int[] ids;

    public Pirates() {
        super("Pirates");
        ids = new int[10000];
        int valorActual = 1;
        int position = 0;
        while (position < ids.length) {
            for (int i = 0; i < 1000; i++) {
                ids[position] = valorActual;
                position++;
            }
            valorActual++;
        }
    }

    @Override
    public void occur() {
        System.out.println("Space pirates have invaded the planet!");
    }
}