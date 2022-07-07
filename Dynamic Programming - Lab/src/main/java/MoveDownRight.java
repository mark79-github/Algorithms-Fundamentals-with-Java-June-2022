import java.util.*;
import java.util.stream.Collectors;

public class MoveDownRight {

    private static class Node {
        private final int row;
        private final int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return String.format("[%s, %s]", this.row, this.col);
        }
    }

    private static int rows;
    private static int cols;
    private static int[][] dp;

    private static String createOutput(List<Node> coordinates) {
        return coordinates.stream()
                .map(Node::toString)
                .collect(Collectors.joining(" "));
    }

    private static List<Node> reconstructSolution() {
        int row = rows - 1;
        int col = cols - 1;

        List<Node> coordinates = new LinkedList<>();
        coordinates.add(new Node(row, col));
        while (row != 0 || col != 0) {

            int up = -1;
            if (row - 1 >= 0) {
                up = dp[row - 1][col];
            }

            int left = -1;
            if (col - 1 >= 0) {
                left = dp[row][col - 1];
            }
            if (left < up) {
                row--;
            } else {
                col--;
            }
            coordinates.add(new Node(row, col));
        }
        Collections.reverse(coordinates);
        return coordinates;
    }

    private static void solve(int[][] matrix) {
        dp = new int[rows][cols];
        dp[0][0] = matrix[0][0];
        for (int col = 1; col < cols; col++) {
            dp[0][col] = matrix[0][col] + dp[0][col - 1];
        }

        for (int row = 1; row < rows; row++) {
            dp[row][0] = matrix[row][0] + dp[row - 1][0];
        }

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                int max = Math.max(dp[row - 1][col], dp[row][col - 1]);
                dp[row][col] = matrix[row][col] + max;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        rows = Integer.parseInt(scanner.nextLine());
        cols = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }

        solve(matrix);

        List<Node> nodes = reconstructSolution();

        String output = createOutput(nodes);
        System.out.println(output);
    }
}
