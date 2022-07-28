import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Guards {

    private static final Map<Integer, Set<Integer>> graph = new HashMap<>();
    private static int nodes;
    private static int edges;
    private static int source;

    private static boolean[] bfs() {
        boolean[] isVisited = new boolean[graph.size() + 1];
        Deque<Integer> queue = new ArrayDeque<>();

        isVisited[source] = true;
        queue.offer(source);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int child : graph.get(node)) {
                if (!isVisited[child]) {
                    queue.offer(child);
                    isVisited[child] = true;
                }
            }
        }

        return isVisited;
    }

    private static void printNotConnectedComponents(boolean[] visited) {
        String result = IntStream.range(0, visited.length)
                .skip(1)
                .filter(e -> !visited[e])
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(result);
    }

    public static void initializeGraph() {
        IntStream.rangeClosed(1, nodes)
                .forEach(i -> graph.putIfAbsent(i, new HashSet<>()));
    }

    private static void initializeGraphData(Scanner reader) {
        IntStream.range(0, edges)
                .mapToObj(i -> Arrays.stream(reader.nextLine().split("\\s"))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .forEach(arr -> graph.get(arr[0]).add(arr[1]));
    }

    private static int readSingleIntegerNumber(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine());
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        nodes = readSingleIntegerNumber(scanner);
        edges = readSingleIntegerNumber(scanner);

        initializeGraph();
        initializeGraphData(scanner);

        source = readSingleIntegerNumber(scanner);

        boolean[] visited = bfs();
        printNotConnectedComponents(visited);
    }
}
