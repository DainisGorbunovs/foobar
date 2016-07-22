package save_beta_rabbit;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        int food = 7;
        int[][] grid = new int[][] {
                {0, 2, 5},
                {1, 1, 3},
                {2, 1, 1}
        };

        Assert.assertEquals(Answer.answer(food, grid), 0);
    }

    @Test
    public void caseBTest() {
        int food = 12;
        int[][] grid = new int[][] {
                {0, 2, 5},
                {1, 1, 3},
                {2, 1, 1}
        };

        Assert.assertEquals(Answer.answer(food, grid), 1);
    }
}
