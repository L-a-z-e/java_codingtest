public class Solution26 {
    /**
     * 예상 대진표
     * 차례대로 1~N번 번호 부여
     * 1<->2 3<->4 N-1 <-> N
     * 각 그룹별 승자가 새롭게 1번부터 부여
     * A번 참가자가 B번 참가자를 몇번째 라운드에서 만나는지 반환하는 solution() 함수 완성
     * 둘은 서로 만나기 전까지 항상 이긴다고 가정
     *
     * 2 <= N <= 2^20 (2의 지수로 주어짐)
     * A,B <= N
     *
     * 입출력 예시
     * N = 8
     * A = 4
     * B = 7
     * answer = 3
     */
    public static void main(String[] args) {
        int A = 4;
        int B = 7;
        int N = 8;
        System.out.println(solution(N, A, B));
        System.out.println(solution2(N, A, B));
    }

    /**
     * 문제 분석
     * 그룹 번호 1, 2 = 1  3, 4 = 2
     * 2로 나누고 + 1 을 하면 본인 그룹이 나옴
     * 두개가 동일한 그룹일때까지 반복하면 경기수 구할 수 있음
     */
    public static int solution2(int N, int A, int B) {
        int result = 0;

        for(result = 0; A != B; result++) {
            A = A / 2 + 1;
            B = B / 2 + 1;
        }

        return result;
    }

    // 풀이
    public static int solution(int n, int a, int b) {
        int answer;

        for(answer = 0; a != b; answer++) {
            a = (a + 1) / 2;
            b = (b + 1) / 2;
        }

        return answer;
    }
}
