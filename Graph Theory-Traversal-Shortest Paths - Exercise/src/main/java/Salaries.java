import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Salaries {
    private static final List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    private static long[] salariesArray;

    private static void dfs(int node) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;

        for (int child : graph.get(node)) {
            if (!visited[child]) {
                dfs(child);

                long sum = graph.get(child)
                        .stream()
                        .mapToLong(c -> salariesArray[c])
                        .sum();

                salariesArray[child] = sum == 0 ? 1 : sum;
            }
        }

        long sum = graph.get(node)
                .stream()
                .mapToLong(c -> salariesArray[c])
                .sum();

        salariesArray[node] = sum == 0 ? 1 : sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int employees = Integer.parseInt(reader.readLine());

        visited = new boolean[employees];
        salariesArray = new long[employees];

        int[] managersCount = new int[employees];

        for (int i = 0; i < employees; i++) {
            graph.add(new ArrayList<>());

            String line = reader.readLine();
            for (int employee = 0; employee < line.length(); employee++) {
                char currentChar = line.charAt(employee);

                if (currentChar == 'Y') {
                    graph.get(i).add(employee);
                    managersCount[employee]++;
                }
            }
        }

        List<Integer> sources = new ArrayList<>();
        for (int i = 0; i < managersCount.length; i++) {
            if (managersCount[i] == 0) {
                sources.add(i);
            }
        }

        for (int source : sources) {
            dfs(source);
        }

        long sum = Arrays.stream(salariesArray).sum();

        System.out.println(sum);
    }
}
