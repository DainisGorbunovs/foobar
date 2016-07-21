package backward_and_forward;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void testCaseTest() {
        Assert.assertEquals(Answer.answer(0), 2);
        Assert.assertEquals(Answer.answer(42), 4);
    }
}
