package src;

import java.util.Scanner;

public class TicTacToe {
    private Player player1=new Player('X');
    private Player player2=new Player('O');
    private Player currentPlayer=player1;
    private Board board=new Board();

    public TicTacToe() {
    }

    public void start() {
        Scanner scn = new Scanner(System.in);

        System.out.println( "Welcome to src.TicTacToe!");
        System.out.println("src.Player 1: "+player1.getMarker());
        System.out.println("src.Player 2: "+player2.getMarker());

        for (int i = 0; i < 9; i++) {
            System.out.printf("Current player: %s%n", currentPlayer.getMarker());

            board.printBoard();

            boolean isEmpty=false;

            while(!isEmpty) {
                System.out.println("Enter the coordinates: ");
                int row = scn.nextInt();
                int col = scn.nextInt();
                if (board.isCellEmpty(row, col)) {
                    board.place(row, col, currentPlayer.getMarker());
                    switchCurrentPlayer();
                } else {
                    System.out.println("This cell is occupied! Try again...");
                }
                isEmpty = board.isFull(row, col);
            }

            if (hasWinner()) {
                switchCurrentPlayer();
                System.out.printf("Congratulations! Player %s won!%n", currentPlayer.getMarker());
                break;
            }
        }

        System.out.println("Thank you for playing!");


        scn.close();
    }

    private void switchCurrentPlayer() {
        if(currentPlayer==player1) {
            currentPlayer=player2;
        } else if (currentPlayer==player2) {
            currentPlayer=player1;
        }
    }

    public boolean hasWinner() {
        char[][] cells = board.getCells();

        // Zeilen prüfen
        for (int i = 0; i < 3; i++) {
            if (cells[i][0] != ' ' &&
                    cells[i][0] == cells[i][1] &&
                    cells[i][1] == cells[i][2]) {
                return true;
            }
        }

        // Spalten prüfen
        for (int i = 0; i < 3; i++) {
            if (cells[0][i] != ' ' &&
                    cells[0][i] == cells[1][i] &&
                    cells[1][i] == cells[2][i]) {
                return true;
            }
        }

        // Diagonale prüfen
        if (cells[0][0] != ' ' && cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]) return true;
        if (cells[0][2] != ' ' && cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]) return true;

        return false;
    }

}
