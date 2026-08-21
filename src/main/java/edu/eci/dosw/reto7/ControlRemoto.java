package edu.eci.dosw.reto7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Invoker of the Command pattern. Does not know the details of any
 * device: only knows that it receives a Command and executes or
 * undoes it, delegating the "how" to each concrete command. Also
 * maintains the complete history of actions for the audit.
 */
public class ControlRemoto {

    private final List<RegistroAccion> history = new ArrayList<>();

    /** Executes a command and leaves a record in the history of who executed it. */
    public void executeAction(Comando command, String user) {
        command.execute();
        history.add(new RegistroAccion(command, user));
    }

    /**
     * Undoes the action registered at the indicated position (1-based, as
     * it is shown to the user). The same action cannot be undone twice.
     */
    public boolean undoAction(int actionNumber) {
        int index = actionNumber - 1;
        if (index < 0 || index >= history.size()) {
            return false;
        }
        RegistroAccion record = history.get(index);
        if (record.isUndone()) {
            return false;
        }
        record.getCommand().undo();
        record.markUndone();
        return true;
    }

    /** Complete history, in order of execution, read-only. */
    public List<RegistroAccion> getHistory() {
        return Collections.unmodifiableList(history);
    }
}
