package disorderly_escape;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void caseATest() {
        Assert.assertEquals("430", Solution.solution(2, 3, 4));
    }

    @Test
    public void caseBTest() {
        Assert.assertEquals("7", Solution.solution(2, 2, 2));
    }

    @Test
    public void caseCTest() {
        Assert.assertEquals("97195340925396730736950973830781340249131" +
                "67907359236085614170014873420799787797800541973582287876882108" +
                "83439779692091397216821714879599670122864746289784704871930515" +
                "91840", Solution.solution(12, 12, 20));
    }

    @Test
    public void caseDTest() {
        Assert.assertEquals("1137863754106723400", Solution.solution(4, 4, 20));
    }

    @Test
    public void caseETest() {
        Assert.assertEquals("23301834615661488487765745000", Solution.solution(5, 5, 20));
    }

    @Test
    public void caseFTest() {
        Assert.assertEquals("132560781153101038829213988789736592649360", Solution.solution(6, 6, 20));
    }

    @Test
    public void caseGTest() {
        Assert.assertEquals("22161988689419882120187267887616330579221" +
                "0161226545392840", Solution.solution(7, 7, 20));
    }

    @Test
    public void caseHTest() {
        Assert.assertEquals("11346937861481789731271832998937451898372" +
                "4697432844009920312263602471667640", Solution.solution(8, 8, 20));
    }

    @Test
    public void caseITest() {
        Assert.assertEquals("738", Solution.solution(3, 3, 3));
    }

    @Test
    public void caseJTest() {
        Assert.assertEquals("8240", Solution.solution(3, 3, 4));
    }

    @Test
    public void caseKTest() {
        Assert.assertEquals("57675", Solution.solution(3, 3, 5));
    }

    @Test
    public void caseLTest() {
        Assert.assertEquals("7880456", Solution.solution(4, 4, 4));
    }

    @Test
    public void caseMTest() {
        Assert.assertEquals("270656150", Solution.solution(4, 4, 5));
    }

    @Test
    public void caseNTest() {
        Assert.assertEquals("20834113243925", Solution.solution(5, 5, 5));
    }
}