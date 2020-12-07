package CWnQueens;

/**
 * Holds the main function.
 */
public class Main {

    private static void printError() {
        System.err.println("Please specify a board size as an integer between 1 and 20 inclusive.");
    }

    /**
     * The main function.
     * @param args command-line arguments passed in from the OS
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            printError();
            return;
        }
        int size = Integer.parseInt(args[0]);
        if (size < 1 || size > 20) {
            printError();
            return;
        }
        long startTime = System.currentTimeMillis();
        NQueens nQueens = new NQueens(size);
        nQueens.solve();
        long endTime = System.currentTimeMillis();
        System.out.println("Solved in " + (endTime - startTime) + " milliseconds");
        nQueens.print();
    }
}
