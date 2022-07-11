import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BlackMesa {

    private static List<Integer> bfs(Map<Integer, Set<Integer>> graph, int source, int destination) {

        boolean[] visited = new boolean[graph.size() + 1];
        List<Integer> visitedNodes = new LinkedList<>();
        visited[source] = true;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            visitedNodes.add(x);
            if (x == destination) {
                return visitedNodes;
            }
            for (int child : graph.get(x)) {
                if (!visited[child]) {
                    queue.offer(child);
                    visited[child] = true;
                }
            }
        }
        return visitedNodes;
    }

    private static boolean[] bfs(Map<Integer, Set<Integer>> graph, int source) {

        boolean[] visited = new boolean[graph.size() + 1];

        visited[source] = true;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int child : graph.get(x)) {
                if (!visited[child]) {
                    queue.offer(child);
                    visited[child] = true;
                }
            }
        }

        return visited;
    }

    private static void printNotConnectedComponents(boolean[] visited) {
        String result = IntStream.range(1, visited.length).filter(e -> !visited[e]).mapToObj(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(result);
    }

    private static void printVisitedNodes(List<Integer> visited) {
        String result = visited.stream().map(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine().trim());
        int edges = Integer.parseInt(scanner.nextLine().trim());

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= nodes; i++) {
            graph.putIfAbsent(i, new TreeSet<>());
        }

        for (int i = 1; i <= edges; i++) {
            String line = scanner.nextLine();
            int[] tokens = Arrays.stream(line.trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            graph.get(tokens[0]).add(tokens[1]);
        }

        int start = Integer.parseInt(scanner.nextLine().trim());
        int end = Integer.parseInt(scanner.nextLine().trim());

        List<Integer> visitedNodes = bfs(graph, start, end);
        boolean[] notVisitedNodes = bfs(graph, start);
        printVisitedNodes(visitedNodes);
        printNotConnectedComponents(notVisitedNodes);
    }
}
