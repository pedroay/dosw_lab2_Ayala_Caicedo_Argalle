package reto7;

/** Receiver: una puerta del hogar, con estado abierta/cerrada. */
public class Puerta implements Dispositivo {

    private final String nombre;
    private boolean abierta;

    public Puerta(String nombre) {
        this.nombre = nombre;
    }

    public void abrir() {
        this.abierta = true;
    }

    public void cerrar() {
        this.abierta = false;
    }

    public boolean isAbierta() {
        return abierta;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " [" + (abierta ? "ABIERTA" : "CERRADA") + "]";
    }
}
