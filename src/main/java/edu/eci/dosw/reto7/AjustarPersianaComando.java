package reto7;

/**
 * Comando concreto con parametro: fija la posicion de una persiana.
 * Igual que SetVolumenComando, guarda la posicion anterior para poder
 * restaurarla exactamente al deshacer.
 */
public class AjustarPersianaComando implements Comando {

    private final Persiana persiana;
    private final int nuevaPosicion;
    private int posicionAnterior;

    public AjustarPersianaComando(Persiana persiana, int nuevaPosicion) {
        this.persiana = persiana;
        this.nuevaPosicion = nuevaPosicion;
    }

    @Override
    public void ejecutar() {
        this.posicionAnterior = persiana.getPosicion();
        persiana.ajustarPosicion(nuevaPosicion);
    }

    @Override
    public void deshacer() {
        persiana.ajustarPosicion(posicionAnterior);
    }

    @Override
    public String getDescripcion() {
        return "Ajustar " + persiana.getNombre() + " a posicion " + nuevaPosicion;
    }

    @Override
    public Dispositivo getDispositivo() {
        return persiana;
    }
}
