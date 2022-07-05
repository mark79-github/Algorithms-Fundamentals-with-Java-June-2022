import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class WordCruncher {
    private static final List<String> combined = new ArrayList<>();
    private static final Map<Integer, List<String>> table = new HashMap<>();
    private static final Map<String, Integer> occurrences = new HashMap<>();
    private static final Set<String> out = new TreeSet<>();
    private static String target;

    private static void permute(int index) {
        if (target.length() == index) {
            print();
        } else if (table.containsKey(index)) {
            List<String> strings = table.get(index);
            for (String str : strings) {
                if (occurrences.get(str) > 0) {
                    occurrences.put(str, occurrences.get(str) - 1);
                    combined.add(str);
                    permute(index + str.length());
                    combined.remove(combined.size() - 1);
                    occurrences.put(str, occurrences.get(str) + 1);
                }
            }
        }
    }

    private static void print() {
        String actual = String.join("", combined);
        if (actual.contains(target)) {
            out.add(String.join(" ", combined));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> words = Arrays.stream(reader.readLine().split(",\\s+")).collect(Collectors.toList());
        target = reader.readLine();

        words.removeIf(word -> !target.contains(word));

        for (String substr : words) {
            occurrences.putIfAbsent(substr, 0);
            occurrences.put(substr, occurrences.get(substr) + 1);
            int index = target.indexOf(substr);
            while (index != -1) {
                table.putIfAbsent(index, new ArrayList<>());
                table.get(index).add(substr);
                index = target.indexOf(substr, index + 1);
            }
        }

        permute(0);
        out.forEach(System.out::println);
    }
}
