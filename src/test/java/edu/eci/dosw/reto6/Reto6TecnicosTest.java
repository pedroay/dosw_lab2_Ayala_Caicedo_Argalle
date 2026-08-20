package edu.eci.dosw.reto6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class Reto6TecnicosTest {

    private final InputStream standardIn = System.in;
    private final PrintStream standardOut = System.out;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    void tearDown() {
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
     * Método auxiliar para obtener todo lo que el programa imprimió en consola.
     */
    private String obtenerSalidaConsola() {
        return testOut.toString();
    }


    @Test
    @DisplayName("La cadena de técnicos debe estar configurada en el orden correcto de escalamiento")
    void testConstruirCadenaOrdenCorrecto() throws Exception {
        Method metodoConstruir = Reto6Tecnicos.class.getDeclaredMethod("construirCadena");
        metodoConstruir.setAccessible(true);
        Tecnico actual = (Tecnico) metodoConstruir.invoke(null);

        assertNotNull(actual, "El primer técnico (Ana) no debe ser nulo");
        String[] ordenEsperado = {"Ana", "Luis", "alfonso", "Carla", "Jorge", "Sofia"};

        for (String nombre : ordenEsperado) {
            assertNotNull(actual, "La cadena se rompió prematuramente, se esperaba a: " + nombre);
        
            String tecnicoStr = actual.toString();
            assertTrue(tecnicoStr.contains(nombre) || tecnicoStr.contains(nombre.toLowerCase()), 
                "El técnico actual debería ser " + nombre);
            
            actual = actual.getSiguiente();
        }
        
        assertNull(actual, "La cadena debe terminar (ser null) después del último técnico (Sofia)");
    }

    // -----------------------------------------------------------------
    // Interacción por consola (Simulación de usuario)
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Si el usuario escribe 'salir' sin ingresar tickets, el programa termina correctamente")
    void testSalirInmediatamente() {
        simularEntradaUsuario("salir\n");
        Reto6Tecnicos.run();

        String consola = obtenerSalidaConsola();
        assertTrue(consola.contains("No se ingreso ningun ticket. Fin del programa."),
                "Debe indicar que no se ingresaron tickets e interrumpir el flujo.");
    }

    @Test
    @DisplayName("El sistema debe rechazar descripciones vacías e iterar de nuevo")
    void testTicketDescripcionVacia() {
        simularEntradaUsuario("\nsalir\n"); 
        Reto6Tecnicos.run();

        String consola = obtenerSalidaConsola();
        assertTrue(consola.contains("La descripcion no puede estar vacia."),
                "Debe advertir al usuario que no puede dejar la descripción en blanco.");
    }

    @Test
    @DisplayName("Si el usuario ingresa una dificultad o prioridad inválida, el sistema exige un número válido")
    void testOpcionesInvalidasEnDificultadYPrioridad() {
        // Entradas:
        // - "Mi PC falla"
        // - Dificultad: "9" (Inválida) -> Luego "1" (Válida)
        // - Prioridad: "A" (Inválida) -> Luego "2" (Válida)
        // - "salir"
        String entrada = "Mi PC falla\n9\n1\nA\n2\nsalir\n";
        simularEntradaUsuario(entrada);

        try {
            Reto6Tecnicos.run();
        } catch (Exception e) {
        }

        String consola = obtenerSalidaConsola();
        assertTrue(consola.contains("Opcion invalida"),
                "Debe advertir 'Opcion invalida' cuando se meten valores fuera del 1, 2 o 3.");
    }

    // -----------------------------------------------------------------
    // Flujo Completo
    // -----------------------------------------------------------------

    @Test
    @DisplayName("El sistema permite registrar un ticket válido, procesarlo y mostrar resultados")
    void testFlujoCompletoConUnTicket() {
        String entrada = "Pantalla azul\n2\n3\nsalir\n";
        simularEntradaUsuario(entrada);

        try {
            Reto6Tecnicos.run();
        } catch (Exception e) {
        }

        String consola = obtenerSalidaConsola();
        assertTrue(consola.contains("===== PROCESANDO TICKETS ====="), 
                "Debe llegar a la fase de procesamiento.");
        assertTrue(consola.contains("Pantalla azul"), 
                "Debe mostrar el nombre del ticket al procesarlo.");
        assertTrue(consola.contains("===== RESULTADO FINAL ====="), 
                "Debe imprimir el resultado final del procesamiento.");
    }
}
