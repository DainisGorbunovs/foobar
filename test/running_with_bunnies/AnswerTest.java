package running_with_bunnies;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        int[][] times = new int[][]{
                {0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 0}
        };
        Assert.assertArrayEquals(new int[]{0, 1}, Answer.answer(times, 3));
    }

    @Test
    public void caseBTest() {
        int[][] times = new int[][]{
                {0, 2, 2, 2, -1},
                {9, 0, 2, 2, -1},
                {9, 3, 0, 2, -1},
                {9, 3, 2, 0, -1},
                {9, 3, 2, 2,  0}
        };
        Assert.assertArrayEquals(new int[]{1, 2}, Answer.answer(times, 1));
    }

    @Test
    public void caseCTest() {
        int[][] times = new int[][]{
                {0,  99, 99, 99, 99, 99, -1},
                {99,  0, 99, 99, 99, 99, 99},
                {99, 99,  0, 99, 99, 99, 99},
                {99, 99, 99,  0, 99, 99, 99},
                {99, 99, 99, 99,  0, 99, 99},
                {99, 99, 99, 99,  0,  0, 99},
                {0,  99, 99, 99, 99, 99,  0}
        };
        Assert.assertArrayEquals(new int[]{0, 1, 2, 3, 4}, Answer.answer(times, 1));
    }

    @Test
    public void caseDTest() {
        int[][] times = new int[][]{
                {0, 9, 9, 1, 9, 9, 9},
                {1, 0, 9, 9, 9, 9, 9},
                {1, 9, 0, 9, 9, 9, 9},
                {9, 9, 9, 0, 1, 9, 9},
                {9, 2, 4, 9, 0, 3, 1},
                {1, 9, 9, 9, 9, 0, 9},
                {1, 9, 9, 9, 9, 9, 0}
        };
        Assert.assertArrayEquals(new int[]{0, 2, 3, 4}, Answer.answer(times, 14));
    }

    @Test
    public void caseETest() {
        int[][] times = new int[][]{
                { 0,  1, -2, 3,  2, -1,  0},
                {-1,  0, -3, 2,  1, -2, -1},
                { 2,  3,  0, 5,  4,  1,  2},
                {-3, -2, -5, 0, -1, -4, -3},
                {-2, -1, -4, 1,  0, -3, -2},
                { 1,  2, -1, 4,  3,  0,  1},
                { 0,  1, -2, 3,  2, -1,  0}
        };
        Assert.assertArrayEquals(new int[]{0, 1, 2, 3, 4}, Answer.answer(times, 0));
    }

    @Test
    // No bunnies can be picked up during time limit
    public void caseFTest() {
        int[][] times = new int[][]{
                {0, 2, 2, 2},
                {2, 0, 2, 2},
                {2, 2, 0, 2},
                {2, 2, 2, 0}
        };
        Assert.assertArrayEquals(new int[]{}, Answer.answer(times, 3));
    }

    @Test
    public void caseGTest() {
        int[][] times = new int[][]{
                { 0,  3, 82, 91, 15, 24, 77},
                { 8,  0,  7, 32,  6, 33, 14},
                {66, 98,  0, 62, 59,  5, 39},
                {64, 97,  5,  0, 45, 84, 21},
                { 3, 33, 81, 24,  0, 53,  5},
                {73, 93, 29,  9, 78,  0, 44},
                {70, 76, 15,  0, 43, 58,  0}
        };
        Assert.assertArrayEquals(new int[]{0, 1, 2, 3, 4}, Answer.answer(times, 999));
    }

    @Test
    public void caseHTest() {
        int[][] times = new int[][]{
                {0, -3, -5, -4, -1, -2, 0},
                {5,  0, -1,  0,  3,  2, 4},
                {7,  3,  0,  2,  5,  4, 6},
                {6,  2,  0,  0,  4,  3, 5},
                {3, -1, -3, -2,  0,  0, 2},
                {4,  0, -2, -1,  2,  0, 3},
                {2, -2, -4, -3,  0, -1, 0}
        };
        Assert.assertArrayEquals(new int[]{0, 1, 2}, Answer.answer(times, 3));
    }

    @Test
    public void caseITest() {
        int[][] times = new int[][]{
                { 0, 1, -1,  2, -3, -2,  0},
                { 2, 0,  1,  4, -1,  0,  2},
                { 5, 6,  0,  7,  2,  3,  5},
                {-1, 0, -2,  0, -4, -3, -1},
                { 8, 9,  7, 10,  0,  6,  8},
                { 4, 5,  3,  6,  1,  0,  4},
                { 0, 1, -1,  2, -3,  2,  0}
        };
        Assert.assertArrayEquals(new int[]{0, 2, 4}, Answer.answer(times, 6));
    }

    @Test
    public void caseJTest() {
        int[][] times = new int[][]{
                { 0, 15, 19, 10, -1, 12,  4},
                { 7,  0, 19,  4, 19, 17,  7},
                {15,  8,  0, 14,  8,  4,  3},
                {10, 14,  6,  0,  0,  5,  9},
                {18,  8,  4,  0,  0, 12, 16},
                { 0, 13,  1, -1, 12,  0,  4},
                { 8,  5,  2, 11, 12, 16,  0}
        };
        Assert.assertArrayEquals(new int[]{1, 2, 3}, Answer.answer(times, 7));
    }
}