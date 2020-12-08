package CWnQueens;

/**
 * Stores the board state for the problem.
 */
final class BoardState {
    /**
     * Construct a board state with the given size.
     * @param size the width and height of the board.
     */
    public BoardState(int size) {
        this.storage = new byte[size * size];
        this.size = size;
    }

    /**
     * Overwrites this board state with another.
     * @param other the board state to duplicate.
     */
    public void makeCopyOf(BoardState other) {
        System.arraycopy(other.storage, 0, this.storage, 0, other.storage.length);
    }

    /**
     * Prints the board state to stdout.
     */
    public void print() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (storage[r * size + c] == 2)
                    System.out.print("Q");
//                else if (storage[r * size + c] == 1)
//                    System.out.print("X");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * Determine if a square is a valid location for a queen.
     * @param col the column.
     * @param row the row.
     * @return true if the square is valid, false otherwise.
     */
    public boolean available(int col, int row) {
        return storage[row * size + col] == 0;
    }

    /**
     * Places a queen on the given spot, and marks other spots on the board as invalid placement locations.
     * @param col the column.
     * @param row the row.
     */
    public void placeQueen(int col, int row) {
        for (int i = 0; i < size; i++) {
            // Mark the row invalid
            storage[i * size + col] = (byte) 1;
            // Mark the column invalid
            storage[row * size + i] = (byte) 1;
            // Mark diagonals invalid
            setBits(col + i, row + i, (byte) 1);
            setBits(col + i, row - i, (byte) 1);
            setBits(col - i, row + i, (byte) 1);
            setBits(col - i, row - i, (byte) 1);
        }
        // Mark cells invalid when they are in a line with existing queens and the new queen.
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                // Skip this square if there's no queen here.
                if (storage[r * size + c] != 2)
                    continue;
                // Treat 1/2, 2/4, 3/6 slopes as the same by reducing them.
                int[] slope = LineSlope.normalize(c - col, r - row);
                // Search along the line to see if we find another queen before
                // running off the end of the board.
                for (int i = 1; true; i++) {
                    final boolean b = setBits(c - (slope[0] * i), r - (slope[1] * i), (byte) 1);
                    final boolean a = setBits(c + (slope[0] * i), r + (slope[1] * i), (byte) 1);
                    if (!a && !b)
                        break;
                }
            }
        }
        // Mark the queen's location.
        storage[row * size + col] = (byte) 2;
    }

    // Sets the storage value for the given location and returns true, or does nothing if the location is invalid and
    // returns false.
    private boolean setBits(int col, int row, byte bits) {
        if (col < 0 || col >= size || row < 0 || row >= size)
            return false;
        if (storage[row * size + col] == 0)
            storage[row * size + col] = bits;
        return true;
    }

    private final int size;
    private final byte[] storage;
}