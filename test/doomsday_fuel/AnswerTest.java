package doomsday_fuel;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        int[][] input = new int[][]{
                {0, 2, 1, 0, 0},
                {0, 0, 0, 3, 4},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        int[] expected = new int[]{
                7, 6, 8, 21
        };
        Assert.assertArrayEquals(expected, Answer.answer(input));
    }

    @Test
    public void caseBTest() {
        int[][] input = new int[][]{
                {0, 1, 0, 0, 0, 1},
                {4, 0, 0, 3, 2, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        int[] expected = new int[]{
                0, 3, 2, 9, 14
        };
        Assert.assertArrayEquals(expected, Answer.answer(input));
    }
}