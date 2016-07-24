package when_it_rains_it_pours;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        int[] heights = new int[] {1, 4, 2, 5, 1, 2, 3};

        Assert.assertEquals(5, Answer.answer(heights));
    }

    @Test
    public void caseBTest() {
        int[] heights = new int[] {1, 2, 3, 2, 1};

        Assert.assertEquals(0, Answer.answer(heights));
    }
}
