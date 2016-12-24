package doomsday_fuel;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    private static final double DOUBLEDELTA = 0.0000001;
    int[][] caseAinput = new int[][]{
            {0, 2, 1, 0, 0},
            {0, 0, 0, 3, 4},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
    };

    int[][] caseBinput = new int[][]{
            {0, 1, 0, 0, 0, 1},
            {4, 0, 0, 3, 2, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
    };

    @Test
    public void caseATest() {
        int[] expected = new int[]{
                7, 6, 8, 21
        };
        Assert.assertArrayEquals(expected, Answer.answer(caseAinput));
    }

    @Test
    public void caseBTest() {
        int[] expected = new int[]{
                0, 3, 2, 9, 14
        };
        Assert.assertArrayEquals(expected, Answer.answer(caseBinput));
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
    public void matrix2x2InversionTest() {
        double[][] input = new double[][]{
                {1D, -1/2D},
                {-4/9D, 1D}
        };

        double[][] expectedInverse = new double[][]{
                {9/7D, 9/14D},
                {4/7D, 9/7D}
        };

        double[][] inverse = Answer.invert(input);

        for (int index = 0; index < expectedInverse.length; ++index) {
            Assert.assertArrayEquals(expectedInverse[index], inverse[index], DOUBLEDELTA);
        }
    }

    @Test
    public void matrix3x3InversionTest() {
        double[][] input = new double[][]{
                {1D, 2D, 3D},
                {0D, 1D, 5D},
                {5D, 6D, 0D}
        };

        double[][] expectedInverse = new double[][]{
                {-6D, 18/5D, 7/5D},
                {5D, -3D, -1D},
                {-1D, 4/5D, 1/5D}
        };

        double[][] inverse = Answer.invert(input);

        for (int index = 0; index < expectedInverse.length; ++index) {
            Assert.assertArrayEquals(expectedInverse[index], inverse[index], DOUBLEDELTA);
        }
    }

    @Test
    public void matrix4x4InversionTest() {
        double[][] input = new double[][]{
                {1D, 1D, 1D, 0D},
                {0D, 3D, 1D, 2D},
                {2D, 3D, 1D, 0D},
                {1D, 0D, 2D, 1D}
        };

        double[][] expectedInverse = new double[][]{
                {-3D, -1/2D, 3/2D, 1D},
                {1D, 1/4D, -1/4D, -1/2D},
                {3D, 1/4D, -5/4D, -1/2D},
                {-3D, 0D, 1D, 1D}
        };

        double[][] inverse = Answer.invert(input);

        for (int index = 0; index < expectedInverse.length; ++index) {
            Assert.assertArrayEquals(expectedInverse[index], inverse[index], DOUBLEDELTA);
        }
    }

    @Test
    public void matrix5x5InversionTest() {
        double[][] input = new double[][]{
                {2D, -2D, 0D, 0D, -3D},
                {3D, -12D, 3D, 2D, -1D},
                {0D, 9D, -2D, 0D, 2D},
                {-1D, 2D, 0D, 3D, 0D},
                {0D, 0D, 1D, 0D, 0D}
        };

        double[][] expectedInverse = new double[][]{
                {53/359D, 69/359D, 114/359D, -46/359D, 21/359D},
                {22/359D, -12/359D, 27/359D, 8/359D, 90/359D},
                {0, 0, 0, 0, 1},
                {3/359D, 31/359D, 20/359D, 99/359D, -53/359D},
                {-99/359D, 54/359D, 58/359D, -36/359D, -46/359D}
        };

        double[][] inverse = Answer.invert(input);

        for (int index = 0; index < expectedInverse.length; ++index) {
            Assert.assertArrayEquals(expectedInverse[index], inverse[index], DOUBLEDELTA);
        }
    }

    @Test
    public void matrix6x6InversionTest() {
        double[][] input = new double[][]{
                {1D, 0D, 2D, 0D, 0D, 4D},
                {18D, 1D, 5D, 0D, 0D, 9D},
                {3D, 5D, 3D, 6D, 0D, 4D},
                {2D, 0D, 8D, 0D, 0D, 7D},
                {7D, 0D, 4D, 0D, 6D, 0D},
                {0D, 0D, 1D, 0D, 0D, 0D}
        };

        double[][] expectedInverse = new double[][]{
                {-7D, 0D, 0D, 4D, 0D, -18D},
                {108D, 1D, 0D, -63D, 0D, 283D},
                {0D, 0D, 0D, 0D, 0D, 1D},
                {-527/6D, -5/6D, 1/6D, 307/6D, 0D, -230D},
                {49/6D, 0D, 0D, -14/3D, 1/6D, 61/3D},
                {2D, 0D, 0D, -1D, 0D, 4D}
        };

        double[][] inverse = Answer.invert(input);

        for (int index = 0; index < expectedInverse.length; ++index) {
            Assert.assertArrayEquals(expectedInverse[index], inverse[index], DOUBLEDELTA);
        }
    }

    @Test
    public void matrix7x7InversionTest() {
        double[][] input = new double[][]{
                {-10D, 0D, 6D, 0D, 0D, -6D, 0D},
                {-49D, -4D, -26D, 10D, 4D, 28D, -12D},
                {-6D, 0D, 5D, 0D, 0D, -3D, 0D},
                {-18D, 3D, 9D, -3D, -2D, -11D, 6D},
                {17D, -3D, -9D, 5D, 4D, 10D, -6D},
                {12D, 0D, -6D, 0D, 0D, 8D, 0D},
                {-30D, 3D, 15D, -6D, -3D, -17D, 8D}
        };

        double[][] expectedInverse = new double[][]{
                {-11/2D, 0D, 3D, 0D, 0D, -3D, 0D},
                {5513/4D, -5/2D, -1505/2D, 2D, -1D, 1503/2D, -6D},
                {-3D, 0D, 2D, 0D, 0D, -3/2D, 0D},
                {-821D, 3/2D, 897/2D, -1/2D, 1/2D, -1791/4D, 3D},
                {3285/4D, -3/2D, -897/2D, 1D, 0D, 448D, -3D},
                {6D, 0D, -3D, 0D, 0D, 7/2D, 0D},
                {-6615/8D, 3/2D, 903/2D, -3/4D, 3/4D, -3605/8D, 7/2D}
        };

        double[][] inverse = Answer.invert(input);

        for (int index = 0; index < expectedInverse.length; ++index) {
            Assert.assertArrayEquals(expectedInverse[index], inverse[index], DOUBLEDELTA);
        }
    }

    @Test
    public void separateTransitionTerminalNodesTest() {
        int[][] separatedCaseANodes = Answer.separateTransitionTerminalNodes(caseAinput);
        Assert.assertArrayEquals(new int[]{2,3,4}, separatedCaseANodes[Answer.TERMINAL_NODE]);
        Assert.assertArrayEquals(new int[]{0, 1}, separatedCaseANodes[Answer.TRANSITION_NODE]);

        int[][] separatedCaseBNodes = Answer.separateTransitionTerminalNodes(caseBinput);
        Assert.assertArrayEquals(new int[]{2,3,4,5}, separatedCaseBNodes[Answer.TERMINAL_NODE]);
        Assert.assertArrayEquals(new int[]{0, 1}, separatedCaseBNodes[Answer.TRANSITION_NODE]);
    }

    @Test
    public void transitionProbabilityTest() {
        Assert.assertEquals(3/7D, Answer.getTransitionProbability(caseAinput, 1, 3), 0.0000001);
    }

    @Test
    public void getTransitionProbabilitiesCaseATest() {
        double[][] probabilities = Answer.getTransitionProbabilities(caseAinput, new int[]{0, 1});
        double[][] expected = new double[][]{
                {0D, 2/3D},
                {0D, 0D}
        };
        for (int index = 0; index < probabilities.length; ++index) {
            Assert.assertArrayEquals(expected[index], probabilities[index], DOUBLEDELTA);
        }
    }

    @Test
    public void getTransitionProbabilitiesCaseBTest() {
        double[][] probabilities = Answer.getTransitionProbabilities(caseBinput, new int[]{0, 1});
        double[][] expected = new double[][]{
                {0D, 1/2D},
                {4/9D, 0D}
        };
        for (int index = 0; index < probabilities.length; ++index) {
            Assert.assertArrayEquals(expected[index], probabilities[index], DOUBLEDELTA);
        }
    }

    @Test
    public void getReachingProbabilitiesCaseATest() {
        double[][] F = Answer.getTransitionProbabilities(caseAinput, new int[]{0, 1});
        double[][] probabilities = Answer.getReachingProbabilities(F);
        double[][] expected = new double[][]{
                {1D, -2/3D},
                {0D, 1D}
        };
        for (int index = 0; index < probabilities.length; ++index) {
            Assert.assertArrayEquals(expected[index], probabilities[index], DOUBLEDELTA);
        }
    }

    @Test
    public void getReachingProbabilitiesCaseBTest() {
        double[][] F = Answer.getTransitionProbabilities(caseBinput, new int[]{0, 1});
        double[][] probabilities = Answer.getReachingProbabilities(F);
        double[][] expected = new double[][]{
                {1D, -1/2D},
                {-4/9D, 1D}
        };
        for (int index = 0; index < probabilities.length; ++index) {
            Assert.assertArrayEquals(expected[index], probabilities[index], DOUBLEDELTA);
        }
    }

    @Test
    public void getAbsorbingProbabilitiesCaseATest() {
        double[][] R = Answer.getAbsorbingProbabilities(caseAinput, new int[]{0,1}, new int[]{2,3,4});
        double[][] expected = new double[][]{
                {1/3D, 0D, 0D},
                {0D, 3/7D, 4/7D}
        };

        for (int index = 0; index < R.length; ++index) {
            Assert.assertArrayEquals(expected[index], R[index], DOUBLEDELTA);
        }
    }

    @Test
    public void getAbsorbingProbabilitiesCaseBTest() {
        double[][] R = Answer.getAbsorbingProbabilities(caseBinput, new int[]{0,1}, new int[]{2,3,4,5});
        double[][] expected = new double[][]{
                {0D, 0D, 0D, 1/2D},
                {0D, 3/9D, 2/9D, 0D}
        };

        for (int index = 0; index < R.length; ++index) {
            Assert.assertArrayEquals(expected[index], R[index], DOUBLEDELTA);
        }
    }

    @Test
    public void multiplyMatrixTest() {
        double[][] first = new double[][]{
                {1D, 2/3D},
                {0D, 1D}
        };

        double[][] second = new double[][]{
                {1/3D, 0D, 0D},
                {0D, 3/7D, 4/7D}
        };

        double[][] expected = new double[][]{
                {1/3D, 2/7D, 8/21D},
                {0D, 3/7D, 4/7D}
        };

        double[][] multiplied = Answer.multiplyMatrix(first, second);
        for (int index = 0; index < multiplied.length; ++index) {
            Assert.assertArrayEquals(expected[index], multiplied[index], DOUBLEDELTA);
        }
    }
}