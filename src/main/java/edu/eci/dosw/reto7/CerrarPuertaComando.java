package edu.eci.dosw.reto7;

/** Comando concreto: cierra una puerta. Deshacer la vuelve a abrir. */
public class CerrarPuertaComando implements Comando {

    private final Puerta puerta;

    public CerrarPuertaComando(Puerta puerta) {
        this.puerta = puerta;
    }

    @Override
    public void ejecutar() {
        puerta.cerrar();
    }

    @Override
    public void deshacer() {
        puerta.abrir();
    }

    @Override
    public String getDescripcion() {
        return "Cerrar " + puerta.getNombre();
    }

    @Override
    public Dispositivo getDispositivo() {
        return puerta;
    }
}
