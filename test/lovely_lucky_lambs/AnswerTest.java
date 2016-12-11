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

    @Test
    public void caseCTest() {
        Assert.assertEquals(3, Answer.answer(511));
    }

    @Test
    public void caseDTest() {
        Assert.assertEquals(8, Answer.answer(1048575));
    }

    @Test
    public void caseETest() {
        Assert.assertEquals(9, Answer.answer(1048574));
    }
}
