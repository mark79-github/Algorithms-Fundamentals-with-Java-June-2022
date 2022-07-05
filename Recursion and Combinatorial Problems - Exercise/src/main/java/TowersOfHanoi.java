import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TowersOfHanoi {
    private static final Deque<Integer> source = new ArrayDeque<>();
    private static final Deque<Integer> spare = new ArrayDeque<>();
    private static final Deque<Integer> destination = new ArrayDeque<>();
    private static int counter = 0;
    private static final StringBuilder builder = new StringBuilder();

    private static void solve(int disk, Deque<Integer> source, Deque<Integer> destination, Deque<Integer> spare) {
        if (disk == 1) {
            destination.push(source.pop());
            printHeader();
            printRods();
        } else {
            solve(disk - 1, source, spare, destination);
            solve(1, source, destination, spare);
            solve(disk - 1, spare, destination, source);
        }
    }

    private static void printHeader() {
        builder.append("Step #")
                .append(++counter)
                .append(": Moved disk")
                .append(System.lineSeparator());
    }

    private static void printRods() {
        builder.append("Source: ")
                .append(formatRod(source))
                .append(System.lineSeparator())
                .append("Destination: ")
                .append(formatRod(destination))
                .append(System.lineSeparator())
                .append("Spare: ")
                .append(formatRod(spare))
                .append(System.lineSeparator())
                .append(System.lineSeparator());
    }

    private static String formatRod(Deque<Integer> rod) {
        return rod.stream()
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxElements = scanner.nextInt();

        for (int i = 1; i <= maxElements; i++) {
            source.offer(i);
        }

        printRods();
        solve(maxElements, source, destination, spare);
        System.out.println(builder.toString().trim());
    }
}
