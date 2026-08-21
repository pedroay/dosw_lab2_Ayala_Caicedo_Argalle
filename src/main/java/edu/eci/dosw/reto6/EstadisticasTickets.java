package edu.eci.dosw.reto6;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Generates statistics about a list of tickets using the Streams API.
 */
public class EstadisticasTickets {
    private final List<Ticket> tickets;

    public EstadisticasTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    /** Number of tickets grouped by difficulty level. */
    public Map<Dificultad, Long> ticketsByLevel() {
        return tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getDifficulty, Collectors.counting()));
    }

    /** Total number of resolved tickets. */
    public long totalResolved() {
        return tickets.stream()
                .filter(Ticket::isResolved)
                .count();
    }

    /** Total number of tickets pending escalation. */
    public long totalPending() {
        return tickets.stream()
                .filter(t -> !t.isResolved())
                .count();
    }

    /** Number of resolved tickets grouped by technician. */
    public Map<String, Long> resolvedByTechnician() {
        return tickets.stream()
                .filter(Ticket::isResolved)
                .collect(Collectors.groupingBy(Ticket::getResolverTechnician, Collectors.counting()));
    }

    /** Priority average (1=LOW, 2=MEDIUM, 3=HIGH) of the resolved tickets. */
    public double averagePriorityResolved() {
        return tickets.stream()
                .filter(Ticket::isResolved)
                .mapToInt(t -> t.getPriority().getLevel())
                .average()
                .orElse(0.0);
    }

    /** Prints a readable summary to the console. */
    public void printSummary() {
        System.out.println("\n===== STATISTICS =====");
        System.out.println("Total tickets: " + tickets.size());

        System.out.println("\nTickets by difficulty level:");
        ticketsByLevel().forEach((level, quantity) -> System.out.printf("  - %-11s: %d%n", level, quantity));

        System.out.println("\nResolved tickets: " + totalResolved());
        System.out.println("Tickets pending escalation: " + totalPending());

        System.out.println("\nResolved by technician:");
        Map<String, Long> byTechnician = resolvedByTechnician();
        if (byTechnician.isEmpty()) {
            System.out.println("  (no tickets were resolved)");
        } else {
            byTechnician.forEach((technician, quantity) -> System.out.printf("  - %-10s: %d%n", technician, quantity));
        }

        System.out.printf("%nAverage priority of resolved tickets: %.2f%n", averagePriorityResolved());
        System.out.println("=========================");
    }
}
