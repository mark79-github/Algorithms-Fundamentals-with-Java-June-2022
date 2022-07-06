import java.util.*;

public class CyclesInGraph {

    private static class Graph {
        int vertices;
        LinkedList<Integer>[] adjList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination) {
            adjList[source].addFirst(destination);
            adjList[destination].addFirst(source);
        }

        public boolean isCycle() {
            boolean[] visited = new boolean[vertices];
            for (int i = 0; i < vertices; i++) {
                if (!visited[i] && isCycleUtil(i, visited, -1)) {
                    return true;
                }
            }
            return false;
        }

        boolean isCycleUtil(int currVertex, boolean[] visited, int parent) {

            visited[currVertex] = true;

            for (int i = 0; i < adjList[currVertex].size(); i++) {
                int vertex = adjList[currVertex].get(i);
                if (vertex != parent) {
                    if (visited[vertex]) {
                        return true;
                    } else {
                        if (isCycleUtil(vertex, visited, currVertex)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> graphMap = new LinkedHashMap<>();
        List<String> mapper = new LinkedList<>();

        String line = scanner.nextLine();
        while (!line.equalsIgnoreCase("END")) {
            String[] tokens = line.split("-");
            String key = tokens[0];
            String value = tokens[1];
            graphMap.putIfAbsent(key, new ArrayList<>());
            if (!mapper.contains(key)) {
                mapper.add(key);
            }
            if (!mapper.contains(value)) {
                mapper.add(value);
            }
            graphMap.get(key).add(value);
            line = scanner.nextLine();
        }

        int vertices = mapper.size();

        Graph graph = new Graph(vertices);

        for (Map.Entry<String, List<String>> entry : graphMap.entrySet()) {
            String key = entry.getKey();
            for (String value : entry.getValue()) {
                graph.addEdge(mapper.indexOf(key), mapper.indexOf(value));
            }
        }

        System.out.printf("Acyclic: %s%n", graph.isCycle() ? "No" : "Yes");
    }
}
