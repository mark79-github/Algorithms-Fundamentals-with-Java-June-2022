import java.util.Scanner;

public class VariationsWithoutRepetition {
    private static String[] elements;
    private static boolean[] used;
    private static String[] kSlots;

    private static void variations(int index) {
        if (index >= kSlots.length) {
            print();
        } else {
            for (int i = 0; i < elements.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    kSlots[index] = elements[i];
                    variations(index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void print() {
        System.out.println(String.join(" ", kSlots));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split("\\s+");
        int slots = scanner.nextInt();
        used = new boolean[elements.length];
        kSlots = new String[slots];

        variations(0);
    }
}
