package carrotland;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        Assert.assertEquals(289, Answer.answer(new int[][] {{2, 3}, {6, 9}, {10, 160}}));

    }

    @Test
    public void caseBTest() {
        Assert.assertEquals(1730960165, Answer.answer(new int[][] {{91207, 89566}, {-88690, -83026}, {67100, 47194}}));
    }
}
