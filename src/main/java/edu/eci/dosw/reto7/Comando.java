package reto7;

/**
 * Contrato del patron Command. Cada accion del control remoto se
 * encapsula como un Comando que sabe ejecutarse y deshacerse a si
 * mismo, y sabe describirse y decir a que dispositivo afecta
 * (necesario para la auditoria).
 *
 * Al depender solo de esta abstraccion, el ControlRemoto (invoker)
 * puede ejecutar y deshacer cualquier accion presente o futura sin
 * conocer los detalles de cada dispositivo (Open/Closed + Dependency
 * Inversion).
 */
public interface Comando {

    /** Ejecuta la accion sobre el dispositivo. */
    void ejecutar();

    /** Revierte la accion, dejando el dispositivo en su estado previo. */
    void deshacer();

    /** Descripcion legible de la accion, para historial y auditoria. */
    String getDescripcion();

    /** Dispositivo afectado por este comando. */
    Dispositivo getDispositivo();
}
