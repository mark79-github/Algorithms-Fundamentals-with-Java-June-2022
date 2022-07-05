import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class RecursiveFibonacci {
    private static Map<Integer, Long> memory = new HashMap<>();

    private static long fib(int number) {
        if (number <= 1) {
            return 1;
        }

        if (!memory.containsKey(number)) {
            long value = fib(number - 2) + fib(number - 1);
            memory.put(number, value);
        }

        return memory.get(number);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());

        long fibNumber = fib(number);
        System.out.println(fibNumber);
    }
}
