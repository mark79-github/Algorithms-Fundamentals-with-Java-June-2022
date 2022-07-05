import java.util.Scanner;

public class PermutationsWithoutRepetition {
    private static String[] elements;
    private static boolean[] used;
    private static String[] perm;

    private static void permute(int index) {
        if (index >= elements.length) {
            print();
        } else {
            for (int i = 0; i < elements.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    perm[index] = elements[i];
                    permute(index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void print() {
        System.out.println(String.join(" ", perm));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split("\\s+");
        used = new boolean[elements.length];
        perm = new String[elements.length];

        permute(0);
    }
}