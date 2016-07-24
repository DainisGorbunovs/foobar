package zombit_monitoring;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        int[][] intervals = new int[][] {
                {1, 3},
                {3, 6}
        };
        Assert.assertEquals(5, Answer.answer(intervals));
    }

    @Test
    public void caseBTest() {
        int[][] intervals = new int[][] {
                {10, 14},
                { 4, 18},
                {19, 20},
                {19, 20},
                {13, 20}
        };
        Assert.assertEquals(16, Answer.answer(intervals));
    }
}
