import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Solution31 {
    /**
     * 문제
     *  N마리 포켓몬 중 N/2 마리
     *  같은 종류의 포켓몬은 같은 번호를 가짐
     *  N마리 포켓몬 종류 번호가 담긴 nums가 매개변수로 주어질 때 가장 많은 종류의 포켓몬을 선택하는 방법을 찾아
     *  포켓몬 종류 번호의 개수를 반환하는 solution() 함수 완성
     *
     * 제약조건
     *  nums는 1차원 배열
     *  1 <= nums.length <= 10,000 && 짝수
     *  포켓몬의 종류 번호 1 ~ 200,000 자연수
     *  가장 많은 포켓몬을 선택하는 방법이 여러 가지 일때에도 최댓값 하나만 반환하면 됨
     *
     * 입출력 예시
     *  int[] nums = {3, 1, 2, 3}
     *  result = 2
     *  int[] nums2 = {3, 3, 3, 2, 2, 4}
     *  result = 3
     *  int[] nums3 = {3, 3, 3, 2, 2, 2}
     *  result = 2
     */
    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 3};
        int[] nums2 = {3, 3, 3, 2, 2, 4};
        int[] nums3 = {3, 3, 3, 2, 2, 2};

        System.out.println(solution(nums));
        System.out.println(solution(nums2));
        System.out.println(solution(nums3));
    }

    // 풀이
    public static int solution(int[] nums) {
        // ❶ nums 리스트에서 중복을 제거한 집합(set)을 구함
        HashSet<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toCollection(HashSet::new));
        // ❷ 폰켓몬의 총 수
        int n = nums.length;
        // ❸ 선택할 폰켓몬의 수
        int k = n / 2;
        // ❹ 중복을 제거한 폰켓몬의 종류 수와 선택할 폰켓몬의 수 중 작은 값 반환
        return Math.min(k, set.size());
    }
}
