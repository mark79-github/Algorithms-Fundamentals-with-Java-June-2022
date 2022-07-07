import java.util.Arrays;
import java.util.Scanner;

public class SumWithLimitedCoins {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] coins = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int sum = Integer.parseInt(scanner.nextLine());

        int[][] dp = new int[coins.length + 1][sum + 1];

        int result = 0;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (coins[i - 1] <= j) {
                    dp[i][j] = Math.max(coins[i - 1] + dp[i - 1][j - coins[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                if (j == sum && dp[i][j] == sum) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}
