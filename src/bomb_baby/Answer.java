package bomb_baby;

import java.math.BigInteger;

public class Answer {
    private static final int MACH = 0;
    private static final int FACULA = 1;

    /**
     * LAMBCHOP needs to be destroyed by correct amount of bombs (not too few, not too much).
     * Start with 1 Mach, 1 Facula bomb, and replicate until has enough.
     *
     * M: M+F
     * F: F
     *
     * OR
     *
     * M: M
     * F: M+F
     *
     * @param M Mach bombs
     * @param F Facula bombs
     * @return Number of replication cycles (generations) to produce bombs
     */
    public static String answer(String M, String F) {
        BigInteger[] bombs = new BigInteger[2];
        bombs[MACH] = new BigInteger(M);
        bombs[FACULA] = new BigInteger(F);

        // Count is negative because the loop subtracts
        // till 0 instead till 1
        BigInteger count = BigInteger.valueOf(-1);
        while (bombs[MACH].compareTo(BigInteger.ONE) >= 0
                && bombs[FACULA].compareTo(BigInteger.ONE) >= 0) {
            int max = MACH;
            if (bombs[FACULA].compareTo(bombs[MACH]) > 0)
                max = FACULA;

            int min = max^1;

            BigInteger times = bombs[max].divide(bombs[min]);

            bombs[max] = bombs[max].subtract(
                    times.multiply(bombs[min])
            );

            count = count.add(times);
        }

        // If ends with M:2 F:0, then impossible
        if (bombs[MACH].compareTo(BigInteger.ONE) > 0
            || bombs[FACULA].compareTo(BigInteger.ONE) > 0) {
            return "impossible";
        }

        return count.toString();
    }

    public static void main(String[] args) {
        System.out.printf("Expected '1', got '%s'\n", answer("2", "1"));
        System.out.printf("Expected '4', got '%s'\n", answer("4", "7"));
        System.out.printf("Expected 'impossible', got '%s'\n", answer("2", "4"));
    }
}