import java.util.Arrays;

public class Solution01 {
    /**
     * 정수의 배열을 정렬해서 반환하는 solution() 함수 완성
     *
     * 제약조건
     *  정수 배열의 길이는 2이상 10^5 이하
     *  정수 배열의 각 데이터 값은 -100000 이상 100000 이하
     *
     * 입출력 예시
     *  [1, -5, 2, 4, 3] -> [-5, 1, 2, 3, 4]
     */

    // 데이터의 개수 최대 10^5 -> 제한 시간 3초이면 O(N^2)은 사용 불가능
    // 시간 복잡도 Arrays.sort -> nlogn

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, -5, 2, 4, 3})));
        System.out.println(Arrays.toString(solution(new int[]{2, 1, 1, 3, 2, 5, 4})));
        System.out.println(Arrays.toString(solution(new int[]{6, 1, 7})));
    }

    private static int[] solution(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    // 원본 배열 유지해야하는 경우 clone() 사용
    private static int[] solution2(int[] arr) {
        int[] clone = arr.clone();
        Arrays.sort(clone);
        return clone;
    }

    public static int[] solution3(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

}
