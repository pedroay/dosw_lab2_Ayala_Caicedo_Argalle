package reto7;

/** Comando concreto: apaga una luz. Deshacer la vuelve a encender. */
public class ApagarLuzComando implements Comando {

    private final Luz luz;

    public ApagarLuzComando(Luz luz) {
        this.luz = luz;
    }

    @Override
    public void ejecutar() {
        luz.apagar();
    }

    @Override
    public void deshacer() {
        luz.encender();
    }

    @Override
    public String getDescripcion() {
        return "Apagar " + luz.getNombre();
    }

    @Override
    public Dispositivo getDispositivo() {
        return luz;
    }
}
