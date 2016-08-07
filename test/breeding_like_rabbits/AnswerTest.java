package breeding_like_rabbits;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        Assert.assertEquals("4", Answer.answer("7"));
    }

    @Test
    public void caseBTest() {
        Assert.assertEquals("None", Answer.answer("100"));
    }
}