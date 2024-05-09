package com.logica3.peopleAndShip;

public record Person(
        String name,
        int id,
        int age,
        String gender,
        int[] familyIds
) {
}
