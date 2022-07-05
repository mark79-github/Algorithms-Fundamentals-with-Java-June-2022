import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RecursiveArraySum {
    private static int sum(int[] intArray, int index) {
        if (index >= intArray.length) {
            return 0;
        }
        return intArray[index] + sum(intArray, index + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int[] arr = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int sum = sum(arr, 0);
        System.out.println(sum);
    }
}
