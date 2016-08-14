package grid_zero;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        int[][] matrix = new int[][]{
                {1, 1},
                {0, 0}
        };

        Assert.assertEquals(2, Answer.answer(matrix));
    }

    @Test
    public void caseBTest() {
        int[][] matrix = new int[][]{
                {1, 1, 1},
                {1, 0, 0},
                {1, 0, 1}
        };

        Assert.assertEquals(-1, Answer.answer(matrix));
    }
}