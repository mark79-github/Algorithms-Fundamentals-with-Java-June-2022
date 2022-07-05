import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RecursiveDrawing {
    private static void calcRow(int row) {
        if (row < 1) {
            return;
        }
        drawRow(row, '*');
        calcRow(row - 1);
        drawRow(row, '#');
    }

    private static void drawRow(int maxSigns, char sign) {
        for (int i = 0; i < maxSigns; i++) {
            System.out.print(sign);
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int numberOfSigns = Integer.parseInt(line);

        calcRow(numberOfSigns);
    }
}
