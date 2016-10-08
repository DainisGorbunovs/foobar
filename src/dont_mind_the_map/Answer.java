/*
    Don't mind the map
    ==================

    After the trauma of Dr. Boolean's lab, the rabbits are eager to get back to their normal lives in a well-connected
    community, where they can visit each other frequently. Fortunately, the rabbits learned something about engineering
    as part of their escape from the lab. To get around their new warren fast, they built an elaborate subway system to
    connect their holes. Each station has the same number of outgoing subway lines (outgoing tracks), which are
    numbered.

    Unfortunately, sections of warrens are very similar, so they can't tell where they are in the subway system. Their
    stations have system maps, but not an indicator showing which station the map is in. Needless to say, rabbits get
    lost in the subway system often. The rabbits adopted an interesting custom to account for this: Whenever they are
    lost, they take the subway lines in a particular order, and end up at a known station.

    For example, say there were three stations A, B, and C, with two outgoing directions, and the stations were
    connected as follows

    Line 1 from A, goes to B. Line 2 from A goes to C.
    Line 1 from B, goes to A. Line 2 from B goes to C.
    Line 1 from C, goes to B. Line 2 from C goes to A.

    Now, suppose you are lost at one of the stations A, B, or C. Independent of where you are,
    if you take line 2,and then line 1, you always end up at station B. Having a path that takes everyone to the
    same place is called a meeting path.
    We are interested in finding a meeting path which consists of a fixed set of instructions like,
    'take line 1, then line 2,' etc. It is possible that you might visit a station multiple times. It is also possible
    that such a path might not exist. However, subway stations periodically close for maintenance. If a station is
    closed, then the paths that would normally go to that station, go to the next station in the same direction.
    As a special case, if the track still goes to the closed station after that rule, then it comes back to the
    originating station. Closing a station might allow for a meeting path where previously none existed.
    That is, if you have
    A -> B -> C
    and station B closes, then you'll have
    A -> C
    Alternately, if it was
    A -> B -> B
    then closing station B yields
    A -> A

    Write a function answer(subway) that returns one of:

    -1 (minus one): If there is a meeting path without closing a station
    The least index of the station to close: that allows for a meeting path or
    -2 (minus two): If even with closing 1 station, there is no meeting path.
    subway will be a list of lists of integers such that subway[station][direction] = destination_station.

    That is, the subway stations are numbered 0, 1, 2, and so on. The k^th element of subway (counting from 0) will
    give the list of stations directly reachable from station k.

    The outgoing lines are numbered 0, 1, 2... The r^th element of the list for station k, gives the number of the
    station directly reachable by taking line r from station k.

    Each element of subway will have the same number of elements (so, each station has the same number of outgoing
    lines), which will be between 1 and 5.

    There will be at least 1 and no more than 50 stations.

    For example, if
    subway = [
        [2, 1], // 0 station
        [2, 0], // 1 station
        [3, 1], // 2 station
        [1, 0]  // 3 station
    ]
    Then one could take the path [1, 0]. That is, from the starting station, take the second direction, then the
    first. If the first direction was the red line, and the second was the green line, you could phrase this as:
    if you are lost, take the green line for 1 stop, then the red line for 1 stop.
    So, consider following the directions starting at each station.
    0 -> 1 -> 2.
    1 -> 0 -> 2.
    2 -> 1 -> 2.
    3 -> 0 -> 2.
    So, no matter the starting station, the path leads to station 2. Thus, for this subway, answer should return -1.

    If
    subway = [[1], [0]]
    then no matter what path you take, you will always be at a different station than if you started elsewhere.
    If station 0 closed, that would leave you with
    subway = [[0]]
    So, in this case, answer would return 0 because there is no meeting path until you close station 0.

    To illustrate closing stations,
    subway = [[1,1],[2,2],[0,2]]
    If station 2 is closed, then
    station 1 direction 0 will follow station 2 direction 0 to station 0, which will then be its new destination.
    station 1 direction 1 will follow station 2 direction 1 to station 2, but that station is closed, so it will
    get routed back to station 1, which will be its new destination.  This yields
    subway = [[1,1],[0,1]]
 */

package dont_mind_the_map;

import java.util.*;

public class Answer {
    /*
      The following is not needed as not using a bruteforce algorithm.
      1. Go through the stations
      2. Check backwards, has to access all other stations
        0: from 1 (from 2 or 0) or 3 (from 1 or 0)
     */
    @Deprecated
    private static boolean isMeetingPath(int[][] subway, int[] path) {
        return false;
    }

    /*
      Return a list of stations which lead towards the toStation
     */
    private static LinkedHashSet<Integer> findFromStations(int[][] subway, int toStation) {
        LinkedHashSet<Integer> fromStations = new LinkedHashSet<Integer>();
//        List fromStations = new LinkedList<Integer>();
        for (int station = 0; station < subway.length; ++station) {
            for (int direction : subway[station]) {
                if (direction == toStation) {
                    fromStations.add(station);
                }
            }
        }
        return fromStations;
    }

