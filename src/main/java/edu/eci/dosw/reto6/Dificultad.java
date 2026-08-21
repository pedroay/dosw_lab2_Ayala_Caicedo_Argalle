package edu.eci.dosw.reto6;

import java.util.List;

public class Dificultad {
    public static final Dificultad BASIC = new Dificultad("BASIC", 1);
    public static final Dificultad INTERMEDIATE = new Dificultad("INTERMEDIATE", 2);
    public static final Dificultad ADVANCED = new Dificultad("ADVANCED", 3);

    private static final List<Dificultad> VALUES = List.of(BASIC, INTERMEDIATE, ADVANCED);

    private final String name;
    private final int level;

    private Dificultad(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public static List<Dificultad> values() {
        return VALUES;
    }

    public static boolean numberExists(int number) {
        return number >= 1 && number <= VALUES.size();
    }

    public static Dificultad fromNumber(int number) {
        if (!numberExists(number))
            throw new IllegalArgumentException("Invalid difficulty number: " + number);
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