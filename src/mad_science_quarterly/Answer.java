/*
    Mad Science Quarterly
    =====================

    The deadline for submitting papers to the Mad Science Quarterly is approaching. Professor Boolean's topic:
    On the Rate of Growth of Zombie Rabbits.

    In the lab, Boolean's minions recorded the net growth of the number of zombits for each day, which is the number
    of births minus the number of deaths (yes, zombits do die). He realized that if these figures were to be added up
    over time, it would seem like the zombits multiplied very quickly. Then everyone shall be convinced of his mad
    genius! He would proudly display these figures in his paper. Since some of the figures may be negative, and larger
    numbers are more convincing, Professor Boolean will choose which figures to show so that the sum is maximized.
    However, he must show a sequence of consecutive figures without omitting the unfavorable ones - Professor Boolean
    also needs to be scientific, you see. Unfortunately, the Mad Science Quarterly limits how much data can be shown -
    it is, after all, Mad. This means the mad doctor can display no more than a certain number of figures.

    Write a function answer(L, k) which returns the maximum sum Professor Boolean can obtain by choosing some
    consecutive figures from his data. L will be a list of integers representing his data, the daily net growth of
    the number of zombits over a period of time. k will be the maximum number of figures he can display. Each element
    of L will have absolute value no greater than 100. L will contain at least 2 and no more than 7000 elements, and
    at least one element will be positive. k will be an integer, at least 3 and no greater than the length of L.
 */

package mad_science_quarterly;

public class Answer {
    // L - list of integers for the daily net growth of the number of zombits over a period of time
    // k - the maximum number of figures he can display
    public static int answer(int[] L, int k) {
        // Initially maximum consecutive sum is 0 as no elements checked
        int maxConsecutiveSum = 0;

        for (int figureCount = 1; figureCount <= k; ++figureCount) {
            // Keep track of current sum to see if it is higher than max
            int windowSum = 0;

            // Calculate sum for a window of these figures
            for (int windowIndex = 0; windowIndex < figureCount; ++windowIndex) {
                windowSum += L[windowIndex];
            }

            // This window can be higher than the maximum consecutive sum
            if (windowSum > maxConsecutiveSum) {
                maxConsecutiveSum = windowSum;
            }

            // Start sliding this window of figures to find the next window sum
            for (int fromIndex = 0; fromIndex < L.length-figureCount; ++fromIndex) {
                // Remove the first element of the previous window
                windowSum -= L[fromIndex];

                // and append the last element of current window
                windowSum += L[fromIndex+figureCount];
                
                if (windowSum > maxConsecutiveSum) {
                    maxConsecutiveSum = windowSum;
                }
            }

        }

        return maxConsecutiveSum;
    }

    public static void main (String[] args) {
        int[] L = new int[]{-100, 95, 86, 47};
        int k = 3;
        System.out.println(228 + " ?= " + answer(L, k));

        L = new int[]{40, 91, -68, -36, 24, -67, -32, -23, -33, -52};
        k = 7;
        System.out.println(131 + " ?= " + answer(L, k));
    }
}
