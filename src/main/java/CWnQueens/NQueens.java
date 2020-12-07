package CWnQueens;

import java.util.ArrayList;

/**
 * Algorithm for solving the modified N-Queens problem.
 */
public class NQueens {

    /**
     * Constructor
     * @param size the width and height of the board.
     */
    NQueens(int size)
    {
        this.size = size;
        stack = new BoardStack(size);
        solutions = new ArrayList<>();
    }

    /**
     * Solves the problem. After calling this, the CWnQueens.NQueens#print() function can be called to view the results
     */
    public void solve() {
        solve(0);
    }

    // Solve implementation
    private void solve(int row) {
        if (row >= size) {
            BoardState s = new BoardState(size);
            s.makeCopyOf(stack.top());
            solutions.add(s);
            return;
        }
        for (int col = 0; col < size; col++) {
            if (stack.top().available(col, row)) {
                stack.push();
                stack.top().placeQueen(col, row);
                solve(row + 1);
                stack.pop();
            }
        }
    }

    /**
     * Prints the results of solving the problem.
     */
    public void print() {
        int n = Math.min(5, solutions.size());
        System.out.format("Found %d solutions for a %dx%d board. Showing the first %d of them\n",
                solutions.size(), size, size, n);
        for (int i = 0; i < n; i++) {
            solutions.get(i).print();
            System.out.println();
        }
    }

    private final int size;
    private final BoardStack stack;
    private final ArrayList<BoardState> solutions;
}