import java.util.Arrays;
import java.util.HashSet;

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
        int[] numbers = new int[]{2,1,3,4,1};
        System.out.println(Arrays.toString(solution(numbers)));
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
}
