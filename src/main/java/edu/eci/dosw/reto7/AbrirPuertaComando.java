package edu.eci.dosw.reto7;

/** Concrete command: opens a door. Undoing closes it again. */
public class AbrirPuertaComando implements Comando {

    private final Puerta door;

    public AbrirPuertaComando(Puerta door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.open();
    }

    @Override
    public void undo() {
        door.close();
    }

    @Override
    public String getDescription() {
        return "Open " + door.getName();
    }

    @Override
    public Dispositivo getDevice() {
        return door;
    }
}
