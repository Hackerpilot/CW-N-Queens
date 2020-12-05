package CWnQueens;

import java.util.ArrayList;

public class NQueens {

    private final int size;

    NQueens(int size)
    {
        this.size = size;
        solutions = new ArrayList<BoardState>();
    }

    public void solve() {
        BoardState board = new BoardState(size);
        solve(0, board);
    }

    public void solve(int row, BoardState board) {
        if (row >= board.size) {
            solutions.add((BoardState) board.clone());
            return;
        }
        for (int col = 0; col < board.size; col++) {
            if (board.available(row, col)) {
                board.placeQueen(row, col);
                solve(row + 1, board);
                board.removeQueen(row, col);
            }
        }
    }

    public void print() {
        System.out.format("Found %d solutions for a %dx%d board\n", solutions.size(), size, size);
        for (CWnQueens.BoardState board: solutions) {
            board.print();
            System.out.println();
        }
    }

    ArrayList<BoardState> solutions;
}
