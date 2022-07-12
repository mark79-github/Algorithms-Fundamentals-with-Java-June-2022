import java.util.*;
import java.util.stream.Collectors;

public class TheStoryTelling {
    private static final Map<String, List<String>> graph = new LinkedHashMap<>();

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

    public static Collection<String> topSort(Map<String, List<String>> graph, List<String> elements) {
        List<String> sorted = new ArrayList<>();

        while (true) {
            String nodeToRemove = getPredecessorCount(graph).entrySet()
                    .stream()
                    .filter(e -> e.getValue() == 0)
                    .map(Map.Entry::getKey)
                    .max(Comparator.comparingInt(elements::indexOf))
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
        String line = scanner.nextLine().trim();
        LinkedHashSet<String> keys = new LinkedHashSet<>();
        while (!line.equalsIgnoreCase("END")) {
            String[] tokens = line.split("->");
            String key = tokens[0].trim();
            keys.add(key);
            graph.putIfAbsent(key, new ArrayList<>());
            if (tokens.length > 1) {
                String[] values = tokens[1].trim().split("\\s+");
                for (String value : values) {
                    graph.get(key).add(value);
                    keys.add(value);
                }
            }
            line = scanner.nextLine();
        }

        List<String> elements = new LinkedList<>(keys);
        Collection<String> topSortCollection = topSort(graph, elements);
        System.out.println(topSortCollection.stream().collect(Collectors.joining(" ")));
    }
}
