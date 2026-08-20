package reto6;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Genera estadisticas sobre una lista de tickets usando la API de Streams.
 */
public class EstadisticasTickets {
    private final List<Ticket> tickets;
    public EstadisticasTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    /** Cantidad de tickets agrupados por nivel de dificultad. */
    public Map<Dificultad, Long> ticketsPorNivel() {
        return tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getDificultad, Collectors.counting()));
    }
    /** Cantidad total de tickets resueltos. */
    public long totalResueltos() {
        return tickets.stream()
                .filter(Ticket::isResuelto)
                .count();
    }

    /** Cantidad total de tickets pendientes de escalacion. */
    public long totalPendientes() {
        return tickets.stream()
                .filter(t -> !t.isResuelto())
                .count();
    }

    /** Cantidad de tickets resueltos agrupados por tecnico. */
    public Map<String, Long> resueltosPorTecnico() {
        return tickets.stream()
                .filter(Ticket::isResuelto)
                .collect(Collectors.groupingBy(Ticket::getTecnicoResolutor, Collectors.counting()));
    }

    /** Promedio de prioridad (1=BAJA, 2=MEDIA, 3=ALTA) de los tickets resueltos. */
    public double promedioPrioridadResueltos() {
        return tickets.stream()
                .filter(Ticket::isResuelto)
                .mapToInt(t -> t.getPrioridad().getNivel())
                .average()
                .orElse(0.0);
    }

    /** Imprime un resumen legible en consola. */
    public void imprimirResumen() {
        System.out.println("\n===== ESTADISTICAS =====");
        System.out.println("Total de tickets: " + tickets.size());

        System.out.println("\nTickets por nivel de dificultad:");
        ticketsPorNivel().forEach((nivel, cantidad) ->
                System.out.printf("  - %-11s: %d%n", nivel, cantidad));

        System.out.println("\nTickets resueltos: " + totalResueltos());
        System.out.println("Tickets pendientes de escalacion: " + totalPendientes());

        System.out.println("\nResueltos por tecnico:");
        Map<String, Long> porTecnico = resueltosPorTecnico();
        if (porTecnico.isEmpty()) {
            System.out.println("  (ningun ticket fue resuelto)");
        } else {
            porTecnico.forEach((tecnico, cantidad) ->
                    System.out.printf("  - %-10s: %d%n", tecnico, cantidad));
        }

        System.out.printf("%nPromedio de prioridad de tickets resueltos: %.2f%n", promedioPrioridadResueltos());
        System.out.println("=========================");
    }
}
