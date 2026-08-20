package edu.eci.dosw.reto7;

/** Receiver: una persiana/ventana con posicion (0=cerrada, 100=abierta). */
public class Persiana implements Dispositivo {

    private final String nombre;
    private int posicion; 

    public Persiana(String nombre) {
        this.nombre = nombre;
        this.posicion = 0;
    }

    public void ajustarPosicion(int posicion) {
        this.posicion = Math.max(0, Math.min(100, posicion));
    }

    public int getPosicion() {
        return posicion;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " [posicion=" + posicion + "]";
    }
}
