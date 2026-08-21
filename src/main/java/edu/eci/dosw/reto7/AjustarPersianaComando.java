package edu.eci.dosw.reto7;

/**
 * Concrete command with parameter: sets the position of a blind.
 * Like SetVolumenComando, saves the previous position to be able to
 * restore it exactly when undoing.
 */
public class AjustarPersianaComando implements Comando {

    private final Persiana blind;
    private final int newPosition;
    private int previousPosition;

    public AjustarPersianaComando(Persiana blind, int newPosition) {
        this.blind = blind;
        this.newPosition = newPosition;
    }

    @Override
    public void execute() {
        this.previousPosition = blind.getPosition();
        blind.adjustPosition(newPosition);
    }

    @Override
    public void undo() {
        blind.adjustPosition(previousPosition);
    }

    @Override
    public String getDescription() {
        return "Adjust " + blind.getName() + " to position " + newPosition;
    }

    @Override
    public Dispositivo getDevice() {
        return blind;
    }
}
