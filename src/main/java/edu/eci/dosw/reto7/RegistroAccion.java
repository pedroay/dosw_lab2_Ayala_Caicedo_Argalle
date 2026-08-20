package edu.eci.dosw.reto7;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Entrada del historial de auditoria: registra QUE comando se ejecuto,
 * QUIEN lo ejecuto, CUANDO, y si posteriormente fue deshecho.
 * Es la pieza que permite responder las preguntas de auditoria
 * (quien hizo cada accion, que se deshizo, quien cambio cada dispositivo).
 */
public class RegistroAccion {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final Comando comando;
    private final String usuario;
    private final LocalDateTime momento;
    private boolean deshecha;

    public RegistroAccion(Comando comando, String usuario) {
        this.comando = comando;
        this.usuario = usuario;
        this.momento = LocalDateTime.now();
        this.deshecha = false;
    }

    public Comando getComando() {
        return comando;
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean isDeshecha() {
        return deshecha;
    }

    public void marcarDeshecha() {
        this.deshecha = true;
    }

    @Override
    public String toString() {
        String estado = deshecha ? "DESHECHA" : "VIGENTE";
        return String.format("[%s] %s | Usuario: %-10s | Dispositivo: %-14s | Estado: %s",
                momento.format(FORMATO), comando.getDescripcion(),
                usuario, comando.getDispositivo().getNombre(), estado);
    }
}
