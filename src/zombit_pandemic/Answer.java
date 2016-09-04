/*
    Zombit pandemic
    ===============

    The nefarious Professor Boolean is up to his usual tricks. This time he is using social engineering to achieve his
    twisted goal of infecting all the rabbits and turning them into zombits! Having studied rabbits at length, he found
    that rabbits have a strange quirk: when placed in a group, each rabbit nudges exactly one rabbit other than itself.
    This other rabbit is chosen with uniform probability. We consider two rabbits to have socialized if either or both
    of them nudged the other. (Thus many rabbits could have nudged the same rabbit, and two rabbits may have socialized
    twice.) We consider two rabbits A and B to belong to the same rabbit warren if they have socialized, or if A has
    socialized with a rabbit belonging to the same warren as B.

    For example, suppose there were 7 rabbits in Professor Boolean's nefarious lab. We denote each rabbit using
    a number. The nudges may be as follows:

    1 nudges 2
    2 nudges 1
    3 nudges 7
    4 nudges 5
    5 nudges 1
    6 nudges 5
    7 nudges 3

    This results in the rabbit warrens {1, 2, 4, 5, 6} and {3, 7}.

    Professor Boolean realized that by infecting one rabbit, eventually it would infect the rest of the rabbits in the
    same warren! Unfortunately, due to budget constraints he can only infect one rabbit, thus infecting only the
    rabbits in one warren. He ponders, what is the expected maximum number of rabbits he could infect?

    Write a function answer(n), which returns the expected maximum number of rabbits Professor Boolean can infect
    given n, the number of rabbits. n will be an integer between 2 and 50 inclusive. Give the answer as a string
    representing a fraction in lowest terms, in the form "numerator/denominator". Note that the numbers may be large.


    For example, if there were 4 rabbits, he could infect a maximum of 2 (when they pair up) or 4 (when they're all
    socialized), but the expected value is 106 / 27. Therefore the answer would be "106/27".
 */
package zombit_pandemic;

import java.math.BigInteger;
import java.util.*;

public class Answer {
    private static Map<Integer, Set<ArrayList<Integer>>> partitionMemo = new HashMap<Integer, Set<ArrayList<Integer>>>();
    private static Set<ArrayList<Integer>> getPartitions(int n) {
        // Check if partitions are memoized for n
        if (partitionMemo.containsKey(n)) {
            return partitionMemo.get(n);
        }

        Set<ArrayList<Integer>> partitions = new HashSet<ArrayList<Integer>>();

        // Find partitions for n
        partition(n, n, partitions, new ArrayList<Integer>());

        // Memoize the partitions for the n
        partitionMemo.put(n, partitions);
        return partitions;
    }

    // Recursive integer partition algorithm
    // Reference: http://stackoverflow.com/questions/23068368/recursive-method-for-the-integer-partition-algorithm
    private static void partition(int n, int max, Set<ArrayList<Integer>> partitions, ArrayList<Integer> partition) {
        if (n == 0) {
            if (!partition.contains(1)) {
                partitions.add(new ArrayList<Integer>(partition));
            }
            return;
        }

        for (int i = Math.min(max, n); i >= 1; --i) {
            partition.add(i);
            partition(n - i, i, partitions, partition);
            partition.remove(partition.size() - 1);
        }
    }

    private static Map<Integer, BigInteger> arrangementMemo = new HashMap<Integer, BigInteger>();
    // Number of n node pseudoforests with exactly one connected component
    // Reference:
    // http://math.stackexchange.com/questions/1090498/how-to-calculate-the-expected-maximum-tree-size-in-a-pseudoforest
    private static BigInteger getNumberOfArrangements(int n) {
        // Check if memoized
        if (arrangementMemo.containsKey(n)) {
            return arrangementMemo.get(n);
        }

        BigInteger arrangements = BigInteger.ZERO;
        for (int nodes = 1; nodes < n; ++nodes) {
            arrangements = arrangements.add(
                    getBinomialCoefficient(n, nodes)
                    .multiply(
                            BigInteger.valueOf(n-nodes).pow(n-nodes)
                    )
                    .multiply(
                            BigInteger.valueOf(nodes).pow(nodes)
                    )
            );
        }

        arrangements = arrangements.divide(BigInteger.valueOf(n));

        // Memoize the number of arrangements for n
        arrangementMemo.put(n, arrangements);
        return arrangements;
    }

