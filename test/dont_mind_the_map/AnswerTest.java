package dont_mind_the_map;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {
    @Test
    public void caseATest() {
        Assert.assertEquals(-1, new int[][] {
                {2, 1},
                {2, 0},
                {3, 1},
                {1, 0}
        });
    }

    @Test
    public void caseBTest() {
        Assert.assertEquals(1, new int[][] {
                {1, 2},
                {1, 1},
                {2, 2},
        });
    }

    @Test
    public void caseCTest() {
        Assert.assertEquals(-2, new int[][] {
                {0, 1, 2},
                {1, 2, 0},
                {2, 0, 1},
                {3, 4, 5},
                {4, 5, 3},
                {5, 3, 4}
        });
    }

    @Test
    public void caseDTest() {
        Assert.assertEquals(-1, new int[][] {
                {1, 2},
                {2, 2},
                {3, 3},
                {4, 4},
                {5, 5},
                {6, 6},
                {7, 7},
                {8, 8},
                {9, 9},
                {10, 10},
                {11, 11},
                {12, 12},
                {13, 13},
                {14, 14},
                {15, 15},
                {16, 16},
                {17, 17},
                {18, 18},
                {19, 19},
                {20, 20},
                {21, 21},
                {22, 22},
                {23, 23},
                {24, 24},
                {25, 25},
                {0, 0}
        });
    }

    /*
    subway[station][direction]
    3; answer is -2, if even with closing 1 station, there is no meeting path
        0: 0, 1 and 2
        1: 1, 2 and 0
        2: 2, 0 and 1
        3: 3, 4 and 5
        4: 4, 5 and 3
        5: 5, 3 and 4
    4: answer is -1, there are two directions from each station
         0: 1 and 2
         1: 2 and 2
         2: 3 and 3
         3: 4 and 4
         4: 5 and 5
         5: 6 and 6
         6: 7 and 7
         7: 8 and 8
         8: 9 and 9
         9: 10 and 10
        10: 11 and 11
        11: 12 and 12
        12: 13 and 13
        13: 14 and 14
        14: 15 and 15
        15: 16 and 16
        16: 17 and 17
        17: 18 and 18
        18: 19 and 19
        19: 20 and 20
        20: 21 and 21
        21: 22 and 22
        22: 23 and 23
        23: 24 and 24
        24: 25 and 25
        25: 0 and 0
    5: answer is 0, there are three directions from each station
         0:  2,  3 and  0
         1:  2,  4 and  1
         2:  0,  5 and  2
         3:  4,  6 and  3
         4:  5,  7 and  4
         5:  3,  8 and  5
         6:  7,  9 and  6
         7:  8, 10 and  7
         8:  6, 11 and  8
         9: 10, 12 and  9
        10: 11, 13 and 10
        11:  9, 14 and 11
        12: 13,
        13: 14,
        14: 12,
        15: 16,
        16: 17,
        17: 15,
        18: 19,
        19: 20,
        20: 18, 
        21:
        22:
        23:
        24:
        25:
        26:
        27:
        28:
        29:
        30:
        31:
        32:
        33:
        34:
        35:
        36:
        37:
        38:
        39:
        40:
        41:
        42:
        43:
        44:
        45:
        46:
        47: three elements
     */
}