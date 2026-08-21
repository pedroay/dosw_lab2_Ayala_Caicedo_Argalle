package edu.eci.dosw.reto7;

/**
 * Concrete command with parameter: sets the volume of the music system.
 * Saves the previous volume at the time of execution, to be able to
 * restore it exactly when undoing (it is not enough to "lower" the volume,
 * you have to return to the exact value it had before).
 */
public class SetVolumenComando implements Comando {

    private final SistemaMusica system;
    private final int newVolume;
    private int previousVolume;

    public SetVolumenComando(SistemaMusica system, int newVolume) {
        this.system = system;
        this.newVolume = newVolume;
    }

    @Override
    public void execute() {
        this.previousVolume = system.getVolume();
        system.setVolume(newVolume);
    }

    @Override
    public void undo() {
        system.setVolume(previousVolume);
    }

    @Override
    public String getDescription() {
        return "Set volume of " + system.getName() + " to " + newVolume;
    }

    @Override
    public Dispositivo getDevice() {
        return system;
    }
}
