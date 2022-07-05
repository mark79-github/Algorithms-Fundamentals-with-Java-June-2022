import java.util.Scanner;

public class NChooseKCount {
    private static int binom(int n, int k) {
        if (k > n)
            return 0;

        if (k == 0 || k == n)
            return 1;

        return binom(n - 1, k - 1) + binom(n - 1, k);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();
        int n = scanner.nextInt();

        int result = binom(n, k);
        System.out.println(result);
    }
}
