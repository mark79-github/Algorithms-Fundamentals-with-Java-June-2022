import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stairs {
    private static long[] dp;

    private static long step(int n) {
        if (dp[n] != 0) {
            return dp[n];
        }

        if (n == 0 || n == 1) {
            dp[n] = 1;
            return dp[n];
        }

        dp[n] = step(n - 2) + step(n - 1);
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
