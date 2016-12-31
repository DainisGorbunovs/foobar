package running_with_bunnies;

import java.util.LinkedList;
import java.util.List;

public class Answer {
    /**
     * Gets the number of time slices it costs to move from a location to another
     * @param times Matrix of times
     * @param from Position to move from
     * @param to Position to move to
     * @return Cost of time slices to move
     */
    static int getMoveTimeCost(int[][] times, int from, int to) {
        return times[from][to];
    }

    /**
     * Tells if exit can be reached directly from the current position
     *
     * @param times Matrix of times
     * @param time_limit Time slices left
     * @param position 0-start, 1-0th bunny, 2-1st bunny.. last-exit
     * @return true, if can reach exit directly
     */
    static boolean isExitDirectlyReachable(int[][] times, int time_limit, int position) {
        return time_limit >= getMoveTimeCost(times, position, times.length-1);
    }

    /**
     * Prints times matrix for debugging purposes
     * @param times Matrix of times
     */
    static void printTimes(int[][] times) {
        System.out.println("[");

        for (int row = 0; row < times.length; ++row) {
            System.out.print("    ");

            for (int column = 0; column < times[0].length; ++column) {
                System.out.print(times[row][column]);

                if (column != times[0].length - 1 || row != times.length - 1) {
                    System.out.print(",");
                }
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println("]");
    }

    /**
     * Turns list of bunnies into an array of bunnies
     * @param bunnies List of bunnies
     * @return Array of bunnies
     */
    static int[] getArrayOfSavedBunnies(List<Integer> bunnies) {
        int[] array = new int[bunnies.size()];
        int index = 0;
        for (Integer bunny : bunnies) {
            array[index++] = bunny;
        }
        return array;
    }

    /**
     * Gets the shortest time from A to B via C (route: A-C-B).
     * @param times Matrix of times
     * @param from From position
     * @param to To position
     * @return Shortest time in from-catalyst-to route
     */
    static int getShorterFromToTime(int[][] times, int from, int to) {
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
    static int[][] getShorterTimes(int[][] times) {
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
     * @param times Matrix of times
     * @return Matrix of shortest times
     */
    static int[][] getShortestTimes(int[][] times) {
        /*
            E.g.,
            S-E can be 99 time units
            S-E: S-0-E 98 time units
            S-E: S-0-1-E 30 time units
            S-E: S-0-1-2-E 15 time units
            S-E: S-0-1-2-3-E 5 time units
            S-E: S-0-1-2-3-4-E 0 time units
         */


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
    static boolean hasInfiniteCycle(int[][] times) {
        for (int element = 0; element < times.length; ++element) {
            if (times[element][element] < 0) {
                return true;
            }
        }

        return false;
    }

    public static int[] answer(int[][] times, int time_limit) {
        // Have a list of saved bunnies
        List<Integer> savedBunnies = new LinkedList<>();

        // Simplify the times matrix by finding alternative routes
        times = getShortestTimes(times);

        // If has infinite cycle, return all bunnies
        if (hasInfiniteCycle(times)) {
            for (int bunny = 0; bunny < times.length - 2; ++bunny) {
                savedBunnies.add(bunny);
            }
        }

        // Print times for debugging purposes
        printTimes(times);

        // Save no bunnies if time does not allow
        return getArrayOfSavedBunnies(savedBunnies);
    }
}