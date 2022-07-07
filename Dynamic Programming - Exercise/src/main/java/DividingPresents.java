import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DividingPresents {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int total = Arrays.stream(numbers).sum();

        int half = total / 2;

        int[][] matrix = new int[numbers.length + 1][half + 1];
        boolean[][] usedMatrix = new boolean[numbers.length][half];

        for (int i = 1; i <= numbers.length; i++) {
            for (int j = 1; j <= half; j++) {
                int include = -1;
                if (numbers[i - 1] <= j) {
                    include = numbers[i - 1] + matrix[i - 1][j - numbers[i - 1]];
                }
                int exclude = matrix[i - 1][j];

                if (include > exclude) {
                    matrix[i][j] = include;
                    usedMatrix[i - 1][j - 1] = true;
                } else {
                    matrix[i][j] = exclude;
                }
            }
        }

        List<Integer> result = new LinkedList<>();

        int currentRow = numbers.length - 1;
        int currentCol = half - 1;


        while (currentRow >= 0 && currentCol >= 0) {
            if (usedMatrix[currentRow][currentCol]) {
                result.add(numbers[currentRow]);
                currentCol -= numbers[currentRow];
            }
            currentRow--;
        }

        int alanSum = result.stream().mapToInt(Integer::intValue).sum();
        int bobSum = Math.abs(total - alanSum);

        System.out.printf("Difference: %d%n", Math.abs(alanSum - bobSum));
        System.out.printf("Alan:%d Bob:%d%n", alanSum, bobSum);
        System.out.printf("Alan takes: %s%n", result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        System.out.println("Bob takes the rest.");
    }
}
