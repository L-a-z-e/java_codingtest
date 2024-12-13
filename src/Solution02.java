import java.util.Arrays;
import java.util.Collections;

public class Solution02 {
    /**
     * 정수 배열을 하나 받아서 중복값을 제거하고 내림차순으로 정렬해서 반환하는 solution() 함수 구현
     * 제약조건
     *  정수 배열의 길이는 2이상 10^5 이하
     *  정수 배열의 각 데이터 값은 -100000 이상 100000 이하
     *
     * 입출력 예시
     *  [4,2,2,1,3,4] -> [4,3,2,1]
     *
     *  시간복잡도 중복제거 O(N), 정렬 O(NlogN)
     */

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{4, 2, 2, 1, 3, 4})));
        System.out.println(Arrays.toString(solution(new int[]{2, 1, 1, 3, 2, 5, 4})));
    }

    private static int[] solution(int[] arr) {
        Integer[] result = Arrays.stream(arr).boxed().distinct().toArray(Integer[]::new); // ❶ 중복값 제거
        Arrays.sort(result, Collections.reverseOrder());                                  // ❷ 내림차순 정렬
        return Arrays.stream(result).mapToInt(Integer::intValue).toArray();  // int형 배열로 변경 후 반환
    }

    private static int[] solution2(int[] arr) {
        Integer[] result = Arrays.stream(arr).boxed().distinct().toArray(Integer[]::new);
        Arrays.sort(result, Collections.reverseOrder());
        return Arrays.stream(result).mapToInt(Integer::intValue).toArray();
    }
}
