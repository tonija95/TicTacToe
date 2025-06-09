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
            System.out.printf("Current player: %s %s%n",currentPlayer,currentPlayer.getMarker()+"");

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
        }

        System.out.println("Thank you for playing!");


        scn.close();
    }

    public void switchCurrentPlayer() {
        if(currentPlayer==player1) {
            currentPlayer=player2;
        } else if (currentPlayer==player2) {
            currentPlayer=player1;
        }
    }
}
