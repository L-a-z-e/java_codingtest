import java.util.ArrayDeque;
import java.util.Stack;

public class Solution11 {
    /**
     * 알파벳 소문자로 구성된 문자열에 같은 알파벳 2개 붙어있는 짝을 찾고 제거
     * 그 뒤 문자열 이어붙이고 다시 짝을 찾아 제거
     * 문자열이 모두 제거되면 1 아니면 0 반환
     *
     * 제약조건
     *  문자열의 길이 1,000,000 이하 자연수
     *  문자열은 모두 소문자로 이루어져있음
     *
     * 입출력 예시
     * s - "baabaa" result - 1
     * s - "cdcd" result - 0
     */
    public static void main(String[] args) {
        String s1 = "baabaa";
        String s2 = "cdcd";

        System.out.println(solution(s1));
        System.out.println(solution(s2));
        System.out.println(solution2(s1));
        System.out.println(solution2(s2));
    }

    /**
     * 제거할 수 있는지 확인하려면 어떤 자료형을 쓰는게 좋은지?
     * 입력값이 1,000,000 이므로 O(N^2)은 시간 초과 발생
     * O(N) 으로 끝내야함
     * 일단 문자열이 붙어있다의 의미는 한 문자열 다음 문자열이 일치하면 없애고 일치하지 않으면 쌓으면 됨 Stack 활용
     */
    public static int solution2(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            }
            else {
                stack.push(c);
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }

    public static int solution(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // ❶ 스택이 비어 있지 않고, 현재 문자와 스택의 맨 위 문자가 같으면
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop(); // ❷ 스택의 맨 위 문자 제거
            }
            else {
                stack.push(c); // ❸ 스택에 현재 문자 추가
            }
        }

        return stack.isEmpty() ? 1 : 0; // ❹ 스택이 비어 있으면 1, 그렇지 않으면 0 반환
    }
}
