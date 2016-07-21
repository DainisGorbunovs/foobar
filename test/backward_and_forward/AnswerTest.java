package backward_and_forward;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void testCaseATest() {
        Assert.assertEquals(Answer.answer(0), 2);
    }

    @Test
    public void testCaseBTest() {
        Assert.assertEquals(Answer.answer(42), 4);
    }
}
