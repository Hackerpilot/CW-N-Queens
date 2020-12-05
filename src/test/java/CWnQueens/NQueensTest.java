package CWnQueens;

import CWnQueens.NQueens;
import org.junit.jupiter.api.Test;

class NQueensTest {
    @Test
    public void testDoThings() {
        NQueens nq = new NQueens(14);
        nq.solve();
        nq.print();
    }
}