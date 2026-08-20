package edu.eci.dosw.reto6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Punto de entrada. Arma la cadena de tecnicos (Chain of Responsibility),
 * captura tickets ingresados por el usuario y muestra el resultado del
 * procesamiento junto con las estadisticas finales.
 */
public class Reto6Tecnicos {

    public static void run() {
        Scanner sc = new Scanner(System.in);
        Tecnico primero = construirCadena();
        List<Ticket> tickets = leerTickets(sc);
        if (tickets.isEmpty()) {
            System.out.println("No se ingreso ningun ticket. Fin del programa.");
            return;
        }

        System.out.println("\n===== PROCESANDO TICKETS =====");
        for (Ticket ticket : tickets) {
            System.out.println("\nTicket: \"" + ticket.getDescripcion() + "\" ("
                    + ticket.getDificultad() + " / " + ticket.getPrioridad() + ")");
            primero.atender(ticket);
        }

        System.out.println("\n===== RESULTADO FINAL =====");
        tickets.forEach(System.out::println);
        new EstadisticasTickets(tickets).imprimirResumen();
        sc.close();
    }

    /**
     * Construye la cadena de tecnicos. El orden define la ruta de escalacion:
     * primero se intenta con los tecnicos junior y, si no dan abasto por
     * especialidad o por prioridad, el ticket sube hacia tecnicos con mas
     * experiencia.
     */
    private static Tecnico construirCadena() {
        Tecnico ana = new Tecnico("Ana", Dificultad.BASICO, Prioridad.BAJA);
        Tecnico luis = new Tecnico("Luis", Dificultad.BASICO, Prioridad.MEDIA);
        Tecnico alfonso = new Tecnico("alfonso", Dificultad.BASICO, Prioridad.ALTA);
        Tecnico carla = new Tecnico("Carla", Dificultad.INTERMEDIO, Prioridad.MEDIA);
        Tecnico jorge = new Tecnico("Jorge", Dificultad.INTERMEDIO, Prioridad.ALTA);
        Tecnico sofia = new Tecnico("Sofia", Dificultad.AVANZADO, Prioridad.ALTA);

        ana.setSiguiente(luis);
        luis.setSiguiente(alfonso);
        alfonso.setSiguiente(carla);
        carla.setSiguiente(jorge);
        jorge.setSiguiente(sofia);

        System.out.println("Cadena de soporte configurada:");
        imprimirCadena(ana);
        return ana;
    }

    /** Imprime la cadena de tecnicos. */
    private static void imprimirCadena(Tecnico primero) {
        StringBuilder sb = new StringBuilder("Cadena de soporte configurada:\n  ");
        Tecnico actual = primero;
        while (actual != null) {
            sb.append(actual);
            actual = actual.getSiguiente();
            if (actual != null)
                sb.append(" -> ");
        }
        System.out.println(sb);
    }

    /** Lee un numero arbitrario de tickets desde la consola. */
    private static List<Ticket> leerTickets(Scanner sc) {
        List<Ticket> tickets = new ArrayList<>();
        System.out.println("\n===== INGRESO DE TICKETS =====");
        while (true) {
            System.out.println("\n--- Nuevo ticket (" + (tickets.size() + 1) + ") ---");
            System.out.print("Descripcion (o 'salir' para terminar): ");
            String descripcion = sc.nextLine().trim();
            if (descripcion.equalsIgnoreCase("salir")) {
                break;
            }
            if (descripcion.isEmpty()) {
                System.out.println("La descripcion no puede estar vacia.");
                continue;
            }
            Dificultad dificultad = pedirDificultad(sc);
            Prioridad prioridad = pedirPrioridad(sc);
            tickets.add(new Ticket(descripcion, dificultad, prioridad));
        }
        return tickets;
    }

    private static Dificultad pedirDificultad(Scanner sc) {
        while (true) {
            System.out.print("Dificultad [1=BASICO, 2=INTERMEDIO, 3=AVANZADO]: ");
            String entrada = sc.nextLine().trim();
            switch (entrada) {
                case "1":
                    return Dificultad.BASICO;
                case "2":
                    return Dificultad.INTERMEDIO;
                case "3":
                    return Dificultad.AVANZADO;
                default:
                    System.out.println("Opcion invalida. Ingrese 1, 2 o 3.");
            }
        }
    }

    private static Prioridad pedirPrioridad(Scanner sc) {
        while (true) {
            System.out.print("Prioridad [1=BAJA, 2=MEDIA, 3=ALTA]: ");
            String entrada = sc.nextLine().trim();
            switch (entrada) {
                case "1":
                    return Prioridad.BAJA;
                case "2":
                    return Prioridad.MEDIA;
                case "3":
                    return Prioridad.ALTA;
                default:
                    System.out.println("Opcion invalida. Ingrese 1, 2 o 3.");
            }
        }
    }
}
