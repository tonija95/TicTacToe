import org.junit.jupiter.api.Test;
import src.Board;

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
}