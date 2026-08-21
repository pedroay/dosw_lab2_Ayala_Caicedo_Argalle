package edu.eci.dosw.reto7;

/** Concrete command: closes a door. Undoing opens it again. */
public class CerrarPuertaComando implements Comando {

    private final Puerta door;

    public CerrarPuertaComando(Puerta door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.close();
    }

    @Override
    public void undo() {
        door.open();
    }

    @Override
    public String getDescription() {
        return "Close " + door.getName();
    }

    @Override
    public Dispositivo getDevice() {
        return door;
    }
}
