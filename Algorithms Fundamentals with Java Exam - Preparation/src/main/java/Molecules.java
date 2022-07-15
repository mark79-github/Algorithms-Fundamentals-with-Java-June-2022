import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Molecules {
    private static final Map<Integer, Set<Integer>> graph = new HashMap<>();
    private static Set<Integer> isVisited;
    private static int n;

    private static void printResult() {
        String result = IntStream.rangeClosed(1, n).filter(i -> !isVisited.contains(i))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(result);
    }

    private static void bfs(int start) {
        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            isVisited.add(node);
            for (int child : graph.get(node)) {
                if (!isVisited.contains(child)) {
                    queue.offer(child);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = Integer.parseInt(scanner.nextLine());
        int m = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            graph.putIfAbsent(i, new HashSet<>());
        }

        isVisited = new HashSet<>();

        for (int i = 0; i < m; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int from = tokens[0];
            int to = tokens[1];
            graph.get(from).add(to);
        }

        int start = Integer.parseInt(scanner.nextLine());

        bfs(start);
        printResult();
    }
}
