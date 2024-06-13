package com.logica3.people;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPersonGenerator {

    private static final Random random = new Random();
    private static final String[] names = {"Alice", "Bob", "Charlie", "David", "Eva", "Fiona", "George", "Hannah", "Ian", "Julia", "Kevin", "Lana", "Mike", "Nora", "Oscar", "Paula", "Quinn", "Rita", "Steve", "Tina", "Umberto", "Vivian", "Walter", "Xena", "Yvonne", "Zack"};
    private static final String[] genders = {"Male", "Female" };

    public static Person createRandomPerson(int id) {
        String name = names[random.nextInt(names.length)];
        int age = random.nextInt(100);
        String gender = genders[random.nextInt(genders.length)];
        int[] familyIds = random.ints(1, 10000).limit(5).toArray();
        return new Person(id, name, age, gender, familyIds);
    }

    public static List<Person> generateRandomPeople(int numberOfPeople) {
        List<Person> people = new ArrayList<>();
        for (int i = 1; i <= numberOfPeople; i++) {
            people.add(createRandomPerson(i));
        }
        return people;
    }
}

