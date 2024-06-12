package com.logica3.events;

public class EventFactory {
    private final EventProbability eventProbability;

    public EventFactory(EventProbability eventProbability) {
        this.eventProbability = eventProbability;
    }

    public Event generateEvent() {
        double random = Math.random();
        double cumulativeProbability = 0.0;

        cumulativeProbability += eventProbability.airPoisonousScapeProbability();
        if (random < cumulativeProbability) {
            return new AirPoisonousScape();
        }

        cumulativeProbability += eventProbability.asteroidProbability();
        if (random < cumulativeProbability) {
            return new Asteroid();
        }

        cumulativeProbability += eventProbability.mentalSicknessProbability();
        if (random < cumulativeProbability) {
            return new MentalSickness();
        }

        cumulativeProbability += eventProbability.piratesProbability();
        if (random < cumulativeProbability) {
            return new Pirates();
        }

        cumulativeProbability += eventProbability.solarStormProbability();
        if (random < cumulativeProbability) {
            return new SolarStorm();
        }

        return null;
    }
}
