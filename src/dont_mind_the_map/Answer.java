package dont_mind_the_map;

public class Answer {
    public static int answer(int[][] subway) {
        return 0;
    }

    public static void main(String[] args) {
        int[][] subway = new int[][] {
                {2, 1},
                {2, 0},
                {3, 1},
                {1, 0}
        };

        System.out.println("-1 ?= answer(subway) = " + answer(subway));
    }
}