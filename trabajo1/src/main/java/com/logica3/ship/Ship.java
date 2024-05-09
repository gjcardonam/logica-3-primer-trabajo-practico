package com.logica3.ship;

import com.logica3.people.Person;
import com.logica3.planet.Planet;
import com.logica3.randomAlgorithms.RandomAlgorithms;
import com.logica3.events.*;
import com.logica3.planet.Node;
import com.logica3.planet.Route;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final Room[][] rooms;
    RandomAlgorithms randomAlgorithms = new RandomAlgorithms();
    List<Report> reports = new ArrayList<>();

    public Ship(){
        this.rooms = new Room[5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                this.rooms[i][j] = new Room();
            }
        }
    }

    public void board(List<Person> persons){
        for (Person person : persons){
            this.addPerson(person);
        }
    }

    private void addPerson(Person person){
        int attempts = 0;
        boolean successful = false;
        while (!successful && attempts < 3){
            try{
                double n = this.randomAlgorithms.hash1(person.id());
                if (attempts == 1)
                    n = this.randomAlgorithms.hash2(person.id());
                if (attempts == 2)
                    n = this.randomAlgorithms.hash3(person.id());
                int indexRoom = (int) (n / 4);
                int i = indexRoom / 5;
                int j = indexRoom % 5;
                int seat = (int) n % 4;
                this.rooms[i][j].assignPlace(person, seat);
                successful = true;
            }
            catch (IllegalArgumentException e){
                attempts++;
            }
        }

        // System.out.println("Person " + person.getName() + " with id " + person.getId() + " was assigned to room " + i + " " + j + " " + " place " + (int) n % 4);

    }

    public void listPeople(){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                List<Person> persons = this.rooms[i][j].getPersons();
                for (Person person : persons){
                    if(person != null){
                        System.out.println("Person " + person.name() + " with id " + person.id() + " is in room " + i + " " + j);
                    }
                }
            }
        }
    }

    public void travel(Route route){
        Node node = route.getHead();
        while (node != null){
            Planet planet = node.getPlanet();
            System.out.println("Traveling to " + planet.getName());
            Event event = planet.generateEvent();
            Room[][] roomsBeforeDamage = deepCopyRooms(this.rooms);
            handleEvent(event);
            compareRooms(roomsBeforeDamage, this.rooms);
            reports.add(new Report(planet, event, roomsBeforeDamage, this.rooms));
            node = node.getNext();
        }
    }

    private void compareRooms(Room[][] before, Room[][] after) {
        for (int i = 0; i < before.length; i++) {
            for (int j = 0; j < before[i].length; j++) {
                List<Person> personsBefore = before[i][j].getPersons();
                List<Person> personsAfter = after[i][j].getPersons();
                for (Person p : personsBefore) {
                    if (!personsAfter.contains(p)) {
                        System.out.println(p.name() + " with id " + p.id() + " has died in room (" + i + ", " + j + "),");
                    }
                }
            }
        }
    }

    private Room[][] deepCopyRooms(Room[][] originalRooms) {
        Room[][] copiedRooms = new Room[originalRooms.length][];
        for (int i = 0; i < originalRooms.length; i++) {
            copiedRooms[i] = new Room[originalRooms[i].length];
            for (int j = 0; j < originalRooms[i].length; j++) {
                copiedRooms[i][j] = new Room(originalRooms[i][j]);
            }
        }
        return copiedRooms;
    }

    private void handleEvent(Event event){
            event.occur();
            int dead = 0;
            switch (event.getName()) {
                case "Air Poisonous Scape":
                    AirPoisonousScape airPoisonousScape = (AirPoisonousScape) event;
                    int[] examResults = airPoisonousScape.ids;
                    int[] seats = new int[100];
                    for (int i = 0; i < 100; i++) {
                        seats[i] = i;
                    }
                    boolean[] findingResults = randomAlgorithms.finding(seats, examResults);
                    for (int i = 0; i < 100; i++) {
                        if (findingResults[i]) {
                            System.out.println("Room " + i + " possibly has been poisoned.");
                        }
                    }
                    break;
                case "Asteroid":
                    Asteroid asteroids = (Asteroid) event;
                    int[]arr = asteroids.ids;
                    randomAlgorithms.ordering(arr);
                    for (int n : arr) {
                        int indexRoom = n / 4;
                        int i = indexRoom / 5;
                        int j = indexRoom % 5;
                        if (this.rooms[i][j].removeRandomPerson())
                            dead++;
                    }
                    System.out.println(arr.length + " asteroids hit the ship.");
                    System.out.println("Total dead: " + dead);
                    break;
                case "Mental Sickness":
                    break;
                case "Pirates":
                    Pirates pirates = (Pirates) event;
                    int[]arr2 = pirates.ids;
                    double estimatedDistinct = randomAlgorithms.counting(arr2);
                    if (estimatedDistinct == 10){
                        System.out.println("Pirates have kidnapped 10 women!");
                    }
                    else{
                        System.out.println("Pirates have kidnapped 10 men!");
                    }
                    break;
                case "Solar Storm":
                    SolarStorm solarStorm = (SolarStorm) event;
                    int[]arr3 = solarStorm.ids;
                    int[] shields = randomAlgorithms.sampling(arr3, 100);
                    int[] noShields = getNoShields(shields);
                    for (int n : noShields) {
                        int indexRoom = n / 4;
                        int i = indexRoom / 5;
                        int j = indexRoom % 5;
                        if (this.rooms[i][j].removeRandomPerson())
                            dead++;
                    }
                    System.out.println(shields.length + " solar storm hit the ship.");
                    System.out.println("Total dead: " + dead);
                    break;
            }
        }

    private static int[] getNoShields(int[] shields) {
        List<Integer> noShields = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            boolean found = false;
            for (int shield : shields) {
                if (i == shield) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                noShields.add(i);
            }
        }
        return noShields.stream().mapToInt(i -> i).toArray();
    }

}