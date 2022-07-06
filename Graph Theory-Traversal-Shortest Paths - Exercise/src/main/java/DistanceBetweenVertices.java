import java.util.*;

public class DistanceBetweenVertices {
    private static class Node {
        Integer name;
        List<Node> neighbors = new ArrayList<>();

        public Node(Integer name) {
            this.name = name;
        }

        public Integer getName() {
            return this.name;
        }

        public void addNeighbor(Node neighbor) {
            neighbors.add(neighbor);
        }

        public List<Node> getNeighbors() {
            return neighbors;
        }
    }

    private static final Map<Integer, Node> graph = new LinkedHashMap<>();

    private static List<Integer> shortestPath(Integer startNode, Integer endNode) {
        Map<Integer, Integer> parents = new HashMap<>();
        List<Node> temp = new ArrayList<>();

        Node start = graph.get(startNode);
        temp.add(start);
        parents.put(startNode, null);

        while (!temp.isEmpty()) {
            Node currentNode = temp.get(0);
            List<Node> neighbors = graph.get(currentNode.getName()).getNeighbors();

            for (Node neighbor : neighbors) {
                Integer nodeName = neighbor.getName();

                if (!parents.containsKey(nodeName)) {
                    temp.add(neighbor);

                    parents.put(nodeName, currentNode.getName());

                    if (nodeName.equals(endNode)) {
                        return getPath(parents, endNode);
                    }
                }
            }

            temp.remove(0);
        }

        return Collections.emptyList();
    }

    private static List<Integer> getPath(Map<Integer, Integer> parents, Integer endNodeName) {
        List<Integer> path = new ArrayList<>();
        Integer node = endNodeName;
        while (node != null) {
            path.add(0, node);
            node = parents.get(node);
        }
        return path;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < nodes; i++) {
            String[] tokens = scanner.nextLine().split(":");
            int start = Integer.parseInt(tokens[0]);
            graph.putIfAbsent(start, new Node(start));
            if (tokens.length > 1) {
                Arrays.stream(tokens[1].split("\\s+"))
                        .map(e -> {
                            int anInt = Integer.parseInt(e);
                            return new Node(anInt);
                        }).forEach(e -> graph.get(start).addNeighbor(e));
            }
        }

        for (int i = 0; i < edges; i++) {
            String[] tokens = scanner.nextLine().split("-");
            int start = Integer.parseInt(tokens[0]);
            int end = Integer.parseInt(tokens[1]);
            System.out.printf("{%d, %d} -> %s%n", start, end, shortestPath(start, end).size() - 1);
        }
    }
}

