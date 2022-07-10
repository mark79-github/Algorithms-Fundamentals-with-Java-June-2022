import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SuperSet {

    private static int[] elements;
    private static int[] combinations;
    private static int slots;

    private static void comb(int index, int start) {
        if (index >= slots) {
            print();
        } else {
            for (int i = start; i < elements.length; i++) {
                combinations[index] = elements[i];
                comb(index + 1, i + 1);
            }
        }
    }

    private static void print() {
        System.out.println(Arrays.stream(combinations).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = Arrays.stream(scanner.nextLine().split(",\\s+")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i <= elements.length; i++) {
            slots = i;
            combinations = new int[slots];
            comb(0, 0);
        }
    }

}
