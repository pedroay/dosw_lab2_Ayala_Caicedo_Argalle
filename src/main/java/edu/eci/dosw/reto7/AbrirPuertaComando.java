package reto7;

/** Comando concreto: abre una puerta. Deshacer la vuelve a cerrar. */
public class AbrirPuertaComando implements Comando {

    private final Puerta puerta;

    public AbrirPuertaComando(Puerta puerta) {
        this.puerta = puerta;
    }

    @Override
    public void ejecutar() {
        puerta.abrir();
    }

    @Override
    public void deshacer() {
        puerta.cerrar();
    }

    @Override
    public String getDescripcion() {
        return "Abrir " + puerta.getNombre();
    }

    @Override
    public Dispositivo getDispositivo() {
        return puerta;
    }
}
