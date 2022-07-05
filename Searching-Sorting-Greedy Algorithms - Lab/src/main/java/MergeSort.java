import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MergeSort {
    private static int[] mergeSort(int[] arr) {
        if (arr.length > 1) {
            int m = arr.length / 2;
            int n = arr.length - m;

            int[] firstPartition = new int[m];
            int[] secondPartition = new int[n];

            System.arraycopy(arr, 0, firstPartition, 0, m);

            int q = 0;
            for (int p = m; p < arr.length; ++p) {
                secondPartition[q] = arr[p];
                q++;
            }

            firstPartition = mergeSort(firstPartition);
            secondPartition = mergeSort(secondPartition);

            int i = 0;
            int j = 0;
            int k = 0;

            while (i < m && j < n) {
                if (firstPartition[i] <= secondPartition[j]) {
                    arr[k] = firstPartition[i];
                    k++;
                    i++;
                } else {
                    arr[k] = secondPartition[j];
                    k++;
                    j++;
                }
            }

            if (i < m) {
                for (int p = i; p < m; ++p) {
                    arr[k] = firstPartition[p];
                    k++;
                }
            } else {
                for (int p = j; p < n; ++p) {
                    arr[k] = secondPartition[p];
                    k++;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] intArray = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        int[] sortedArray = mergeSort(intArray);

        String result = Arrays
                .stream(sortedArray)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(result);
    }
}
