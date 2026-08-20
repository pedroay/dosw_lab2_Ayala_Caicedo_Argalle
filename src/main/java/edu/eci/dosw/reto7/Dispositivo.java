package reto7;

/**
 * Contrato minimo que deben cumplir todos los dispositivos del hogar.
 * Se usa principalmente para poder identificar, en la auditoria,
 * a que dispositivo afecto cada comando (Dependency Inversion: el
 * historial y el resumen dependen de esta abstraccion, no de cada
 * dispositivo concreto).
 */
public interface Dispositivo {
    String getNombre();
}
