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
     * Helper method to simulate user writing in the console.
     */
    private void simulateUserInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    /**
     * Helper method to get everything the program printed to the console.
     */
    private String getConsoleOutput() {
        return testOut.toString();
    }


    @Test
    @DisplayName("The technician chain must be configured in the correct escalation order")
    void testBuildChainCorrectOrder() throws Exception {
        Method buildMethod = Reto6Tecnicos.class.getDeclaredMethod("buildChain");
        buildMethod.setAccessible(true);
        Tecnico current = (Tecnico) buildMethod.invoke(null);

        assertNotNull(current, "The first technician (Ana) must not be null");
        String[] expectedOrder = {"Ana", "Luis", "alfonso", "Carla", "Jorge", "Sofia"};

        for (String name : expectedOrder) {
            assertNotNull(current, "The chain broke prematurely, expected: " + name);
        
            String technicianStr = current.toString();
            assertTrue(technicianStr.contains(name) || technicianStr.contains(name.toLowerCase()), 
                "The current technician should be " + name);
            
            current = current.getNext();
        }
        
        assertNull(current, "The chain must end (be null) after the last technician (Sofia)");
    }

    // -----------------------------------------------------------------
    // Console Interaction (User Simulation)
    // -----------------------------------------------------------------

    @Test
    @DisplayName("If the user types 'exit' without entering tickets, the program ends correctly")
    void testExitImmediately() {
        simulateUserInput("exit\n");
        Reto6Tecnicos.run();

        String console = getConsoleOutput();
        assertTrue(console.contains("No tickets were entered. End of program."),
                "Should indicate that no tickets were entered and interrupt the flow.");
    }

    @Test
    @DisplayName("The system must reject empty descriptions and iterate again")
    void testTicketEmptyDescription() {
        simulateUserInput("\nexit\n"); 
        Reto6Tecnicos.run();

        String console = getConsoleOutput();
        assertTrue(console.contains("The description cannot be empty."),
                "Should warn the user that the description cannot be left blank.");
    }

    @Test
    @DisplayName("If the user enters an invalid difficulty or priority, the system requires a valid number")
    void testInvalidOptionsInDifficultyAndPriority() {
        // Inputs:
        // - "Mi PC falla"
        // - Difficulty: "9" (Invalid) -> Then "1" (Valid)
        // - Priority: "A" (Invalid) -> Then "2" (Valid)
        // - "exit"
        String input = "Mi PC falla\n9\n1\nA\n2\nexit\n";
        simulateUserInput(input);

        try {
            Reto6Tecnicos.run();
        } catch (Exception e) {
        }

        String console = getConsoleOutput();
        assertTrue(console.contains("Invalid option"),
                "Should warn 'Invalid option' when entering values outside of 1, 2, or 3.");
    }

    // -----------------------------------------------------------------
    // Complete Flow
    // -----------------------------------------------------------------

    @Test
    @DisplayName("The system allows registering a valid ticket, processing it, and showing results")
    void testCompleteFlowWithOneTicket() {
        String input = "Pantalla azul\n2\n3\nexit\n";
        simulateUserInput(input);

        try {
            Reto6Tecnicos.run();
        } catch (Exception e) {
        }

        String console = getConsoleOutput();
        assertTrue(console.contains("===== PROCESSING TICKETS ====="), 
                "Should reach the processing phase.");
        assertTrue(console.contains("Pantalla azul"), 
                "Should display the ticket name when processing it.");
        assertTrue(console.contains("===== FINAL RESULT ====="), 
                "Should print the final result of processing.");
    }
}
