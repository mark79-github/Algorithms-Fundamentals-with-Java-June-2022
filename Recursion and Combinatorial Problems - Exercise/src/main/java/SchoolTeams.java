import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SchoolTeams {
    private static final int MIN_GIRLS = 3;
    private static final int MIN_BOYS = 2;
    private static String[] girls;
    private static String[] combGirls;
    private static final List<String[]> listGirls = new ArrayList<>();
    private static final List<String[]> listBoys = new ArrayList<>();
    private static String[] boys;
    private static String[] combBoys;
    private static final StringBuilder builder = new StringBuilder();

    private static void combinationsGirls(int index, int start) {
        if (index >= MIN_GIRLS) {
            listGirls.add(combGirls.clone());
        } else {
            for (int i = start; i < girls.length; i++) {
                combGirls[index] = girls[i];
                combinationsGirls(index + 1, i + 1);
            }
        }
    }
    private static void combinationsBoys(int index, int start) {
        if (index >= MIN_BOYS) {
            listBoys.add(combBoys.clone());
        } else {
            for (int i = start; i < boys.length; i++) {
                combBoys[index] = boys[i];
                combinationsBoys(index + 1, i + 1);
            }
        }
    }
    private static void generateAllCombinations() {
        for (String[] girls : listGirls) {
            String girlsCollection = String.join(", ", girls);
            for (String[] boys : listBoys) {
                String boysCollection = String.join(", ", boys);
                builder.append(girlsCollection)
                        .append(", ")
                        .append(boysCollection)
                        .append(System.lineSeparator());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        girls = reader.readLine().split(",\\s+");
        boys = reader.readLine().split(",\\s+");

        combGirls = new String[MIN_GIRLS];
        combBoys = new String[MIN_BOYS];

        combinationsGirls(0, 0);
        combinationsBoys(0, 0);
        generateAllCombinations();

        System.out.print(builder.toString().trim());
    }
}
