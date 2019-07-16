package elevator_maintenance;

import java.lang.reflect.Array;
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

            for (int index = 0; index < Math.min(first.length, second.length); ++index) {
                int firstInt = Integer.parseInt(first[index]);
                int secondInt = Integer.parseInt(second[index]);

                if (firstInt > secondInt) {
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
        // For some reason the below does not work on Foobar
        // Arrays.sort(l, versionComparator)

        // So wrote my own merge sort
        mergeSort(l, versionComparator);
        return l;
    }

    private static <T> void mergeSort(T[] array, Comparator<? super T> comparator) {
        mergeSort(array, 0, array.length - 1, comparator);
    }

    private static <T> void mergeSort(T[] array, int left, int right, Comparator<? super T> comparator) {
        // Array is sorted if has 0 or 1 elements
        if (left == right)
            return;

        int middle = (left + right) / 2;
        mergeSort(array, left, middle, comparator);
        mergeSort(array, middle + 1, right, comparator);

        merge(array, left, middle, right, comparator);
    }

    private static <T> void merge(T[] array, int left, int middle, int right, Comparator<? super T> comparator) {
        int n = right - left + 1;

        @SuppressWarnings("unchecked")
        T[] values = (T[]) new Object[n];

        int leftIndex = left;

        int rightIndex = middle + 1;

        int index = 0;

        while (leftIndex <= middle && rightIndex <= right) {
            if (comparator.compare(array[leftIndex], array[rightIndex]) < 0) {
                values[index++] = array[leftIndex++];
            } else {
                values[index++] = array[rightIndex++];
            }
        }

        while (leftIndex <= middle) {
            values[index++] = array[leftIndex++];
        }
        while (rightIndex <= right) {
            values[index++] = array[rightIndex++];
        }

        for (index = 0; index < n; index++)
            array[left + index] = values[index];
    }

    public static void main(String[] args) {
        String[] input = {"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"};
        System.out.println("Input: " + Arrays.asList(input));
        System.out.println("Output: " + Arrays.asList(answer(input)));
    }
}