package com.logica3;

public class Planet {
    private final String name;
    private double airPoisonousScapeProbability;
    private double asteroidProbability;
    private double mentalSicknessProbability;
    private double piratesProbability;
    private double solarStormProbability;

    public Planet(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setEventProbability(double airPoisonousScapeProbability, double asteroidProbability, double mentalSicknessProbability, double piratesProbability, double solarStormProbability){
        this.airPoisonousScapeProbability = airPoisonousScapeProbability;
        this.asteroidProbability = asteroidProbability;
        this.mentalSicknessProbability = mentalSicknessProbability;
        this.piratesProbability = piratesProbability;
        this.solarStormProbability = solarStormProbability;
    }

    public Event generateEvent(){
        double random = Math.random();
        if (random < this.airPoisonousScapeProbability){
            return new AirPoisonousScape();
        } else if (random < this.airPoisonousScapeProbability + this.asteroidProbability){
            return new Asteroid();
        } else if (random < this.airPoisonousScapeProbability + this.asteroidProbability + this.mentalSicknessProbability){
            return new MentalSickness();
        } else if (random < this.airPoisonousScapeProbability + this.asteroidProbability + this.mentalSicknessProbability + this.piratesProbability){
            return new Pirates();
        } else if (random < this.airPoisonousScapeProbability + this.asteroidProbability + this.mentalSicknessProbability + this.piratesProbability + this.solarStormProbability){
            return new SolarStorm();
        }
        return null;
    }
    
}
