package edu.eci.dosw.reto7;

import java.util.Scanner;

/**
 * Entry point. Creates the home devices, lets the
 * user execute any number of actions on them through
 * the ControlRemoto (Command pattern), allows undoing
 * specific actions and finally shows the audit summary.
 */
public class Reto7MagicControl {

    public static void run() {
        Scanner sc = new Scanner(System.in);

        Luz luzSala = new Luz("Luz sala");
        Puerta puertaPrincipal = new Puerta("Puerta principal");
        SistemaMusica musica = new SistemaMusica("Equipo de musica");
        Persiana persianaVentana = new Persiana("Persiana ventana");

        ControlRemoto control = new ControlRemoto();

        registerActions(sc, control, luzSala, puertaPrincipal, musica, persianaVentana);
        allowUndo(sc, control);

        printDeviceState(luzSala, puertaPrincipal, musica, persianaVentana);

        new AuditoriaControl(control.getHistory()).printSummary();

        sc.close();
    }

    /** 
     * Automated method to print the state of any number of devices.
     * Uses varargs (Object...) to receive objects regardless of how many there are.
     */
    private static void printDeviceState(Object... devices) {
        System.out.println("\n===== FINAL DEVICE STATE =====");
        for (Object device : devices) {
            System.out.println("  " + device);
        }
    }

    /** Main loop: allows executing any number of actions. */
    private static void registerActions(Scanner sc, ControlRemoto control,
                                           Luz light, Puerta door,
                                           SistemaMusica music, Persiana blind) {
        System.out.println("===== MAGIC REMOTE CONTROL =====");
        int number = 1;
        while (true) {
            System.out.println("\n--- Action " + number + " ---");
            System.out.print("User (or 'exit' to finish): ");
            String user = sc.nextLine().trim();

            if (user.equalsIgnoreCase("exit") || user.equalsIgnoreCase("salir")) {
                break;
            }
            if (user.isEmpty()) {
                System.out.println("The user cannot be empty.");
                continue;
            }

            Comando command = askCommand(sc, light, door, music, blind);
            if (command == null) {
                continue; // invalid option, ask again in the next loop
            }

            control.executeAction(command, user);
            System.out.println("   -> Executed: " + command.getDescription());
            number++;
        }
    }

    /** Asks device and action, and builds the corresponding concrete Command. */
    private static Comando askCommand(Scanner sc, Luz light, Puerta door,
                                         SistemaMusica music, Persiana blind) {
        System.out.println("Device: [1] Light  [2] Door  [3] Music  [4] Blind");
        System.out.print("Option: ");
        String device = sc.nextLine().trim();

        switch (device) {
            case "1":
                System.out.print("Action: [1] Turn on  [2] Turn off: ");
                String actionLight = sc.nextLine().trim();
                if (actionLight.equals("1")) return new EncenderLuzComando(light);
                if (actionLight.equals("2")) return new ApagarLuzComando(light);
                System.out.println("Invalid option.");
                return null;

            case "2":
                System.out.print("Action: [1] Open  [2] Close: ");
                String actionDoor = sc.nextLine().trim();
                if (actionDoor.equals("1")) return new AbrirPuertaComando(door);
                if (actionDoor.equals("2")) return new CerrarPuertaComando(door);
                System.out.println("Invalid option.");
                return null;

            case "3":
                int volume = askInteger(sc, "New volume (0-100): ");
                return new SetVolumenComando(music, volume);

            case "4":
                int position = askInteger(sc, "New blind position (0-100): ");
                return new AjustarPersianaComando(blind, position);

            default:
                System.out.println("Invalid option.");
                return null;
        }
    }

    /** Allows undoing any number of actions, identified by their number in the history. */
    private static void allowUndo(Scanner sc, ControlRemoto control) {
        if (control.getHistory().isEmpty()) {
            return;
        }

        System.out.println("\n===== UNDO ACTIONS =====");
        System.out.println("Current history:");
        int i = 1;
        for (RegistroAccion record : control.getHistory()) {
            System.out.println("  " + i + ". " + record.getCommand().getDescription()
                    + " (user: " + record.getUser() + ")");
            i++;
        }

        while (true) {
            System.out.print("\nAction number to undo (or 'no' to continue): ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("no")) {
                break;
            }
            try {
                int number = Integer.parseInt(input);
                boolean success = control.undoAction(number);
                System.out.println(success
                        ? "   -> Action " + number + " undone."
                        : "   -> Could not undo (invalid number or already undone).");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number or 'no'.");
            }
        }
    }

    private static int askInteger(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }
}