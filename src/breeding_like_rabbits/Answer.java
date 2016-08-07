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
    static Map<Integer, Integer> countMemo = new HashMap<Integer, Integer>();
    public static int getCount(int n) {
        if (n < 0)
            return 0;
        if (n == 0 || n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (countMemo.containsKey(n))
            return countMemo.get(n);

        // Count of zombits at time n
        int count = 0;
        // If even, use R(2n) formula
        if (n % 2 == 0) {
            count = getCount(n/2) + getCount(n/2 + 1) + n;
        } else { // Use R(2n+1) formula
            count = getCount((n-1)/2 - 1) + getCount((n-1)/2) + 1;
        }

        countMemo.put(n, count);
        return count;
    }

    public static String answer(String str_S) {
        BigInteger neededCount = new BigInteger(str_S);

        BigInteger answer = BigInteger.ZERO;

        if (answer.equals(BigInteger.ZERO))
            return "None";

        return answer.toString();
    }

    public static void main(String[] args) {
//        System.out.println("4 =? " + answer("7"));
//        System.out.println("None =? " + answer("100"));
        for (int i = 0; i <= 100; ++i) {
            System.out.println("getCount("+i+") = " + getCount(i));
        }
    }
}
/*
 Answer is initially ZERO

 Start counting
 Count max differences
 If max difference + count is above the neededCount, break the loop
 if the count equals to the neededCount, update Answer

 Return the answer
 If it is 0, return "None"
 Else, return answer.toString()


 Calculating the differences:

    R(0) = 1
    R(1) = 1, difference: 0
    R(2) = 2, difference: 1
    R(3) = 3, difference: 1
    R(4) = 9, difference: 6
    R(2n) = R(n) + R(n + 1) + n (for n > 1)
    R(2n + 1) = R(n - 1) + R(n) + 1 (for n >= 1)

    R(2n + 1) - R(2n) = R(n - 1) + R(n) + 1 - R(n) - R(n + 1) - n

    R(2n) - R(2n - 1) = ?

  Memoize differences
 */
