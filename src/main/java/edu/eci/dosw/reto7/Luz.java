package edu.eci.dosw.reto7;

/** Receiver: a home light, with on/off state. */
public class Luz implements Dispositivo {

    private final String name;
    private boolean on;

    public Luz(String name) {
        this.name = name;
    }

    public void turnOn() {
        this.on = true;
    }

    public void turnOff() {
        this.on = false;
    }

    public boolean isOn() {
        return on;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " [" + (on ? "ON" : "OFF") + "]";
    }
}
