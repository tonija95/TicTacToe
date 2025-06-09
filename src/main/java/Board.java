public class Board {
    private char[][] cells=new char[3][3];

    public boolean isCellEmpty(int row, int col) {
        return cells[row][col]==' ';
    }

    public boolean isFull(int row, int col) {
        return cells[row][col]!=' ';
    }

    public void place(int row, int col, char sign) {
        cells[row][col]=sign;
    }

}
