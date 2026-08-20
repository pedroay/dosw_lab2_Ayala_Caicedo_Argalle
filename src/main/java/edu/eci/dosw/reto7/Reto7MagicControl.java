package edu.eci.dosw.reto7;

import java.util.Scanner;

/**
 * Punto de entrada. Crea los dispositivos del hogar, deja que el
 * usuario ejecute cualquier cantidad de acciones sobre ellos a
 * traves del ControlRemoto (patron Command), permite deshacer
 * acciones puntuales y finalmente muestra el resumen de auditoria.
 */
public class Reto7MagicControl {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Luz luzSala = new Luz("Luz sala");
        Puerta puertaPrincipal = new Puerta("Puerta principal");
        SistemaMusica musica = new SistemaMusica("Equipo de musica");
        Persiana persianaVentana = new Persiana("Persiana ventana");

        ControlRemoto control = new ControlRemoto();

        registrarAcciones(sc, control, luzSala, puertaPrincipal, musica, persianaVentana);
        permitirDeshacer(sc, control);

        System.out.println("\n===== ESTADO FINAL DE LOS DISPOSITIVOS =====");
        System.out.println("  " + luzSala);
        System.out.println("  " + puertaPrincipal);
        System.out.println("  " + musica);
        System.out.println("  " + persianaVentana);

        new AuditoriaControl(control.getHistorial()).imprimirResumen();

        sc.close();
    }

    /** Bucle principal: permite ejecutar cualquier cantidad de acciones. */
    private static void registrarAcciones(Scanner sc, ControlRemoto control,
                                           Luz luz, Puerta puerta,
                                           SistemaMusica musica, Persiana persiana) {
        System.out.println("===== CONTROL REMOTO MAGICO =====");
        int numero = 1;
        while (true) {
            System.out.println("\n--- Accion " + numero + " ---");
            System.out.print("Usuario (o 'salir' para terminar): ");
            String usuario = sc.nextLine().trim();

            if (usuario.equalsIgnoreCase("salir")) {
                break;
            }
            if (usuario.isEmpty()) {
                System.out.println("El usuario no puede estar vacio.");
                continue;
            }

            Comando comando = pedirComando(sc, luz, puerta, musica, persiana);
            if (comando == null) {
                continue; // opcion invalida, se vuelve a pedir en la siguiente vuelta
            }

            control.ejecutarAccion(comando, usuario);
            System.out.println("   -> Ejecutado: " + comando.getDescripcion());
            numero++;
        }
    }

    /** Pregunta dispositivo y accion, y arma el Comando concreto correspondiente. */
    private static Comando pedirComando(Scanner sc, Luz luz, Puerta puerta,
                                         SistemaMusica musica, Persiana persiana) {
        System.out.println("Dispositivo: [1] Luz  [2] Puerta  [3] Musica  [4] Persiana");
        System.out.print("Opcion: ");
        String dispositivo = sc.nextLine().trim();

        switch (dispositivo) {
            case "1":
                System.out.print("Accion: [1] Encender  [2] Apagar: ");
                String accionLuz = sc.nextLine().trim();
                if (accionLuz.equals("1")) return new EncenderLuzComando(luz);
                if (accionLuz.equals("2")) return new ApagarLuzComando(luz);
                System.out.println("Opcion invalida.");
                return null;

            case "2":
                System.out.print("Accion: [1] Abrir  [2] Cerrar: ");
                String accionPuerta = sc.nextLine().trim();
                if (accionPuerta.equals("1")) return new AbrirPuertaComando(puerta);
                if (accionPuerta.equals("2")) return new CerrarPuertaComando(puerta);
                System.out.println("Opcion invalida.");
                return null;

            case "3":
                int volumen = pedirEntero(sc, "Nuevo volumen (0-100): ");
                return new SetVolumenComando(musica, volumen);

            case "4":
                int posicion = pedirEntero(sc, "Nueva posicion de la persiana (0-100): ");
                return new AjustarPersianaComando(persiana, posicion);

            default:
                System.out.println("Opcion invalida.");
                return null;
        }
    }

    /** Permite deshacer cualquier cantidad de acciones, identificadas por su numero en el historial. */
    private static void permitirDeshacer(Scanner sc, ControlRemoto control) {
        if (control.getHistorial().isEmpty()) {
            return;
        }

        System.out.println("\n===== DESHACER ACCIONES =====");
        System.out.println("Historial actual:");
        int i = 1;
        for (RegistroAccion registro : control.getHistorial()) {
            System.out.println("  " + i + ". " + registro.getComando().getDescripcion()
                    + " (usuario: " + registro.getUsuario() + ")");
            i++;
        }

        while (true) {
            System.out.print("\nNumero de accion a deshacer (o 'no' para continuar): ");
            String entrada = sc.nextLine().trim();
            if (entrada.equalsIgnoreCase("no")) {
                break;
            }
            try {
                int numero = Integer.parseInt(entrada);
                boolean exito = control.deshacerAccion(numero);
                System.out.println(exito
                        ? "   -> Accion " + numero + " deshecha."
                        : "   -> No se pudo deshacer (numero invalido o ya estaba deshecha).");
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Ingrese un numero o 'no'.");
            }
        }
    }

    private static int pedirEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }
}
