public class Solution45 {
    /**
     * 문제
     *  유저의 현재 피로도 k
     *  각 던전별 최소 필요 피로도, 소모 피로도가 담긴 2차원 배열 dungeons 가 매개변수로 주어질 때
     *  유저가 탐험할 수 있는 최대 던전 수를 반환하는 solution() 작성
     *
     * 제약 조건
     *  1 <= k <= 5000 자연수
     *  dungeons 세로 길이 (던전의 개수) 1 이상 8 이하
     *  dungeons의 각 행은 (최소 필요 피로도, 소모 피로도)
     *  최소 필요 피로도는 항상 소모 피로도보다 크거나 같음
     *  최소 필요 피로도와 소모 피로도는 1 이상 1,000이하 자연수
     *  서로 다른 던전의 (최소 필요 피로도, 소모 피로도)가 같을 수 있음
     *
     * 입출력 예시
     *  int k = 80;
     *  int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
     *  result = 3;
     */
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println(solution(k, dungeons));
    }

    private static int answer;
    private static int[][] Dungeons;
    private static boolean[] visited;

    // 백트래킹을 위한 DFS
    private static void backtrack(int k, int cnt) {
        for (int i = 0; i < Dungeons.length; i++) {
            // ❶ 현재 피로도(k)가 i번째 던전의 최소 필요 피로도보다 크거나 같고,
            // i번째 던전을 방문한 적이 없다면
            if (!visited[i] && k >= Dungeons[i][0]) {
                visited[i] = true; // i번째 던전을 방문 처리
                // ❷ 현재까지의 최대 탐험 가능 던전 수와
                // i번째 던전에서 이동할 수 있는 최대 탐험 가능 던전 수 중 큰 값을 선택하여 업데이트
                backtrack(k - Dungeons[i][1], cnt + 1);
                answer = Math.max(answer, cnt + 1);
                visited[i] = false; // i번째 던전을 다시 방문 취소
            }
        }
    }

    public static int solution(int k, int[][] dungeons) {
        answer = 0;
        Dungeons = dungeons;
        // ❸ 던전 방문 여부를 저장할 배열
        visited = new boolean[dungeons.length];

        backtrack(k, 0); // ❹ DFS 메소드 수행

        return answer;
    }
}
