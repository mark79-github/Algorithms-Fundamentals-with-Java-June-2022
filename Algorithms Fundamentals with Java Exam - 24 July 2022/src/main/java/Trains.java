import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Trains {
    private static double[] readInput(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    private static int findNumberOfPlatforms(double[] arr, double[] dep) {
        int platformNeeded = 0;
        int maxPlatforms = 0;
        int n = arr.length;

        Arrays.sort(arr);
        Arrays.sort(dep);

        int i = 0;
        int j = 0;
        while (i < n) {
            if (arr[i] < dep[j]) {
                platformNeeded++;
                i++;
                maxPlatforms = Math.max(maxPlatforms, platformNeeded);
            } else {
                platformNeeded--;
                j++;
            }
        }
        return maxPlatforms;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        double[] arrivalTimes = readInput(reader);
        double[] departureTimes = readInput(reader);

        int totalCount = findNumberOfPlatforms(arrivalTimes, departureTimes);
        System.out.println(totalCount);
    }
}
