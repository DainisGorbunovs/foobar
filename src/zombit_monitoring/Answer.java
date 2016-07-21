/*
    Zombit monitoring
    =================

    The first successfully created zombit specimen, Dolly the Zombit, needs constant monitoring, and Professor
    Boolean has tasked the minions with it. Any minion who monitors the zombit records the start and end times of
    their shifts. However, those minions, they are a bit disorganized: there may be times when multiple minions are
    monitoring the zombit, and times when there are none!

    That's fine, Professor Boolean thinks, one can always hire more minions... Besides, Professor Boolean can at least
    figure out the total amount of time that Dolly the Zombit was monitored. He has entrusted you, another one of his
    trusty minions, to do just that. Are you up to the task?

    Write a function answer(intervals) that takes a list of pairs [start, end] and returns the total amount of time
    that Dolly the Zombit was monitored by at least one minion. Each [start, end] pair represents the times when a
    minion started and finished monitoring the zombit. All values will be positive integers no greater than 2^30 - 1.
    You will always have end > start for each interval.
 */

package zombit_monitoring;

import java.util.Arrays;
import java.util.Comparator;

public class Answer {
    private static final int start = 0;
    private static final int end   = 1;

    private static int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] first, int[] second) {
                return first[start] - second[end];
            }
        });

        int lastIndex = 0;
        for (int index = 0; index < intervals.length; index++) {
            if (lastIndex != 0 && intervals[lastIndex-1][start] <= intervals[lastIndex][end]) {
                intervals[lastIndex-1][end]   = Math.max(intervals[lastIndex-1][end], intervals[index][end]);
                intervals[lastIndex-1][start] = Math.min(intervals[lastIndex-1][start], intervals[index][start]);
                lastIndex--;
            } else {
                intervals[lastIndex] = intervals[index];
            }

            lastIndex++;
        }

        intervals = Arrays.copyOfRange(intervals, 0, lastIndex);

        return intervals;
    }

    public static int answer(int[][] intervals) {
        intervals = mergeIntervals(intervals);

        for (int[] column : intervals) {
            for (int row : column) {
                System.out.print(row + ", ");
            }
            System.out.println();
        }

        return 0;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][] {
                {10, 14},
                { 4, 18},
                {19, 20},
                {19, 20},
                {13, 20}
        };

        answer(intervals);
    }
}