import java.util.Arrays;
import java.util.Scanner;

public class ConnectingCables {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] cables = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] orderedCables = new int[cables.length + 1];

        for (int i = 1; i < orderedCables.length; i++) {
            orderedCables[i] = i;
        }

        int[][] dp = new int[cables.length + 1][orderedCables.length + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (cables[i - 1] == orderedCables[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.printf("Maximum pairs connected: %d%n", dp[dp.length - 1][dp[dp.length - 1].length - 1]);
    }
}
