package backward_and_forward;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        Assert.assertEquals(2, Answer.answer(0));
    }

    @Test
    public void caseBTest() {
        Assert.assertEquals(4, Answer.answer(42));
    }
}
