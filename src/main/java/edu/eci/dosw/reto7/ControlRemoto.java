package edu.eci.dosw.reto7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Invoker del patron Command. No conoce los detalles de ningun
 * dispositivo: solo sabe que recibe un Comando y lo ejecuta o
 * deshace, delegando el "como" a cada comando concreto. Ademas
 * mantiene el historial completo de acciones para la auditoria.
 */
public class ControlRemoto {

    private final List<RegistroAccion> historial = new ArrayList<>();

    /** Ejecuta un comando y deja constancia en el historial de quien lo ejecuto. */
    public void ejecutarAccion(Comando comando, String usuario) {
        comando.ejecutar();
        historial.add(new RegistroAccion(comando, usuario));
    }

    /**
     * Deshace la accion registrada en la posicion indicada (1-based, tal
     * como se le muestra al usuario). No se puede deshacer dos veces la
     * misma accion.
     */
    public boolean deshacerAccion(int numeroAccion) {
        int indice = numeroAccion - 1;
        if (indice < 0 || indice >= historial.size()) {
            return false;
        }
        RegistroAccion registro = historial.get(indice);
        if (registro.isDeshecha()) {
            return false;
        }
        registro.getComando().deshacer();
        registro.marcarDeshecha();
        return true;
    }

    /** Historial completo, en orden de ejecucion, de solo lectura. */
    public List<RegistroAccion> getHistorial() {
        return Collections.unmodifiableList(historial);
    }
}
