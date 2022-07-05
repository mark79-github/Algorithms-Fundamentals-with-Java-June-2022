import java.util.HashSet;
import java.util.Scanner;

public class PermutationsWithRepetition {
    private static String[] elements;

    private static void permuteWithRepetitions(int index) {
        if (index >= elements.length) {
            print();
        } else {
            permuteWithRepetitions(index + 1);
            HashSet<String> swapped = new HashSet<>();
            swapped.add(elements[index]);
            for (int i = index + 1; i < elements.length; i++) {
                if (!swapped.contains(elements[i])) {
                    swap(index, i);
                    permuteWithRepetitions(index + 1);
                    swap(index, i);
                    swapped.add(elements[i]);
                }
            }
        }
    }

    private static void swap(int index, int i) {
        String element = elements[index];
        elements[index] = elements[i];
        elements[i] = element;
    }

    private static void print() {
        System.out.println(String.join(" ", elements));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split("\\s+");

        permuteWithRepetitions(0);
    }
}
