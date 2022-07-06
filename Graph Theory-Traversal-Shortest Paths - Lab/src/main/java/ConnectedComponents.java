import java.util.*;
import java.util.stream.Collectors;

public class ConnectedComponents {
    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        List<Deque<Integer>> connectedComponents = new ArrayList<>();

        boolean[] isVisited = new boolean[graph.size()];

        for (int i = 0; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                Deque<Integer> elements = dfsWithoutRecursion(graph, isVisited, i);
                connectedComponents.add(elements);
            }
        }

        return connectedComponents;
    }

    private static Deque<Integer> dfsWithoutRecursion(List<List<Integer>> graph, boolean[] isVisited, int start) {
        Deque<Integer> elements = new ArrayDeque<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!isVisited[current]) {
                isVisited[current] = true;
                elements.push(current);
                for (Integer dest : graph.get(current)) {
                    if (!isVisited[dest]) {
                        stack.push(dest);
                    }
                }
            }
        }
        return elements;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            if (!Objects.equals(line, "")) {
                List<Integer> listOfIntegers = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
                graph.add(listOfIntegers);
            } else {
                graph.add(new ArrayList<>());
            }
        }

        List<Deque<Integer>> connectedComponents = getConnectedComponents(graph);
        for (Deque<Integer> component : connectedComponents) {
            String result = component.stream().map(String::valueOf).collect(Collectors.joining(" "));
            System.out.printf("Connected component: %s%n", result);
        }
    }
}