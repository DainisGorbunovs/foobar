package find_the_access_codes;

public class Answer {
    public static int answer(int[] l) {
        int count = 0;
        int[] occurrences = new int[l.length];

        // Check all numbers in the list
        for (int divider = 0; divider < l.length; ++divider) {
            // Check numbers further down the list
            for (int current = divider + 1; current < l.length; ++current) {
                // If the current number can be divided
                if ((l[current] % l[divider]) == 0) {
                    // Increment the occurrences for the current number
                    ++occurrences[current];

                    // Count is increased by divider occurrences
                    count += occurrences[divider];
                }
            }
        }

        return count;
    }
}