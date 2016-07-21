package backward_and_forward;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        Assert.assertEquals(Answer.answer(0), 2);
    }

    @Test
    public void caseBTest() {
        Assert.assertEquals(Answer.answer(42), 4);
    }
}
