package binary_bunnies;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        Assert.assertEquals("6", Answer.answer(new int[] {5, 9, 8, 2, 1}));
    }

    @Test
    public void caseBTest() {
        Assert.assertEquals("1", Answer.answer(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}