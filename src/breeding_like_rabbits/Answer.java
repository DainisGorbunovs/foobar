/*
    Breeding like rabbits
    =====================

    As usual, the zombie rabbits (zombits) are breeding... like rabbits! But instead of following the Fibonacci
    sequence like all good rabbits do, the zombit population changes according to this bizarre formula,
    where R(n) is the number of zombits at time n:

    R(0) = 1
    R(1) = 1
    R(2) = 2
    R(2n) = R(n) + R(n + 1) + n (for n > 1)
    R(2n + 1) = R(n - 1) + R(n) + 1 (for n >= 1)

    (At time 2, we realized the difficulty of a breeding program with only one zombit and so added an additional
    zombit.)

    Being bored with the day-to-day duties of a henchman, a bunch of Professor Boolean's minions passed the time by
    playing a guessing game: when will the zombit population be equal to a certain amount? Then, some clever minion
    objected that this was too easy, and proposed a slightly different game: when is the last time that the zombit
    population will be equal to a certain amount? And thus, much fun was had, and much merry was made.

    (Not in this story: Professor Boolean later downsizes his operation, and you can guess what happens to these
    minions.)

    Write a function answer(str_S) which, given the base-10 string representation of an integer S, returns the
    largest n such that R(n) = S. Return the answer as a string in base-10 representation. If there is no such n,
    return "None". S will be a positive integer no greater than 10^25.
 */

package breeding_like_rabbits;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Answer {
    static Map<BigInteger, BigInteger> countMemo = new HashMap<BigInteger, BigInteger>();
    private static final BigInteger TWO = BigInteger.valueOf(2);
    public static BigInteger getCount(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) < 0)
            return BigInteger.ZERO;
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE))
            return BigInteger.ONE;
        if (n.equals(TWO))
            return TWO;
        if (countMemo.containsKey(n))
            return countMemo.get(n);

        // Count of zombits at time n
        // First from 2n find n by shifting
        BigInteger half = n.shiftRight(1);
        // Get R(n)
        BigInteger count = getCount(half);

        if (n.testBit(0))
            // If odd, use R(2n+1) formula
            count = count
                    .add(getCount(half.subtract(BigInteger.ONE)))
                    .add(BigInteger.ONE);
        else
            // If even, use R(2n) formula
            count = count
                    .add(getCount(half.add(BigInteger.ONE)))
                    .add(half);

        countMemo.put(n, count);
        return count;
    }

    public static BigInteger binarySearch(BigInteger start, BigInteger end,
                                          BigInteger neededCount, boolean parity) {
        // Parity: odd-true, even-false
        if (end.compareTo(start) <= 0)
            return BigInteger.ZERO;

        // Find midpoint
        BigInteger midpoint = start.add(
                end.subtract(start).shiftRight(1)
        );

        // Fix midpoint to be parity accurate
        if (parity != midpoint.testBit(0)) {
            midpoint = midpoint.add(BigInteger.ONE);
        }

        // Get count at midpoint
        BigInteger count = getCount(midpoint);

        // If found count is equal to the needed, answer is at midpoint
        if (count.equals(neededCount))
            return midpoint;

        // Change starting or ending point to be at midpoint
        if (count.compareTo(neededCount) > 0)
            end = midpoint.subtract(BigInteger.ONE);
        else
            start = midpoint.add(BigInteger.ONE);

        // Continue searching
        return binarySearch(start, end, neededCount, parity);
    }

    public static String answer(String str_S) {
        BigInteger neededCount = new BigInteger(str_S);
        // Start with odd binary search
        BigInteger answer = binarySearch(BigInteger.ZERO, neededCount, neededCount, true);

        // Search for even next
        if (answer.equals(BigInteger.ZERO))
            answer = binarySearch(BigInteger.ZERO, neededCount, neededCount, false);

        // Return the answer
        if (answer.equals(BigInteger.ZERO))
            return "None";

        return answer.toString();
    }

    public static void main(String[] args) {
        System.out.println("4 =? " + answer("7"));
        System.out.println("None =? " + answer("100"));
//        for (int i = 0; i <= 50; ++i) {
//            System.out.println("getCount("+i+") = " + getCount(BigInteger.valueOf(i)));
//        }
    }
}