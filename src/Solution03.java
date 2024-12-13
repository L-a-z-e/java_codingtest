import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class Solution03 {
    /**
     * 정수 배열 numbers 주어짐.
     * numbers에서 서로 다른 인덱스에 있는 2개의 수를 더해 만들 수 있는 모든 수를 배열에 오름차순으로 담아 반환하는 solution() 작성
     *
     * 제약조건
     *  numbers 길이 2 이상 100 이하
     *  numbers의 모든 수는 0 이상 100 이하
     *
     * 입출력 예시
     *  [2,1,3,4,1] -> [2,3,4,5,6,7]
     */

    // 1. 배열에서 두 수를 선택하는 모든 경우의 수 구함
    // 2. 과정 1에서 구한 수를 새로운 배열에 저장하고 중복 제거
    // 3. 배열을 오름차순으로 정렬하고 반환
    public static void main(String[] args) {
//        int[] numbers = new int[]{2,1,3,4,1,1,3,4,5,10,20,32,77,81,1,1,3,4,5,10,20,32,77,81,2,1,3,4,1,1,3,4,5,10,20,32,77,81,1,1,3,4,5,10,20,32,77,81,1,3,29,23,88,4,2,64,23,83,72,77,81,2,1,3,4,1,1,3,4,5,10,20,32,77,81,1,1,3,4,5,10,20,32,77,81,1,3,29,23,88,4,2,64,23,83,72};
        Random random = new Random();
        int[] numbers = IntStream.range(0, 1000).map(i -> random.nextInt(101)).toArray();
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(solution(numbers)));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        Set<Integer> sums = new HashSet<>();
        combine(numbers, 2, 0, new int[2], 0, sums);
        System.out.println(sums);
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static int[] solution(int[] numbers) {

        HashSet<Integer> set = new HashSet<>(); // ❶ 중복 값 제거를 위한 해쉬셋 생성

        // ❷ 두 수를 선택하는 모든 경우의 수를 반복문으로 구함
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                // ❸ 두 수를 더한 결과를 새로운 배열에 추가
                set.add(numbers[i] + numbers[j]);
            }
        }

        // ❹ 해쉬셋의 값을 오름차순 정렬하고 int[] 형태의 배열로 변환하여 반환
        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    public static void combine(int[] arr, int r, int start, int[] result, int depth, Set<Integer> sums) {

        if (depth == r) {
           int sum = 0;
           for (int value : result) {
               sum += value;
           }
           sums.add(sum);
           return;
        }

        for (int i = start; i < arr.length; i++) {
            result[depth] = arr[i];
            combine(arr, r, i+1, result, depth + 1, sums);
        }

    }
}
