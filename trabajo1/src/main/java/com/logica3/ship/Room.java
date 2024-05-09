package com.logica3.ship;

import com.logica3.people.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    private final List<Person> persons;

    public Room(){
        this.persons = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {
            this.persons.add(null);
        }
    }

    public Room(Room other) {
        this.persons = new ArrayList<>(other.persons.size());
        this.persons.addAll(other.persons);
    }

    public void assignPlace(Person person, int index){
        if (this.persons.get(index) != null){
            // System.out.println("Place is already taken");
            throw new IllegalArgumentException();
        }
        this.persons.set(index, person);
    }

    public boolean removePerson(int index){
        if (this.persons.get(index) != null){
            // System.out.println("Place is already taken");
            this.persons.set(index, null);
            return true;
        }
        return false;
    }

    public boolean removeRandomPerson(){
        int index = (int) (Math.random() * 3);
        if (this.persons.get(index) != null){
            this.persons.set(index, null);
            return true;
        }
        return false;
    }

    public Person[] getWomen(){
        return this.persons.stream().filter(person -> person != null && person.isWoman()).toArray(Person[]::new);
    }

    public Person[] getMen(){
        return this.persons.stream().filter(person -> person != null && person.isMan()).toArray(Person[]::new);
    }

    public List<Person> getPersons(){
        return this.persons;
    }

    public Person getPerson (int index){
        return this.persons.get(index);
    }
}
