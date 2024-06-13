package com.logica3.ship;

import com.logica3.people.Person;
import com.logica3.planet.Planet;
import com.logica3.randomAlgorithms.RandomAlgorithms;
import com.logica3.events.*;
import com.logica3.planet.Node;
import com.logica3.planet.Route;

import java.util.*;

public class Ship {
    private final Room[][] rooms;
    RandomAlgorithms randomAlgorithms = new RandomAlgorithms();
    List<Report> reports = new ArrayList<>();
    Room[][] initialPeople;

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
        this.initialPeople = deepCopyRooms(this.rooms);
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

            // Traveling message
            System.out.println("Traveling to " + planet.getName());

            Room[][] roomsBeforeDamage = deepCopyRooms(this.rooms);

            // An event occurs in the planet and the ship is damaged
            Event event = planet.generateEvent();
            handleEvent(event); // Handle the event and people die

            // Generate a report
            Room [][] roomsAfterDamage = deepCopyRooms(this.rooms);
            reports.add(new Report(planet, event, roomsBeforeDamage, roomsAfterDamage));

            node = node.getNext();
        }
        // Generate final comparison after all events have occurred in all planets and people have died or been kidnapped
        compareRooms(this.initialPeople, this.rooms);
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
                        List<Person> women = new ArrayList<>();
                        for(int i=0; i<5; i++){
                            for(int j=0; j<5; j++){
                                Person[] womenInRoom = this.rooms[i][j].getWomen();
                                women.addAll(Arrays.asList(womenInRoom));
                            }
                        }
                        kidnapPeople(dead, women);
                        System.out.println("Pirates have kidnapped 10 women!");
                    }
                    else{
                        List<Person> men = new ArrayList<>();
                        for(int i=0; i<5; i++){
                            for(int j=0; j<5; j++){
                                Person[] menInRoom = this.rooms[i][j].getMen();
                                men.addAll(Arrays.asList(menInRoom));
                            }
                        }
                        kidnapPeople(dead, men);
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
                        List<Person> persons = this.rooms[i][j].getPersons();
                        for (int seat = 0; seat < persons.size(); seat++) {
                            Person person = persons.get(seat);
                            if (person != null && person.isBaby()) {
                                if (this.rooms[i][j].removePerson(seat)) {
                                    dead++;
                                    break;
                                }
                            }
                        }
                    }
                    System.out.println(shields.length + " solar storm hit the ship.");
                    System.out.println("Total dead: " + dead);
                    break;
            }
        }

    private void kidnapPeople(int dead, List<Person> persons) {
        Collections.shuffle(persons);
        List<Person> selectedPersons = persons.size() >= 10 ? persons.subList(0, 10) : persons;
        for (Person person: selectedPersons) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (this.rooms[i][j].getPersons().contains(person)) {
                        int seat = this.rooms[i][j].getPersons().indexOf(person);
                        if(this.rooms[i][j].removePerson(seat))
                            dead++;
                    }
                }
            }
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

    public void generateFinalReport() {
        System.out.println("Reporte final:");
        showDistinctAgesAndTotalCount();
    }

    public void showDistinctAgesAndTotalCount() {
        List<Person> allPeople = new ArrayList<>();
        for (Room[] row : rooms) {
            for (Room room : row) {
                allPeople.addAll(room.getPersons());
            }
        }

        Set<Integer> distinctAges = new HashSet<>();
        int totalCount = 0;
        int sumOfAges = 0;
        Map<Integer, Integer> ageFrequency = new HashMap<>();

        for (Person person : allPeople) {
            if (person != null) {
                int age = person.age();
                distinctAges.add(age);
                totalCount++;
                sumOfAges += age;
                ageFrequency.put(age, ageFrequency.getOrDefault(age, 0) + 1);
            }
        }

        double averageAge = (double) sumOfAges / totalCount;
        double secondMoment = 0;

        for (Map.Entry<Integer, Integer> entry : ageFrequency.entrySet()) {
            int age = entry.getKey();
            int frequency = entry.getValue();
            double deviation = age - averageAge;
            secondMoment += frequency * deviation * deviation;
        }

        secondMoment /= totalCount;

        System.out.println("Cantidad de personas con edades distintas: " + distinctAges.size());
        System.out.println("Total de personas que quedaron en la nave: " + totalCount);
        System.out.println("Segundo momento: " + secondMoment);
        System.out.println("Edad promedio: " + averageAge);
    }
}
