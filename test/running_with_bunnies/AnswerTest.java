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
                {9, 3, 2, 2, 0}
        };
        Assert.assertArrayEquals(new int[]{1, 2}, Answer.answer(times, 1));
    }
}