package src;

public class Board {
    private char[][] cells = new char[3][3];

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = ' ';
            }
        }
    }

    public boolean isCellEmpty(int row, int col) {
        return cells[row][col] == ' ';
    }

    public boolean isFull(int row, int col) {
        return cells[row][col] != ' ';
    }

    public void place(int row, int col, char sign) {
        cells[row][col] = sign;
    }


    public void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append("|");
            for (int j = 0; j < 3; j++) {
                sb.append(cells[i][j]).append(" |");
            }
            if (i < 2) {
                sb.append(System.lineSeparator());
            }
        }
        System.out.println(sb);
    }
}

