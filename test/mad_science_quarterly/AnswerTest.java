package mad_science_quarterly;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        int[] L = new int[]{-100, 95, 86, 47};
        int k = 3;
        Assert.assertEquals(228, Answer.answer(L, k));
    }

    @Test
    public void caseBTest() {
        int[] L = new int[]{40, 91, -68, -36, 24, -67, -32, -23, -33, -52};
        int k = 7;
        Assert.assertEquals(131, Answer.answer(L, k));
    }
}