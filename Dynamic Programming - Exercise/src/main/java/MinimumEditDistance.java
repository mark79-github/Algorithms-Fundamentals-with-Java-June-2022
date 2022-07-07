import java.util.Scanner;

public class MinimumEditDistance {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int replace = Integer.parseInt(scanner.nextLine());
        int insert = Integer.parseInt(scanner.nextLine());
        int delete = Integer.parseInt(scanner.nextLine());

        char[] firstWord = scanner.nextLine().toCharArray();
        char[] secondWord = scanner.nextLine().toCharArray();

        int[][] dp = new int[firstWord.length + 1][secondWord.length + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 && j > 0) {
                    dp[i][j] = dp[i][j - 1] + insert;
                }
                if (j == 0 && i > 0) {
                    dp[i][j] = dp[i - 1][j] + delete;
                }
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (firstWord[i - 1] == secondWord[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int replaceValue = dp[i - 1][j - 1] + replace;
                    int insertValue = dp[i][j - 1] + insert;
                    int deleteValue = dp[i - 1][j] + delete;
                    int minValue = Math.min(replaceValue, Math.min(insertValue, deleteValue));
                    dp[i][j] = minValue;
                }
            }
        }

        System.out.printf("Minimum edit distance: %d%n", dp[dp.length - 1][dp[dp.length - 1].length - 1]);
    }
}
