import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        int N;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.nextLine();
        String[] crops = new String[N];
        for (int i = 0; i < N; i++) {
            crops[i] = sc.nextLine().trim();
        }
        String[] crops1 = new String[N];
        if (N >= 0) System.arraycopy(crops, 0, crops1, 0, N);
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < crops[0].length(); j++) {
                if (first_check(crops, i, j)) {
                    crops[i] = crops[i].substring(0, j) + replace(crops, i, j) + crops[i].substring(j + 1);
                    count++;
                }
            }
        }
        int count1 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < crops1[0].length(); j++) {
                if (first_check(crops1, i, j) || both_equal(crops1, i, j)) {
                    crops1[i] = crops1[i].substring(0, j) + replace(crops1, i, j) + crops1[i].substring(j + 1);
                    count1++;
                }
            }
        }
        System.out.println(Math.min(count, count1));
    }

    private static char replace(String[] crops, int row, int col) {
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (check(crops, ch, row, col)){
                return ch;
            }
        }
        return 'z';
    }

    private static boolean both_equal(String[] crops, int row, int col) {
        return (row + 1 < crops.length && crops[row].charAt(col) == crops[row + 1].charAt(col)) &&
                (col + 1 < crops[0].length() && crops[row].charAt(col) == crops[row].charAt(col + 1));
    }

    private static boolean check(String[] crops, char ch, int row, int col) {
        boolean upper_cell = true, bottom_cell = true, right_cell = true, left_cell = true;
        if (row - 1 >= 0) {
            left_cell = ch != crops[row - 1].charAt(col);
        }
        if (col - 1 >= 0) {
            upper_cell = ch != crops[row].charAt(col - 1);
        }
        if (row + 1 < crops.length) {
            bottom_cell = ch != crops[row + 1].charAt(col);
        }
        if (col + 1 < crops[0].length()) {
            right_cell = ch != crops[row].charAt(col + 1);
        }
        return (upper_cell && bottom_cell && right_cell && left_cell);
    }

    private static boolean first_check(String[] crops, int row, int col) {
        boolean upper_cell = false;
        boolean left_call = false;
        if (row - 1 >= 0) {
            left_call = (crops[row].charAt(col) == crops[row - 1].charAt(col));
        }
        if (col - 1 >= 0) {
            upper_cell = (crops[row].charAt(col) == crops[row].charAt(col - 1));
        }
        return upper_cell || left_call;
    }
}