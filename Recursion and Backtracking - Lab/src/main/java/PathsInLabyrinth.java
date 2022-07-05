import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PathsInLabyrinth {
    private static final List<Character> path = new ArrayList<>();

    private static void findPaths(char[][] labyrinth, int row, int col, char direction) {
        if (!isInBounds(labyrinth, row, col)) {
            return;
        }

        path.add(direction);

        if (isEqualToChar(labyrinth, row, col, 'e')) {
            printPath();
            path.remove(path.size() - 1);
            return;
        }

        if (isEqualToChar(labyrinth, row, col, '-')) {
            markCell(labyrinth, row, col, 'V');
            findPaths(labyrinth, row, col + 1, 'R');
            findPaths(labyrinth, row + 1, col, 'D');
            findPaths(labyrinth, row, col - 1, 'L');
            findPaths(labyrinth, row - 1, col, 'U');
            markCell(labyrinth, row, col, '-');
        }
        path.remove(path.size() - 1);
    }

    private static void printPath() {
        String result = path.stream()
                .map(String::valueOf)
                .map(String::trim)
                .collect(Collectors.joining(""));
        System.out.println(result);
    }

    private static boolean isInBounds(char[][] labyrinth, int row, int col) {
        return (row >= 0 && row < labyrinth.length) && (col >= 0 && col < labyrinth[row].length);
    }

    private static boolean isEqualToChar(char[][] labyrinth, int row, int col, char aChar) {
        return labyrinth[row][col] == aChar;
    }

    private static void markCell(char[][] labyrinth, int row, int col, char symbol) {
        labyrinth[row][col] = symbol;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int rows = Integer.parseInt(br.readLine());
        int columns = Integer.parseInt(br.readLine());
        char[][] labyrinth = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            String line = br.readLine();
            labyrinth[i] = line.toCharArray();
        }

        findPaths(labyrinth, 0, 0, ' ');
    }
}
