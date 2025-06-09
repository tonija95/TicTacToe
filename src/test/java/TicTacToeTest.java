import org.junit.jupiter.api.Test;
import src.TicTacToe;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TicTacToeTest {

    /**
     * Test to ensure that only empty cells are filled during the game.
     */
    @Test
    void testPreventsOverwritingOccupiedCells() {
        // Simulated user input: first move is valid, second targets the same cell (invalid), third is valid again
        String mockInput = "0 0\n0 0\n0 1\n1 1\n2 2\n1 0\n2 0\n2 1\n1 2\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(mockInput.getBytes());
        System.setIn(inputStream);

        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Start the game
        TicTacToe game = new TicTacToe();
        game.start();

        // Reset standard input and output
        System.setIn(System.in);
        System.setOut(System.out);

        // Check if the warning message was printed
        String output = outputStream.toString();
        assertTrue(output.contains("This cell is occupied! Try again..."),
                "Expected warning for attempting to overwrite an occupied cell was not displayed.");
    }
}
