package com.logica3;

public class AirPoisonousScape extends Event{
    public AirPoisonousScape(){
        super("Air Poisonous Scape", "The air has become poisonous on the planet!");
    }

    @Override
    public void occur() {
        System.out.println("The air has become poisonous on the planet!");
    }
    
}
