import java.util.*;
import java.util.stream.Collectors;

public class ShortestPath {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> graph = new LinkedList<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph.get(tokens[0]).add(tokens[1]);
        }

        int start = Integer.parseInt(scanner.nextLine());
        int end = Integer.parseInt(scanner.nextLine());

        printShortestDistance(graph, start, end, edges);
    }

    private static void printShortestDistance(List<List<Integer>> graph, int source, int destination, int edges) {
        int[] predecessors = new int[edges];
        int[] distances = new int[edges];

        bfs(graph, source, destination, edges, predecessors, distances);

        List<Integer> path = new LinkedList<>();
        int crawl = destination;
        path.add(crawl);
        while (predecessors[crawl] != -1) {
            path.add(predecessors[crawl]);
            crawl = predecessors[crawl];
        }

        Collections.reverse(path);
        System.out.println("Shortest path length is: " + distances[destination]);
        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static void bfs(List<List<Integer>> graph, int source, int dest, int edges, int[] predecessors, int[] distances) {
        Deque<Integer> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[edges];

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
