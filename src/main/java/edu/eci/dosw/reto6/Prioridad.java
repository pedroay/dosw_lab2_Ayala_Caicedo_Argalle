package edu.eci.dosw.reto6;

import java.util.List;

/**
 * Priority of a ticket. Just like Difficulty, it is implemented without the
 * "enum" keyword: it is a class with fixed instances (LOW, MEDIUM,
 * HIGH) stored in a static list, which is used both to validate
 * the number entered by console and to compare "who can
 * handle what" (getLevel()) and calculate the priority average with
 * Streams.
 */
public class Prioridad {

    public static final Prioridad LOW = new Prioridad("LOW", 1);
    public static final Prioridad MEDIUM = new Prioridad("MEDIUM", 2);
    public static final Prioridad HIGH = new Prioridad("HIGH", 3);

    private static final List<Prioridad> VALUES = List.of(LOW, MEDIUM, HIGH);

    private final String name;
    private final int level;

    private Prioridad(String name, int level) {
        this.name = name;
        this.level = level;
    }

    /** All possible priority values, in order. */
    public static List<Prioridad> values() {
        return VALUES;
    }

    /**
     * Indicates if the entered number corresponds to an existing priority
     * (1..VALUES.size()).
     */
    public static boolean numberExists(int number) {
        return number >= 1 && number <= VALUES.size();
    }

    /**
     * Returns the Priority associated with that number (1=LOW, 2=MEDIUM, 3=HIGH).
     * Must be validated first with numberExists(); if the number is invalid, throws
     * exception.
     */
    public static Prioridad fromNumber(int number) {
        if (!numberExists(number)) {
            throw new IllegalArgumentException("Invalid priority number: " + number);
        }
        return VALUES.get(number - 1);
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return name;
    }
}