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

    @Test
    public void caseImpossibleTest() {
        Assert.assertEquals("impossible", Answer.answer("2", "4"));
    }

    @Test
    public void caseImpossible2Test() {
        Assert.assertEquals("impossible", Answer.answer("5", "10"));
    }
}