import java.util.ArrayList;
import java.util.Arrays;

public class Solution04 {

    /**
     * 1번 ~ 마지막 문제까지 정답이 순서대로 저장된 배열 answer가 주어졌을 때 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 반환
     * 사람 1 - 1,2,3,4,5,1,2,3,4,5,...
     * 사람 2 - 2,1,2,3,2,4,2,5,2,1,2,3,2,4...
     * 사람 3 - 3,3,1,1,2,2,4,4,5,5,3,3,1,1,2,2,...
     * 제약조건
     *  시험은 최대 10000문제
     *  문제의 정답은 1,2,3,4,5 중 하나
     *  가장 높은 점수를 받은 사람이 여럿이라면 반환하는 값 오름차순 정렬
     *
     * 입출력 예시
     *  [1,2,3,4,5] -> [1]
     *  [1,3,2,4,2] -> [1,2,3]
     */

    // 시간복잡도 - O(N)
    public static void main(String[] args) {

        int[] answers = {1,2,3,4,5};
        System.out.println(Arrays.toString(solution(answers)));

        int[] answers2 = {1,3,2,4,2};
        System.out.println(Arrays.toString(solution(answers2)));

    }

    public static int[] solution(int[] answers) {
        // ❶ 수포자들의 패턴
        int[][] pattern = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        // ❷ 수포자들의 점수를 저장할 배열
        int[] scores = new int[3];

        // ❸ 각 수포자의 패턴과 정답이 얼마나 일치하는지 확인
        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < pattern.length; j++) {
                if (answers[i] == pattern[j][i % pattern[j].length]) {
                    scores[j]++;
                }
            }
        }
        // ❹ 가장 높은 점수 저장
        int maxScore = Arrays.stream(scores).max().getAsInt();
        // ❺ 가장 높은 점수를 가진 수포자들의 번호를 찾아서 리스트에 담음
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == maxScore) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
