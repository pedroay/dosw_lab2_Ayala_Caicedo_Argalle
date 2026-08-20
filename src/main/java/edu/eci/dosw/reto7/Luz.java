package edu.eci.dosw.reto7;

/** Receiver: una luz del hogar, con estado encendido/apagado. */
public class Luz implements Dispositivo {

    private final String nombre;
    private boolean encendida;

    public Luz(String nombre) {
        this.nombre = nombre;
    }

    public void encender() {
        this.encendida = true;
    }

    public void apagar() {
        this.encendida = false;
    }

    public boolean isEncendida() {
        return encendida;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " [" + (encendida ? "ENCENDIDA" : "APAGADA") + "]";
    }
}
