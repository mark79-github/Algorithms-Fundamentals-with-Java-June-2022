import java.util.Scanner;

public class CombinationsWithoutRepetition {
    private static int n;
    private static int k;
    private static int[] combinations;
    private static final StringBuilder builder = new StringBuilder();

    private static void comb(int index, int start) {
        if (index >= n) {
            print();
        } else {
            for (int i = start; i <= k; i++) {
                combinations[index] = i;
                comb(index + 1, i + 1);
            }
        }
    }

    private static void print() {
        for (int number : combinations) {
            builder.append(number).append(" ");
        }
        builder.append(System.lineSeparator());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        k = scanner.nextInt();
        n = scanner.nextInt();
        combinations = new int[n];

        comb(0, 1);
        System.out.println(builder.toString().trim());
    }
}