package CWnQueens;

import java.util.HashSet;

/**
 * Board state for the modified N-Queens problem.
 */
public class BoardState implements Cloneable {

    /**
     * @param size = the width and height of the board.
     */
    BoardState(int size) {
        queens = new HashSet<>();
        rowTaken = new boolean[size];
        colTaken = new boolean[size];
        descendingTaken = new boolean[size * 2 - 1];
        ascendingTaken = new boolean[size * 2 - 1];
        this.size = size;
    }

    @Override
    public Object clone() {
        try {
            BoardState b = (BoardState) super.clone();
            b.queens = (HashSet<Pair>) queens.clone();
            return b;
        } catch (CloneNotSupportedException c) {
            assert false;
        }
        return null;
    }

    /**
     * Prints the board state to the console.
     */
    public void print() {
        Pair p = new Pair(0, 0);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                p.x = col;
                p.y = row;
                if (queens.contains(p))
                    System.out.print('Q');
                else
                    System.out.print('_');
            }
            System.out.println();
        }
    }

    /**
     * Marks the give spot on the board as taken.
     *
     * @param row the row
     * @param col the column
     */
    public void placeQueen(final int row, final int col) {
        rowTaken[row] = true;
        colTaken[col] = true;
        ascendingTaken[diagonalUp(row, col)] = true;
        descendingTaken[diagonalDown(row, col)] = true;
        queens.add(new Pair(col, row));
    }

    /**
     * Marks the give spot on the board as empty.
     *
     * @param row the row
     * @param col the column
     */
    public void removeQueen(final int row, final int col) {
        rowTaken[row] = false;
        colTaken[col] = false;
        ascendingTaken[diagonalUp(row, col)] = false;
        descendingTaken[diagonalDown(row, col)] = false;
        queens.remove(new Pair(col, row));
    }

    /**
     * Determine if the give space on the board can have a queen placed on it
     *
     * @param row the row
     * @param col the column
     * @return true if the given space is available
     */
    public boolean available(final int row, final int col) {
        return !rowTaken[row]
                && !colTaken[col]
                && !descendingTaken[diagonalDown(row, col)]
                && !ascendingTaken[diagonalUp(row, col)]
                && !coLinear(row, col);
    }

    // Translates a row,col pair into an array index
    private int diagonalUp(final int row, final int col) {
        return row + col;
    }

    // Translates a row,col pair into an array index
    private int diagonalDown(final int row, final int col) {
        return size + col - row - 1;
    }

    // Tests if the given point is co-linear with any two existing points
    private boolean coLinear(final int row, final int col) {
        Pair base = new Pair(0, 0);
        for (final Pair p : queens) {
            // First get the slope between the test point and the current point
            Pair slope = LineSlope.normalize(p.x - col, p.y - row);
            // Base our search from the point
            base.x = p.x;
            base.y = p.y;
            // Search along the line to see if we find another queen before
            // running off the end of the board.
            while (true) {
                base.x += slope.x;
                base.y += slope.y;
                if (base.x < 0 || base.x >= size || base.y < 0 || base.y >= size)
                    break;
                if (queens.contains(base))
                    return true;
            }
            base.x = col;
            base.y = row;
            while (true) {
                base.x -= slope.x;
                base.y -= slope.y;
                if (base.x < 0 || base.x >= size || base.y < 0 || base.y >= size)
                    break;
                if (queens.contains(base))
                    return true;
            }
        }
        return false;
    }

    // Both the width and height of the board
    public final int size;
    // Positions of the queens on the board
    private HashSet<Pair> queens;
    // Is a row taken
    private final boolean[] rowTaken;
    // Is a column taken
    private final boolean[] colTaken;
    // top-left to bottom-right
    private final boolean[] descendingTaken;
    // bottom-left to top-right
    private final boolean[] ascendingTaken;
}
