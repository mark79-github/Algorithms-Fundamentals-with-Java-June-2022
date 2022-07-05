import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class QuickSort {
    private static void quickSort(int[] arr, int start, int end) {
        int pivotValue = arr[(start + end) / 2];

        int i = start;
        int j = end;

        while (i <= j) {
            while (arr[i] < pivotValue) {
                i++;
            }
            while (arr[j] > pivotValue) {
                j--;
            }
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        if (start < j) {
            quickSort(arr, start, j);
        }
        if (i < end) {
            quickSort(arr, i, end);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] intArray = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        quickSort(intArray, 0, intArray.length - 1);

        String result = Arrays.stream(intArray)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}
