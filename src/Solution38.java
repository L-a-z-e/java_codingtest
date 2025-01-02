public class Solution38 {
    /**
     * 문제
     *  네트워크 - 컴퓨터 상호 간에 정보를 교환하도록 연결된 형태
     *  A,B 가 직접 연결, B,C가 직접 연결되어있을 때 A,C는 간접 연결되어있어 정보 교환 가능하고 같은 네트워크에 있다고 할 수 있음
     *  컴퓨터 개수가 n 연결 정보가 담긴 2차원배열 computers가 주어질 때 네트워크 개수를 반환하는 solution() 작성
     *
     * 제약조건
     *  1 <= n <= 200
     *  각 컴퓨터는 0부터 n-1인 정수로 표현
     *  i번 컴퓨터와 j번 컴퓨터가 연결되어있으면 computers[i][j] 를 1로 표현
     *  computers[i][i] 는 항상 1
     *
     * 입출력 예시
     *  int n = 3;
     *  int[][] computers = {{1,1,0}, {1,1,0}, {0,0,1}};
     *  result = 2
     *
     *  int n2 = 3;
     *  int[][] computers2 = {{1,1,0}, {1,1,1}, {0,1,1}};
     *  result = 1
     */
    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1,1,0}, {1,1,0}, {0,0,1}};
        int n2 = 3;
        int[][] computers2 = {{1,1,0}, {1,1,1}, {0,1,1}};
        System.out.println(solution(n, computers));
        System.out.println(solution(n2, computers2));
    }

    // 풀이
    private static boolean[] visit;
    private static int[][] computer;

    private static void dfs(int now) {
        visit[now] = true; // ❶ 현재 노드 방문 처리
        for (int i = 0; i < computer[now].length; i++) {
            // ❷ 연결되어 있으며 방문하지 않은 노드라면
            if (computer[now][i] == 1 && !visit[i]) {
                dfs(i); // ❸ 해당 노드를 방문하러 이동
            }
        }
    }

    public static int solution(int n, int[][] computers) {
        int answer = 0;
        computer = computers;
        visit = new boolean[n]; // ❹ 방문 여부를 저장할 배열

        for (int i = 0; i < n; i++) {
            if (!visit[i]) { // ❺ 아직 방문하지 않은 노드라면 해당 노드를 시작으로 깊이 우선 탐색 진행
                dfs(i);
                answer++; // ❻ DFS로 연결된 노드들을 모두 방문하면서 네트워크 개수 증가
            }
        }

        return answer;
    }
}
