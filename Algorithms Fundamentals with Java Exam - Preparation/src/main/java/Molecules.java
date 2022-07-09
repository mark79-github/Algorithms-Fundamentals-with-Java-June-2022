import java.util.*;

public class Molecules {
    private static final List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] isVisited;
    private static int n;

    private static String generateResult(Deque<Integer> deque) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (!deque.contains(i)) {
                builder.append(i).append(" ");
            }
        }
        return builder.toString();
    }

    private static Deque<Integer> dfsWithoutRecursion(int start) {
        Deque<Integer> elements = new ArrayDeque<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!isVisited[current]) {
                isVisited[current] = true;
                elements.push(current);
                for (Integer dest : graph.get(current)) {
                    if (!isVisited[dest]) {
                        stack.push(dest);
                    }
                }
            }
        }
        return elements;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = Integer.parseInt(scanner.nextLine());
        int m = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        isVisited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int from = tokens[0];
            int to = tokens[1];

            graph.get(from).add(to);
        }

        int start = Integer.parseInt(scanner.nextLine());

        Deque<Integer> deque = dfsWithoutRecursion(start);

        System.out.println(generateResult(deque));
    }
}
