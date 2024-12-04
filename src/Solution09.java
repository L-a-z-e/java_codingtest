import java.util.ArrayDeque;
import java.util.Stack;

public class Solution09 {
    /**
     * 10진수를 입력받아 2진수로 변환해 반환하는 solution 함수 구현
     * 제약조건
     *  decimal 은 1 이상 1,000,000,000 이하 자연수
     *
     * 입출력 예시
     *  decimal - 12345
     *  return - 11000000111001
     */
    public static void main(String[] args) {
        System.out.println(solution(12345));
        System.out.println(solution2(12345));
    }

    public static String solution2(int decimal) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        while (decimal != 0) {
            stack.push(decimal % 2);
            decimal /= 2;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();

    }

    public static String solution(int decimal) {
        Stack<Integer> stack = new Stack<>();
        while (decimal > 0) {
            int remainder = decimal % 2;
            stack.push(remainder);
            decimal /= 2;
        }

        // String 의 + 연산은 시간복잡도 측면에서 성능이 좋지 않습니다.
        // 따라서 StringBuilder 를 사용했습니다.
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

}
