import java.util.Arrays;
import java.util.Scanner;

public class RodCutting {
    private static int[] bestPrices;
    private static int[] prices;
    private static int[] bestCombo;

    private static int cutRod(int n) {
        if (bestPrices[n] >= 0) {
            return bestPrices[n];
        }
        if (n == 0) {
            return 0;
        }
        int currentBest = bestPrices[n];
        for (int i = 1; i <= n; i++) {
            currentBest = Math.max(currentBest, prices[i] + cutRod(n - i));
            if (currentBest > bestPrices[n]) {
                bestPrices[n] = currentBest;
                bestCombo[n] = i;
            }
        }
        return bestPrices[n];
    }

    private static void reconstructSolution(int length) {
        while (length - bestCombo[length] != 0) {
            System.out.print(bestCombo[length] + " ");
            length = length - bestCombo[length];
        }
        System.out.println(bestCombo[length]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        prices = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        int length = Integer.parseInt(scanner.nextLine());

        bestPrices = new int[length + 1];
        bestCombo = new int[length + 1];

        int best = cutRod(length);
        System.out.println(best);

        reconstructSolution(length);
    }
}
