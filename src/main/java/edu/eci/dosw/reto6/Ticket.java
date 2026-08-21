package edu.eci.dosw.reto6;

/**
 * Represents a technical support ticket.
 */
public class Ticket {
    private final String description;
    private final Dificultad difficulty;
    private final Prioridad priority;
    private boolean resolved;
    private String resolverTechnician;

    public Ticket(String description, Dificultad difficulty, Prioridad priority) {
        this.description = description;
        this.difficulty = difficulty;
        this.priority = priority;
        this.resolved = false;
        this.resolverTechnician = null;
    }

    public String getDescription() {
        return description;
    }

    public Dificultad getDifficulty() {
        return difficulty;
    }

    public Prioridad getPriority() {
        return priority;
    }

    public boolean isResolved() {
        return resolved;
    }

    public String getResolverTechnician() {
        return resolverTechnician;
    }

    /** Marks the ticket as resolved by a specific technician. */
    public void markResolved(String technicianName) {
        this.resolved = true;
        this.resolverTechnician = technicianName;
    }

    /**
     * Marks the ticket as pending escalation (no one in the chain could
     * resolve it).
     */
    public void markPendingEscalation() {
        this.resolved = false;
        this.resolverTechnician = null;
    }

    @Override
    public String toString() {
        String state = resolved
                ? "RESOLVED by " + resolverTechnician
                : "PENDING ESCALATION (no one in the chain could resolve it)";
        return String.format("Ticket[\"%s\" | Difficulty=%s | Priority=%s] -> %s",
                description, difficulty, priority, state);
    }
}
