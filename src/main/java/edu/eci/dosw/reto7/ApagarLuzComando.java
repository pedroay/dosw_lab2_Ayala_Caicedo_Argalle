package edu.eci.dosw.reto7;

/** Concrete command: turns off a light. Undoing turns it on again. */
public class ApagarLuzComando implements Comando {

    private final Luz light;

    public ApagarLuzComando(Luz light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }

    @Override
    public String getDescription() {
        return "Turn off " + light.getName();
    }

    @Override
    public Dispositivo getDevice() {
        return light;
    }
}
