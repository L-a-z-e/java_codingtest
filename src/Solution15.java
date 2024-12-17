import java.util.ArrayDeque;
import java.util.Queue;

public class Solution15 {

    /**
     * 문제
     *  N명의 사람이 원형으로 서있음
     *  각 사람은 1부터 N까지 번호표 갖고있음
     *  임의의 숫자 K가 주어졌을 때
     *  1번 기준으로 K번째 사람 제거
     *  K번째 다음 사람 기준으로 K번째 사람 제거 반복
     *  N과 K가 주어질때 마지막으로 남아있는 사람은?
     *
     * 제약조건
     *  N과 K는 1 이상 1000이하 자연수
     *
     * 입출력 예시
     *  N - 5, K - 2, return - 3
     */
    public static void main(String[] args) {

        int N = 5;
        int K = 2;

        System.out.println(solution(N,K));

    }

    public static int solution(int N, int K) {

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        while (queue.size() > 1) {
            for (int i = 0; i < K - 1; i++) {
                queue.add(queue.poll());
            }
            queue.poll();
        }

        return queue.poll();
    }

}
