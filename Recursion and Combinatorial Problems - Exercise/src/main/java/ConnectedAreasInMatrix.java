import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConnectedAreasInMatrix {
    private static class AreaRecord {
        private final int row;
        private final int col;
        private final int count;

        private AreaRecord(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }

        private int getRow() {
            return row;
        }

        private int getCol() {
            return col;
        }

        private int getCount() {
            return count;
        }
    }

    private static char[][] matrix;
    private static int countElements = 0;
    private static final StringBuilder builder = new StringBuilder();
    private static final List<AreaRecord> records = new ArrayList<>();

    private static void solve(int row, int col) {
        if (!isInBounds(row, col) || !isFree(row, col)) {
            return;
        }
        matrix[row][col] = 'V';
        countElements++;
        solve(row + 1, col);
        solve(row - 1, col);
        solve(row, col + 1);
        solve(row, col - 1);
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static boolean isFree(int row, int col) {
        return matrix[row][col] == '-';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine());
        int columns = Integer.parseInt(scanner.nextLine());

        matrix = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (isFree(row, col)) {
                    countElements = 0;
                    solve(row, col);
                    records.add(new AreaRecord(row, col, countElements));
                }
            }
        }

        List<AreaRecord> sortedRecords = records.stream()
                .sorted((f, s) -> {
                    if (s.getCount() == f.getCount()) {
                        return f.getRow() - s.getRow();
                    }
                    return s.getCount() - f.getCount();
                }).collect(Collectors.toList());

        for (int i = 0; i < sortedRecords.size(); i++) {
            AreaRecord data = sortedRecords.get(i);
            builder.append(String.format("Area #%d at (%d, %d), size: %d", i + 1, data.getRow(), data.getCol(), data.getCount()))
                    .append(System.lineSeparator());
        }

        System.out.printf("Total areas found: %d%n", sortedRecords.size());
        System.out.println(builder.toString().trim());
    }
}
