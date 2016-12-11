package lovely_lucky_lambs;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        Assert.assertEquals(1, Answer.answer(10));
    }

    @Test
    public void caseBTest() {
        Assert.assertEquals(3, Answer.answer(143));
    }
}
