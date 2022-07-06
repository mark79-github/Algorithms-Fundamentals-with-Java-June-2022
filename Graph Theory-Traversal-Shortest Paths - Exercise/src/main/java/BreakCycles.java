import java.util.*;
import java.util.stream.Collectors;

public class BreakCycles {

    private static final Map<Character, List<Character>> graph = new TreeMap<>();

    private static boolean hasPath(Character first, Character second) {
        Set<Character> visited = new HashSet<>();
        Deque<Character> queue = new ArrayDeque<>();

        visited.add(first);
        queue.offer(first);

        while (!queue.isEmpty()) {
            Character currentNode = queue.poll();
            for (Character aChar : graph.get(currentNode)) {
                if (!visited.contains(aChar)) {
                    visited.add(aChar);
                    queue.offer(aChar);
                    if (aChar.equals(second)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }

            String[] tokens = line.split("\\s+->\\s+");
            graph.putIfAbsent(tokens[0].charAt(0), new ArrayList<>());
            List<Character> characterList = Arrays.stream(tokens[1].split("\\s+"))
                    .map(e -> e.charAt(0))
                    .collect(Collectors.toList());
            graph.put(tokens[0].charAt(0), characterList);
        }

        List<String> results = new ArrayList<>();

        for (Map.Entry<Character, List<Character>> entry : graph.entrySet()) {
            List<Character> connections = entry.getValue();
            Character first = entry.getKey();
            Collections.sort(connections);

            for (ListIterator<Character> iterator = connections.listIterator(); iterator.hasNext(); ) {
                Character second = iterator.next();

                iterator.remove();
                graph.get(second).removeIf(c -> c.equals(first));

                if (hasPath(first, second)) {
                    results.add(first + " - " + second);
                } else {
                    iterator.add(second);
                    graph.get(second).add(first);
                }
            }
        }

        System.out.println("Edges to remove: " + results.size());
        for (String string : results) {
            System.out.println(string);
        }
    }
}
