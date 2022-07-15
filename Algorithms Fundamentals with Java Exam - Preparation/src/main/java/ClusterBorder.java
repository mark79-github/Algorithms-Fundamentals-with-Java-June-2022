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
        int index = dp.length - 1;
        while (index > 0) {
            int timeDifference = dp[index] - dp[index - 1];
            if (timeDifference == shipsAlone[index - 1]) {
                output.add(0, "Single " + index);
                index--;
            } else {
                output.add(0, "Pair of " + (index - 1) + " and " + index);
                index -= 2;
            }
        }

        System.out.println("Optimal Time: " + dp[shipsAlone.length]);
        output.forEach(System.out::println);
    }
}
