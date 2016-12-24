package find_the_access_codes;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        Assert.assertEquals(1, Answer.answer(new int[]{1, 1, 1}));
    }

    @Test
    public void caseBTest() {
        Assert.assertEquals(3, Answer.answer(new int[]{1, 2, 3, 4, 5, 6}));
    }
}