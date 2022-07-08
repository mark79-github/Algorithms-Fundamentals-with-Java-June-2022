import java.util.Arrays;
import java.util.Scanner;

public class PathFinder {
    private static int[][] graph;

    private static boolean checkConnected(int[] paths) {
        for (int i = 1; i < paths.length; i++) {
            int row = paths[i - 1];
            int col = paths[i];

            if (graph[row][col] != 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            if (line.equals("")) {
                continue;
            }

            int[] nodes = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            for (int node : nodes) {
                graph[i][node] = 1;
            }
        }

        int p = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < p; i++) {
            int[] paths = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            boolean hasConnection = checkConnected(paths);
            System.out.println((hasConnection ? "yes" : "no"));
        }

        System.out.println();
    }
}
