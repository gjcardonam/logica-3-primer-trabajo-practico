package com.logica3;

public class Main {
    public static void main(String[] args) {

        // Objects
        Planet planetGama = new Planet("Gama");
        Planet planetBeta = new Planet("Beta");
        Planet planetSigma = new Planet("Sigma");

        Ship ship = new Ship();

        Person person1 = new Person("Jack", 222222);
        Person person2 = new Person("Rose", 222222);
        Person person3 = new Person("Kate", 444444);
        Person person4 = new Person("Leo", 555555);

        Person[] persons = {person1, person2, person3, person4};

        Route route = new Route();

        // Settings
        // planetGama.setEventProbability(
        //     0,
        //     0, 
        //     0.35, 
        //     0.35, 
        //     0.3
        //     );
        planetGama.setEventProbability(
            0,
            0, 
            0, 
            1, 
            0
            );
        // planetBeta.setEventProbability(
        //     0.4, 
        //     0.2, 
        //     0, 
        //     0.4, 
        //     0
        //     );
        planetBeta.setEventProbability(
            0, 
            1, 
            0, 
            0, 
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
