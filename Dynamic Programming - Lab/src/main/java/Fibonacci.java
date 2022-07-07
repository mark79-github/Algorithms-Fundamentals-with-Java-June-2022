import java.util.Scanner;

public class Fibonacci {
    private static long[] dp;

    private static long fib(int n) {
        if (dp[n] != 0) {
            return dp[n];
        }

        if (n < 2) {
            dp[n] = n;
            return dp[n];
        }

        dp[n] = fib(n - 2) + fib(n - 1);
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        dp = new long[n + 1];

        long fibSumOfNthElement = fib(n);
        System.out.println(fibSumOfNthElement);
    }
}
