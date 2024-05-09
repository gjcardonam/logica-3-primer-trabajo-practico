package com.logica3.people;

public record Person(
        int id,
        String name,
        int age,
        String gender,
        int[] familyIds
) {
}
