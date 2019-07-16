package dodge_the_lasers;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void caseATest() {
        System.out.println(Solution.solution("19493958214737295721949395821473729572194939582147372957219493958214737295721949395821473729572194939"));
        Assert.assertEquals("4208", Solution.solution("77"));
    }

    @Test
    public void caseBTest() {
        Assert.assertEquals("19", Solution.solution("5"));
    }

    @Test
    public void caseCTest() {
        Assert.assertEquals("707314", Solution.solution("1000"));
    }

    @Test
    public void caseDTest() {
        Assert.assertEquals("707106781186547524400844362125559717403" +
                "49069012855847279",
                Solution.solution("10000000000000000000000000000"));
    }

    @Test
    public void caseETest() {
        Assert.assertEquals("26871076405196362991682450001989958376" +
                "308701027080619461439377264770269805701965122904992187884764" +
                "349301791878515079704272160373589383705408343670901558527604" +
                "7156809792391683564861028746778522878469385",
                Solution.solution("1949395821473729572194939582147372957219" +
                        "49395821473729572194939582147372957219493958214737295" +
                        "72194939"));
    }
}