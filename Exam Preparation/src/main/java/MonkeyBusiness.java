import java.util.Arrays;
import java.util.Scanner;

public class MonkeyBusiness {
    private static int n;
    private static int[] elements;
    private static int[] combinations;

    private static final StringBuilder builder = new StringBuilder();
    private static int countCombinations = 0;

    private static void comb(int index) {
        if (index >= n) {
            print();
        } else {
            combinations[index] = elements[index];
            comb(index + 1);
            combinations[index] = -elements[index];
            comb(index + 1);
        }
    }

    private static void print() {
        int sum = Arrays.stream(combinations).sum();
        if (sum == 0) {
            countCombinations++;
            for (int digit : combinations) {
                if (digit > 0) {
                    builder.append("+");
                }
                builder.append(digit).append(" ");
            }
            builder.append(System.lineSeparator());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = Integer.parseInt(scanner.nextLine());

        elements = new int[n];
        combinations = new int[n];

        for (int i = 1; i <= n; i++) {
            elements[i - 1] = i;
        }

        comb(0);

        System.out.println(builder.append("Total Solutions: ").append(countCombinations));
    }
}
