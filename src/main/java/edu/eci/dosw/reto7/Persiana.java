package edu.eci.dosw.reto7;

/** Receiver: a blind/window with position (0=closed, 100=opened). */
public class Persiana implements Dispositivo {

    private final String name;
    private int position; 

    public Persiana(String name) {
        this.name = name;
        this.position = 0;
    }

    public void adjustPosition(int position) {
        this.position = Math.max(0, Math.min(100, position));
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " [position=" + position + "]";
    }
}
