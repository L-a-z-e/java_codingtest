import java.util.ArrayList;
import java.util.Arrays;

public class Solution29 {
    /**
     * 문제
     * 프렌즈를 두 팀으로 나누고 각 팀이 같은 곳을 다른 순서로 방문하도록 해 먼저 순회를 마친 팀이 승리
     * 방문할 곳의 2차원 좌표 값을 구하게 하고
     * 각 장소를 이진 트리의 노드가 되도록 구성한 후 순회 방법을 힌트로 주어 각 팀이 스스로 경로를 찾도록 함
     * 트리를 구성하는 모든 노드의 x,y 좌표는 정수
     * 모든 노드는 서로 다른 x값을 갖는다
     * 같은 레벨에 있는 노드는 같은 y좌표를 갖는다
     * 자식 노드의 y값은 항상 부모 노드보다 작다
     * 임의의 노드 V의 왼쪽 하위 트리에 있는 모든 노드의 x값은 V의 x값보다 작다
     * 임의의 노드 V 오른쪽 하위 트리에 있는 모든 노드의 x값은 V의 x값보다 크다.
     * nodeinfo 가 매개변수로 주어질 때 노드들로 구성된 이진 트리를 전위, 후위 순회 한 결과를 2차원 배열에 순서대로 담아 반환하는 solution() 작성
     *
     * 제약 조건
     *  nodeinfo는 이진 트리를 구성하는 각 노드의 좌표가 1번부터 순서대로 들어있는 2차원 배열
     *  1 <= nodeinfo.length <= 10,000
     *  nodeinfo[i] 는 i+1번 노드의 좌표 (x좌표, y좌표)
     *  모든 노드의 좌표 값은 0 이상 100,000이하 정수
     *  트리의 깊이가 1,000 이하인 경우만 입력으로 주어짐
     *
     * 입출력 예시
     * int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
     * result = {{7, 4, 6, 9, 1, 8, 5, 2, 3}, {9, 6, 5, 8, 1, 4, 3, 2, 7}}
     */
    public static void main(String[] args) {

    }


    // 풀이
    // ❶ Node 클래스 정의
    private static class Node {
        int x, y, num; // 노드의 좌표, 번호 저장
        Node left, right; // 노드의 왼쪽, 오른쪽 자식 노드

        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    // ❷ 이진 트리 생성 메소드
    private static Node makeBT(int[][] nodeinfo) {
        // ❸ 각 노드에 대한 좌표, 번호를 배열에 저장
        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
        }

        // ❹ y 기준으로 내림차순 정렬, y가 같다면 x를 기준으로 오름차순 정렬
        Arrays.sort(nodes, (o1, o2) -> {
            if (o1.y == o2.y)
                return Integer.compare(o1.x, o2.x);
            return Integer.compare(o2.y, o1.y);
        });

        Node root = nodes[0]; // 맨 처음 노드는 무조건 루트

        for (int i = 1; i < nodes.length; i++) {
            Node parent = root;
            while (true) {
                // ❺ 부모 노드의 x좌표가 더 크면 왼쪽으로
                if (nodes[i].x < parent.x) {
                    if (parent.left == null) {
                        parent.left = nodes[i];
                        break;
                    }
                    else {
                        parent = parent.left;
                    }
                }
                // ❻ 부모 노드의 x좌표가 더 작거나 같으면 오른쪽으로
                else {
                    if (parent.right == null) {
                        parent.right = nodes[i];
                        break;
                    }
                    else {
                        parent = parent.right;
                    }
                }
            }
        }

        return nodes[0];
    }

    // ❼ 전위 순회 메소드
    private static void preOrder(Node curr, ArrayList<Integer> answer) {
        if (curr == null) {
            return;
        }
        answer.add(curr.num);
        preOrder(curr.left, answer);
        preOrder(curr.right, answer);
    }

    // ❽ 후위 순회 메소드
    private static void postOrder(Node curr, ArrayList<Integer> answer) {
        if (curr == null) {
            return;
        }
        postOrder(curr.left, answer);
        postOrder(curr.right, answer);
        answer.add(curr.num);
    }

    public int[][] solution(int[][] nodeinfo) {
        Node root = makeBT(nodeinfo); // 이진트리 생성

        ArrayList<Integer> preOrderList = new ArrayList<>();
        preOrder(root, preOrderList); // 전위 순회
        ArrayList<Integer> postOrderList = new ArrayList<>();
        postOrder(root, postOrderList); // 후위 순회

        // 결과 반환
        int[][] answer = new int[2][nodeinfo.length];
        answer[0] = preOrderList.stream().mapToInt(Integer::intValue).toArray();
        answer[1] = postOrderList.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }
}
