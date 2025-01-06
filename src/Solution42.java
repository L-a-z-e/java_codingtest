import java.util.ArrayList;

public class Solution42 {
    /**
     * 문제
     *  n개의 송전탑이 트리의 형태로 되어있음
     *  전선 중 하나를 끊어서 전력망 네트워크를 2개로 분할 하려고 함
     *  두 전력망이 갖는 송전탑 개수를 최대한 비슷하게 맞추려 함
     *  송전탑 개수 n, 전선 정보 wires가 매개변수로 주어짐
     *  송전탑 개수가 비슷하도록 전선 중 하나를 끊어 두개로 나누고, 두 전력망이 가지고 있는 송전탑 개수 차이를 반환하는 solution() 작성
     *
     * 제약조건
     *  2 <= n <= 100 자연수
     *  wires.length <= n-1 정수형 2차원 배열
     *  1 <= v1 < v2 <= n (v1, v2) 연결되어있다는 의미
     *
     * 입출력 예시
     *  int n = 9;
     *  int[][] wires = {{1,3}, {2,3}, {3,4}, {4,5}, {4,6}, {4,7}, {7,8}, {7,9}};
     *  result = 3;
     *
     *  int n2 = 4;
     *  int[][] wires2 = {{1,2}, {2,3}, {3,4}};
     *  result = 0
     *
     *  int n3 = 7;
     *  int[][] wires3 = {{1,2}, {2,7}, {3,7}, {3,4}, {4,5}, {6,7}};
     *  result = 1
     *
     */
    public static void main(String[] args) {
        int n = 9;
        int[][] wires = {{1,3}, {2,3}, {3,4}, {4,5}, {4,6}, {4,7}, {7,8}, {7,9}};
        int n2 = 4;
        int[][] wires2 = {{1,2}, {2,3}, {3,4}};
        int n3 = 7;
        int[][] wires3 = {{1,2}, {2,7}, {3,7}, {3,4}, {4,5}, {6,7}};

        System.out.println(solution(n, wires));
        System.out.println(solution(n2, wires2));
        System.out.println(solution(n3, wires3));
    }

    private static boolean[] visited;
    private static ArrayList<Integer>[] adjList;
    private static int N, answer;

    public static int solution(int n, int[][] wires) {
        N = n;
        answer = n - 1;

        // ❶ 전선의 연결 정보를 저장할 인접 리스트 초기화
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        // ❷ 전선의 연결 정보를 인접 리스트에 저장
        for (int[] wire : wires) {
            adjList[wire[0]].add(wire[1]);
            adjList[wire[1]].add(wire[0]);
        }

        visited = new boolean[n + 1];

        // ❸ 깊이 우선 탐색 시작
        dfs(1);

        return answer;
    }

    private static int dfs(int now) {
        visited[now] = true;

        // ❹ 자식 노드의 수를 저장하고 반환할 변수 선언
        int sum = 0;
        // ❺ 연결된 모든 전선을 확인
        for (int next : adjList[now]) {
            if (!visited[next]) {
                // ❻ (전체 노드 - 자식 트리의 노드 수) - (자식 트리의 노드 수) 의 절대값이 가장 작은 값을 구함
                int cnt = dfs(next); // 자식 트리가 가진 노드의 수
                answer = Math.min(answer, Math.abs(N - cnt * 2));
                // ❼ 자식 노드의 수를 더함
                sum += cnt;
            }
        }

        // ❽ 전체 자식 노드의 수에 1(현재 now 노드)을 더해서 반환
        return sum + 1;
    }
}
