package edu.eci.dosw.reto6;

/**
 * Representa un ticket de soporte tecnico.
 */
public class Ticket {
    private final String descripcion;
    private final Dificultad dificultad;
    private final Prioridad prioridad;
    private boolean resuelto;
    private String tecnicoResolutor;

    public Ticket(String descripcion, Dificultad dificultad, Prioridad prioridad) {
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.prioridad = prioridad;
        this.resuelto = false;
        this.tecnicoResolutor = null;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public String getTecnicoResolutor() {
        return tecnicoResolutor;
    }

    /** Marca el ticket como resuelto por un tecnico determinado. */
    public void marcarResuelto(String nombreTecnico) {
        this.resuelto = true;
        this.tecnicoResolutor = nombreTecnico;
    }

    /**
     * Marca el ticket como pendiente de escalar (nadie en la cadena pudo
     * resolverlo).
     */
    public void marcarPendienteEscalacion() {
        this.resuelto = false;
        this.tecnicoResolutor = null;
    }

    @Override
    public String toString() {
        String estado = resuelto
                ? "RESUELTO por " + tecnicoResolutor
                : "PENDIENTE DE ESCALACION (nadie en la cadena pudo resolverlo)";
        return String.format("Ticket[\"%s\" | Dificultad=%s | Prioridad=%s] -> %s",
                descripcion, dificultad, prioridad, estado);
    }
}
