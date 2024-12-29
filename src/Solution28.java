import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class Solution28 {
    /**
     * 문제
     *  2진 트리 모양의 초원
     *  루트 노드 = 양
     *  노드를 방문할때마다 양과 늑대가 따라옴
     *  양의 수 <= 늑대 수 인경우 모두 잡아먹힘
     *  잡아먹히지 않게 최대한 많은 수의 양을 모아서 다시 루트 노드로 돌아오려면?
     *  각 노드에 있는 양 또는 늑대에 대한 정보가 담긴 배열 info
     *  각 노드들의 연결 관계를 담은 2차원 배열 edges
     *  각 노드를 방문하면서 모을 수 있는 양은 최대 몇마리인지를 반환하는 solution() 작성
     *
     * 제약조건
     * 2 <= info <= 17
     * info의 원소는 0 또는 1
     * 0은 양, 1은 늑대
     * info[0] = 0
     * edges row = info.length - 1
     * edges col = 2
     * edges의 각 행은 부모 - 자식 노드 번호 형태
     *
     * 입출력 예시
     *  int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
     *  int[][] egdes = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
     *  result = 5;
     */
    public static void main(String[] args) {
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        System.out.println(solution(info, edges));
    }

    // 현재 위치, 양의 수, 늑대의 수 방문한 노드 저장을 위한 클래스
    private static class Info {
        int node, sheep, wolf;
        HashSet<Integer> visited;

        public Info(int node, int sheep, int wolf, HashSet<Integer> visited) {
            this.node = node;
            this.sheep = sheep;
            this.wolf = wolf;
            this.visited = visited;
        }
    }

    private static ArrayList<Integer>[] tree; // 트리 정보를 저장할 인접리스트

    // ❶ 트리 구축 메소드
    private static void buildTree(int[] info, int[][] edges) {
        tree = new ArrayList[info.length];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }
    }

    public static int solution(int[] info, int[][] edges) {
        buildTree(info, edges); // ❷ 트리 생성

        int answer = 0; // ❸ 최대 양의 수를 저장할 변수

        // ❹ BFS를 위한 큐 생성 및 초기 상태 설정
        ArrayDeque<Info> queue = new ArrayDeque<>();
        queue.add(new Info(0, 1, 0, new HashSet<>()));

        // BFS(너비 우선 탐색) 시작
        while (!queue.isEmpty()) {
            // ❺ 큐에서 현재 상태를 꺼냄
            Info now = queue.poll();
            // ❻ 최대 양의 수 업데이트
            answer = Math.max(answer, now.sheep);
            // ❼ 방문한 노드 집합에 현재 노드의 이웃 노드 추가
            now.visited.addAll(tree[now.node]);

            // ❽ 인접한 노드들에 대해 탐색
            for (int next : now.visited) {
                // ❾ 기존 해시셋의 데이터를 복사하고 현재 방문한 정점을 해시셋에서 제거
                HashSet<Integer> set = new HashSet<>(now.visited);
                set.remove(next);

                if (info[next] == 1) { // ➓ 늑대일 경우
                    if (now.sheep != now.wolf + 1) {
                        queue.add(new Info(next, now.sheep, now.wolf + 1, set));
                    }
                }
                else { // ⓫ 양일 경우
                    queue.add(new Info(next, now.sheep + 1, now.wolf, set));
                }
            }
        }

        return answer;
    }
}
