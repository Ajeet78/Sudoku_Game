import java.util.Random;

public class SudokuGame {
    private int[][] board;
    private Random random;

    public SudokuGame() {
        board = new int[9][9];
        random = new Random();
    }

    public void generate() {
        fillBoard(0, 0);
        removeCells();
    }

    private boolean fillBoard(int row, int col) {
        if (col == 9) {
            col = 0;
            row++;
            if (row == 9) {
                return true;
            }
        }

        if (board[row][col] != 0) {
            return fillBoard(row, col + 1);
        }

        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        shuffleArray(numbers);

        for (int number : numbers) {
            if (isValidPlacement(row, col, number)) {
                board[row][col] = number;
                if (fillBoard(row, col + 1)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }

        return false;
    }

    private boolean isValidPlacement(int row, int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == number || board[i][col] == number) {
                return false;
            }
        }

        int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    private void removeCells() {
        int cellsToRemove = 40; 

        while (cellsToRemove > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            if (board[row][col] != 0) {
                board[row][col] = 0;
                cellsToRemove--;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void shuffleArray(int[] array) {
        int index, temp;
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public static void main(String[] args) {
        SudokuGenerator generator = new SudokuGenerator();
        generator.generate();
        generator.printBoard();
    }
}
