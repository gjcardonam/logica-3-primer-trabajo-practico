package com.logica3;

import com.logica3.people.Person;
import com.logica3.ship.Ship;
import com.logica3.planet.Planet;
import com.logica3.planet.Route;

import java.util.List;

import static com.logica3.people.RandomPersonGenerator.generateRandomPeople;

public class Main {
    public static void main(String[] args) {

        // Objects
        Planet planetGama = new Planet("Gama");
        Planet planetBeta = new Planet("Beta");
        Planet planetSigma = new Planet("Sigma");

        Ship ship = new Ship();

        List<Person> persons = generateRandomPeople(1000000);

        Route route = new Route();

        // Settings
         planetGama.setEventProbability(
             0,
             0,
             0.35,
             0.35,
             0.3
             );
         planetBeta.setEventProbability(
             0.4,
             0.2,
             0,
             0.4,
             0
             );
        planetSigma.setEventProbability(
            0, 
            0.25, 
            0.4, 
            0, 
            0.35
            );
        route.addPlanet(planetGama);
        route.addPlanet(planetBeta);
        route.addPlanet(planetSigma);

        
        // Algorithm
        ship.board(persons);
        // ship.listPeople();
        ship.travel(route);
    }
}
