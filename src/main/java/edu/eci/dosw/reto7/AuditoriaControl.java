package edu.eci.dosw.reto7;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Generates the final summary and audit evidence from the
 * history of actions of the ControlRemoto, using the Streams API.
 */
public class AuditoriaControl {

    private final List<RegistroAccion> history;

    public AuditoriaControl(List<RegistroAccion> history) {
        this.history = history;
    }

    /** Total amount of executed actions (includes those that were later undone). */
    public long totalExecuted() {
        return history.size();
    }

    /** Amount of actions that were undone. */
    public long totalUndone() {
        return history.stream()
                .filter(RegistroAccion::isUndone)
                .count();
    }

    /** Amount of executed actions, grouped by responsible user. */
    public Map<String, Long> actionsByUser() {
        return history.stream()
                .collect(Collectors.groupingBy(RegistroAccion::getUser, Collectors.counting()));
    }

    /**
     * Last user who modified each device through an active
     * action (not undone). Directly answers "who changed each
     * device": when traversing the history in order and overwriting by
     * device, the last value left is always the most recent.
     */
    public Map<String, String> lastResponsibleByDevice() {
        Map<String, String> result = new LinkedHashMap<>();
        history.stream()
                .filter(r -> !r.isUndone())
                .forEach(r -> result.put(r.getCommand().getDevice().getName(), r.getUser()));
        return result;
    }

    /** Prints the readable final summary to console. */
    public void printSummary() {
        System.out.println("\n===== FINAL SUMMARY =====");

        System.out.println("\nComplete action history:");
        history.forEach(r -> System.out.println("  " + r));

        System.out.println("\nExecuted actions: " + totalExecuted());
        System.out.println("Undone actions: " + totalUndone());

        System.out.println("\nActions by user:");
        actionsByUser().forEach((user, amount) ->
                System.out.printf("  - %-10s: %d%n", user, amount));

        System.out.println("\nAudit - last responsible by device:");
        Map<String, String> byDevice = lastResponsibleByDevice();
        if (byDevice.isEmpty()) {
            System.out.println("  (no device has active changes)");
        } else {
            byDevice.forEach((device, user) ->
                    System.out.printf("  - %-14s: %s%n", device, user));
        }
        System.out.println("==========================");
    }
}
