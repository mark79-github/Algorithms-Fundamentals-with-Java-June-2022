import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Guards {

    private static final Map<Integer, Set<Integer>> graph = new HashMap<>();
    private static int nodes;
    private static int edges;

    private static boolean[] bfs(int source) {

        boolean[] isVisited = new boolean[graph.size() + 1];

        isVisited[source] = true;
        Deque<Integer> queue = new ArrayDeque<>();
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
        for (int i = 1; i <= nodes; i++) {
            graph.putIfAbsent(i, new HashSet<>());
        }
    }

    private static void initializeGraphData(BufferedReader reader) throws IOException {
        for (int i = 0; i < edges; i++) {
            int[] connection = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = connection[0];
            int to = connection[1];

            graph.get(from).add(to);
        }
    }

    private static int readSingleIntegerNumber(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nodes = readSingleIntegerNumber(reader);
        edges = readSingleIntegerNumber(reader);

        initializeGraph();
        initializeGraphData(reader);

        int start = readSingleIntegerNumber(reader);

        boolean[] visited = bfs(start);
        printNotConnectedComponents(visited);
    }
}
