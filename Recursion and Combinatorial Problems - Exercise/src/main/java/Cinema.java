import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Cinema {

    private static List<String> availablePeople = new ArrayList<>();
    private static final List<String> allCombinations = new ArrayList<>();
    private static boolean[] used;
    private static String[] combinations;
    private static String[] seats;
    private static final StringBuilder builder = new StringBuilder();

    private static void print() {
        for (String allCombination : allCombinations) {
            int combinationsCounter = 0;
            String[] strings = allCombination.split(":");
            for (int i = 0; i < seats.length; i++) {
                String person = seats[i];
                if (person != null) {
                    builder.append(person);
                } else {
                    builder.append(strings[combinationsCounter++]);
                }
                if (i != seats.length - 1) {
                    builder.append(" ");
                }
            }
            builder.append(System.lineSeparator());
        }
        System.out.println(builder.toString().trim());
    }

    private static void variations(int index) {
        if (index >= combinations.length) {
            allCombinations.add(String.join(":", combinations));
        } else {
            for (int i = 0; i < availablePeople.size(); i++) {
                if (!used[i]) {
                    used[i] = true;
                    combinations[index] = availablePeople.get(i);
                    variations(index + 1);
                    used[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] people = scanner.nextLine().split(",\\s+");
        availablePeople = Arrays.stream(people).collect(Collectors.toList());
        seats = new String[people.length];

        String line = scanner.nextLine();
        while (!line.equalsIgnoreCase("generate")) {
            String[] tokens = line.split("\\s+-\\s+");
            String name = tokens[0];
            int position = Integer.parseInt(tokens[1]) - 1;
            seats[position] = name;
            availablePeople.remove(name);
            line = scanner.nextLine();
        }

        used = new boolean[availablePeople.size()];
        combinations = new String[availablePeople.size()];

        variations(0);

        print();
    }
}
