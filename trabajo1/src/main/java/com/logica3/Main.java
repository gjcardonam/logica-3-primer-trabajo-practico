package com.logica3;

import com.logica3.people.Person;
import com.logica3.ship.Ship;
import com.logica3.planet.Planet;
import com.logica3.planet.Route;

import java.util.List;

import static com.logica3.people.RandomPersonGenerator.generateRandomPeople;

public class Main {
    private static Ship ship;
    private static List<Person> persons;
    private static Route route;

    public static void main(String[] args) {
        long totalStartTime = System.currentTimeMillis();

        godCreateEveryThing();

        long boardStartTime = System.currentTimeMillis();
        ship.board(persons);
        long boardEndTime = System.currentTimeMillis();
        long boardTime = boardEndTime - boardStartTime;

        long travelStartTime = System.currentTimeMillis();
        ship.travel(route);
        long travelEndTime = System.currentTimeMillis();
        long travelTime = travelEndTime - travelStartTime;

        ship.generateFinalReport();

        long totalEndTime = System.currentTimeMillis();
        long totalTime = totalEndTime - totalStartTime;

        System.out.println("Tiempo de abordaje: " + boardTime + " milisegundos");
        System.out.println("Tiempo de viaje por los planetas: " + travelTime + " milisegundos");
        System.out.println("Tiempo total de ejecución del algoritmo: " + totalTime + " milisegundos");
    }

    public static void godCreateEveryThing(){
        // Setup
        ship = new Ship();
        persons = generateRandomPeople(1000000);
        Planet planetGama = new Planet("Gama");
        Planet planetBeta = new Planet("Beta");
        Planet planetSigma = new Planet("Sigma");
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
        route = new Route();
        route.addPlanet(planetGama);
        route.addPlanet(planetBeta);
        route.addPlanet(planetSigma);
    }
}
