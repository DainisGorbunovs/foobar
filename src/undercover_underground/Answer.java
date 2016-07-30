/*
    Undercover underground
    ======================

    As you help the rabbits establish more and more resistance groups to fight against Professor Boolean, you need
    a way to pass messages back and forth.

    Luckily there are abandoned tunnels between the warrens of the rabbits, and you need to find the best way to use
    them. In some cases, Beta Rabbit wants a high level of interconnectedness, especially when the groups show their
    loyalty and worthiness. In other scenarios the groups should be less intertwined, in case any are compromised by
    enemy agents or zombits.

    Every warren must be connected to every other warren somehow, and no two warrens should ever have more than one
    tunnel between them. Your assignment: count the number of ways to connect the resistance warrens.

    For example, with 3 warrens (denoted A, B, C) and 2 tunnels, there are three distinct ways to connect them:

    A-B-C
    A-C-B
    C-A-B

    With 4 warrens and 6 tunnels, the only way to connect them is to connect each warren to every other warren.
    A - B
    | X |
    C - D

    Write a function answer(N, K) which returns the number of ways to connect N distinctly labelled warrens with
    exactly K tunnels, so that there is a path between any two warrens.

    The return value must be a string representation of the total number of ways to do so, in base 10.
    N will be at least 2 and at most 20.
    K will be at least one less than N and at most (N * (N - 1)) / 2
 */

package undercover_underground;

import java.math.BigInteger;
import java.util.*;

public class Answer {
    static Map<List<Integer>, BigInteger> coefficientMemo = new HashMap<List<Integer>, BigInteger>();
    static Map<List<Integer>, BigInteger> countMemo = new HashMap<List<Integer>, BigInteger>();

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

    private static BigInteger getNumberOfWays(int n, int k) {
        List<Integer> key = Arrays.asList(n, k);

        // Check if memoized count answer
        if (countMemo.containsKey(key)) {
            return countMemo.get(key);
        }

        // Cayley's formula
        if (k == n - 1) {
            return BigInteger.valueOf((int) Math.pow(n, n-2));
        }

        // Calculate the number of ways
        // http://math.stackexchange.com/questions/689526/how-many-connected-graphs-over-v-vertices-and-e-edges
        BigInteger count = getBinomialCoefficient(n*(n-1)/ 2, k);

        for (int m = 0; m <= n-2; ++m) {
            BigInteger tempCount = BigInteger.ZERO;

            for (int p = Math.max(0, k - m*(m+1)/2); p <= k - m; ++p) {
                tempCount = tempCount
                        .add(getBinomialCoefficient((n-1-m) * (n-2-m) / 2, p)
                            .multiply(getNumberOfWays(m+1, k-p))
                        );
            }

            count = count
                    .subtract(getBinomialCoefficient(n-1, m)
                            .multiply(tempCount)
                    );
        }

        countMemo.put(key, count);

        return count;
    }
    public static String answer(int N, int K) {
        return getNumberOfWays(N, K).toString();
    }

    public static void main(String[] args) {
        // N is at least 2 and at most 20
        System.out.println("Choosing random N (warrens) and K (tunnels)");
        int N = new Random().nextInt(19) + 2;
        int K = new Random().nextInt(N*(N-1)/2 - (N-1) + 1) + (N-1);
        System.out.printf("N = %d (range:[%d-%d])\n", N, 2, 20);
        System.out.printf("K = %d (range:[%d-%d] specific for N = %d)\n", K, N-1, N*(N-1)/2, N);
        System.out.println("Answer: " + answer(N, K) + ".");
    }
}
