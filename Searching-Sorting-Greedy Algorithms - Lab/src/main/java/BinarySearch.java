import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    private static int getIndex(int[] arr, int value) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (value < arr[mid]) {
                end = mid - 1;
            } else if (value > arr[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] intArray = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int numberToFind = Integer.parseInt(scanner.nextLine());

        int foundIndex = getIndex(intArray, numberToFind);
        System.out.println(foundIndex);
    }
}
