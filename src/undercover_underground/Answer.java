/*
    Undercover underground
    ======================

    As you help the rabbits establish more and more resistance groups to fight against Professor Boolean, you need
    a way to pass messages back and forth.

    Luckily there are abandoned tunnels between the warrens of the rabbits, and you need to find the best way to use
    them. In some cases, Beta Rabbit wants a high level of interconnectedness, especially when the groups show their
    loyalty and worthiness. In other scenarios the groups should be less intertwined, in case any are compromised by
    enemy agents or zombits.

    Every warren must be connected to every other warren somehow, and no two warrens should ever have more than one
    tunnel between them. Your assignment: count the number of ways to connect the resistance warrens.

    For example, with 3 warrens (denoted A, B, C) and 2 tunnels, there are three distinct ways to connect them:

    A-B-C
    A-C-B
    C-A-B

    With 4 warrens and 6 tunnels, the only way to connect them is to connect each warren to every other warren.
    A - B
    | X |
    C - D

    Write a function answer(N, K) which returns the number of ways to connect N distinctly labelled warrens with
    exactly K tunnels, so that there is a path between any two warrens.

    The return value must be a string representation of the total number of ways to do so, in base 10.
    N will be at least 2 and at most 20.
    K will be at least one less than N and at most (N * (N - 1)) / 2
 */

package undercover_underground;

import java.util.Random;

public class Answer {
    public static String answer(int N, int K) {
        if (K < N)
            return "1";
        return "0";
    }

    public static void main(String[] args) {
        // N is at least 2 and at most 20
        System.out.println("Choosing random N (warrens) and K (tunnels)");
        int N = new Random().nextInt(19) + 2;
        int K = new Random().nextInt(N*(N-1)/2 - (N-1) + 1) + (N-1);
        System.out.printf("N = %d (range:[%d-%d])\n", N, 2, 20);
        System.out.printf("K = %d (range:[%d-%d] specific for N = %d)\n", K, N-1, N*(N-1)/2, N);
        System.out.println("Answer: " + answer(N, K) + ".");
    }
}
