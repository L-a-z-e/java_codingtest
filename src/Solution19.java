import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution19 {

    /**
     * 문제
     *  마라톤에 참여한 선수들의 이름이 담긴 배열 participant
     *  완주한 선수들의 이름이 담긴 배열 completion
     *  완주하지 못한 선수의 이름을 반환하는 solution() 작성
     *
     * 제약 조건
     *  1 <= participant <= 100,000
     *  completion.length = participant.length - 1
     *  참가자 이름은 1개 이상 20개 이하 알파벳 소문자
     *  참가자 중에는 동명이인이 있을 수 있음
     *
     * 입출력 예시
     *  String[] participant = {"leo", "kiki", "eden"};
     *  String[] completion = {"eden", "kiki"};
     *  return = "leo"
     *
     */
    public static void main(String[] args) {
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};

        System.out.println(solution(participant, completion));
        System.out.println(solution2(participant, completion));
    }

    // 풀이
    public static String solution(String[] participant, String[] completion) {
        // ❶ 해시맵 생성
        HashMap<String, Integer> hashMap = new HashMap<>();
        // ❷ 완주한 선수들의 이름을 해시맵에 저장
        for (String string : completion) {
            hashMap.put(string, hashMap.getOrDefault(string, 0) + 1);
        }

        // ❸ 참가한 선수들의 이름을 키로 하는 값을 1씩 감소
        for (String string : participant) {
            // ❹ 완주하지 못한 선수를 찾으면 반환
            if (hashMap.getOrDefault(string, 0) == 0) {
                return string;
            }
            hashMap.put(string, hashMap.get(string) - 1);
        }

        return null;
    }

    /**
     * 분석
     *  n 이 100,000 개이므로 n^2 10,000,000,000 n^2 아래로 구해야함
     *
     * 풀이
     *  간단하게 Map에 넣고 contains로 체크해서 없는 경우 이름 반환하면 될 듯
     *  동명이인도 있을 수 있다고 했으므로 이부분 확인 필요
     */
    public static String solution2(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);

        return IntStream.range(0, completion.length)
                .filter(i -> !participant[i].equals(completion[i]))
                .mapToObj(i -> participant[i])
                .findFirst()
                .orElse(participant[completion.length]);
    }
}
