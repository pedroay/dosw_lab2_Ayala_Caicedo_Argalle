package reto7;

/** Receiver: un sistema de musica con volumen (0-100). */
public class SistemaMusica implements Dispositivo {

    private final String nombre;
    private int volumen; // 0-100

    public SistemaMusica(String nombre) {
        this.nombre = nombre;
        this.volumen = 0;
    }

    public void setVolumen(int volumen) {
        this.volumen = Math.max(0, Math.min(100, volumen));
    }

    public int getVolumen() {
        return volumen;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " [volumen=" + volumen + "]";
    }
}
