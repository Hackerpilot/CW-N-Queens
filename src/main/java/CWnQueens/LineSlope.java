package CWnQueens;

/**
 * Functionality for normalizing line slopes
 */
public final class LineSlope {

    /**
     * Reduces a fraction
     * @param x the numerator
     * @param y the denominator
     * @return the reduced fraction, as a pair, with x and y
     */
    public static Pair normalize(final int x, final int y) {
        final int d = gcd(Math.abs(x), Math.abs(y));
        return new Pair(x / d, y / d);
    }

    // Reiser's algorithm, taken from Hacker's Delight, second edition.
    private static int tzcnt(int i) {
        int lookup[] = {
            32, 0, 1, 26, 2, 23, 27, 0, 3, 16, 24, 30, 28, 11, 0, 13, 4, 7, 17,
            0, 25, 22, 31, 15, 29, 10, 12, 6, 0, 21, 14, 9, 5, 20, 8, 19, 18
        };
        return lookup[(-i & i) % 37];
    }

    // Taken from Wikipedia which took it from Rust's standard library.
    // https://en.wikipedia.org/wiki/Binary_GCD_algorithm#Iterative_version_in_Rust
    private static int gcd(int u, int v) {
        if (u == 0)
            return v;
        else if (v == 0)
            return u;

        final int i = tzcnt(u);
        u >>= i;
        final int j = tzcnt(v);
        v >>= j;
        final int k = Math.min(i, j);

        while (true) {
            if (u > v) {
                int t = u;
                u = v;
                v = t;
            }
            v -= u;
            if (v == 0)
                return u << k;
            v >>= tzcnt(v);
        }
    }
}
