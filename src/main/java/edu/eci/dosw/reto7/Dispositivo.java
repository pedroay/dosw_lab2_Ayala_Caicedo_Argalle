package edu.eci.dosw.reto7;

/**
 * Minimum contract that all home devices must fulfill.
 * Mainly used to identify, in the audit,
 * which device each command affected (Dependency Inversion: the
 * history and summary depend on this abstraction, not on each
 * concrete device).
 */
public interface Dispositivo {
    String getName();
}
