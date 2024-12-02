import java.util.Collections;
import java.util.TreeSet;

public class Solution02_1 {
    public static void main(String[] args) {


    }

    private static int[] solution(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
        for (int num : arr) {
            set.add(num);
        }

        int[] result = new int[set.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = set.pollFirst();
        }

        return result;
    }
}
