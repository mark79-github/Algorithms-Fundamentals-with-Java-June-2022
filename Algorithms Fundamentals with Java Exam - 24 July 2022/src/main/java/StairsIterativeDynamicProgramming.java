import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StairsIterativeDynamicProgramming {
    private static long[] dp;

    private static long step(int n) {
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        dp = new long[n + 1];

        long result = step(n);
        System.out.println(result);
    }
}
