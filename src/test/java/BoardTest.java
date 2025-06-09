import org.junit.jupiter.api.Test;
import src.Board;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    /**
     * Test class for the `isCellEmpty` and `isFull` methods in the `src.Board` class.
     *
     * The `isCellEmpty` method checks if a specific cell in the board is empty (contains a space character).
     * The `isFull` method checks if a specific cell in the board is occupied (does not contain a space character).
     */

    @Test
    void testCellIsEmptyWhenBoardIsInitialized() {
        Board board = new Board();
        assertTrue(board.isCellEmpty(0, 0), "Expected cell (0, 0) to be empty after board initialization.");
        assertTrue(board.isCellEmpty(1, 1), "Expected cell (1, 1) to be empty after board initialization.");
        assertTrue(board.isCellEmpty(2, 2), "Expected cell (2, 2) to be empty after board initialization.");
    }

    @Test
    void testCellIsNotEmptyAfterPlacingSign() {
        Board board = new Board();
        board.place(0, 0, 'X');
        assertFalse(board.isCellEmpty(0, 0), "Expected cell (0, 0) to be not empty after placing a sign.");
    }

    @Test
    void testCellIsEmptyInOtherCases() {
        Board board = new Board();
        board.place(0, 0, 'O');
        assertTrue(board.isCellEmpty(2, 2), "Expected cell (2, 2) to remain empty after placing a sign in (0, 0).");
    }
    @Test
    void testIsFullWhenBoardIsNotFilled() {
        Board board = new Board();
        assertFalse(board.isFull(0, 0), "Expected cell (0, 0) to not be full on an empty board.");
        assertFalse(board.isFull(1, 1), "Expected cell (1, 1) to not be full on an empty board.");
        assertFalse(board.isFull(2, 2), "Expected cell (2, 2) to not be full on an empty board.");
    }

    @Test
    void testIsFullAfterPlacingSign() {
        Board board = new Board();
        board.place(0, 0, 'X');
        assertTrue(board.isFull(0, 0), "Expected cell (0, 0) to be full after placing a sign.");
    }

    @Test
    void testIsFullForPartiallyFilledBoard() {
        Board board = new Board();
        board.place(0, 0, 'X');
        board.place(1, 1, 'O');
        assertTrue(board.isFull(0, 0), "Expected cell (0, 0) to be full after placing a sign.");
        assertTrue(board.isFull(1, 1), "Expected cell (1, 1) to be full after placing a sign.");
        assertFalse(board.isFull(2, 2), "Expected cell (2, 2) to not be full when remaining empty.");
    }

    @Test
    void testPlaceSignInEmptyCell() {
        Board board = new Board();
        board.place(0, 0, 'X');
        assertEquals('X', board.isFull(0, 0) ? 'X' : ' ', "Expected 'X' to be placed in cell (0, 0).");
    }

    @Test
    void testPlaceOverwriteSign() {
        Board board = new Board();
        board.place(0, 0, 'X');
        board.place(0, 0, 'O');
        assertEquals('O', board.isFull(0, 0) ? 'O' : ' ', "Expected 'O' to overwrite 'X' in cell (0, 0).");
    }

    @Test
    void testPlaceDoesNotAffectOtherCells() {
        Board board = new Board();
        board.place(0, 0, 'X');
        assertEquals(' ', board.isFull(1, 1) ? 'O' : ' ', "Expected cell (1, 1) to remain unchanged.");
        assertEquals(' ', board.isFull(2, 2) ? 'X' : ' ', "Expected cell (2, 2) to remain unchanged.");
    }


    @Test
    void testPrintBoardInitialState() {
        Board board = new Board();
        String expectedOutput = String.join(System.lineSeparator(),
                "|  |  |  |",
                "|  |  |  |",
                "|  |  |  |"
        );

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        board.printBoard();
        System.setOut(System.out);

        assertEquals(expectedOutput, outContent.toString().trim(), "Expected empty board layout.");
    }

    @Test
    void testPrintBoardWithMoves() {
        Board board = new Board();
        board.place(0, 1, 'X');
        board.place(1, 1, 'O');
        board.place(2, 0, 'X');

        String expectedOutput = String.join(System.lineSeparator(),
                "|  |X |  |",
                "|  |O |  |",
                "|X |  |  |"
        );

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        board.printBoard();
        System.setOut(System.out);

        assertEquals(expectedOutput, outContent.toString().trim(), "Expected correct board with moves.");
    }

    @Test
    void testGetCellsReturnsCorrectInitialState () {
        Board board = new Board();
        char[][] cells = board.getCells();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(' ', cells[i][j], "Expected cell (" + i + ", " + j + ") to be empty.");
            }
        }
    }

    @Test
    void testGetCellsReturnsUpdatedState () {
        Board board = new Board();
        board.place(0, 0, 'X');
        board.place(1, 1, 'O');
        char[][] cells = board.getCells();
        assertEquals('X', cells[0][0], "Expected cell (0, 0) to contain 'X'.");
        assertEquals('O', cells[1][1], "Expected cell (1, 1) to contain 'O'.");
        assertEquals(' ', cells[2][2], "Expected cell (2, 2) to remain empty.");
    }

    @Test
    void testGetCellsReturnsCopy () {
        Board board = new Board();
        char[][] cells = board.getCells();
        cells[0][0] = 'X';  // Modify the returned array
        assertTrue(board.isCellEmpty(0, 0), "Expected internal board state to remain unchanged.");
    }

}