package com.logica3.people;

import java.util.Objects;

public record Person(
        int id,
        String name,
        int age,
        String gender,
        int[] familyIds
) {
    public boolean isWoman() {
        return Objects.equals(this.gender, "Female");
    }
    public boolean isMan() {
        return Objects.equals(this.gender, "Male");
    }
    public boolean isBaby() {
        return this.age < 3;
    }
}
