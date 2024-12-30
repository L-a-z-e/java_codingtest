import java.util.Arrays;
import java.util.HashSet;

public class Solution32 {
    /**
     * 문제
     *  1 ~ n 번 끝말잇기
     *  1번부터 순서대로 단어 말함
     *  1번 -> 마지막 사람 -> 1번 순서
     *  이전에 등장했던 단어는 사용 X
     *  한 글자인 단어는 인정되지 않음
     *  사람 수 n과 단어 배열 words 가 매개변수로 주어질 때 가장 먼저 탈락하는 사람의 번호와 그 사람이 자신의 몇 번째 차례에 탈락했는지 반환하는 solution() 작성
     *
     * 제약 조건
     *  2 <= n <= 10
     *  n <= words.length <= 100
     *  정답은 [번호, 차례] 형태로 반환
     *  탈락자가 생기지 않으면 [0, 0] 반환
     *
     * 입출력 예시
     *  int n = 3
     *  String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"}
     *  result = {3, 3}
     *  int n2 = 5
     *  String[] words2 = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"}
     *  result = {0, 0}
     *  int n3 = 2
     *  String[] words3 = {"hello", "one", "even", "never", "now", "world", "draw"}
     *  result = {1, 3}
     *
     */
    public static void main(String[] args) {
        int n = 3;
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        int n2 = 5;
        String[] words2 = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        int n3 = 2;
        String[] words3 = {"hello", "one", "even", "never", "now", "world", "draw"};

        System.out.println(Arrays.toString(solution(n, words)));
        System.out.println(Arrays.toString(solution(n2, words2)));
        System.out.println(Arrays.toString(solution(n3, words3)));

    }

    public static int[] solution(int n, String[] words) {
        // ❶ 이미 사용한 단어를 저장하는 set
        HashSet<String> usedWords = new HashSet<>();
        // ❷ 이전 단어의 마지막 글자
        char prevWord = words[0].charAt(0);

        for (int i = 0; i < words.length; i++) {
            // ❸ 이미 사용한 단어이거나 첫 글자가 이전 단어와 일치하지 않으면
            if (usedWords.contains(words[i]) || words[i].charAt(0) != prevWord) {
                // ❹ 탈락하는 사람의 번호와 차례를 반환
                return new int[]{(i % n) + 1, (i / n) + 1};
            }
            // ❺ 사용한 단어로 추가
            usedWords.add(words[i]);
            // ❻ 이전 단어의 마지막 글자 업데이트
            prevWord = words[i].charAt(words[i].length() - 1);
        }

        // ❼ 모두 통과했을 경우 반환값
        return new int[]{0, 0};
    }
}
