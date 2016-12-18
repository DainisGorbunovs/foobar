package elevator_maintenance;

import java.util.Arrays;
import java.util.Comparator;

public class Answer {
    private static Comparator<String> versionComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            String[] first = o1.split("\\.");
            String[] second = o2.split("\\.");

            // ["1", "1.0", "1.0.0"] case
            boolean firstIsOlder = first.length < second.length;
            System.out.printf("Comparing %s with %s\n", o1, o2);
//            System.out.println("firstIsOlder = " + firstIsOlder);

            for (int index = 0; index < Math.min(first.length, second.length); ++index) {
                int firstInt = Integer.parseInt(first[index]);
                int secondInt = Integer.parseInt(second[index]);

                System.out.printf("First [%d] vs [%d] Second\n", firstInt, secondInt);

                if (firstInt > secondInt) {
                    System.out.println("First is newer than second");
                    firstIsOlder = false;
                    break;
                } else if (firstInt < secondInt) {
                    firstIsOlder = true;
                    break;
                }
            }

            return firstIsOlder ? -1 : 0;
        }
    };

    public static String[] answer(String[] l) {
        Arrays.sort(l, versionComparator);
        return l;
    }

    public static void main(String[] args) {
        String[] input = {"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"};
        System.out.println("Input: " + Arrays.asList(input));
        System.out.println("Output: " + Arrays.asList(answer(input)));
    }
}