    // Number of ways to split n rabbits into partition p
    // Reference:
    // http://math.stackexchange.com/questions/1090498/how-to-calculate-the-expected-maximum-tree-size-in-a-pseudoforest
    private static BigInteger getNumberOfSplits(int n, ArrayList<Integer> partition) {
        Map<Integer, Integer> multiplicities = new HashMap<Integer, Integer>();
        BigInteger numerator = BigInteger.ONE;
        int sum = 0;

        // The sizes of the partition lets find the number of ways of
        // splitting n labelled nodes into connected components
        for (int number : partition) {
            numerator = numerator.multiply(
                    getBinomialCoefficient(n-sum, number)
            );
            sum += number;

            if (!multiplicities.containsKey(number)) {
                multiplicities.put(number, 0);
            }

            multiplicities.put(number, multiplicities.get(number) + 1);
        }

        BigInteger denominator = BigInteger.ONE;
        for (Integer key : multiplicities.keySet()) {
            denominator = denominator
                    .multiply(
                            factorial(multiplicities.get(key))
                    );
        }

        return numerator.divide(denominator);
    }

    static Map<List<Integer>, BigInteger> coefficientMemo = new HashMap<List<Integer>, BigInteger>();
    private static BigInteger getBinomialCoefficient(int n, int k) {
        // http://stackoverflow.com/questions/11032781/fastest-way-to-generate-binomial-coefficients
        List<Integer> key = Arrays.asList(n, k);

        // Check edge cases
        if (n == k || k == 0) return BigInteger.ONE;
        if (k > n) return BigInteger.ZERO;
        if (k > (n - k)) k = n - k;
        if (k == 1) return BigInteger.valueOf(n);

        // Check if memoized
        if (coefficientMemo.containsKey(key)) {
            return coefficientMemo.get(key);
        }

        // Calculate the coefficient
        BigInteger coefficient = BigInteger.ONE;
        for (int i = 1; i <= k; ++i) {
            coefficient = coefficient
                    .multiply(BigInteger.valueOf(n - (k - i)))
                    .divide(BigInteger.valueOf(i));

        }

        // Memoize the calculated coefficient
        coefficientMemo.put(key, coefficient);

        return coefficient;
    }

    static Map<Integer, BigInteger> factorialMemo = new HashMap<Integer, BigInteger>();
    private static BigInteger factorial(int n) {
        // Check if memoized
        if (factorialMemo.containsKey(n)) {
            return factorialMemo.get(n);
        }

        // Base case
        if (n == 1) {
            return BigInteger.ONE;
        }

        // Step case
        BigInteger factorial = BigInteger.valueOf(n)
                .multiply(factorial(n - 1));

        // Memoize the factoriayl
        factorialMemo.put(n, factorial);

        return factorial;
    }

    static Map<Integer, BigInteger> numeratorMemo = new HashMap<Integer, BigInteger>();
    // Reference:
    // http://math.stackexchange.com/questions/1090498/how-to-calculate-the-expected-maximum-tree-size-in-a-pseudoforest
    private static BigInteger getNumerator(int n) {
        if (numeratorMemo.containsKey(n)) {
            return numeratorMemo.get(n);
        }

        BigInteger numerator = BigInteger.ZERO;

        Set<ArrayList<Integer>> partitions = getPartitions(n);
        for (ArrayList<Integer> partition: partitions) {
            BigInteger numProduct = BigInteger.ONE;
            for (Integer number : partition) {
                numProduct = numProduct.multiply(getNumberOfArrangements(number));
            }
            numerator = numerator.add(
                    numProduct
                    .multiply(
                            BigInteger.valueOf(Collections.max(partition))
                    )
                    .multiply(
                            getNumberOfSplits(n, partition)
                    )
            );
        }

        numeratorMemo.put(n, numerator);
        return numerator;
    }

    private static BigInteger getDenominator(int n) {
        return BigInteger.valueOf(n-1).pow(n);
    }

    public static String answer(int n) {
        BigInteger numerator = getNumerator(n);
        BigInteger denominator = getDenominator(n);
        BigInteger gcd = numerator.gcd(denominator);

        return numerator.divide(gcd) + "/" + denominator.divide(gcd);
    }

    public static void main(String[] args) {
        System.out.println("106/27 ?= " + answer(4));
        System.out.println("2/1 ?= " + answer(2));
    }
}