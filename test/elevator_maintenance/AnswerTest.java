package elevator_maintenance;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        String[] input = {"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"};
        String[] output = {"1.0", "1.0.2", "1.0.12", "1.1.2", "1.3.3"};
        Assert.assertArrayEquals(output, Answer.answer(input));
    }

    @Test
    public void caseBTest() {
        String[] input = {"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"};
        String[] output = {"0.1", "1.1.1", "1.2", "1.2.1", "1.11", "2", "2.0", "2.0.0"};
        Assert.assertArrayEquals(output, Answer.answer(input));
    }
}