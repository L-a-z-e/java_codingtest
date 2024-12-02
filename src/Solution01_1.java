import java.util.Arrays;

public class Solution01_1 {

    // 시간복잡도    bubble - n^2
    //            sort - nlogn
    public static void main(String[] args) {
        int[] arr = new int[100000];
        long start = System.currentTimeMillis();
        int[] bubble = bubbleSort(arr);
        long end = System.currentTimeMillis();

        System.out.println((end - start) + "ms");
        start = System.currentTimeMillis();
        int[] sort = doSort(arr);
        end = System.currentTimeMillis();

        System.out.println((end - start) + "ms");
        System.out.println(Arrays.equals(bubble, sort));
    }

    private static int[] bubbleSort(int[] arr) {
        int[] bubble = arr.clone();
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        return bubble;
    }

    private static int[] doSort(int[] arr) {
        int[] sort = arr.clone();
        Arrays.sort(sort);

        return sort;
    }

}
