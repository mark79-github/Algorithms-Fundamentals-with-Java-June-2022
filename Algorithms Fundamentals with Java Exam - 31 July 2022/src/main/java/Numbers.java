import java.util.*;
import java.util.stream.Collectors;

class Numbers {

    private static int[] candidates;

    private static List<List<Integer>> combinationSum(int target) {
        return combinationSum(target, new HashMap<>());
    }

    private static List<List<Integer>> combinationSum(int target, Map<Integer, List<List<Integer>>> memo) {
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        List<List<Integer>> result = new ArrayList<>();
        if (target == 0) {
            result.add(new ArrayList<>());
            return result;
        } else if (target < 0) {
            return result;
        }
        for (int candidate : candidates) {
            int newTarget = target - candidate;
            List<List<Integer>> subLists = combinationSum(newTarget, memo);
            for (List<Integer> subList : subLists) {
                List<Integer> tempList = new ArrayList<>(subList);
                if (tempList.isEmpty() || tempList.get(tempList.size() - 1) <= candidate) {
                    tempList.add(candidate);
                    result.add(tempList);
                }
            }
        }
        memo.put(target, result);
        return result;
    }

    private static int[] initializeData(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    private static String recreateOutput(List<List<Integer>> combinationSum) {
        StringBuilder builder = new StringBuilder();
        for (int i = combinationSum.size() - 1; i >= 0; i--) {
            builder.append(combinationSum.get(i).stream().sorted(Comparator.reverseOrder()).map(String::valueOf).collect(Collectors.joining(" + "))).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        candidates = initializeData(n);
        List<List<Integer>> combinationSum = combinationSum(n);

        String output = recreateOutput(combinationSum);
        System.out.println(output);
    }
}