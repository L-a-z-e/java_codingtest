import java.util.HashSet;

public class Solution18 {
    /**
     * 문제
     *  n 개의 양의 정수로 이루어진 배열 arr
     *  arr의 두 수의 합이 target인 값이 있는지? boolean 반환하는 solution 작성
     *
     * 제약 조건
     *  2 <= n <= 10,000 자연수
     *  1 <= arr[k] <= 10,000 자연수
     *  arr은 중복된 원소 없음
     *  1 <= target <= 20,000 자연수
     *
     * 입출력 예시
     *  arr = {1, 2, 3, 4, 8}
     *  target = 6
     *  return = true
     *
     *  arr = {2, 3, 5, 9}
     *  target = 10
     *  return = false
     *
     */
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 8};
        int target1 = 6;
        int[] arr2 = {2, 3, 5, 9};
        int target2 = 10;

        System.out.println(solution(arr1, target1));
        System.out.println(solution(arr2, target2));
        System.out.println(solution2(arr1, target1));
        System.out.println(solution2(arr2, target2));
    }

    // 풀이
    private static boolean solution(int[] arr, int target) {
        HashSet<Integer> hashSet = new HashSet<>();

        for (int i : arr) {
            // ❶ target에서 현재 원소를 뺀 값이 hashSet에 있는지 확인
            if (hashSet.contains(target - i)) {
                return true;
            }

            // ❷ hashSet에 현재 값 저장
            hashSet.add(i);
        }

        return false;
    }

    /**
     * 분석 입력값 10,000 이므로 n^2 까지는 문제 없을 듯
     *
     * 배열의 두 원소의 합이 target인지 확인하고 return true / false
     *
     */
    public static boolean solution2(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return true;
                }
            }
        }

        return false;

    }

}
