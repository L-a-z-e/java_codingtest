import java.util.ArrayDeque;
import java.util.HashMap;

public class Solution10 {

    /**
     * "()","[]","{}" 올바른 괄호 문자열
     * 대괄호, 중괄호, 소괄호로 이루어진 문자열 s가 매개변수로 주어질 때
     * s를 왼쪽으로 x칸만큼 회전시켰을 때 s가 올바른 괄호 문자열이 되도록 하는 x의 개수를 반환하는 solution() 함수 작성
     *
     * 제약조건
     *  s의 길이는 1 <= x <= 1000
     *
     * 입출력 예시
     *  s - "[](){}"  x - 3
     *  s - "}]()[{" x - 2
     *  s - "[)(]" x - 0
     *  s - "}}}" x - 0
     */
    public static void main(String[] args) {
        String s1 = "[](){}";
        String s2 = "}]()[{";
        String s3 = "[)(]";
        String s4 = "}}}";

        System.out.println(solution(s1));
        System.out.println(solution(s2));
        System.out.println(solution(s3));
        System.out.println(solution(s4));
        System.out.println("----------");
        System.out.println(solution2(s1));
        System.out.println(solution2(s2));
        System.out.println(solution2(s3));
        System.out.println(solution2(s4));
        System.out.println("----------");
        System.out.println(solution3(s1));
        System.out.println(solution3(s2));
        System.out.println(solution3(s3));
        System.out.println(solution3(s4));
    }

    /**
     * 회전 -> 문자열 왼쪽으로 한칸씩 이동, 맨 왼쪽이 가장끝으로가게
     * 어떤 자료형을 써야하는지? Stack? 뭘 보고 판단해야할지
     * 꺼내썼을때 일치하면 없애는 방식으로 가면 Stack 사용하는게 수월
     * 회전만 어떻게 구현할지 정리
     * 문자 그대로가면 ArrayList를 썼을때는 시간복잡도 n * n + n ?
     * Stack을 활용하려면 -> Stack 사용시 시간복잡도가 얼만큼 걸리는지 모름 O(n) 아닌가?
     * 실제로 회전이 일어나야하는지? 아니면 안되는 케이스만 걸러낼 수 있는지도 확인 필요
     * 코드 간결성도 생각해서 작성해야함
     * Stack -> 선입 후출 그럼 String을 0번째에는 그대로 Stack에 넣고 올바른 괄호 문자열인지 체크하고 그 다음부터는 i개씩 짤라서 뒤에다가 붙이고 그 다음 Stack에 넣는 식으로 해결
     * 유효한 문자열인지 확인하려면? (가 나온뒤에만 )가 나올 수 있고 { 가 나온 뒤에만 }가 나올 수 있는 식으로
     * 이걸 왜 스택으로 처리해야하지?
     */

    public static int solution2(String s) {

        ArrayDeque<Character> stack = new ArrayDeque<>();
        int x = 0;
        boolean isValid = true;
        for (int i = 0; i < s.length(); i++) {
            s = s.substring(1) + s.substring(0, 1);
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '(') {
                    stack.push('(');
                }
                else if (s.charAt(j) == '{') {
                    stack.push('{');
                }
                else if (s.charAt(j) == '[') {
                    stack.push('[');
                }
                else if (s.charAt(j) == ')') {
                    if (!(stack.isEmpty() || stack.pop() != '(')) {
                        isValid = false;
                        break;
                    }
                }
                else if (s.charAt(j) == '}') {
                    if (!(stack.isEmpty() || stack.pop() != '{')) {
                        isValid = false;
                        break;
                    }
                }
                else if (s.charAt(j) == ']') {
                    if (!(stack.isEmpty() || stack.pop() != '[')) {
                        isValid = false;
                        break;
                    }
                }
            }
            if (isValid) {
                x++;
            }
        }

        return x;
    }

    // 재정리 및 개선
    public static int solution3(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        int x = 0;
        HashMap<Character, Character> pairMap = new HashMap<>();
        pairMap.put(')', '(');
        pairMap.put('}', '{');
        pairMap.put(']', '[');

        for (int i = 0; i < s.length(); i++) {
            boolean isValid = true;

            for(int j = 0; j < s.length(); j++) {
                char c = s.charAt((i + j) % s.length());
                if (pairMap.containsKey(c)) {
                    if (stack.isEmpty() || stack.pop() != pairMap.get(c)) {
                        isValid = false;
                    }
                }
                else {
                    stack.push(c);
                }
            }

            if (isValid) {
                x++;
            }

        }

        return x;
    }

    /**
     * HashMap을 사용하여 열리는 괄호 닫히는 괄호를 구별했고
     * s + s 를해서 실제 회전이 아니라 해당인덱스부터 s의 length만큼 길이까지만 반복을 했고
     * 레이블을 사용해서 반복문 빠져나오는 걸 사용했는데 이부분은 피하는 게 좋을 것 같음
     */
    public static int solution(String s) {
        // ❶ 괄호 정보를 저장함. 코드를 간결하게 할 수 있음
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        int n = s.length(); // 원본 문자열의 길이 저장
        s += s; // 원본 문자열 뒤에 원본 문자열을 이어 붙여서 2번 나오도록 만들어줌

        int answer = 0;

        // ❷ 확인할 문자열의 시작 인덱스를 0 부터 n 까지 이동
        A:for (int i = 0; i < n; i++) {
            ArrayDeque<Character> stack = new ArrayDeque<>();

            // ❸ i(시작 위치)부터 원본 문자열의 길이인 n개까지 올바른 괄호 문자열인지 확인
            for (int j = i; j < i + n; j++) {
                char c = s.charAt(j);
                // HashMap 안에 해당 key 가 없다면 열리는 괄호임
                if (!map.containsKey(c)) {
                    stack.push(c);
                }
                else {
                    // ❹ 짝이 맞지 않으면 내부 for문은 종료하고 for문 A로 이동
                    if(stack.isEmpty() || !stack.pop().equals(map.get(c)))
                        continue A;
                }
            }

            // ❺ 3에서 continue 되지 않았고, 스택이 비어있으면 올바른 괄호 문자열임
            if (stack.isEmpty())
                answer++;
        }

        return answer;
    }

}
