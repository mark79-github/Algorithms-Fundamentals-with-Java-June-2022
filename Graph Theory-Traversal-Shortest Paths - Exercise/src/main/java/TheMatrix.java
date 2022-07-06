import java.util.Arrays;
import java.util.Scanner;

public class TheMatrix {
    private static char[][] matrix;
    private static boolean[][] visited;

    private static void dfs(int row, int col, char symbol, char charToBeReplaced) {
        if (!isInBounds(row, col) || charToBeReplaced != matrix[row][col]) {
            return;
        }
        if (!visited[row][col]) {
            visited[row][col] = true;
            matrix[row][col] = symbol;
            dfs(row + 1, col, symbol, charToBeReplaced);
            dfs(row - 1, col, symbol, charToBeReplaced);
            dfs(row, col + 1, symbol, charToBeReplaced);
            dfs(row, col - 1, symbol, charToBeReplaced);
        }
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static String viewMatrix() {
        StringBuilder builder = new StringBuilder();
        for (char[] row : matrix) {
            for (char symbol : row) {
                builder.append(symbol);
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] ints = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int rows = ints[0];
        int cols = ints[1];

        matrix = new char[rows][cols];
        visited = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] strings = scanner.nextLine().split("\\s+");
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = strings[col].charAt(0);
            }
        }


        char fillChar = scanner.nextLine().charAt(0);
        ints = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int start = ints[0];
        int end = ints[1];

        char charToBeReplaced = matrix[start][end];
        dfs(start, end, fillChar, charToBeReplaced);
        System.out.println(viewMatrix());
    }
}
