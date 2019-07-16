package dodge_the_lasers;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void caseATest() {
        Assert.assertEquals("4208", Solution.solution("77"));
    }

    @Test
    public void caseBTest() {
        Assert.assertEquals("19", Solution.solution("5"));
    }

}