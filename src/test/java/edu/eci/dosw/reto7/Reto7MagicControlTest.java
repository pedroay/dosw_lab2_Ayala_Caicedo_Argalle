package edu.eci.dosw.reto7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class Reto7MagicControlTest {

    private final InputStream standardIn = System.in;
    private final PrintStream standardOut = System.out;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    void setUp() {
        // Redirigir la salida estándar para capturar lo que imprime el programa
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    void tearDown() {
        // Restaurar la consola a su estado original después de cada prueba
        System.setIn(standardIn);
        System.setOut(standardOut);
    }

    /**
     * Método auxiliar para simular que el usuario escribe en la consola.
     */
    private void simularEntradaUsuario(String datos) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(datos.getBytes());
        System.setIn(testIn);
    }

    /**
     * Método auxiliar para obtener todo lo que el programa imprimió.
     */
    private String obtenerSalidaConsola() {
        return testOut.toString();
    }

    // -----------------------------------------------------------------
    // Pruebas de validación de entradas
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Si el usuario escribe 'salir' inmediatamente, el programa pasa al estado final y termina")
    void testSalirInmediatamente() {
        // Entrada: "salir" en la primera pregunta
        simularEntradaUsuario("salir\n");
        
        try {
            Reto7MagicControl.run();
        } catch (Exception e) {
        }

        String consola = obtenerSalidaConsola();
        assertTrue(consola.contains("===== ESTADO FINAL DE LOS DISPOSITIVOS ====="),
                "Debe imprimir el estado final antes de terminar.");
    }

    @Test
    @DisplayName("El sistema debe rechazar un nombre de usuario vacío")
    void testUsuarioVacio() {
        simularEntradaUsuario("\nsalir\n");
        
        try {
            Reto7MagicControl.run();
        } catch (Exception e) {}

        String consola = obtenerSalidaConsola();
        assertTrue(consola.contains("El usuario no puede estar vacio."),
                "Debe advertir al usuario que ingrese un nombre válido.");
    }

    @Test
    @DisplayName("Si se ingresa un número de dispositivo inválido, muestra error y vuelve a preguntar")
    void testOpcionDispositivoInvalida() {
        simularEntradaUsuario("Juan\n9\nsalir\nno\n");
        
        try {
            Reto7MagicControl.run();
        } catch (Exception e) {}

        String consola = obtenerSalidaConsola();
        assertTrue(consola.contains("Opcion invalida."),
                "Debe mostrar 'Opcion invalida.' al ingresar un dispositivo que no existe.");
    }

    @Test
    @DisplayName("Validar que pedirEntero capture el error si se ingresan letras en lugar de números")
    void testEntradaLetrasEnVezDeNumero() {
        // Entrada: 
        // - "Maria" (Usuario)
        // - "3" (Música)
        // - "abc" (Volumen inválido) -> "50" (Volumen válido)
        // - "salir" (Salir de acciones)
        // - "no" (No deshacer)
        simularEntradaUsuario("Maria\n3\nabc\n50\nsalir\nno\n");
        
        try {
            Reto7MagicControl.run();
        } catch (Exception e) {}

        String consola = obtenerSalidaConsola();
        assertTrue(consola.contains("Ingrese un numero valido."),
                "Debe atrapar el NumberFormatException y pedir el número nuevamente.");
    }

    // -----------------------------------------------------------------
    // Pruebas de Flujo Completo (Acciones y Deshacer)
    // -----------------------------------------------------------------

    @Test
    @DisplayName("El sistema permite ejecutar una acción, deshacerla, y muestra el resumen correctamente")
    void testFlujoCompletoEjecutarYDeshacer() {
        // Entrada:
        // - "Pedro" (Usuario)
        // - "1" (Dispositivo: Luz)
        // - "1" (Acción: Encender)
        // - "salir" (Terminar acciones)
        // - "1" (Deshacer la acción 1)
        // - "no" (Terminar deshacer)
        String entrada = "Pedro\n1\n1\nsalir\n1\nno\n";
        simularEntradaUsuario(entrada);

        try {
            Reto7MagicControl.run();
        } catch (Exception e) {}

        String consola = obtenerSalidaConsola();
        
        assertTrue(consola.contains("===== CONTROL REMOTO MAGICO ====="), "Debe iniciar el control mágico.");
        assertTrue(consola.contains("-> Ejecutado:"), "Debe confirmar que el comando se ejecutó.");
        
        assertTrue(consola.contains("===== DESHACER ACCIONES ====="), "Debe entrar al menú de deshacer.");
        assertTrue(consola.contains("-> Accion 1 deshecha."), "Debe confirmar que la acción fue deshecha.");
        
        assertTrue(consola.contains("===== ESTADO FINAL DE LOS DISPOSITIVOS ====="), "Debe imprimir el estado final.");
    }
}
