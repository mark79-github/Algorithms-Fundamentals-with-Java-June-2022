import java.util.Scanner;

public class ReverseArray {
    private static String[] tokens;

    private static void rec(int index) {
        if (index < 0) {
            return;
        }
        System.out.print(tokens[index] + " ");
        rec(index - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        tokens = scanner.nextLine().split("\\s+");
        rec(tokens.length - 1);
    }
}
