package com.softtechsupport;

/**
 * Handler del patron Chain of Responsibility.
 *
 * Cada Tecnico tiene una especialidad (el nivel de dificultad que domina)
 * y una prioridad maxima que es capaz de atender. Si el ticket no encaja
 * con su especialidad o supera la prioridad que puede manejar, lo pasa
 * al siguiente eslabon de la cadena. Si ningun tecnico de la cadena puede
 * resolverlo, el propio ticket queda marcado como pendiente de escalacion.
 */
public class Tecnico {

    private final String nombre;
    private final Dificultad especialidad;
    private final Prioridad prioridadMaxima;
    private Tecnico siguiente; // siguiente eslabon de la cadena

    public Tecnico(String nombre, Dificultad especialidad, Prioridad prioridadMaxima) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.prioridadMaxima = prioridadMaxima;
    }

    /** Enlaza el siguiente tecnico en la cadena y lo devuelve, para poder encadenar llamadas. */
    public Tecnico setSiguiente(Tecnico siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Punto de entrada del patron: intenta resolver el ticket; si no puede,
     * delega la responsabilidad al siguiente tecnico de la cadena.
     */
    public void atender(Ticket ticket) {
        if (puedeResolver(ticket)) {
            resolver(ticket);
        } else if (siguiente != null) {
            System.out.printf("   %s no puede atender \"%s\" (requiere %s/%s) -> escalando...%n",
                    nombre, ticket.getDescripcion(), ticket.getDificultad(), ticket.getPrioridad());
            siguiente.atender(ticket);
        } else {
            ticket.marcarPendienteEscalacion();
            System.out.printf("   %s no puede atender \"%s\" y no hay mas tecnicos en la cadena.%n",
                    nombre, ticket.getDescripcion());
        }
    }

    /** Un tecnico puede resolver el ticket si coincide su especialidad y la prioridad no supera su limite. */
    protected boolean puedeResolver(Ticket ticket) {
        return ticket.getDificultad() == especialidad
                && ticket.getPrioridad().getNivel() <= prioridadMaxima.getNivel();
    }

    protected void resolver(Ticket ticket) {
        ticket.marcarResuelto(nombre);
        System.out.printf("   %s resolvio el ticket \"%s\".%n", nombre, ticket.getDescripcion());
    }

    @Override
    public String toString() {
        return String.format("%s (especialidad=%s, prioridad maxima=%s)", nombre, especialidad, prioridadMaxima);
    }
}
