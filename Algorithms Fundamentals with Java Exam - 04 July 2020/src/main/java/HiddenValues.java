import java.util.*;
import java.util.stream.Collectors;

public class HiddenValues {
    private static int[] kadaneAlgorithm(int[] numbers) {
        int[] result = new int[2];

        int maximumEndingHere = 0;
        int maxSoFar = Integer.MIN_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            maximumEndingHere = maximumEndingHere + numbers[i];
            if (maximumEndingHere < numbers[i]) {
                maximumEndingHere = numbers[i];
            }
            if (maxSoFar < maximumEndingHere) {
                result[1] = i;
                maxSoFar = maximumEndingHere;
            }
        }
        result[0] = maxSoFar;
        return result;
    }

    private static List<Integer> recreateSequence(int[] numbers, int sum, int lastIndex) {
        List<Integer> sequence = new LinkedList<>();
        int index = lastIndex;
        int currentSum = sum;
        while (currentSum != 0) {
            sequence.add(numbers[index]);
            currentSum -= numbers[index--];
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int[] csc = kadaneAlgorithm(numbers);
        int sum = csc[0];
        int lastIndex = csc[1];
        System.out.println(sum);

        List<Integer> sequence = recreateSequence(numbers, sum, lastIndex);
        System.out.println(sequence.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
