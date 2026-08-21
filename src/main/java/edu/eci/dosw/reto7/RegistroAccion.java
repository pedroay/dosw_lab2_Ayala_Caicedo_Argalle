package edu.eci.dosw.reto7;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Audit history entry: records WHICH command was executed,
 * WHO executed it, WHEN, and if it was subsequently undone.
 * It is the piece that allows answering audit questions
 * (who did each action, what was undone, who changed each device).
 */
public class RegistroAccion {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final Comando command;
    private final String user;
    private final LocalDateTime timestamp;
    private boolean undone;

    public RegistroAccion(Comando command, String user) {
        this.command = command;
        this.user = user;
        this.timestamp = LocalDateTime.now();
        this.undone = false;
    }

    public Comando getCommand() {
        return command;
    }

    public String getUser() {
        return user;
    }

    public boolean isUndone() {
        return undone;
    }

    public void markUndone() {
        this.undone = true;
    }

    @Override
    public String toString() {
        String state = undone ? "UNDONE" : "ACTIVE";
        return String.format("[%s] %s | User: %-10s | Device: %-14s | State: %s",
                timestamp.format(FORMAT), command.getDescription(),
                user, command.getDevice().getName(), state);
    }
}
