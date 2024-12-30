import java.util.Arrays;

public class Solution33 {
    /**
     * 문제
     *  n 개의 섬 사이에 다리를 건설하는 비용 costs가 주어질 때
     *  최소 비용으로 모든 섬이 서로 통행하는 solution() 작성
     *  다리를 여러번 건너도 목표 지점에 도달할 수만 있으면 통행할 수 있다고 봄 A -> B -> C 이면 A <-> C 통행 가능
     *
     * 제약조건
     *  1 <= n <= 100
     *  costs.length <= ((n-1)*n)/2
     *  임의의 i에 대해 costs[i][0] 과 costs[i][1] 에는 다리가 연결되는 두 섬의 번호가 들어있고 costs[i][2] 는 이때 비용
     *  같은 연결은 두 번 주어지지 않음 0, 1 <-> 1, 0 은 한 번만 주어짐
     *  연결할 수 없는 섬은 주어지지 않음
     *
     * 입출력 예
     *  int n = 4
     *  int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}}
     *  result = 4
     */
    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};

        System.out.println(solution(n, costs));
    }

    // 풀이
    private static int[] parent;

    private static int find(int x) {
        // ❶ x가 속한 집합의 루트 노드 찾기
        if (parent[x] == x)
            return x;
        // ❷ 경로 압축: x의 부모를 루트로 설정
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        // ❸ 두 집합을 하나의 집합으로 합치기
        int root1 = find(x);
        int root2 = find(y);
        parent[root2] = root1;
    }

    public static int solution(int n, int[][] costs) {
        // ❹ 비용을 기준으로 다리를 오름차순 정렬
        Arrays.sort(costs, (o1, o2) -> Integer.compare(o1[2], o2[2]));

        // ❺ parent 배열 초기화
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int answer = 0; // 최소 신장 트리의 총 비용
        int edges = 0; // 연결된 다리의 수

        for (int[] edge : costs) {
            // ❻ n - 1개의 다리가 연결된 경우 모든 섬이 연결됨
            if (edges == n - 1)
                break;

            // ❼ 현재 다리가 연결하는 두 섬이 이미 연결되어 있는지 확인
            if (find(edge[0]) != find(edge[1])) {
                // ❽ 두 섬을 하나의 집합으로 연결함
                union(edge[0], edge[1]);
                // ❾ 현재 다리의 건설 비용을 비용에 추가
                answer += edge[2];
                // ➓ 사용된 다리의 수 1증가
                edges++;
            }
        }

        return answer;
    }
}
