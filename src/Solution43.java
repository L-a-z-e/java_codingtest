import java.util.ArrayList;

public class Solution43 {
    /**
     * 문제
     *  정수 N을 입력받아 1부터 N까지의 숫자 중 합이 10이 되는 조합을
     *  리스트로 반환하는 solution() 작성
     *
     * 제약 조건
     *  백트래킹 활용
     *  숫자 조합은 오름차순으로 정렬되어있어야함
     *  같은 숫자는 한 번만 선택할 수 있음
     *  1 <= N <= 10 정수
     *
     * 입출력 예시
     *  int N = 5;
     *  result = [[1,2,3,4], [1,4,5], [2,3,5]]
     *
     *  int N = 2;
     *  result = []
     *
     *  int N = 7;
     *  result = [[1,2,3,4], [1,2,7], [1,3,6], [1,4,5], [2,3,5], [3,7], [4,6]]
     */
    public static void main(String[] args) {
        System.out.println(solution(5));
        System.out.println(solution(2));
        System.out.println(solution(7));
    }

    // ❶ 조합 결과를 담을 리스트
    private static ArrayList<ArrayList<Integer>> result;
    private static int n;
    private static void backtrack(int sum, ArrayList<Integer> selectedNums, int start) {
        // ❷ 합이 10이 되면 결과 리스트에 추가
        if (sum == 10) {
            result.add(selectedNums);
            return;
        }

        // ❸ 다음에 선택할 수 있는 숫자들을 하나씩 선택하면서
        for (int i = start; i <= n; i++) {
            // ❹ 선택한 숫자의 합이 10보다 작거나 같으면
            if (sum + i <= 10) {
                ArrayList<Integer> list = new ArrayList<>(selectedNums);
                list.add(i);
                // ❺ 백트래킹 메소드를 재귀적으로 호출합니다.
                backtrack(sum + i, list, i + 1);
            }
        }
    }

    private static ArrayList<ArrayList<Integer>> solution(int N) {
        result = new ArrayList<>();
        n = N;

        // ❻ 백트래킹 메소드 호출
        backtrack(0, new ArrayList<>(), 1);

        return result;
    }
}
