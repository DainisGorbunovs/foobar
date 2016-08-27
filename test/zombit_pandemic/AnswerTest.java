package zombit_pandemic;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        Assert.assertEquals("106/27", Answer.answer(4));
    }

    @Test
    public void caseBTest() {
        Assert.assertEquals("2/1", Answer.answer(2));
    }
}