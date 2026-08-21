package edu.eci.dosw.reto7;

/** Concrete command: turns on a light. Undoing turns it off again. */
public class EncenderLuzComando implements Comando {

    private final Luz light;

    public EncenderLuzComando(Luz light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }

    @Override
    public String getDescription() {
        return "Turn on " + light.getName();
    }

    @Override
    public Dispositivo getDevice() {
        return light;
    }
}
