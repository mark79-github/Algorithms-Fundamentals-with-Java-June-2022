import java.util.Scanner;

public class AlphaDecay {

    private static String[] elements;
    private static boolean[] used;
    private static String[] perm;

    private static int slots;

    private static void permute(int index) {
        if (index >= slots) {
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
        slots = Integer.parseInt(scanner.nextLine());
        used = new boolean[elements.length];
        perm = new String[slots];

        permute(0);
    }
}
