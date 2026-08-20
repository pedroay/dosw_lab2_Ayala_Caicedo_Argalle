package edu.eci.dosw.reto7;

/** Comando concreto: enciende una luz. Deshacer la vuelve a apagar. */
public class EncenderLuzComando implements Comando {

    private final Luz luz;

    public EncenderLuzComando(Luz luz) {
        this.luz = luz;
    }

    @Override
    public void ejecutar() {
        luz.encender();
    }

    @Override
    public void deshacer() {
        luz.apagar();
    }

    @Override
    public String getDescripcion() {
        return "Encender " + luz.getNombre();
    }

    @Override
    public Dispositivo getDispositivo() {
        return luz;
    }
}
