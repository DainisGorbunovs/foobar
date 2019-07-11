package disorderly_escape;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {
    static int gcd(int a, int b) {
        // Everything divides 0, base case
        if (a == 0 || b == 0 || a == b)
            return a;

        // a is greater
        if (a > b)
            return gcd(a - b, b);
        return gcd(a, b - a);
    }

    static BigInteger factorial(BigInteger n) {
        BigInteger result = BigInteger.ONE;

        while (!n.equals(BigInteger.ZERO)) {
            result = result.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }

        return result;
    }

    static BigInteger factorial(int n) {
        return factorial(BigInteger.valueOf(n));
    }

    static List<List<Integer>> cyclePartitions(int numbers, int startNumber) {
        List<List<Integer>> allPartitions = new LinkedList<>();
        List<Integer> initialPartition = new LinkedList<>();
        initialPartition.add(numbers);
        allPartitions.add(initialPartition);

        for (; startNumber < numbers / 2 + 1; ++startNumber) {
            // Partitions beginning with i
            List<List<Integer>> partitions = cyclePartitions(numbers - startNumber, startNumber);
            for (List<Integer> integers : partitions) {
                integers.add(0, startNumber);
            }
            allPartitions.addAll(partitions);
        }

        return allPartitions;
    }

    static List<List<Integer>> cyclePartitions(int numbers) {
        return cyclePartitions(numbers, 1);
    }

    static BigInteger cycleCount(List<Integer> c, int n) {
        BigInteger cycleCount = factorial(n);
        // Find occurrence count
        Map<Integer, Integer> val = new HashMap<>();
        for (int number : c) {
            if (!val.containsKey(number))
                val.put(number, 0);

            val.put(number, val.get(number) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : val.entrySet()) {
            BigInteger div = BigInteger.valueOf(entry.getKey())
                    .pow(entry.getValue())
                    .multiply(factorial(entry.getValue()));

            cycleCount = cycleCount.divide(div);
        }

        return cycleCount;
    }

    public static String solution(int width, int height, int states) {
        BigInteger grid = BigInteger.ZERO;
        BigInteger statesBig = BigInteger.valueOf(states);

        for (List<Integer> widthPartitions : cyclePartitions(width)) {
            for (List<Integer> heightPartitions : cyclePartitions(height)) {
                BigInteger m = cycleCount(widthPartitions, width).multiply(cycleCount(heightPartitions, height));

                int powInt = 0;
                for (Integer j : heightPartitions) {
                    for (Integer i : widthPartitions) {
                        powInt += gcd(i, j);
                    }
                }

                BigInteger val = statesBig.pow(powInt).multiply(m);
                grid = grid.add(val);
            }
        }

        grid = grid.divide(factorial(width)).divide(factorial(height));
        return grid.toString();
    }

    public static void main(String[] args) {
        // System.out.println(7 + " - truth, our solution: " + Solution.solution(2, 2, 2));
        System.out.println(7880456 + " - truth, our solution: " + Solution.solution(4, 4, 4));
        // System.out.println(430 + " - truth, our solution: " + Solution.solution(2, 3, 4));
    }
}
