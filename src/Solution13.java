import java.util.ArrayDeque;
import java.util.Stack;

public class Solution13 {
    /**
     * 문제
     *  N x N 크기 격자 게임
     *  오른쪽 바구니에 먼저뽑은 인형부터 아래 깔리고 같은모양 두개가 위 아래로 있으면 사라짐
     *  board [2차원배열], moves [크레인 작동시킬 위치가 담긴 배열] 이 주어질 때
     *  모두 작동시킨 이후 사라진 인형의 개수 반환
     *
     * 제약조건
     *  board - 2차원 배열 크기 5 x 5 ~ 30 x 30
     *  각 칸에는 0~100 사이의 정수가 담겨져 있고 같은 수는 같은 모향 인형 의미
     *  0은 빈칸 의미
     *  moves - 배열크기 1 이상 1000 이하
     *  moves 배열 원소들은 1이상 board 가로 크기 이하 자연수
     *
     * 입출력 예시
     *  board - {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}}
     *  moves - {1, 5, 3, 5, 1, 2, 1, 4}
     *  result - 4
     */
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        System.out.println(solution(board, moves));
        System.out.println(solution2(board, moves));
    }

    public static int solution(int[][] board, int[] moves) {
        // ❶ 각 열에 대한 스택을 생성합니다.
        Stack<Integer>[] lanes = new Stack[board.length];
        for (int i = 0; i < lanes.length; i++) {
            lanes[i] = new Stack<>();
        }

        // ❷ board를 역순으로 탐색하며, 각 열의 인형을 lanes에 추가합니다.
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] > 0) {
                    lanes[j].push(board[i][j]);
                }
            }
        }

        // ❸ 인형을 담을 bucket을 생성합니다.
        Stack<Integer> bucket = new Stack<>();

        // ❹ 사라진 인형의 총 개수를 저장할 변수를 초기화합니다.
        int answer = 0;

        // ❺ moves를 순회하며, 각 열에서 인형을 뽑아 bucket에 추가합니다.
        for (int move : moves) {
            if (!lanes[move - 1].isEmpty()) { // 해당 열에 인형이 있는 경우
                int doll = lanes[move - 1].pop();
                // ❻ 바구니에 인형이 있고, 가장 위에 있는 인형과 같은 경우
                if (!bucket.isEmpty() && bucket.peek() == doll) {
                    bucket.pop();
                    answer += 2;
                }
                else { // ❼ 바구니에 인형이 없거나, 가장 위에 있는 인형과 다른 경우
                    bucket.push(doll);
                }
            }
        }

        return answer;
    }

    /**
     * 인형 배열 각 열별로 스택처리
     * 바구니 스택처리
     * moves 배열 순회하며 각 원소 - 1 값 열의 스택에서 뽑기
     * 1 ) bucket의 최상단과 pop한 결과가 동일한지 비교해서 없애고 result ++
     * 2 ) return result
     */

    public static int solution2(int[][] board, int[] moves) {
        int result = 0;

        ArrayDeque<Integer>[] colStack = new ArrayDeque[board[0].length];
        ArrayDeque<Integer> bucket = new ArrayDeque<>();

        // board 배열 각 열별로 stack 에 push
        for (int i = 0; i < colStack.length; i++) {
            colStack[i] = new ArrayDeque<>();

            for(int j = board[0].length - 1; j >= 0; j--) {
                if(board[j][i] > 0) {
                    colStack[i].push(board[j][i]);
                }
            }
        }

        // move 배열 순회
        for (int move : moves) {
            if (!colStack[move - 1].isEmpty()) {
                if(!bucket.isEmpty() && bucket.peek() == colStack[move - 1].peek()) {
                    colStack[move - 1].pop();
                    bucket.pop();
                    result += 2;
                }
                else {
                    bucket.push(colStack[move - 1].pop());
                }
            }
        }
        return result;
    }
}
