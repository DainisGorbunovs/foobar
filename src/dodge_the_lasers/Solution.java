package dodge_the_lasers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.lang.Math;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class Solution {
    static BigDecimal sqrt(BigDecimal A, final int SCALE) {
        // https://stackoverflow.com/questions/13649703/square-root-of-bigdecimal-in-java/19743026#19743026
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = A.divide(x0, SCALE, ROUND_HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(BigDecimal.valueOf(2), SCALE, ROUND_HALF_UP);
        }
        return x1;
    }

    static BigInteger findFraction(BigInteger n) {
        BigDecimal TWO = BigDecimal.valueOf(2);
        BigDecimal fractPart = sqrt(TWO, 101).subtract(BigDecimal.ONE);
        BigDecimal fractPartN = new BigDecimal(n).multiply(fractPart);

        return fractPartN.toBigInteger();
    }

    // find sum of fractional parts
    static BigInteger findSum(BigInteger n) {
        if (n.equals(BigInteger.ZERO))
            return BigInteger.ZERO;
        BigInteger TWO = BigInteger.valueOf(2);

        BigInteger sum = BigInteger.ZERO;
        sum = sum.add(
                n.multiply(n.add(BigInteger.ONE))
                        .divide(TWO)
        );

        BigInteger flooredN = findFraction(n);
        sum = sum.add(
                n.multiply(flooredN)
        );

        sum = sum.subtract(
                flooredN.multiply(flooredN.add(BigInteger.ONE))
                        .divide(TWO)
        );

        sum = sum.subtract(findSum(flooredN));

        return sum;
    }

    public static String solution(String s) {
        BigInteger number = new BigInteger(s);
        BigInteger sum = findSum(number);

        return sum.toString();
    }
}