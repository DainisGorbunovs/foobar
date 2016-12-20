package bomb_baby;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        Assert.assertEquals("1", Answer.answer("2", "1"));
    }

    @Test
    public void caseBTest() {
        Assert.assertEquals("4", Answer.answer("4", "7"));
    }
}