    private static Set<Integer> travelPathRecursively(int[][] subway, Set<Integer> seen, int station) {
//        if (seen.size() == subway.length) {
//            return seen;
//        }
//        Set<Integer> result = seen;
//
//        for (int direction : subway[station]) {
//            result = travelPathRecursively(subway, seen, direction);
//            if (result.size() == subway.length) {
//                return result;
//            }
//        }
        // Start with an empty set,
        // Start from station 0
        //
        if (seen.contains(station)) {
            return seen;
        }

        seen.add(station);
        LinkedHashSet<Integer> fromStations = findFromStations(subway, station);
        Set<Integer> result = seen;
        for (Integer fromStation : fromStations) {
            result = travelPathRecursively(subway, seen, fromStation);
            if (result.size() == subway.length) {
                return result;
            }
        }

        return result;
    }

    private static boolean hasMeetingPath(int[][] subway) {
//        for (int station = 0; station < subway.length; ++station) {
//            Set fromStations = new HashSet<Integer>();
//            int[] directions = subway[station];
//
//            for (int direction : directions) {
//
//            }
//            findFromStations(subway, station);
//
//            if (fromStations.size() == subway.length) {
//                return true;
//            }
//        }
//        return false;
        return travelPathRecursively(subway, new HashSet<Integer>(), 0).size() == subway.length;
    }

    private static int[][] subwayWithClosedStation(int[][] subway, int station) {
        return subway;
    }

    public static int answer(int[][] subway) {
        // If there is a meeting path without closing a station
        if (hasMeetingPath(subway)) {
            return -1;
        }

        // If closing a station, there is a meeting path
//        for (int station = 0; station < subway.length; ++station) {
//            // Close this station
//            if (hasMeetingPath(subwayWithClosedStation(subway, station))) {
//                return station;
//            }
//        }

        // If even with closing 1 station, there is no meeting path.
        return -2;
    }

    public static void main(String[] args) {
        int[][] subway = new int[][] {
                {1, 2}, // 0 station
                {2, 2}, // 1 station
                {3, 3}, // 2 station
                {4, 4}, // 3 station
                {5, 5}, // 4 station
                {6, 6}, // 5 station
                {7, 7}, // 6 station
                {8, 8}, // 7 station
                {9, 9}, // 8 station
                {10, 10}, // 9 station
                {11, 11}, // 10 station
                {12, 12}, // 11 station
                {13, 13}, // 12 station
                {14, 14}, // 13 station
                {15, 15}, // 14 station
                {16, 16}, // 15 station
                {17, 17}, // 16 station
                {18, 18}, // 17 station
                {19, 19}, // 18 station
                {20, 20}, // 19 station
                {21, 21}, // 20 station
                {22, 22}, // 21 station
                {23, 23}, // 22 station
                {24, 24}, // 23 station
                {25, 25}, // 24 station
                {0, 0}    // 25 station
        };

        for (int station = 0; station < subway.length; ++station) {
            LinkedHashSet<Integer> from = findFromStations(subway, station);
            System.out.print(station + " <- ");
            for (Integer kappa : from) {
                System.out.print(kappa + ", ");
            }
            System.out.println();
        }
        // 1 and then 0
        // 0: 2
        // 1: 2

        // BFS
        // 2 is final destination
        // get here from 0 or 1
        // total: 0, 1

        // 0 is new final destination
        // get here from 1 or 3
        // 1 is new final destination
        // get here from 0 or 2 or 3
        // total 0, 1, 2, 3


        /*
            0 <- 1, 3,
            1 <- 0, 2, 3,
            2 <- 0, 1,
            3 <- 2,
         */
        /*
            0 <- 1, 3,
            1 <- 0, 2, 3,
            2 <- 0(1, 3), 1(0,2,3),
            3 <- 2(0(1, 3), 1(0,2,3)),
         */

        /*
            0 <- 25,
            1 <- 0,
            2 <- 0, 1,
            3 <- 2,
            4 <- 3,
            5 <- 4,
            6 <- 5,
            7 <- 6,
            8 <- 7,
            9 <- 8,
            10 <- 9,
            11 <- 10,
            12 <- 11,
            13 <- 12,
            14 <- 13,
            15 <- 14,
            16 <- 15,
            17 <- 16,
            18 <- 17,
            19 <- 18,
            20 <- 19,
            21 <- 20,
            22 <- 21,
            23 <- 22,
            24 <- 23,
            25 <- 24,


            0 <- 25,
            1 <- 0,
            2 <- 0, 1,
            3 <- 2,
            4 <- 3,
            5 <- 4,
            6 <- 5,
            7 <- 6,
            8 <- 7,
            9 <- 8,
            10 <- 9,
            11 <- 10,
            12 <- 11,
            13 <- 12,
            14 <- 13,
            15 <- 14,
            16 <- 15,
            17 <- 16,
            18 <- 17,
            19 <- 18,
            20 <- 19,
            21 <- 20,
            22 <- 21,
            23 <- 22,
            24 <- 23,
            25 <- 24 (23(22(21(20(19(18(17(16(15(14(13(12(11(10(9(8(7(6(5(4(3(2(0(25), 1(0))))))))))))))))))))))),
         */
        System.out.println("-1 ?= answer(subway) = " + answer(subway));
    }
}