import java.util.Scanner;

public class CombinationsWithRepetition {
    private static String[] elements;
    private static String[] combinations;
    private static int slots;

    private static void comb(int index, int start) {
        if (index >= slots) {
            print();
        } else {
            for (int i = start; i < elements.length; i++) {
                combinations[index] = elements[i];
                comb(index + 1, i);
            }
        }
    }

    private static void print() {
        System.out.println(String.join(" ", combinations));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");
        slots = scanner.nextInt();
        combinations = new String[slots];

        comb(0, 0);
    }
}
