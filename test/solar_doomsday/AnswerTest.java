package solar_doomsday;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        Assert.assertArrayEquals(new int[]{9, 1, 1, 1}, Answer.answer(12));
    }

    @Test
    public void caseBTest() {
        Assert.assertArrayEquals(new int[]{15129, 169, 25, 1}, Answer.answer(15324));
    }
}
