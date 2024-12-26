import java.util.Arrays;

public class Solution25 {
    /**
     * 문제
     *  이진 트리를 표현한 리스트 nodes를 인자로 받음
     *  해당 이진 트리에 대해 전위, 중위, 후위 순회 결과를 반환하는 solution 함수
     *
     * 제약조건
     *  입력 노드값의 개수는 1개 이상 1,000개 이하
     *  노드값은 정수형이며, 중복되지 않는다.
     *
     * 입출력 예시
     * int[] nodes = {1, 2, 3, 4, 5, 6, 7};
     * result = {"1 2 4 5 3 6 7", "4 2 5 1 6 3 7", "4 5 2 6 7 3 1"}
     */
    public static void main(String[] args) {
        int[] nodes = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(solution(nodes)));
    }
    
    public static String[] solution(int[] nodes) {
        String[] result = new String[3];
        result[0] = preorder(nodes, 0).trim(); // 마지막 공백 제거
        result[1] = inorder(nodes, 0).trim(); // 마지막 공백 제거
        result[2] = postorder(nodes, 0).trim(); // 마지막 공백 제거
        return result;
    }

    private static String preorder(int[] nodes, int idx) {
        if (idx >= nodes.length) { // idx가 범위를 벗어나면 빈 문자열 반환
            return "";
        }

        // 루트 노드 -> 왼쪽 서브 트리 -> 오른쪽 서브 트리 순으로 재귀 호출하여 결과를 이어붙임
        return nodes[idx] + " " +
                preorder(nodes, 2 * idx + 1) +
                preorder(nodes, 2 * idx + 2);
    }

    private static String inorder(int[] nodes, int idx) {
        if (idx >= nodes.length) { // idx가 범위를 벗어나면 빈 문자열 반환
            return "";
        }

        // 왼쪽 서브 트리 -> 루트 노드 -> 오른쪽 서브 트리 순으로 재귀 호출하여 결과를 이어붙임
        return inorder(nodes, 2 * idx + 1) +
                nodes[idx] + " " +
                inorder(nodes, 2 * idx + 2);
    }

    private static String postorder(int[] nodes, int idx) {
        if (idx >= nodes.length) { // idx가 범위를 벗어나면 빈 문자열 반환
            return "";
        }

        // 왼쪽 서브 트리 -> 오른쪽 서브 트리 -> 루트 노드 순으로 재귀 호출하여 결과를 이어붙임
        return postorder(nodes, 2 * idx + 1) +
                postorder(nodes, 2 * idx + 2) +
                nodes[idx] + " ";
    }

}
