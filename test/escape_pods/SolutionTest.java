package escape_pods;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void caseATest() {
        int[] entrances = {0, 1};
        int[] exits = {4, 5};
        int[][] path = {
                {0, 0, 4, 6, 0, 0},
                {0, 0, 5, 2, 0, 0},
                {0, 0, 0, 0, 4, 4},
                {0, 0, 0, 0, 6, 6},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        int output = 16;
        Assert.assertEquals(output, Solution.solution(entrances, exits, path));
    }

    @Test
    public void caseBTest() {
        int[] entrances = {0};
        int[] exits = {3};
        int[][] path = {
                {0, 7, 0, 0},
                {0, 0, 6, 0},
                {0, 0, 0, 8},
                {9, 0, 0, 0}
        };
        int output = 6;
        Assert.assertEquals(output, Solution.solution(entrances, exits, path));
    }
}