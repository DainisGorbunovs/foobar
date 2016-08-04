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

}