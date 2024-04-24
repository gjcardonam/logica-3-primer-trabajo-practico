package com.logica3;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<Person> persons;

    public Room(){
        this.persons = new ArrayList<Person>(4);

        for (int i = 0; i < 4; i++) {
            this.persons.add(null);
        }
    }

    public void assignPlace(Person person, int index){
        if (this.persons.get(index) != null){
            // System.out.println("Place is already taken");
            throw new IllegalArgumentException();
        }
        this.persons.set(index, person);
    }

    public void removePerson(int index){
        if (this.persons.get(index) != null){
            // System.out.println("Place is already taken");
            this.persons.set(index, null);
        }
        throw new IllegalArgumentException();
    }

    public boolean removeRandomPerson(){
        int index = (int) (Math.random() * 4);
        if (this.persons.get(index) != null){
            this.persons.set(index, null);
            return true;
        }
        return false;
    }

    // public boolean isFull(){
    //     return this.persons.size() == 4;
    // }

    // public boolean isEmpty(){
    //     return this.persons.size() == 0;
    // }

    public List<Person> getPersons(){
        return this.persons;
    }

    public Person getPerson (int index){
        return this.persons.get(index);
    }
}
