import java.util.*;
import java.util.stream.Collectors;

public class Gateways {

    private static final Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= nodes; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            int[] edge = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph.get(edge[0]).add(edge[1]);
        }

        int source = Integer.parseInt(scanner.nextLine());
        int destination = Integer.parseInt(scanner.nextLine());

        printShortestDistance(source, destination, nodes);
    }

    private static void printShortestDistance(int source, int destination, int edges) {
        int[] predecessors = new int[edges + 1];
        int[] distances = new int[edges + 1];

        bfs(source, destination, edges, predecessors, distances);

        List<Integer> path = new LinkedList<>();
        int crawl = destination;
        path.add(crawl);
        while (predecessors[crawl] != -1) {
            path.add(predecessors[crawl]);
            crawl = predecessors[crawl];
        }
        if (path.size() == 1) {
            return;
        }
        Collections.reverse(path);
        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static void bfs(int source, int dest, int edges, int[] predecessors, int[] distances) {
        Deque<Integer> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[edges + 1];

        Arrays.fill(visited, false);
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);

        visited[source] = true;
        distances[source] = 0;
        queue.offer(source);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            for (int i = 0; i < graph.get(current).size(); i++) {
                Integer child = graph.get(current).get(i);
                if (!visited[child]) {
                    visited[child] = true;
                    distances[child] = distances[current] + 1;
                    predecessors[child] = current;
                    queue.offer(child);

                    if (child == dest) {
                        return;
                    }
                }
            }
        }
    }
}
