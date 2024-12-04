import java.util.ArrayDeque;

public class Solution08 {

    /**
     * 올바른 괄호 판별 (로 열렸으면 )로 반드시 닫혀야 함
     * 문자열 s에 대해 올바른 괄호인지 여부를 반환하는 solution 함수 작성
     *
     * 제약조건
     *  s 길이는 100,000 이하의 자연수
     *  문자열 s 는 '(' 또는 ')' 로만 이루어져 있음
     * 입출력 예
     *  s - "()()"
     *  answer - true
     *  s - ")()("
     *  answer - false
     *
     */
    public static void main(String[] args) {

        String s = "()()";
        String s1 = ")()(";
        System.out.println(solution(s));
        System.out.println(solution(s1));

        System.out.println(solution2(s));
        System.out.println(solution2(s1));

    }

    private static boolean solution2(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        char[] str = s.toCharArray();
        for (char c : str) {
            if (c == '(') {
                stack.push(c);
            }
            else {
                // stack.isEmpty() 를 먼저 체크하는 이유 -> EmptyStackException 발생할 수 있음
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }


        return stack.isEmpty();
    }

    private static boolean solution(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        char[] a = s.toCharArray();
        for (char c : a) {
            if (c == '(') {
                stack.push(c);
            }
            else {
                if(stack.isEmpty() || stack.pop() == c)
                    return false;
            }
        }

        return stack.isEmpty();
    }

}
