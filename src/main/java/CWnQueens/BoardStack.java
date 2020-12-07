package CWnQueens;

/**
 * A stack of re-usable board states used with the recursive solver.
 */
public class BoardStack {
    /**
     *
     * @param size the maximum number of items on the stack.
     */
    public BoardStack(int size) {
        this.stack = new BoardState[size + 1];
        for (int i = 0; i < stack.length; i++) {
            stack[i] = new BoardState(size);
        }
        index = 0;
    }

    /**
     * Gets the active board state.
     * @return the active board state.
     */
    public BoardState top() {
        return stack[index];
    }

    /**
     * Revert to the previous state
     */
    public void pop() {
        index--;
    }

    /**
     * Make the current state a copy of the previous current state.
     */
    public void push() {
        stack[index + 1].makeCopyOf(stack[index]);
        index++;
    }

    final private BoardState[] stack;
    private int index;
}
