package edu.eci.dosw.reto7;

/**
 * Command pattern contract. Each remote control action is
 * encapsulated as a Command that knows how to execute and undo itself,
 * and knows how to describe itself and say which device it affects
 * (needed for auditing).
 *
 * By depending only on this abstraction, the ControlRemoto (invoker)
 * can execute and undo any current or future action without
 * knowing the details of each device (Open/Closed + Dependency
 * Inversion).
 */
public interface Comando {

    /** Executes the action on the device. */
    void execute();

    /** Reverts the action, leaving the device in its previous state. */
    void undo();

    /** Readable description of the action, for history and audit. */
    String getDescription();

    /** Device affected by this command. */
    Dispositivo getDevice();
}
