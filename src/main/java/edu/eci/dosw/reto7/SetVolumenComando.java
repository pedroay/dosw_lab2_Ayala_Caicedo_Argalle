package reto7;

/**
 * Comando concreto con parametro: fija el volumen del sistema de musica.
 * Guarda el volumen anterior en el momento de ejecutar, para poder
 * restaurarlo exactamente al deshacer (no basta con "bajar" el volumen,
 * hay que volver al valor exacto que tenia antes).
 */
public class SetVolumenComando implements Comando {

    private final SistemaMusica sistema;
    private final int nuevoVolumen;
    private int volumenAnterior;

    public SetVolumenComando(SistemaMusica sistema, int nuevoVolumen) {
        this.sistema = sistema;
        this.nuevoVolumen = nuevoVolumen;
    }

    @Override
    public void ejecutar() {
        this.volumenAnterior = sistema.getVolumen();
        sistema.setVolumen(nuevoVolumen);
    }

    @Override
    public void deshacer() {
        sistema.setVolumen(volumenAnterior);
    }

    @Override
    public String getDescripcion() {
        return "Fijar volumen de " + sistema.getNombre() + " en " + nuevoVolumen;
    }

    @Override
    public Dispositivo getDispositivo() {
        return sistema;
    }
}
