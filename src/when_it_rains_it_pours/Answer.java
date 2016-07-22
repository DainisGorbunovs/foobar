/*
    When it rains it pours
    ======================

    It's raining, it's pouring. You and your agents are nearing the building where the captive rabbits are being held,
    but a sudden storm puts your escape plans at risk. The structural integrity of the rabbit hutches you've built to
    house the fugitive rabbits is at risk because they can buckle when wet. Before the rabbits can be rescued from
    Professor Boolean's lab, you must compute how much standing water has accumulated on the rabbit hutches.

    Specifically, suppose there is a line of hutches, stacked to various heights and water is poured from the top
    (and allowed to run off the sides). We'll assume all the hutches are square, have side length 1, and for the
    purposes of this problem we'll pretend that the hutch arrangement is two-dimensional.

    For example, suppose the heights of the stacked hutches are [1,4,2,5,1,2,3] (the hutches are shown below):

    ...X...
    .X.X...
    .X.X..X
    .XXX.XX
    XXXXXXX
    1425123

    When water is poured over the top at all places and allowed to runoff, it will remain trapped at the 'O' locations:

    ...X...
    .XOX...
    .XOXOOX
    .XXXOXX
    XXXXXXX
    1425123

    The amount of water that has accumulated is the number of Os, which, in this instance, is 5.

    Write a function called answer(heights) which, given the heights of the stacked hutches from left-to-right as
    a list, computes the total area of standing water accumulated when water is poured from the top and allowed to
    run off the sides.

    The heights array will have at least 1 element and at most 9000 elements. Each element will have a value of at
    least 1, and at most 100000.
 */

package when_it_rains_it_pours;

public class Answer {
    private static final int left  = 0;
    private static final int right = 1;

    public static int answer(int[] heights) {
        // Water is trapped between number A and C, where there are smaller numbers B < A and B < C
        // Runs off the sides, so first and last numbers do not contain water
        int[] max = new int[] {0, 0};
        int[] index = new int[] {0, heights.length - 1};
        int droplets = 0;

        // Go from both sides
        while (index[left] < index[right]) {
            // Keep track of maximum encountered height from left and right side
            max[left]  = Math.max(max[left], heights[index[left]]);
            max[right] = Math.max(max[right], heights[index[right]]);

            // If right side's max height is greater than left side's
            // Count droplets from the side with a lower max height,
            // Then move the side's index closer to the other index
            if (max[left] < max[right]) {
                droplets += max[left] - heights[index[left]];
                index[left]++;
            } else {
                droplets += max[right] - heights[index[right]];
                index[right]--;
            }
        }
        return droplets;
    }
    
    public static void main(String[] args) {
        System.out.println("answer(new int[] {1, 4, 2, 5, 1, 2, 3}) = " + answer(new int[] {1, 4, 2, 5, 1, 2, 3}));
//        System.out.println("answer(new int[] {1, 2, 3, 2, 1}) = " + answer(new int[] {1, 2, 3, 2, 1, 0}));
    }
}
