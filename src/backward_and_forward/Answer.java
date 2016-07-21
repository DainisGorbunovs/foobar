/*
    Backward and forward
    ====================

    The sign outside reads: Name no one man.

    "Escape. We must escape." Staring at the locked door of his cage, Beta Rabbit, spy and brilliant mathematician,
    has a revelation. "Of course! Name no one man - it's a palindrome! Palindromes are the key to opening this lock!"

    To help Beta Rabbit crack the lock, write a function answer(n) which returns the smallest positive integer base b,
    at least 2, in which the integer n is a palindrome. The input n will satisfy "0 <= n <= 1000."

 */


package backward_and_forward;

public class Answer {
    private static boolean isPalindrome(int number) {
        String strNumber = String.valueOf(number);
        String reverseNumber = new StringBuffer(strNumber).reverse().toString();

        return strNumber.equals(reverseNumber);
    }

    private static int convertToBase(int number, int base) {
        String converted = "";
        int remainder = 0;
        while (number > 0) {
            remainder = number % base;
            number = number / base;
            converted = String.valueOf(remainder) + converted;
        }
        return Integer.parseInt(converted);
    }

    public static int answer(int n) {
        int limit = (n <= 6) ? n + 2 : n / 2;
        for (int base = 2; base <= limit; ++base) {
            if (n % base > 0 && isPalindrome(convertToBase(n, base)))
                return base;
        }
        return (n == 0) ? 2 : n - 1;
    }

    public static void main(String args[]) {
        for (int number = 1; number < 1001; ++number) {
            int base = answer(number);
            System.out.printf("Number: %d, Base: %d\n", number, base);
        }
    }
}