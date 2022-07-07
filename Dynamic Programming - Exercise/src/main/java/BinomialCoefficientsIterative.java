import java.util.Scanner;

public class BinomialCoefficientsIterative {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        long[][] dp = new long[n + 1][n + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j <= n - i; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[n - k][k]);
    }
}
