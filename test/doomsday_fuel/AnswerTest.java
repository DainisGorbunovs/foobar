package doomsday_fuel;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        int[][] input = new int[][]{
                {0, 2, 1, 0, 0},
                {0, 0, 0, 3, 4},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        int[] expected = new int[]{
                7, 6, 8, 21
        };
        Assert.assertArrayEquals(expected, Answer.answer(input));
    }

    @Test
    public void caseBTest() {
        int[][] input = new int[][]{
                {0, 1, 0, 0, 0, 1},
                {4, 0, 0, 3, 2, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        int[] expected = new int[]{
                0, 3, 2, 9, 14
        };
        Assert.assertArrayEquals(expected, Answer.answer(input));
    }

    @Test
    public void fractionTests() {
        Assert.assertEquals(17, Answer.getFractionDenominator(0.2352941));
        Assert.assertEquals(7, Answer.getFractionDenominator(2.0/7));
        Assert.assertEquals(14, Answer.getFractionDenominator(9.0/14));

        Assert.assertArrayEquals(new int[]{7, 14}, Answer.getFractionDenominators(new double[]{2.0/7, 9.0/14}));

        Assert.assertEquals(21, Answer.leastCommonMultiple(Answer.getFractionDenominators(new double[]{1.0/3, 2.0/7,
                8.0/21})));

        Assert.assertEquals(14, Answer.leastCommonMultiple(Answer.getFractionDenominators(new double[]{3.0/14, 1.0/7,
                9.0/14})));
    }

    @Test
    public void matrixTests() {
        double[][] input = new double[][]{
                {1D, 2D},
                {3D, 4D}
        };

        double[][] expectedInverse = new double[][]{
                {-2D, 1D},
                {1.5D, -0.5D}
        };

        double[][] inverse = Answer.invert(input);

        for (int index = 0; index < expectedInverse.length; ++index) {
            Assert.assertArrayEquals(expectedInverse[index], inverse[index], 0.0000001);
        }
    }
}