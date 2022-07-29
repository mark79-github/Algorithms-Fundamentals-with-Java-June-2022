import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GuardsAdjacencyMatrix {

    private static int[][] graph;
    private static int nodes;
    private static int edges;
    private static int source;

    private static boolean[] bfs() {
        boolean[] isVisited = new boolean[nodes + 1];
        Deque<Integer> queue = new ArrayDeque<>();

        isVisited[source] = true;
        queue.add(source);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int i = 1; i <= nodes; i++)
                if ((graph[x][i] == 1) && (!isVisited[i])) {
                    queue.add(i);
                    isVisited[i] = true;
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
        graph = new int[nodes + 1][nodes + 1];
    }

    private static void initializeGraphData(Scanner reader) {
        IntStream.range(0, edges)
                .mapToObj(i -> Arrays.stream(reader.nextLine().split("\\s"))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .forEach(arr -> graph[arr[0]][arr[1]] = 1);
    }

    private static int readSingleIntegerNumber(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine());
    }

    public static void main(String[] args) {
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

