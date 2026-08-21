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
        // Redirect standard output to capture what the program prints
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    void tearDown() {
        // Restore console to its original state after each test
        System.setIn(standardIn);
        System.setOut(standardOut);
    }

    /**
     * Helper method to simulate user typing in the console.
     */
    private void simulateUserInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    /**
     * Helper method to get everything the program printed.
     */
    private String getConsoleOutput() {
        return testOut.toString();
    }

    // -----------------------------------------------------------------
    // Input validation tests
    // -----------------------------------------------------------------

    @Test
    @DisplayName("If the user types 'exit' immediately, the program goes to the final state and ends")
    void testExitImmediately() {
        // Input: "exit" in the first question
        simulateUserInput("exit\n");
        
        try {
            Reto7MagicControl.run();
        } catch (Exception e) {
        }

        String console = getConsoleOutput();
        assertTrue(console.contains("===== FINAL DEVICE STATE ====="),
                "It should print the final state before ending.");
    }

    @Test
    @DisplayName("The system must reject an empty user name")
    void testEmptyUser() {
        simulateUserInput("\nexit\n");
        
        try {
            Reto7MagicControl.run();
        } catch (Exception e) {}

        String console = getConsoleOutput();
        assertTrue(console.contains("The user cannot be empty."),
                "It should warn the user to enter a valid name.");
    }

    @Test
    @DisplayName("If an invalid device number is entered, it shows an error and asks again")
    void testInvalidDeviceOption() {
        simulateUserInput("Juan\n9\nexit\nno\n");
        
        try {
            Reto7MagicControl.run();
        } catch (Exception e) {}

        String console = getConsoleOutput();
        assertTrue(console.contains("Invalid option."),
                "It should show 'Invalid option.' when entering a device that does not exist.");
    }

    @Test
    @DisplayName("Validate that askInteger catches the error if letters are entered instead of numbers")
    void testLettersInputInsteadOfNumber() {
        // Input: 
        // - "Maria" (User)
        // - "3" (Music)
        // - "abc" (Invalid volume) -> "50" (Valid volume)
        // - "exit" (Exit actions)
        // - "no" (No undo)
        simulateUserInput("Maria\n3\nabc\n50\nexit\nno\n");
        
        try {
            Reto7MagicControl.run();
        } catch (Exception e) {}

        String console = getConsoleOutput();
        assertTrue(console.contains("Enter a valid number."),
                "It should catch the NumberFormatException and ask for the number again.");
    }

    // -----------------------------------------------------------------
    // Full Flow Tests (Actions and Undo)
    // -----------------------------------------------------------------

    @Test
    @DisplayName("The system allows executing an action, undoing it, and shows the summary correctly")
    void testFullFlowExecuteAndUndo() {
        // Input:
        // - "Pedro" (User)
        // - "1" (Device: Light)
        // - "1" (Action: Turn on)
        // - "exit" (Finish actions)
        // - "1" (Undo action 1)
        // - "no" (Finish undo)
        String input = "Pedro\n1\n1\nexit\n1\nno\n";
        simulateUserInput(input);

        try {
            Reto7MagicControl.run();
        } catch (Exception e) {}

        String console = getConsoleOutput();
        
        assertTrue(console.contains("===== MAGIC REMOTE CONTROL ====="), "It should start the magic control.");
        assertTrue(console.contains("-> Executed:"), "It should confirm that the command was executed.");
        
        assertTrue(console.contains("===== UNDO ACTIONS ====="), "It should enter the undo menu.");
        assertTrue(console.contains("-> Action 1 undone."), "It should confirm that the action was undone.");
        
        assertTrue(console.contains("===== FINAL DEVICE STATE ====="), "It should print the final state.");
    }
}
