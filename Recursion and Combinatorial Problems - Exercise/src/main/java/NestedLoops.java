import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NestedLoops {
    private static int number;
    private static int[] intArr;
    private static final StringBuilder result = new StringBuilder();

    private static void permute(int index) {
        if (index == intArr.length) {
            generateResult();
        } else {
            for (int i = 1; i <= number; i++) {
                intArr[index] = i;
                permute(index + 1);
            }
        }
    }

    private static void generateResult() {
        for (int j : intArr) {
            result.append(j).append(" ");
        }
        result.append(System.lineSeparator());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        number = Integer.parseInt(reader.readLine());
        intArr = new int[number];

        permute(0);

        System.out.println(result.toString());
    }
}
