package minions_bored_game;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        Assert.assertEquals(1, Answer.answer(1, 2));
    }

    @Test
    public void caseBTest() {
        Assert.assertEquals(3, Answer.answer(3, 2));
    }

    @Test
    public void caseCTest() {
        Assert.assertEquals(7, Answer.answer(4, 3));
    }

    @Test
    public void caseDTest() {
        Assert.assertEquals(862, Answer.answer(10, 6));
    }

    @Test
    public void caseETest() {
        Assert.assertEquals(71002640, Answer.answer(1000, 501));
    }
}