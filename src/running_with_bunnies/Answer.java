package running_with_bunnies;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Answer {
    /**
     * Gets the shortest time from A to B via C (route: A-C-B).
     * @param times Matrix of times
     * @param from From position
     * @param to To position
     * @return Shortest time in from-catalyst-to route
     */
    private static int getShorterFromToTime(int[][] times, int from, int to) {
        int leastTime = times[from][to];

        for (int otherTo = 0; otherTo < times[0].length; ++otherTo) {
            if (otherTo == to)
                continue;

            int otherTime = times[from][otherTo];
            otherTime += times[otherTo][to];

            if (otherTime < leastTime)
                leastTime = otherTime;
        }

        return leastTime;
    }

    /**
     * Makes times matrix simpler by accounting alternative routes
     * Complexity O(n^2).
     * @param times Matrix of times
     * @return Matrix of simplified times
     */
    private static int[][] getShorterTimes(int[][] times) {
        for (int from = 0; from < times.length; ++from) {
            for (int to = 0; to < times[0].length; ++to) {
                times[from][to] = getShorterFromToTime(times, from, to);
            }
        }
        return times;
    }

    /**
     * Checks all possible path combinations to find the shortest times
     * from A to B via any other route.
     * Complexity O(n^3). As max n is only 7, it is not too slow.
     *
     * E.g., running times and paths from S to E.
     * S-E can be 99 time units
     * S-E: S-0-E 98 time units
     * S-E: S-0-1-E 30 time units
     * S-E: S-0-1-2-E 15 time units
     * S-E: S-0-1-2-3-E 5 time units
     * S-E: S-0-1-2-3-4-E 0 time units

     * @param times Matrix of times
     * @return Matrix of shortest times
     */
    private static int[][] getShortestTimes(int[][] times) {
        for (int bunny = 0; bunny < times.length - 2; ++bunny) {
            times = getShorterTimes(times);
        }

        return times;
    }

    /**
     * Checks if there is an infinite cycle, which gives player infinite time
     * @param times Matrix of times
     * @return true if has infinite cycle
     */
    private static boolean hasInfiniteCycle(int[][] times) {
        for (int element = 0; element < times.length; ++element) {
            if (times[element][element] < 0) {
                return true;
            }
        }

        return false;
    }

    //

    /**
     * Find the best bunnies possible to be saved.
     *
     * Permutation algorithm adapted from rjberg7's code
     * http://stackoverflow.com/questions/20906214/permutation-algorithm-for-array-of-integers-in-java
     *
     * @param selectedBunnyCount Number of bunnies to save
     * @param bunnies Possible bunnies in the map
     * @param times Matrix of times
     * @param timeLeft Time left
     * @return Best bunnies possible to save
     */
    private static int[] getBunnyPath(int selectedBunnyCount, int[] bunnies, int[][] times, int timeLeft) {
        int allBunnyCount = bunnies.length;

        for (int i = 0; i < Math.pow(allBunnyCount, selectedBunnyCount); i++) {
            int[] permutation = new int[selectedBunnyCount];
            Set<Integer> values = new HashSet<>();
            boolean hasDupes = false;
            for (int j = 0; j < selectedBunnyCount; j++) {
                // Pick the appropriate item from the item pool given j and i
                int itemPoolIndex = (int) Math.floor((double) (i % (int) Math.pow(allBunnyCount, j + 1)) / (int) Math.pow(allBunnyCount, j));
                permutation[selectedBunnyCount - 1 - j] = bunnies[itemPoolIndex];
                if (values.contains(bunnies[itemPoolIndex])) {
                    hasDupes = true;
                    break;
                }
                values.add(bunnies[itemPoolIndex]);
            }

            if (hasDupes)
                continue;

            if (isSolvable(permutation, selectedBunnyCount, times, timeLeft)) {
                return permutation;
            }
        }

        return new int[]{};
    }

    /**
     * Gets the saved bunnies. They are sorted in ascending order.
     * @param bunnies All possible bunnies
     * @param bunnyCount Number of saved bunnies
     * @return Saved bunnies
     */
    private static int[] getSavedBunnies(int[] bunnies, int bunnyCount) {
        int[] savedBunnies = Arrays.copyOfRange(bunnies, 0, bunnyCount);

        Arrays.sort(savedBunnies);
        return savedBunnies;
    }

    /**
     * Checks if with the current bunny order the map can be solved in time.
     * @param bunnyOrder The order of bunnies
     * @param bunnyCount Number of saved bunnies
     * @param times Matrix of times
     * @param timeLeft Time left
     * @return
     */
    private static boolean isSolvable(int[] bunnyOrder, int bunnyCount, int[][] times, int timeLeft) {
        // S - 0/1/2/3/4, find time to first bunny
        // System.out.printf("Time left: %d, bunny count: %d\n", timeLeft, bunnyCount);
        // System.out.printf("S-%d: %d, left: %d\n", bunnyOrder[0], times[0][bunnyOrder[0] + 1],
        //        timeLeft-times[0][bunnyOrder[0] + 1]);

        timeLeft -= times[0][bunnyOrder[0] + 1];

        // 0 - 1, 3 - 2, and other route times subtracted
        for (int index = 0; index < bunnyCount - 1; ++index) {
            // System.out.printf("%d-%d: %d, left: %d\n", bunnyOrder[index], bunnyOrder[index+1],
            //        times[bunnyOrder[index]][bunnyOrder[index+1]], timeLeft - times[bunnyOrder[index] +
            //        1][bunnyOrder[index+1] + 1]);
            timeLeft -= times[bunnyOrder[index] + 1][bunnyOrder[index+1] + 1];
        }

        // [0, 1, 2, 3] has 4 bunnies, find time to exit
        // System.out.printf("%d-E: %d, left: %d\n", bunnyOrder[bunnyCount - 1],
        //         times[bunnyOrder[bunnyCount - 1] + 1][times.length - 1],
        //         timeLeft - times[bunnyOrder[bunnyCount - 1] + 1][times.length - 1]);
        timeLeft -= times[bunnyOrder[bunnyCount - 1] + 1][times.length - 1];

        return timeLeft >= 0;
    }

    public static int[] answer(int[][] times, int time_limit) {
        // Number of saved bunnies
        int bunnyCount = times.length - 2;

        // Have a list of saved bunnies
        int[] savedBunnies = new int[bunnyCount];

        // Fill saved bunnies list full
        for (int bunny = 0; bunny < bunnyCount; ++bunny) {
            savedBunnies[bunny] = bunny;
        }

        // Simplify the times matrix by finding alternative routes
        times = getShortestTimes(times);

        // If has infinite cycle, return all bunnies
        if (hasInfiniteCycle(times)) {
            return savedBunnies;
        }

        // Try to save as many bunnies as possible, start from 5
        // S-MIDDLE-E
        for (; bunnyCount > 0; --bunnyCount) {
            // Loop through all permutations:
            // if solvable, break
            int[] bunnyPath = getBunnyPath(bunnyCount, savedBunnies, times, time_limit);


            if (bunnyPath.length != 0) {
                savedBunnies = bunnyPath;
                break;
            }
        }

        // Save no bunnies if time does not allow
        return getSavedBunnies(savedBunnies, bunnyCount);
    }
}