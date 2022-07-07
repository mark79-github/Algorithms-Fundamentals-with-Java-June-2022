import java.util.Scanner;

public class BinomialCoefficients {
    private static long[][] dp;

    private static long binom(int n, int k) {
        if (n == 0 || k == 0) {
            return 1;
        }

        if (dp[n][k] != 0) {
            return dp[n][k];
        }

        dp[n][k] = binom(n - 1, k) + binom(n - 1, k - 1);
        return dp[n][k];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        dp = new long[n + 1][k + 1];

        long result = binom(n, k);
        System.out.println(result);
    }
}
