public class Solution46 {
    /**
     * 문제
     *  가로, 세로 n 정사각형 체스판
     *  n-퀸
     *  조건을 만족하는 퀸 배치수를 반환하는 solution() 작성
     *
     * 입출력 예시
     *  n = 4
     *  result = 2
     *
     */
    public static void main(String[] args) {

    }

    private static int N;
    private static boolean[] width;
    private static boolean[] diagonal1;
    private static boolean[] diagonal2;

    // ❶ 퀸이 서로 공격할 수 없는 위치에 놓이는 경우의 수를 구하는 함수
    private static int getAns(int y) {
        int ans = 0;
        // ❷ 모든 행에 대해서 퀸의 위치가 결장되었을 경우
        if (y == N) {
            // ❸ 해결 가능한 경우의 수를 1 증가시킴
            ans++;
        }
        else {
            // ❹ 현재 행에서 퀸이 놓일 수 있는 모든 위치를 시도
            for (int i = 0; i < N; i++) {
                // ❺ 해당 위치에 이미 퀸이 있는 경우, 대각선상에 퀸이 있는 경우 스킵
                if (width[i] || diagonal1[i + y] || diagonal2[i - y + N])
                    continue;

                // ❻ 해당 위치에 퀸을 놓음
                width[i] = diagonal1[i + y] = diagonal2[i - y + N] = true;
                // ❼ 다음 행으로 이동하여 재귀적으로 해결 가능한 경우의 수 찾기
                ans += getAns(y + 1);
                // ❽ 해당 위치에 놓인 퀸을 제거함
                width[i] = diagonal1[i + y] = diagonal2[i - y + N] = false;
            }
        }

        return ans;
    }

    public int solution(int n) {
        N = n;
        width = new boolean[n];
        diagonal1 = new boolean[n * 2];
        diagonal2 = new boolean[n * 2];

        int answer = getAns(0);
        return answer;
    }

}
