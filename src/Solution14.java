import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;

public class Solution14 {
    /**
     * 문제
     *  처음 표의 행의 개수 n
     *  처음에 선택한 행의 위치 k
     *  수행한 명령어들이 담긴 문자배열 cmd
     *  모든 명령어 수행 후 와 처음 표의 상태를 비교해 삭제되지 않은 행 O, 삭제된 행 X로 표시해 문자열 형태로 반환하는 solution() 작성
     *  멸령어
     *   UX - 현재행기준 위로 X칸 선택
     *   DX - 현재행기준 아래로 X칸 선택
     *   C - 현재행 삭제 후 바로아래행 선택, 마지막행인경우 윗칸 선택
     *   Z - 가장 최근에 삭제한 행 원래대로 복구. 현재 선택행은 바뀌지 않음
     *
     *  제약조건
     *   5<=n<=1,000,000
     *   0<=k<n
     *   1<=cmd<=200,000
     *   X는 1이상 300,000 이하 자연수
     *   X에 쉼표 없음
     *   cmd에 모든 X들의 합이 1,000,000 이하
     *   표의 모든 행을 제거하는 경우는 없음
     *   표의 범위를 벗어나는 이동은 없음
     *   원래대로 복구할 행이 없을때 Z명령어 주어지는 경우 없음
     *   0행부터 n-1 행까지 해당되는 O, X를 순서대로 이어붙인 문자열로 반환
     *
     *   정확성 테스트 제약조건
     *    10초
     *    5<=n<=1,000
     *    1<= cmd.length <= 1,000
     *
     *  입출력 예시
     *   n - 8
     *   k - 2
     *   cmd - {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}
     *   result - "OOOOXOOO"
     *
     *   n - 8
     *   k - 2
     *   cmd - {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}
     *   result - "OOXOXOOO"
     */
    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        String[] cmd2 = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};

        System.out.println(solution(n, k, cmd));
        System.out.println(solution2(n, k, cmd));
        System.out.println(solution(n, k, cmd2));
        System.out.println(solution2(n, k, cmd2));
    }

    /**
     * input - int n, int k, String[] cmd
     *
     * result -> 삭제 여부
     * 삭제 이후에 원래 행을 복구하지만 현재 선택 위치와 차이가 있을 수 있음
     *
     * 문제 U 2 이런식으로 띄워져있는건 어떻게 분리? split
     *
     * 1. 인덱스 변경하고 삭제 -> 이후 변경하고 복원시 어떻게 되돌릴 것인지?
     * 2. 마지막 명령어까지 완료한 이후 원본 배열과 비교는 어떻게 할 것인지?
     *
     * 시간복잡도는 n^2 로 해결 안됨
     *
     * k 부터 cmd 명령어를 순회하며 적용한다
     * case "U X" 위로 X만큼 이동
     * case "D X" 아래로 X만큼 이동
     * case "C" 현재행이 마지막인지 확인 -> 마지막이면 현재행 삭제 후 위로 이동 아니면 현재행 삭제 후 아래로 이동
     * case "Z" 현재위치 관계 없이 이전에 지웠던 행 복구
     * 실제로 지우기보다는 지웠다는 플래그만 달아두는 것도 하나의 방법이 될듯
     *
     */
    public static String solution2(int n, int k, String[] cmd) {

        int index = k;
        int lastIndex = n;
        int[][] originalList = new int[n][2];
        int[][] result = new int[n][2];

        ArrayDeque<Integer> removeStack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            originalList[i][0] = i;
            originalList[i][1] = 1;
            result[i][0] = i;
            result[i][1] = 1;
        }

        for (String c : cmd) {
            if (c.startsWith("U")) {
                String[] splitC = c.split(" ");
                index -= Integer.parseInt(splitC[1]);
            }
            else if (c.startsWith("D")) {
                String[] splitC = c.split(" ");
                index += Integer.parseInt(splitC[1]);
            }
            else if (c.startsWith("C")) {
                if ( index == lastIndex ) {
                    removeStack.push(index);
                    result[index][1] = 0;
                    --index;
                    lastIndex = index;
                }
                else {
                    removeStack.push(index);
                    result[index][1] = 0;
                    ++index;
                }
            }
            else if (c.startsWith("Z")) {
                if(!removeStack.isEmpty()) {

                    int undoIndex = removeStack.pop();
                    result[undoIndex][1] = 1;

                    if (undoIndex > lastIndex)
                        lastIndex = undoIndex;

                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(result[i][1] == 1 ? "O" : "X");
        }

        return sb.toString();

    }
    // 평가 -> 삭제 이후에 index가 조정되는 것을 반여하지 못해서 예상과 다른 결과값 나옴
    // 다음 복습에서 이부분 개선해볼 방안 찾기

    public static String solution(int n, int k, String[] cmd) {
        // ❶ 삭제된 행의 인덱스를 저장하는 스택
        Stack<Integer> deleted = new Stack<>();

        // ❷ 각 행을 기준으로 연산에 따른 위치를 표시하기 위한 배열
        int[] up = new int[n + 2];
        int[] down = new int[n + 2];

        for (int i = 0; i < n + 2; i++) {
            up[i] = i - 1;
            down[i] = i + 1;
        }

        // ❸ 현재 위치를 나타내는 인덱스
        k++;

        // ❹ 주어진 명령어(cmd) 배열을 하나씩 처리
        for (String c : cmd) {
            // ❺ 현재 위치를 삭제하고 그 다음 위치로 이동
            if (c.startsWith("C")) {
                deleted.push(k);
                up[down[k]] = up[k];
                down[up[k]] = down[k];
                k = n < down[k] ? up[k] : down[k];
            }
            // ❻ 가장 최근에 삭제된 행을 복원
            else if (c.startsWith("Z")) {
                int restore = deleted.pop();
                down[up[restore]] = restore;
                up[down[restore]] = restore;
            }
            // ❼ U 또는 D를 사용해 현재 위치를 위아래로 이동
            else {
                String[] s = c.split(" ");
                int x = Integer.parseInt(s[1]);
                for (int i = 0; i < x; i++) {
                    k = s[0].equals("U") ? up[k] : down[k];
                }
            }
        }

        // ❽ 삭제된 행의 위치에 'X'를, 그렇지 않은 행 위치에는 'O'를 저장한 문자열 반환
        char[] answer = new char[n];
        Arrays.fill(answer, 'O');

        for (int i : deleted) {
            answer[i - 1] = 'X';
        }

        return new String(answer);
    }
}
