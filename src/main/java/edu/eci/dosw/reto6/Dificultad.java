package edu.eci.dosw.reto6;

import java.util.List;

public class Dificultad {
    public static final Dificultad BASICO = new Dificultad("BASICO", 1);
    public static final Dificultad INTERMEDIO = new Dificultad("INTERMEDIO", 2);
    public static final Dificultad AVANZADO = new Dificultad("AVANZADO", 3);

    private static final List<Dificultad> VALORES = List.of(BASICO, INTERMEDIO, AVANZADO);

    private final String nombre;
    private final int nivel;

    private Dificultad(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public static List<Dificultad> valores() {
        return VALORES;
    }

    public static boolean existeNumero(int numero) {
        return numero >= 1 && numero <= VALORES.size();
    }

    public static Dificultad desdeNumero(int numero) {
        if (!existeNumero(numero))
            throw new IllegalArgumentException("Numero de dificultad invalido: " + numero);
        return VALORES.get(numero - 1);
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    @Override
    public String toString() {
        return nombre;
    }
}