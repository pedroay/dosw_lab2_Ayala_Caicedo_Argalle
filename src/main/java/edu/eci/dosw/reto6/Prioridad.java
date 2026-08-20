package edu.eci.dosw.reto6;

import java.util.List;

/**
 * Prioridad de un ticket. Igual que Dificultad, se implementa sin la
 * palabra clave "enum": es una clase con instancias fijas (BAJA, MEDIA,
 * ALTA) guardadas en una lista estatica, que se usa tanto para validar
 * el numero ingresado por consola como para comparar "quien puede
 * atender que" (getNivel()) y calcular el promedio de prioridad con
 * Streams.
 */
public class Prioridad {

    public static final Prioridad BAJA = new Prioridad("BAJA", 1);
    public static final Prioridad MEDIA = new Prioridad("MEDIA", 2);
    public static final Prioridad ALTA = new Prioridad("ALTA", 3);

    private static final List<Prioridad> VALORES = List.of(BAJA, MEDIA, ALTA);

    private final String nombre;
    private final int nivel;

    private Prioridad(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    /** Todos los valores posibles de prioridad, en orden. */
    public static List<Prioridad> valores() {
        return VALORES;
    }

    /**
     * Indica si el numero ingresado corresponde a una prioridad existente
     * (1..VALORES.size()).
     */
    public static boolean existeNumero(int numero) {
        return numero >= 1 && numero <= VALORES.size();
    }

    /**
     * Devuelve la Prioridad asociada a ese numero (1=BAJA, 2=MEDIA, 3=ALTA).
     * Se debe validar antes con existeNumero(); si el numero no es valido, lanza
     * excepcion.
     */
    public static Prioridad desdeNumero(int numero) {
        if (!existeNumero(numero)) {
            throw new IllegalArgumentException("Numero de prioridad invalido: " + numero);
        }
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