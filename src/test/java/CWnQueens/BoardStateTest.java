package CWnQueens;

import org.junit.jupiter.api.Test;

class BoardStateTest {

    @Test
    void available() {
        {
            BoardState b = new BoardState(4);
            for (int row = 0; row < b.size; row++) {
                for (int col = 0; col < b.size; col++) {
                    assert b.available(row, col);
                }
            }
            b.placeQueen(0, 0);
            assert !b.available(0, 0);
            for (int a = 0; a < b.size; a++) {
                assert !b.available(0, a);
                assert !b.available(a, 0);
                assert !b.available(a, a);
            }
        }
        {
            BoardState b = new BoardState(4);
            b.placeQueen(2, 0);
            assert !b.available(1,1);
            assert !b.available(3,1);
        }
    }

    @Test
    void coLinearTest() {
        {
            BoardState b = new BoardState(5);
            b.placeQueen(0, 0);
            b.placeQueen(1, 2);
            // Check that it rejects placing a queen in a line with two existing queens
            assert !b.available(2, 4);
        }
        {
            BoardState b = new BoardState(5);
            b.placeQueen(0, 0);
            b.placeQueen(2, 4);
            // Check that it rejects placing a queen between two existing queens
            assert !b.available(1, 2);
        }
    }
}