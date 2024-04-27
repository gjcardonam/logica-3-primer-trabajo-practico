package com.logica3;

public class MentalSickness extends Event{
    public MentalSickness(){
        super("Mental Sickness");
    }

    @Override
    public void occur() {
        System.out.println("A mental sickness outbreak has occurred on the planet!");
    }
    
}
