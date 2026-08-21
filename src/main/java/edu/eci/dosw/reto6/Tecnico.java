package edu.eci.dosw.reto6;

/**
 * Handler for the Chain of Responsibility pattern.
 *
 * Each Technician has a specialty (the difficulty level they master)
 * and a maximum priority they are able to handle. If the ticket does not fit
 * with their specialty or exceeds the priority they can manage, they pass it
 * to the next link in the chain. If no technician in the chain can
 * resolve it, the ticket itself is marked as pending escalation.
 */
public class Tecnico {
    private final String name;
    private final Dificultad specialty;
    private final Prioridad maxPriority;
    private Tecnico next;

    public Tecnico(String name, Dificultad specialty, Prioridad maxPriority) {
        this.name = name;
        this.specialty = specialty;
        this.maxPriority = maxPriority;
    }

    /**
     * Links the next technician in the chain and returns it, to be able to chain
     * calls.
     */
    public Tecnico setNext(Tecnico next) {
        this.next = next;
        return next;
    }

    public String getName() {
        return name;
    }

    /* Returns the next technician in the chain, or null if it is the last one. */
    public Tecnico getNext() {
        return next;
    }

    /**
     * Entry point of the pattern: attempts to resolve the ticket; if they cannot,
     * delegates the responsibility to the next technician in the chain.
     */
    public void handle(Ticket ticket) {
        if (canResolve(ticket)) {
            resolve(ticket);
        } else if (next != null) {
            System.out.printf("   %s cannot handle \"%s\" (requires %s/%s) -> escalating...%n",
                    name, ticket.getDescription(), ticket.getDifficulty(), ticket.getPriority());
            next.handle(ticket);
        } else {
            ticket.markPendingEscalation();
            System.out.printf("   %s cannot handle \"%s\" and there are no more technicians in the chain.%n",
                    name, ticket.getDescription());
        }
    }

    /**
     * A technician can resolve the ticket if their specialty matches and the
     * priority does not exceed their limit.
     */
    protected boolean canResolve(Ticket ticket) {
        return ticket.getDifficulty() == specialty
                && ticket.getPriority().getLevel() <= maxPriority.getLevel();
    }

    protected void resolve(Ticket ticket) {
        ticket.markResolved(name);
        System.out.printf("   %s resolved the ticket \"%s\".%n", name, ticket.getDescription());
    }

    @Override
    public String toString() {
        return String.format("%s (specialty=%s, max priority=%s)", name, specialty, maxPriority);
    }
}
