package edu.eci.dosw.reto7;

/** Receiver: a home door, with opened/closed state. */
public class Puerta implements Dispositivo {

    private final String name;
    private boolean opened;

    public Puerta(String name) {
        this.name = name;
    }

    public void open() {
        this.opened = true;
    }

    public void close() {
        this.opened = false;
    }

    public boolean isOpen() {
        return opened;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " [" + (opened ? "OPENED" : "CLOSED") + "]";
    }
}
