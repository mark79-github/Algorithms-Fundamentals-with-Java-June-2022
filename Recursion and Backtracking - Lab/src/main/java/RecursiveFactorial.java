import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RecursiveFactorial {
    private static long calcFactorial(int num) {
        if (num < 1) {
            return 1;
        }

        return num * calcFactorial(num - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int num = Integer.parseInt(line);

        long result = calcFactorial(num);
        System.out.println(result);
    }
}
