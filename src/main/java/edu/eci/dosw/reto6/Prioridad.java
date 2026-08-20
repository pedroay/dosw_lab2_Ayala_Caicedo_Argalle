package reto6;

/**
 * Prioridad de un ticket. Cada valor lleva asociado un nivel numerico
 * que se usa tanto para comparar "quien puede atender que" como para
 * calcular estadisticas (promedio de prioridad) con Streams.
 */
public enum Prioridad {
    BAJA(1),
    MEDIA(2),
    ALTA(3);
    private final int nivel;
    Prioridad(int nivel) {
        this.nivel = nivel;
    }
    public int getNivel() {
        return nivel;
    }
}
