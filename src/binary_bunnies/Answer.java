/*
    Binary bunnies
    ==============

    As more and more rabbits were rescued from Professor Booleans horrid laboratory, you had to develop a system to
    track them, since some habitually continue to gnaw on the heads of their brethren and need extra supervision.
    For obvious reasons, you based your rabbit survivor tracking system on a binary search tree, but all of a sudden
    that decision has come back to haunt you.

    To make your binary tree, the rabbits were sorted by their ages (in days) and each, luckily enough, had a distinct
    age. For a given group, the first rabbit became the root, and then the next one (taken in order of rescue) was
    added, older ages to the left and younger to the right. The order that the rabbits returned to you determined
    the end pattern of the tree, and herein lies the problem.

    Some rabbits were rescued from multiple cages in a single rescue operation, and you need to make sure that all
    of the modifications or pathogens introduced by Professor Boolean are contained properly. Since the tree did not
    preserve the order of rescue, it falls to you to figure out how many different sequences of rabbits could have
    produced an identical tree to your sample sequence, so you can keep all the rescued rabbits safe.

    For example, if the rabbits were processed in order from [5, 9, 8, 2, 1], it would result in a binary tree
    identical to one created from [5, 2, 9, 1, 8].

    You must write a function answer(seq) that takes an array of up to 50 integers and returns a string representing
    the number (in base-10) of sequences that would result in the same tree as the given sequence.
 */
package binary_bunnies;

import java.math.BigInteger;
import java.util.*;

class BinarySearchTree {
    public Integer number;
    public BinarySearchTree left;
    public BinarySearchTree right;

    public BinarySearchTree() {
        number = null;
        left   = null;
        right  = null;
    }

    public BinarySearchTree(int[] list) {
        this();
        for (int number : list) {
            insert(number);
        }
    }

    public BinarySearchTree getLeft() {
        if (left != null)
            return left;

        left = new BinarySearchTree();
        return left;
    }

    public BinarySearchTree getRight() {
        if (right != null)
            return right;

        right = new BinarySearchTree();
        return right;
    }

    public void insert(int additional) {
        if (number != null) {
            if (number > additional) {
                left.insert(additional);
            } else {
                right.insert(additional);
            }
        } else {
            number = additional;
            left = new BinarySearchTree();
            right = new BinarySearchTree();
        }
    }

    public int getSize() {
        if (number == null) {
            return 0;
        }

        return left.getSize() + right.getSize() + 1;
    }

    public List<Integer> getPreOrderList() {
        List<Integer> list = new ArrayList<Integer>();
        if (number != null) {
            list.add(number);
            list.addAll(left.getPreOrderList());
            list.addAll(right.getPreOrderList());
        }
        return list;
    }
}

public class Answer {
    static Map<List<Integer>, BigInteger> coefficientMemo = new HashMap<List<Integer>, BigInteger>();

    private static BigInteger getBinomialCoefficient(int n, int k) {
        // http://stackoverflow.com/questions/11032781/fastest-way-to-generate-binomial-coefficients
        List<Integer> key = Arrays.asList(n, k);

        // Check edge cases
        if (n == k || k == 0) return BigInteger.ONE;
        if (k > n) return BigInteger.ZERO;
        if (k > (n - k)) k = n - k;
        if (k == 1) return BigInteger.valueOf(n);

        // Check if memoized
        if (coefficientMemo.containsKey(key)) {
            return coefficientMemo.get(key);
        }

        // Calculate the coefficient
        BigInteger coefficient = BigInteger.ONE;
        for (int i = 1; i <= k; ++i) {
            coefficient = coefficient
                    .multiply(BigInteger.valueOf(n - (k - i)))
                    .divide(BigInteger.valueOf(i));

        }

        // Memoize the calculated coefficient
        coefficientMemo.put(key, coefficient);

        return coefficient;
    }

    public static BigInteger getNumberOfPermutations(BinarySearchTree tree) {
        if (tree.number == null) {
            return BigInteger.ONE;
        }

        BigInteger leftPermutations = getNumberOfPermutations(tree.getLeft());
        BigInteger rightPermutations = getNumberOfPermutations(tree.getRight());

        int leftSize = tree.getLeft().getSize();
        int rightSize = tree.getRight().getSize();

        BigInteger binomialCoefficient = getBinomialCoefficient(leftSize + rightSize, rightSize);

        return binomialCoefficient
                .multiply(leftPermutations)
                .multiply(rightPermutations);
    }

    public static String answer(int[] seq) {
        BinarySearchTree tree = new BinarySearchTree(seq);
        return getNumberOfPermutations(tree).toString();
    }

    public static void main(String[] args) {
        System.out.println("6 ?= " + answer(new int[] {5, 9, 8, 2, 1}));
        System.out.println("6 ?= " + answer(new int[] {5, 2, 9, 1, 8}));
        System.out.println("8 ?= " + answer(new int[] {8, 2, 1, 5, 9}));
        System.out.println("1 ?= " + answer(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}

/*
     5
  2     9
1     8

Level 1: 5
Level 2: 2, 9
Level 3: 1, 8

1) Start with root: 5
   Next is 2 (left of 5) or 9 (right of 5)
   1) if 2:
      next is 1 (left of 2) or 9 (right of 5)
      1) if 1:
         next is 9 (right of 5)
         1) 9:
            then:
               next is 8
      2) if 9:
         next is 1 (left of 2) or 8 (left of 9)
         1) if 1:
           then:
              next is 8
         2) if 8:
           then:
              next is 1
   2) if 9:
      next is 8 (left of 9) or 2 (left of 5)
      1) if 8:
         next is 2 (left of 5)
         1) 2:
            then:
               next is 1
      2) if 2:
         next is 1 (left of 2) or 8 (left of 9)
         1) if 1:
            then:
               next is 8
         2) if 8:
            then:
               next is 1
Return: 6

 */