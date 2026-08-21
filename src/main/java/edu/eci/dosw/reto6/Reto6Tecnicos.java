package edu.eci.dosw.reto6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Entry point. Builds the technician chain (Chain of Responsibility),
 * captures tickets entered by the user and shows the processing result
 * along with the final statistics.
 */
public class Reto6Tecnicos {

    public static void run() {
        Scanner sc = new Scanner(System.in);
        Tecnico first = buildChain();
        List<Ticket> tickets = readTickets(sc);
        if (tickets.isEmpty()) {
            System.out.println("No tickets were entered. End of program.");
            return;
        }

        System.out.println("\n===== PROCESSING TICKETS =====");
        for (Ticket ticket : tickets) {
            System.out.println("\nTicket: \"" + ticket.getDescription() + "\" ("
                    + ticket.getDifficulty() + " / " + ticket.getPriority() + ")");
            first.handle(ticket);
        }

        System.out.println("\n===== FINAL RESULT =====");
        tickets.forEach(System.out::println);
        new EstadisticasTickets(tickets).printSummary();
        sc.close();
    }

    /**
     * Builds the technician chain. The order defines the escalation route:
     * first it is attempted with junior technicians and, if they cannot cope
     * by specialty or priority, the ticket goes up to more experienced technicians.
     */
    private static Tecnico buildChain() {
        Tecnico ana = new Tecnico("Ana", Dificultad.BASIC, Prioridad.LOW);
        Tecnico luis = new Tecnico("Luis", Dificultad.BASIC, Prioridad.MEDIUM);
        Tecnico alfonso = new Tecnico("alfonso", Dificultad.BASIC, Prioridad.HIGH);
        Tecnico carla = new Tecnico("Carla", Dificultad.INTERMEDIATE, Prioridad.MEDIUM);
        Tecnico jorge = new Tecnico("Jorge", Dificultad.INTERMEDIATE, Prioridad.HIGH);
        Tecnico sofia = new Tecnico("Sofia", Dificultad.ADVANCED, Prioridad.HIGH);

        ana.setNext(luis);
        luis.setNext(alfonso);
        alfonso.setNext(carla);
        carla.setNext(jorge);
        jorge.setNext(sofia);

        System.out.println("Configured support chain:");
        printChain(ana);
        return ana;
    }

    /** Prints the technician chain. */
    private static void printChain(Tecnico first) {
        StringBuilder sb = new StringBuilder("Configured support chain:\n  ");
        Tecnico current = first;
        while (current != null) {
            sb.append(current);
            current = current.getNext();
            if (current != null)
                sb.append(" -> ");
        }
        System.out.println(sb);
    }

    /** Reads an arbitrary number of tickets from the console. */
    private static List<Ticket> readTickets(Scanner sc) {
        List<Ticket> tickets = new ArrayList<>();
        System.out.println("\n===== TICKET ENTRY =====");
        while (true) {
            System.out.println("\n--- New ticket (" + (tickets.size() + 1) + ") ---");
            System.out.print("Description (or 'exit' to finish): ");
            String description = sc.nextLine().trim();
            if (description.equalsIgnoreCase("exit") || description.equalsIgnoreCase("salir")) {
                break;
            }
            if (description.isEmpty()) {
                System.out.println("The description cannot be empty.");
                continue;
            }
            Dificultad difficulty = askDifficulty(sc);
            Prioridad priority = askPriority(sc);
            tickets.add(new Ticket(description, difficulty, priority));
        }
        return tickets;
    }

    private static Dificultad askDifficulty(Scanner sc) {
        while (true) {
            System.out.print("Difficulty [1=BASIC, 2=INTERMEDIATE, 3=ADVANCED]: ");
            String input = sc.nextLine().trim();
            switch (input) {
                case "1":
                    return Dificultad.BASIC;
                case "2":
                    return Dificultad.INTERMEDIATE;
                case "3":
                    return Dificultad.ADVANCED;
                default:
                    System.out.println("Invalid option. Enter 1, 2 or 3.");
            }
        }
    }

    private static Prioridad askPriority(Scanner sc) {
        while (true) {
            System.out.print("Priority [1=LOW, 2=MEDIUM, 3=HIGH]: ");
            String input = sc.nextLine().trim();
            switch (input) {
                case "1":
                    return Prioridad.LOW;
                case "2":
                    return Prioridad.MEDIUM;
                case "3":
                    return Prioridad.HIGH;
                default:
                    System.out.println("Invalid option. Enter 1, 2 or 3.");
            }
        }
    }
}
