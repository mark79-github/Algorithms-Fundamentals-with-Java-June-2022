import java.util.Scanner;

public class WordDifferences {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[] firstWord = scanner.nextLine().toCharArray();
        char[] secondWord = scanner.nextLine().toCharArray();

        int[][] dp = new int[firstWord.length + 1][secondWord.length + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 && j > 0) {
                    dp[i][j] = dp[i][j - 1] + 1;
                }
                if (j == 0 && i > 0) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (firstWord[i - 1] == secondWord[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        System.out.printf("Deletions and Insertions: %d%n", dp[dp.length - 1][dp[dp.length - 1].length - 1]);
    }
}
