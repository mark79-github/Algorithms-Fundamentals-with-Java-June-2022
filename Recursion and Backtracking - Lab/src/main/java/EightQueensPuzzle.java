public class EightQueensPuzzle {

    private static boolean isConsistent(int[] q, int n) {
        for (int i = 0; i < n; i++) {
            if (q[i] == q[n]) { // same column
                return false;
            }
            if ((q[i] - q[n]) == (n - i)) { // same major diagonal
                return false;
            }
            if ((q[n] - q[i]) == (n - i)) { // same minor diagonal
                return false;
            }
        }
        return true;
    }

    private static void printQueens(int[] q) {
        int n = q.length;
        for (int i = 0; i < q.length; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(q[i] == j ? "* " : "- ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void enumerate() {
        int[] a = new int[8];
        enumerate(a, 0);
    }

    private static void enumerate(int[] q, int k) {
        int n = q.length;
        if (k == n) {
            printQueens(q);
        } else {
            for (int i = 0; i < n; i++) {
                q[k] = i;
                if (isConsistent(q, k)) {
                    enumerate(q, k + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        enumerate();
    }
}
