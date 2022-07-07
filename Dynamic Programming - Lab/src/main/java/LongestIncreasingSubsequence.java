import java.util.*;
import java.util.stream.Collectors;

public class LongestIncreasingSubsequence {
    private static int[] sequence;
    private static int[] prev;
    private static int[] length;

    private static int getMaxIndex() {
        int maxLength = 0;
        int maxIndex = -1;

        for (int i = 0; i < sequence.length; i++) {
            int current = sequence[i];
            int bestLength = 1;
            int bestIndex = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (sequence[j] < current && length[j] + 1 >= bestLength) {
                    bestLength = length[j] + 1;
                    bestIndex = j;
                }
            }
            prev[i] = bestIndex;
            length[i] = bestLength;
            if (maxLength < bestLength) {
                maxLength = bestLength;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static List<Integer> reconstructSolution(int maxIndex) {
        List<Integer> lis = new ArrayList<>();

        int index = maxIndex;
        while (index != -1) {
            lis.add(sequence[index]);
            index = prev[index];
        }

        Collections.reverse(lis);
        return lis;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        sequence = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        prev = new int[sequence.length];
        length = new int[sequence.length];
        Arrays.fill(prev, -1);

        int maxIndex = getMaxIndex();

        List<Integer> lis = reconstructSolution(maxIndex);
        System.out.println(lis.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
