import java.util.*;

public class TopologicalSorting {
    private static Map<String, Integer> getPredecessorCount(Map<String, List<String>> graph) {
        Map<String, Integer> predecessorCount = new LinkedHashMap<>();

        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            predecessorCount.putIfAbsent(entry.getKey(), 0);
            for (String child : entry.getValue()) {
                predecessorCount.putIfAbsent(child, 0);
                predecessorCount.put(child, predecessorCount.get(child) + 1);
            }
        }

        return predecessorCount;
    }

    public static Collection<String> topSort(Map<String, List<String>> graph) {
        List<String> sorted = new ArrayList<>();

        while (true) {
            String nodeToRemove = getPredecessorCount(graph).entrySet()
                    .stream()
                    .filter(e -> e.getValue() == 0)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);

            if (nodeToRemove == null) {
                break;
            }

            graph.remove(nodeToRemove);
            sorted.add(nodeToRemove);
        }

        if (!graph.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return sorted;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> graph = new LinkedHashMap<>();

        String line = scanner.nextLine();
        while (!Objects.equals(line, "")) {

            String[] tokens = line.split("\\s+->");
            String key = tokens[0].replace("\"", "");
            graph.putIfAbsent(key, new ArrayList<>());

            if (tokens.length <= 1) {
                break;
            }

            String value = tokens[1];
            String[] valueTokens = value.split(",\\s+");
            List<String> values = graph.get(key);
            for (String valueToken : valueTokens) {
                String currentValue = valueToken.replace("\"", "");
                values.add(currentValue);
            }
            graph.put(key, values);

            line = scanner.nextLine();
        }

        Collection<String> topSortCollection = topSort(graph);
        String result = String.join(", ", topSortCollection);
        System.out.printf("Topological sorting: %s", result);
    }
}

