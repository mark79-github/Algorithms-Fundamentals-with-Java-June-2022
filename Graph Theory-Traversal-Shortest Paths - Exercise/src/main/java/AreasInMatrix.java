import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class AreasInMatrix {

    private static char[][] matrix;
    private static boolean[][] visited;
    private static final Map<Character, Integer> nodes = new TreeMap<>();

    private static void dfs(int row, int col, char symbol) {
        if (!isInBounds(row, col)) {
            return;
        }
        if (symbol != matrix[row][col]) {
            return;
        }
        if (!visited[row][col]) {
            visited[row][col] = true;
            dfs(row + 1, col, symbol);
            dfs(row - 1, col, symbol);
            dfs(row, col + 1, symbol);
            dfs(row, col - 1, symbol);
        }
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int m = Integer.parseInt(scanner.nextLine());
        matrix = new char[m][];
        visited = new boolean[m][];
        for (int i = 0; i < m; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
            visited[i] = new boolean[matrix[i].length];
        }


        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (!visited[row][col]) {
                    dfs(row, col, matrix[row][col]);
                    nodes.putIfAbsent(matrix[row][col], 0);
                    nodes.put(matrix[row][col], nodes.get(matrix[row][col]) + 1);
                }
            }
        }

        System.out.printf("Areas: %d%n", nodes.values().stream().mapToInt(Integer::intValue).sum());
        nodes.entrySet().stream()
                .map(entry -> String.format("Letter '%s' -> %d", entry.getKey(), entry.getValue()))
                .forEach(System.out::println);
    }
}
