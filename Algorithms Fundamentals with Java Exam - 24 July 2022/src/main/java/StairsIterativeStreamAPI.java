import java.util.Scanner;
import java.util.stream.Stream;

public class StairsIterativeStreamAPI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = Integer.parseInt(scanner.nextLine());
        Long result = Stream.iterate(new long[]{1, 1}, dp -> new long[]{dp[1], dp[0] + dp[1]})
                .limit(n + 1)
                .map(dp -> dp[0])
                .reduce(0L, (accumulator, element) -> element);
        System.out.println(result);
    }
}

