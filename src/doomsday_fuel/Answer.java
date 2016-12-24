package doomsday_fuel;

public class Answer {
    /**
     * Adapted from Jeffrey Hantin's code
     * http://stackoverflow.com/questions/4201860/how-to-find-gcd-lcm-on-a-set-of-numbers
     * @param a First number
     * @param b Second number
     * @return Least Common Multiple between two numbers
     */
    private static int leastCommonMultiple(int a, int b)
    {
        return a * (b / greatestCommonDivisor(a, b));
    }

    /**
     * Adapted from Jeffrey Hantin's code
     * http://stackoverflow.com/questions/4201860/how-to-find-gcd-lcm-on-a-set-of-numbers
     * @param input Numbers
     * @return Least Common Multiple between all numbers
     */
    static int leastCommonMultiple(int[] input)
    {
        int result = input[0];
        for (int i = 1; i < input.length; i++)
            result = leastCommonMultiple(result, input[i]);
        return result;
    }

    /**
     * Finds greatest common divisor between numbers
     * @param a First number
     * @param b Second number
     * @return Greatest Common Divisor of these numbers
     */
    private static int greatestCommonDivisor(int a, int b) {
        if (b==0)
            return a;

        return greatestCommonDivisor(b, a%b);
    }

    /**
     * @param decimals Decimal numbers
     * @return an int array of fraction denominators
     */
    static int[] getFractionDenominators(double[] decimals) {
        int[] denominators = new int[decimals.length];

        for (int index = 0; index < decimals.length; ++index) {
            denominators[index] = getFractionDenominator(decimals[index]);
        }

        return denominators;
    }

    /**
     * @param decimal Decimal number
     * @return Denominator
     */
    static int getFractionDenominator(double decimal) {
        return getFractionDenominator(decimal, Integer.MAX_VALUE);
    }

    /**
     * Adapted from Peter Lawrey's, Christopher Guray's code
     * http://stackoverflow.com/questions/5968636/converting-a-float-into-a-string-fraction-representation
     * @param d Decimal number
     * @param factor Maximum denominator
     * @return Denominator
     */
    private static int getFractionDenominator(double d, int factor) {
        if (d < 0) {
            d = -d;
        }
        long l = (long) d;
        d -= l;
        double error = Math.abs(d);
        int bestDenominator = 1;
        for(int i=2;i<=factor;i++) {
            double error2 = Math.abs(d - (double) Math.round(d * i) / i);
            if (error2 < error) {
                error = error2;
                bestDenominator = i;
                if (error2 == 0) break;
            }
        }
        return bestDenominator;
    }

    public static int[] answer(int[][] m) {
        return null;
    }
}