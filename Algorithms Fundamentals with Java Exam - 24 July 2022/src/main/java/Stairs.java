import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stairs {
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

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        dp = new long[n + 2];

        long result = fib(n + 1);
        System.out.println(result);
    }
}
