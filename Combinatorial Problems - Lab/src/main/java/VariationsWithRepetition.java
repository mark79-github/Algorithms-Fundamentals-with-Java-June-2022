import java.util.Scanner;

public class VariationsWithRepetition {
    private static String[] elements;
    private static String[] variations;
    private static int slots;

    private static void variationsWithRep(int index) {
        if (index == slots) {
            print();
        } else {
            for (String element : elements) {
                variations[index] = element;
                variationsWithRep(index + 1);
            }
        }
    }

    private static void print() {
        System.out.println(String.join(" ", variations));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");
        slots = scanner.nextInt();
        variations = new String[slots];

        variationsWithRep(0);
    }
}
