package lovely_lucky_lambs;

import java.util.Stack;

public class Answer {
    private static int countMinHenchmen(int total_lambs) {
        // Lamb count follows geometrical progression
        // Count is sum of it: 1 + 2 + 4 + 8 + 16 ...
        return (int) (Math.log(total_lambs) / Math.log(2));
    }

    private static int countMaxHenchmen(int total_lambs) {
        // Lamb count follows Fibonacci sequence
        // Count is sum of it: 1 + 1 + 2 + 3 + 5 ...

        // Start with one henchman (base case)
        int henchmen = 1;
        total_lambs--;

        // Fibonacci sequence (last two numbers)
        Stack<Integer> fibonacci = new Stack<>();
        fibonacci.add(0);
        fibonacci.add(1);

        while (total_lambs > 0) {
            int lastCount = fibonacci.pop();
            int newCount = fibonacci.pop() + lastCount;

            // Leave loop if cannot pay the next henchman
            if (total_lambs < newCount) {
                break;
            }

            total_lambs -= newCount;
            henchmen++;

            fibonacci.push(lastCount);
            fibonacci.push(newCount);
        }

        return henchmen;
    }

    public static int answer(int total_lambs) {
        return countMaxHenchmen(total_lambs) - countMinHenchmen(total_lambs);
    }

    public static void main(String[] args) {
        System.out.printf("%d total lambs, answer should be %d, actually is %d\n", 10, 1, answer(10));
        System.out.printf("%d total lambs, answer should be %d, actually is %d\n", 143, 3, answer(143));
    }
}
    /*
    10
    min henchmen [3]: 1 2 4 = 7
    max henchmen [4]: 1 1 2 3 = 7
    answer: 4-3 = 1

    143
    min henchmen [7] : 1 2 4 8 16 32 64 = 127
    max henchmen [10]: 1 1 2 3  5  8 13 21 34 55 = 143

    Rules:
    1. Start with 1 LAMB
    2. Give a max of 2x more LAMB than previous
    3. Give a min of sum of previous two LAMBs

     */