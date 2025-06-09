import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TicTacToeTest {

    /**
     * Test to ensure that only empty cells are filled during the game.
     */
    @Test
    void testOnlyEmptyCellsAreFilled() {
        // Mock input: Two valid moves, where the second attempts to override the first
        String mockInput = "0 0\n0 0\n0 1\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(mockInput.getBytes());
        System.setIn(inputStream);

        // Capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize the game and start
        TicTacToe game = new TicTacToe();
        game.start();

        // Reset system input and output
        System.setIn(System.in);
        System.setOut(System.out);

        // Check output for appropriate warning and valid move placement
        String output = outputStream.toString();
        assertTrue(output.contains("This cell is occupied! Try again..."), "Warning for occupied cell is missing.");
        assertTrue(output.contains("Current player: Player 1 X"), "First move not registered correctly.");
        assertTrue(output.contains("Current player: Player 2 O"), "Second player's move not registered correctly.");
    }
}