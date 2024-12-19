import java.util.ArrayDeque;
import java.util.Queue;

public class Solution17 {
    /**
     * 문제
     *  영단어 카드 뭉치 2개
     *  원하는 카드 뭉치에서 순서대로 한 장씩 사용
     *  한 번 사용한 카드는 다시 사용할 수 없음
     *  카드를 사용하지 않고 다음 카드로 넘어갈 수 없음
     *  기존에 주어진 카드 뭉치의 단어 순서 바꿀 수 없음
     *  card1, card2 와 원하는 단어 배열 goal 이 있을 때
     *  원하는 배열을 만들 수 있다면 "Yes", 아니면 "No" 반환하는 solution() 완성
     *
     * 제약조건
     *  1<=cards.length<=10
     *  card1과 card2 에는 서로 다른 단어만 있음
     *  2<=goal.length<=card1.length + card2.length
     *  goal의 원소는 card1, card2의 원소로만 이루어져있음
     *  card1, card2, goal 모두 알파벳 소문자로 이루어져있음
     *
     * 입출력 예시
     *  card1 = {"i", "drink", "water"}
     *  card2 = {"want", "to"}
     *  goal = {"i", "want", "to", "drink" "water"}
     *  result = "Yes"
     *
     *  card1 = {"i", "water", "drink"}
     *  card2 = {"want", "to"}
     *  goal = {"i", "want", "to", "drink" "water"}
     *  result = "No"
     */
    public static void main(String[] args) {

        String[] card1 = {"i", "drink", "water"};
        String[] card2 = {"want", "to"};
        String[] goal1 = {"i", "want", "to", "drink", "water"};
        String[] card3 = {"i", "water", "drink"};
        String[] card4 = {"want", "to"};
        String[] goal2 = {"i", "want", "to", "drink", "water"};

        System.out.println(solution(card1, card2, goal1));
        System.out.println(solution(card3, card4, goal2));

    }

    public static String solution(String[] card1, String[] card2, String[] goal1) {
        Queue<String> queue1 = new ArrayDeque<>();
        Queue<String> queue2 = new ArrayDeque<>();

        for (String element : card1) {
            queue1.add(element);
        }

        for (String element : card2) {
            queue2.add(element);
        }

        for (String element : goal1) {
            if (!queue1.isEmpty() && queue1.peek().equals(element)) {
                queue1.poll();
            }
            else if (!queue2.isEmpty() && queue2.peek().equals(element)) {
                queue2.poll();
            }
            else {
                return "No";
            }
        }

        return "Yes";
    }
}
