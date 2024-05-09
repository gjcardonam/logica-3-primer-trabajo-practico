package com.logica3;

import com.logica3.peopleAndShip.Person;
import com.logica3.peopleAndShip.Ship;
import com.logica3.planet.Planet;
import com.logica3.planet.Route;

public class Main {
    public static void main(String[] args) {

        // Objects
        Planet planetGama = new Planet("Gama");
        Planet planetBeta = new Planet("Beta");
        Planet planetSigma = new Planet("Sigma");

        Ship ship = new Ship();

        Person[] persons = getPersons();

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

    private static Person[] getPersons() {
        Person person1 = new Person("Jack", 222222, 25, "M", new int[]{111111, 333333});
        Person person2 = new Person("Rose", 222222, 20, "F", new int[]{111111, 333333});
        Person person3 = new Person("Kate", 444444, 30, "F", new int[]{111111, 333333});
        Person person4 = new Person("Leo", 555555, 35, "M", new int[]{111111, 333333});
        Person person5 = new Person("Luis", 666666, 40, "M", new int[]{111111, 333333});
        Person person6 = new Person("Luisa", 777777, 45, "F", new int[]{111111, 333333});

        return new Person[]{person1, person2, person3, person4, person5, person6};
    }
}
