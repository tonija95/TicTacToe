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

    public char[][] getCells() {
        char[][] copy = new char[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            System.arraycopy(cells[i], 0, copy[i], 0, cells[i].length);
        }
        return copy;
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

    public void clear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j]=' ';
            }
        }
    }
}

