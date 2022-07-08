import java.util.Scanner;

class TBC {
    private static char[][] matrix;
    private static final char TUNNEL_CHAR = 't';
    private static int rows;
    private static int cols;
    private static boolean isSafe(int row, int col, boolean[][] isVisited) {
        return (row >= 0) && (row < rows) && (col >= 0) && (col < cols) && (matrix[row][col] == TUNNEL_CHAR && !isVisited[row][col]);
    }

    private static void dfs(int row, int col, boolean[][] isVisited) {
        int[] rowNbr = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colNbr = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

        isVisited[row][col] = true;

        for (int k = 0; k < 8; k++) {
            if (isSafe(row + rowNbr[k], col + colNbr[k], isVisited)) {
                dfs(row + rowNbr[k], col + colNbr[k], isVisited);
            }
        }

    }

    private static int countIslands() {
        boolean[][] isVisited = new boolean[rows][cols];

        int count = 0;
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                if (matrix[i][j] == TUNNEL_CHAR && !isVisited[i][j]) {
                    dfs(i, j, isVisited);
                    count++;
                }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        rows = Integer.parseInt(scanner.nextLine());
        cols = Integer.parseInt(scanner.nextLine());

        matrix = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }

        System.out.println(countIslands());
    }
}
