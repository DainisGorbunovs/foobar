package zombit_infection;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        int[][] population = new int[][] {
                {1, 2, 3},
                {2, 3, 4},
                {3, 2, 1}
        };
        int x = 0;
        int y = 0;
        int strength = 2;
        Assert.assertArrayEquals(new int[][]{
                {-1, -1, 3},
                {-1,  3, 4},
                { 3,  2, 1}
        }, Answer.answer(population, x, y, strength));
    }

    @Test
    public void caseBTest() {
        int[][] population = new int[][] {
                {9, 3, 4, 5, 4},
                {1, 6, 5, 4, 3},
                {2, 3, 7, 3, 2},
                {3, 4, 5, 8, 1},
                {4, 5, 4, 3, 9}
        };
        int x = 2;
        int y = 1;
        int strength = 5;
        Assert.assertArrayEquals(new int[][]{
                { 6,  7, -1,  7,  6},
                { 6, -1, -1, -1,  7},
                {-1, -1, -1, -1, 10},
                { 8, -1, -1, -1,  9},
                { 8,  7, -1,  9,  9}
        }, Answer.answer(population, x, y, strength));
    }
}
