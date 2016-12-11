package solar_doomsday;

import java.util.LinkedList;
import java.util.List;

public class Answer {
    public static int[] answer(int area) {
        List<Integer> areas = new LinkedList<>();

        // Find small areas
        while (area > 0) {
            int smallArea = (int) Math.pow(Math.floor(Math.sqrt(area)), 2);
            areas.add(smallArea);
            area -= smallArea;
        }

        // Prepare int[] array
        int[] answer = new int[areas.size()];

        for (int index = 0; index < areas.size(); ++index) {
            answer[index] = areas.get(index);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] answer = answer(12);
        System.out.print("Area ");
        for (int area: answer) {
            System.out.printf("| %d", area);
        }
        System.out.println();
    }
}
