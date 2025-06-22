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
        String mockInput = ""
                + "0\n0\n"  // gültiger Zug
                + "0\n0\n"  // ungültiger Zug (gleiche Zelle)
                + "0\n1\n"  // gültig
                + "1\n1\n"  // usw.
                + "2\n2\n"
                + "1\n0\n"
                + "2\n0\n"
                + "2\n1\n"
                + "1\n2\n"
                + "n\n";     // Spiel beenden nach der Partie
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

    /**
     * Test the start method for choosing to replay after a draw.
     */
    @Test
    void testStartGamePlayAgain() {
        String mockInput = ""
                // Erste Runde (Draw)
                + "0\n0\n0\n1\n0\n2\n1\n1\n1\n0\n1\n2\n2\n1\n2\n0\n2\n2\n"
                // Nach der ersten Runde: "Play again?" → y
                + "y\n"
                // Zweite Runde: Spieler X gewinnt
                + "0\n0\n1\n0\n0\n1\n1\n1\n0\n2\n"
                // Nach der zweiten Runde: "Play again?" → n
                + "n\n";
        System.setIn(new ByteArrayInputStream(mockInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TicTacToe game = new TicTacToe();
        game.start();

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("It's a draw!"), "Expected draw message not found.");
        assertTrue(output.contains("Do you want to play again?"), "Replay prompt not found.");
        assertTrue(output.contains("Congratulations! Player X won!"), "Expected win message in replay not found.");
        assertTrue(output.contains("Thank you for playing!"), "Thank you message not found.");
    }

    /**
     * Test the start method for a game resulting in a draw scenario.
     */
    @Test
    void testStartGameDraw() {
        String mockInput = "0\n0\n0\n1\n0\n2\n1\n1\n1\n0\n1\n2\n2\n1\n2\n0\n2\n2\nn\n";
        System.setIn(new ByteArrayInputStream(mockInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TicTacToe game = new TicTacToe();
        game.start();

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("It's a draw!"), "Expected draw message not found.");
        assertTrue(output.contains("Thank you for playing!"), "Thank you message not found.");
    }

    /**
     * Test the start method for a game resulting in a win scenario.
     */
    @Test
    void testStartGameWin() {
        String mockInput = "0\n0\n1\n0\n0\n1\n1\n1\n0\n2\nn\n";
        System.setIn(new ByteArrayInputStream(mockInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TicTacToe game = new TicTacToe();
        game.start();

        System.setIn(System.in);
        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("Congratulations! Player X won!"), "Expected win message not found for Player X.");
        assertTrue(output.contains("Thank you for playing!"), "Thank you message not found.");
    }


    /**
     * Test to ensure the switchCurrentPlayer method alternates between players correctly.
     */
    @Test
    void testSwitchCurrentPlayer() {
        // Create a new TicTacToe instance
        TicTacToe game = new TicTacToe();

        // Verify initial player is player1
        assertTrue(getCurrentPlayerMarker(game) == 'X', "Player 1 should be the starting player.");

        // Switch to player2
        invokeSwitchCurrentPlayer(game);
        assertTrue(getCurrentPlayerMarker(game) == 'O', "Current player should switch to Player 2.");

        // Switch back to player1
        invokeSwitchCurrentPlayer(game);
        assertTrue(getCurrentPlayerMarker(game) == 'X', "Current player should switch back to Player 1.");
    }

    /**
     * Utility method to get the current player's marker for testing.
     *
     * @param game The TicTacToe game instance.
     * @return The marker of the current player.
     */
    private char getCurrentPlayerMarker(TicTacToe game) {
        try {
            var currentPlayerField = TicTacToe.class.getDeclaredField("currentPlayer");
            currentPlayerField.setAccessible(true);
            var currentPlayer = currentPlayerField.get(game);

            var markerField = currentPlayer.getClass().getDeclaredField("marker");
            markerField.setAccessible(true);
            return (char) markerField.get(currentPlayer);

        } catch (Exception e) {
            throw new RuntimeException("Failed to access currentPlayer's marker.", e);
        }
    }

    /**
     * Utility method to invoke the private switchCurrentPlayer method for testing.
     *
     * @param game The TicTacToe game instance.
     */
    private void invokeSwitchCurrentPlayer(TicTacToe game) {
        try {
            var switchMethod = TicTacToe.class.getDeclaredMethod("switchCurrentPlayer");
            switchMethod.setAccessible(true);
            switchMethod.invoke(game);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke switchCurrentPlayer method.", e);
        }
    }

}
