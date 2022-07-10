import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Paths {
    private static final List<List<Integer>> graph = new ArrayList<>();
    private static final List<Integer> path = new ArrayList<>();

    private static void dfs(int current, int destination) {
        if (current == destination) {
            print();
            return;
        }

        for (int child : graph.get(current)) {
            path.add(child);
            dfs(child, destination);
            path.remove(path.size() - 1);
        }
    }

    private static void print() {
        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            graph.add(new ArrayList<>());
            if (!line.equals("")) {
                List<Integer> edges = Arrays.stream(line.split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                graph.get(i).addAll(edges);
            }
        }

        for (int i = 0; i < n - 1; i++) {
            path.add(i);
            dfs(i, n - 1);
            path.clear();
        }
    }
}
