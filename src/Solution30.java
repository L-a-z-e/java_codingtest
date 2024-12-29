import java.util.ArrayList;
import java.util.Arrays;

public class Solution30 {
    /**
     * 문제
     *  union(x, y) : x와 y가 속한 두 집합을 합침
     *  find(x) : x가 속한 집합의 대표 원소를 찾음
     *  operations : 수행할 연산을 의미 (2종류)
     *  [0,1,2] 는 노드 1과 노드2에 대해 union 연산 수행
     *  [1,1,3] 노드 1과 3이 같은 집합에 속해있으면 true 아니면 false 반환하는 연산
     *
     *  초기 노드는 부모 노드를 자신의 값으로 설정했다 가정
     *  각 집합의 루트 노드를 기준으로 루트 노드가 작은 노드를 더 큰 노드의 자식으로 연결하는 방법 사용
     *  operations에 있는 연산에 대한 결과를 연산 순서대로 담은 Boolean배열을 반환하는 solution() 작성
     *
     * 제약조건
     *  0<= k <= 1,000 노드의 개수
     *  1 <= len(operations) <= 100,000
     *  operations[i][0] 은 'u' or 'f'
     *  0은 union 연산
     *  1은 equals 연산
     *  x, y 0 이상 k-1 이하 정수
     *  연산은 항상 유효함
     *
     * 입출력 예시
     *  int k = 3
     *  int[][] operations = {{0, 0, 1}, {0, 1, 2}, {1, 1, 2}};
     *  result = true
     *
     *  int k2 = 4
     *  int[][] operations2 = {{0, 0, 1}, {1, 1, 2}, {0, 1, 2}, {1, 0, 2}};
     *  result = {false, true}
     */
    public static void main(String[] args) {
        int[][] operations1 = {{0, 0, 1}, {0, 1, 2}, {1, 1, 2}};
        System.out.println(Arrays.toString(solution(3, operations1)));
        int[][] operations2 = {{0, 0, 1}, {1, 1, 2}, {0, 1, 2}, {1, 0, 2}};
        System.out.println(Arrays.toString(solution(3, operations2)));
    }

    // 부모 저장을 위한 배열
    private static int[] parent;

    // 루트 노드를 찾는 메소드
    private static int find(int x) {
        // 만약 x의 부모가 자기 자신이면, 즉 x가 루트 노드라면 x를 반환
        if (parent[x] == x)
            return x;
        // 그렇지 않다면 x의 부모를 찾아서 parent[x]에 저장합니다.
        parent[x] = find(parent[x]);
        return parent[x]; // 찾은 루트 노드를 반환
    }

    private static void union(int x, int y) {
        int root1 = find(x); // x가 속한 집합의 루트 노드 찾기
        int root2 = find(y); // y가 속한 집합의 루트 노드 찾기

        parent[root2] = root1; // y가 속한 집합을 x가 속한 집합에 합침
    }

    // 이 부분을 변경해서 실행해보세요.
    private static Boolean[] solution(int k, int[][] operation) {
        // 노드의 수 만큼 배열 생성
        parent = new int[k];
        // 처음에는 각 노드가 자기 자신을 부모로 가지도록 초기화
        for (int i = 0; i < k; i++) {
            parent[i] = i;
        }

        ArrayList<Boolean> answer = new ArrayList<>();

        for (int[] op : operation) {
            if (op[0] == 0) { // 0 연산이면
                union(op[1], op[2]);
            }
            else { // 1 연산이면
                answer.add(find(op[1]) == find(op[2]));
            }
        }

        return answer.toArray(new Boolean[0]);
    }
}
