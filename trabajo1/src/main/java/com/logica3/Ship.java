package com.logica3;

import java.util.ArrayList;
import java.util.List;

public class Ship {
        private final Room[][] rooms;
        private final RandomAlgorithms randomAlgorithms;

        public Ship(){
            this.rooms = new Room[5][5];
            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    this.rooms[i][j] = new Room();
                }
            }

            this.randomAlgorithms = new RandomAlgorithms();
        }

        public void board(Person[] persons){
            for (Person person : persons){
                this.addPerson(person);
            }
        }

        private void addPerson(Person person){
            int attempts = 0;
            boolean successful = false;
            while (!successful && attempts < 3){
                try{
                    double n = this.randomAlgorithms.hash1(person.getId());
                    if (attempts == 1)
                        n = this.randomAlgorithms.hash2(person.getId());
                    if (attempts == 2)
                        n = this.randomAlgorithms.hash3(person.getId());
                    double indexRoom = n / 4;
                    int i = (int) indexRoom / 5;
                    int j = (int ) indexRoom % 5;
                    this.rooms[i][j].assignPlace(person, (int) n % 4);
                    successful = true;
                }
                catch (IllegalArgumentException e){
                    attempts++;
                }
            }            

            // System.out.println("Person " + person.getName() + " with id " + person.getId() + " was assigned to room " + i + " " + j + " " + " place " + (int) n % 4);

        }

        // public Room getRoom(int x, int y){
        //     return this.rooms[x][y];
        // }

        public void listPeople(){
            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    List<Person> persons = this.rooms[i][j].getPersons();
                    for (Person person : persons){
                        if(person != null){
                            System.out.println("Person " + person.getName() + " with id " + person.getId() + " is in room " + i + " " + j);
                        }
                    }
                }
            }
        }

        public void travel(Route route){
            Node node = route.getHead();
            while (node != null){
                System.out.println("Traveling to " + node.getPlanet().getName());
                Event event = node.getPlanet().generateEvent();
                handleEvent(event);
                node = node.getNext();
            }
        }

        private void handleEvent(Event event){
            event.occur();
            int dead = 0;
            switch (event.getName()) {
                case "Air Poisonous Scape":
                    AirPoisonousScape airPoisonousScape = (AirPoisonousScape) event;
                    int[] keys = airPoisonousScape.ids;
                    int[] rooms = new int[100];
                    for (int i = 0; i < 100; i++) {
                        rooms[i] = i;
                    }
                    boolean[] inSet = randomAlgorithms.bloomFilter(rooms, keys);
                    for (int i = 0; i < 100; i++) {
                        if (inSet[i]) {
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
                    long estimatedDistinct = randomAlgorithms.counting(arr2);
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
                    System.out.println(shields.length + " asteroids hit the ship.");
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
