import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;

public class Solution12 {
    /**
     * 초 단위로 기록된 주식 가격이 담긴 배열 prices가 매개변수로 주어질 때
     * 가격이 떨어지지 않은 기간은 몇 초인지 반환하는 solution
     *
     * 제약조건
     *  prices의 각 가격은 1 이상 10,000 이하 자연수
     *  prices의 길이는 2 이상  100,000 이하
     *
     * 입출력 예시
     *  prices = {1, 2, 3, 2, 3} return = {4, 3, 1, 1, 0}
     */
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        System.out.println(Arrays.toString(solution(prices)));
        System.out.println(Arrays.toString(solution2(prices)));
    }

    /**
     * 입력값이 100,000 이므로 O(N^2) 은 시간초과
     * 맨 마지막 값이 아니면 최소 1초 가격이 떨어지지 않은 것으로 출력
     * 알맞은 자료구조?
     * key : value 구조로 stack에 쌓을 수 있는지?
     * stack.peek().getValue() > prices[i] 클때 더 작은 값 찾을때까지 반복해서 peek -> pop() 처리하고 그 지점까지 result의 값이 j - key 값이 됨
     */

//    public static int[] solution2(int[] prices) {
//        int size = prices.length;
//        int[] result = new int[size];
//
//        Stack<AbstractMap.SimpleEntry<Integer, Integer>> stack = new Stack<>();
//
//        for (int i = 0; i < size; i++) {
//            if (!stack.isEmpty() && stack.peek().getValue() < prices[i]) {
//                stack.pop();
//                result[i] =
//            }
//        }
//    }

    public static int[] solution2(int[] prices) {
        int size = prices.length;
        int[] result = new int[size];

        Stack<AbstractMap.SimpleEntry<Integer, Integer>> stack = new Stack<>();

        for (int i = 0; i < size; i++) {
            // 현재 가격이 스택의 맨 위 가격보다 작을 때
            while (!stack.isEmpty() && stack.peek().getValue() > prices[i]) {
                AbstractMap.SimpleEntry<Integer, Integer> entry = stack.pop();
                result[entry.getKey()] = i - entry.getKey(); // 가격이 떨어진 시간 계산
            }
            // 현재 가격과 인덱스를 스택에 추가
            stack.push(new AbstractMap.SimpleEntry<>(i, prices[i]));
        }

        // 스택에 남아 있는 값들은 마지막 가격이 떨어지지 않은 경우이므로
        while (!stack.isEmpty()) {
            AbstractMap.SimpleEntry<Integer, Integer> entry = stack.pop();
            result[entry.getKey()] = size - entry.getKey() - 1; // 마지막까지 떨어지지 않은 경우
        }

        return result;
    }

    public static int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n]; // ❶ 가격이 떨어지지 않은 기간을 저장할 배열

        // 스택(stack)을 사용해 이전 가격과 현재 가격 비교
        Stack<Integer> stack = new Stack<>(); // ❷ 스택 생성
        stack.push(0);

        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                // ❸ 가격이 떨어졌으므로 이전 가격의 기간 계산
                int j = stack.pop();
                answer[j] = i - j;
            }
            stack.push(i);
        }

        // ❹ 스택에 남아 있는 가격들은 가격이 떨어지지 않은 경우
        while (!stack.isEmpty()) {
            int j = stack.pop();
            answer[j] = n - 1 - j;
        }

        return answer;
    }
}
