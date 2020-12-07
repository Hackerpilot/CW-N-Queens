package CWnQueens;

import CWnQueens.NQueens;
import org.junit.jupiter.api.Test;

class NQueensTest {
    @Test
    public void testRun() {
        NQueens nq = new NQueens(14);
        nq.solve();
        nq.print();
    }
}