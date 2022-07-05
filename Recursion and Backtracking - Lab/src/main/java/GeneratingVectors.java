import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GeneratingVectors {
    private static void calcVector(int[] intArray, int index) {
        if (index >= intArray.length) {
            drawArray(intArray);
            return;
        }

        for (int j = 0; j <= 1; j++) {
            intArray[index] = j;
            calcVector(intArray, index + 1);
        }
    }

    private static void drawArray(int[] arr) {
        for (int element : arr) {
            System.out.print(element);
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        int numberOfSymbols = Integer.parseInt(line);
        int[] intArray = new int[numberOfSymbols];

        calcVector(intArray, 0);
    }
}
