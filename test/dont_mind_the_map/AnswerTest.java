package dont_mind_the_map;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseAtest() {
        Assert.assertEquals(-1, new int[][] {
                {2, 1},
                {2, 0},
                {3, 1},
                {1, 0}
        });
    }

    @Test
    public void caseBtest() {
        Assert.assertEquals(1, new int[][] {
                {1, 2},
                {1, 1},
                {2, 2},
        });
    }
}