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
    private static final int start = 0;
    private static final int end   = 1;

    public static int answer(int[] heights) {
        // Water is trapped between number A and C, where there are smaller numbers B < A and B < C
        // Runs off the sides, so first and last numbers do not contain water
        int[] peakIndex = new int[] {0, 0};
        int droplets = 0;
        int peakType = start;

        System.out.println("Heights length: " + heights.length);

        for (int index = 1; index < heights.length; index++) {
            if (peakType == start)
                System.out.print("[start] ");
            else
                System.out.print("[end] ");

            System.out.println("Testing height: " + heights[index] + ", index: " + index);
            if ((heights[index] > heights[peakIndex[peakType]] || peakIndex[start] == peakIndex[end])
                    && index != heights.length-1) {
                peakIndex[peakType] = index;
            } else if (heights[index] < heights[peakIndex[peakType]] || index == heights.length-1) {
                if (index == heights.length-1) {
                    if (heights[heights.length-1] > heights[index-1]) {
                        peakIndex[peakType] = heights.length - 1;
                        System.out.println("Peak index set to last, height: " + heights[peakIndex[peakType]]);
                    }
                }
                if (peakType == start) {
                    System.out.print("start ");
                } else {
                    System.out.print("end ");
                }
                System.out.println("peakindex value = " + heights[peakIndex[peakType]]);

                if (peakType == end) {
                    int minHeight = Math.min(heights[peakIndex[start]], heights[peakIndex[end]]);
                    for (int dropIndex = peakIndex[start] + 1; dropIndex < peakIndex[end]; dropIndex++) {
                        droplets += minHeight - heights[dropIndex];
                        System.out.println("Added droplets += " + (minHeight - heights[dropIndex]));
                    }
                    peakIndex[start] = peakIndex[end];
                    System.out.println("Switched start peak's height to: " + heights[peakIndex[start]]);
                }

                // switch peak type
                if (peakType == start)
                    peakType ^= 1;

            }
            System.out.println();
        }
        return droplets;
    }
    
    public static void main(String[] args) {
        System.out.println("answer(new int[] {1, 4, 2, 5, 1, 2, 3}) = " + answer(new int[] {1, 4, 2, 5, 1, 2, 3}));
//        System.out.println("answer(new int[] {1, 2, 3, 2, 1}) = " + answer(new int[] {1, 2, 3, 2, 1, 0}));
    }
}
