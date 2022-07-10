import java.util.*;
import java.util.stream.Collectors;

public class Climbing {
    private static int[][] matrix;

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

    private static int[][] flipInPlace(int[][] theArray) {
        for (int i = 0; i < (theArray.length / 2); i++) {
            int[] temp = theArray[i];
            theArray[i] = theArray[theArray.length - i - 1];
            theArray[theArray.length - i - 1] = temp;
        }
        return theArray;
    }

    private static int[][] flipLeftToRight(int[][] theArray) {

        for (int i = 0; i < theArray.length; i++) {
            for (int curr = 0; curr < (theArray[0].length + 1) / 2; curr++) {

                int saved = theArray[i][curr];
                theArray[i][curr] = theArray[i][theArray[0].length - 1 - curr];
                theArray[i][theArray[0].length - 1 - curr] = saved;
            }
        }
        return theArray;
    }

    private static void printOutput(List<Node> coordinates) {
        List<Integer> output = new ArrayList<>();
        for (Node coordinate : coordinates) {
            int row = coordinate.row;
            int col = coordinate.col;
            output.add(matrix[row][col]);
        }

        System.out.println(output.stream().mapToInt(Integer::intValue).sum());
        System.out.println(output.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());

        matrix = new int[rows][cols];
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }

        matrix = flipLeftToRight(flipInPlace(matrix));

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
        printOutput(coordinates);
    }
}
