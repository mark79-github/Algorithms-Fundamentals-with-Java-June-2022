import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LongestZigZagSubsequence {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] sequence = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int[][] seq = new int[2][sequence.length];
        int[][] prev = new int[2][sequence.length];
        int maxValue = 0;
        int maxValueIndex = -1;
        int bestSolutionArray = 0;
        seq[0][0] = 1;
        seq[1][0] = 1;
        prev[0][0] = prev[1][0] = -1;

        for (int currentIdx = 1; currentIdx < sequence.length; currentIdx++) {
            for (int prevIndex = 0; prevIndex < currentIdx; prevIndex++) {

                if (sequence[prevIndex] < sequence[currentIdx] && seq[0][currentIdx] < seq[1][prevIndex] + 1) {
                    seq[0][currentIdx] = seq[1][prevIndex] + 1;
                    prev[0][currentIdx] = prevIndex;
                }

                if (sequence[prevIndex] > sequence[currentIdx] && seq[1][currentIdx] < seq[0][prevIndex] + 1) {
                    seq[1][currentIdx] = seq[0][prevIndex] + 1;
                    prev[1][currentIdx] = prevIndex;
                }
            }

            if (seq[0][currentIdx] > maxValue) {
                maxValue = seq[0][currentIdx];
                bestSolutionArray = 0;
                maxValueIndex = currentIdx;
            }

            if (seq[1][currentIdx] > maxValue) {
                maxValue = seq[1][currentIdx];
                bestSolutionArray = 1;
                maxValueIndex = currentIdx;
            }
        }

        ArrayList<Integer> res = new ArrayList<>();

        while (maxValueIndex > -1) {
            res.add(sequence[maxValueIndex]);

            maxValueIndex = prev[bestSolutionArray][maxValueIndex];

            if (bestSolutionArray == 1) {
                bestSolutionArray = 0;
            } else {
                bestSolutionArray = 1;
            }
        }
        Collections.reverse(res);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

}
