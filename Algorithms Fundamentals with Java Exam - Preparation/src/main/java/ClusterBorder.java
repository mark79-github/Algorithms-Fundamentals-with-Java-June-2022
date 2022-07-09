import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClusterBorder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] shipsAlone = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] shipsByPair = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[shipsAlone.length + 1];

        dp[1] = shipsAlone[0];

        for (int i = 2; i <= shipsAlone.length; i++) {
            dp[i] = Math.min(dp[i - 1] + shipsAlone[i - 1], dp[i - 2] + shipsByPair[i - 2]);
        }

        List<String> output = new ArrayList<>();
        for (int i = dp.length - 1; i > 0; i--) {
            int timeDiff = dp[i] - dp[i - 1];

            if (timeDiff == shipsAlone[i - 1]) {
                output.add("Single " + i);
            } else {
                output.add("Pair of " + (i - 1) + " and " + i);
                i--;
            }
        }

        System.out.println("Optimal Time: " + dp[shipsAlone.length]);
        for (int i = output.size() - 1; i >= 0; i--) {
            System.out.println(output.get(i));
        }
    }
}
