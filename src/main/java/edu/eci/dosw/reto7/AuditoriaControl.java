package edu.eci.dosw.reto7;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Genera el resumen final y la evidencia de auditoria a partir del
 * historial de acciones del ControlRemoto, usando la API de Streams.
 */
public class AuditoriaControl {

    private final List<RegistroAccion> historial;

    public AuditoriaControl(List<RegistroAccion> historial) {
        this.historial = historial;
    }

    /** Cantidad total de acciones ejecutadas (incluye las que luego fueron deshechas). */
    public long totalEjecutadas() {
        return historial.size();
    }

    /** Cantidad de acciones que fueron deshechas. */
    public long totalDeshechas() {
        return historial.stream()
                .filter(RegistroAccion::isDeshecha)
                .count();
    }

    /** Cantidad de acciones ejecutadas, agrupadas por usuario responsable. */
    public Map<String, Long> accionesPorUsuario() {
        return historial.stream()
                .collect(Collectors.groupingBy(RegistroAccion::getUsuario, Collectors.counting()));
    }

    /**
     * Ultimo usuario que modifico cada dispositivo mediante una accion
     * vigente (no deshecha). Responde directamente "quien cambio cada
     * dispositivo": al recorrer el historial en orden y sobrescribir por
     * dispositivo, el ultimo valor que queda es siempre el mas reciente.
     */
    public Map<String, String> ultimoResponsablePorDispositivo() {
        Map<String, String> resultado = new LinkedHashMap<>();
        historial.stream()
                .filter(r -> !r.isDeshecha())
                .forEach(r -> resultado.put(r.getComando().getDispositivo().getNombre(), r.getUsuario()));
        return resultado;
    }

    /** Imprime el resumen final legible en consola. */
    public void imprimirResumen() {
        System.out.println("\n===== RESUMEN FINAL =====");

        System.out.println("\nHistorial completo de acciones:");
        historial.forEach(r -> System.out.println("  " + r));

        System.out.println("\nAcciones ejecutadas: " + totalEjecutadas());
        System.out.println("Acciones deshechas: " + totalDeshechas());

        System.out.println("\nAcciones por usuario:");
        accionesPorUsuario().forEach((usuario, cantidad) ->
                System.out.printf("  - %-10s: %d%n", usuario, cantidad));

        System.out.println("\nAuditoria - ultimo responsable por dispositivo:");
        Map<String, String> porDispositivo = ultimoResponsablePorDispositivo();
        if (porDispositivo.isEmpty()) {
            System.out.println("  (ningun dispositivo tiene cambios vigentes)");
        } else {
            porDispositivo.forEach((dispositivo, usuario) ->
                    System.out.printf("  - %-14s: %s%n", dispositivo, usuario));
        }
        System.out.println("==========================");
    }
}
