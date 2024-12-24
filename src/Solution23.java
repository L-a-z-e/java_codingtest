import java.util.*;
import java.util.stream.Stream;

public class Solution23 {
    /**
     * 문제
     *  신고 - 한 유저 여러번해도 횟수 1회 / 여러 유저 신고 가능
     *  k번 이상 신고되면 정지
     *  정지된 유저를 신고한 유저들에게 메일 발송
     *  마지막에 한 번에 신고내역 취합해서 메일 발송
     *  이용자의 ID 담긴 문자열 배열 id_list
     *  각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열 report
     *  정지 기준 신고 횟수 k
     *  각 유저별로 처리결과 메일을 받은 횟수를 배열에 담아 반환하는 solution 작성
     * 제약조건
     *  2 <= id_list.length <= 1000
     *  1 <= id_list[i] <=10
     *  1 <= report.length <= 200,000
     *  3 <= repot[i] <= 21
     *  report 는 "이용자ID 신고한ID" 형태의 문자열
     *  자기 자신을 신고하는 경우는 없음
     *  1 <= k <= 200
     *  id_list에 담긴 ID 순서대로 각 유저가 받은 결과 메일 수 반환
     *
     * 입출력 예시
     *  String[] id_list = {"muzi", "frodo", "apeach", "neo"};
     *  String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
     *  int k = 2;
     *
     */
    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;

        System.out.println(Arrays.toString(solution(id_list, report, k)));
        System.out.println(Arrays.toString(solution2(id_list, report, k)));
    }

    /**
     * 분석
     * 입력값 200,000 이라 N^2 안되고 NLogN까지는 가능
     * 신고한 유저 - 신고당한 유저 목록을 저장하는 Map 필요, 한 유저 여러번 불가능 (중복 X -> Set 사용?)
     * 이용자 - 메일받는 횟수 저장하는 Map 필요
     * 신고당한유저 key - 신고한 유저 value HashSet<String>
     * 신고당한유저 set의 크기가 k 이상인 경우만 filtering 해서
     * 안의 Set 수만큼 각각 userid key - 횟수 value 에 카운트추가
     * userId 순서대로 출력
     */

    public static int[] solution2(String[] id_list, String[] report, int k) {
        HashMap<String, HashSet<String>> reportedMap = new HashMap<>();
        HashMap<String, Integer> mailCount = new HashMap<>();

        // 신고당한 유저 key , 신고한 유저 value
        for (String reportElement : report) {
            String[] split = reportElement.split(" ");
            if (reportedMap.containsKey(split[1])) {
                reportedMap.get(split[1]).add(split[0]);
            }
            else {
                HashSet<String> reportUser = new HashSet<>();
                reportUser.add(split[0]);
                reportedMap.put(split[1], reportUser);
            }
        }

        // 횟수 기록
        for (String reportedId : reportedMap.keySet() ) {
            if (reportedMap.get(reportedId).size() >= k) {
                for (String userid : reportedMap.get(reportedId)){
                    mailCount.put(userid, mailCount.getOrDefault(userid, 0) + 1);
                }
            }
        }
        int[] result = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            if (mailCount.containsKey(id_list[i])) {
                result[i] = mailCount.get(id_list[i]);
            }
            else {
                result[i] = 0;
            }
        }

        return result;
    }

    // 풀이
    public static int[] solution(String[] id_list, String[] report, int k) {
        // 신고당한 유저 - 신고 유저 집합을 저장할 해시맵
        HashMap<String, HashSet<String>> reportedUser = new HashMap<>();
        // 처리 결과 메일을 받은 유저 - 받은 횟수를 저장할 해시맵
        HashMap<String, Integer> count = new HashMap<>();

        // ❶ 신고 기록 순회
        for (String r : report) {
            String[] s = r.split(" ");
            String userId = s[0];
            String reportedId = s[1];

            if (!reportedUser.containsKey(reportedId)) { // ❷ 신고당한 기록이 없다면
                reportedUser.put(reportedId, new HashSet<>());
            }
            // ❸ 신고한 사람의 아이디를 해시맵의 value인 해시셋에 추가
            reportedUser.get(reportedId).add(userId);
        }

        for (Map.Entry<String, HashSet<String>> entry : reportedUser.entrySet()) {
            if (entry.getValue().size() >= k) { // ❹ 정지 기준에 만족하는지 확인
                for (String uid : entry.getValue()) { // ❺ 해시셋을 순회하며 count 계산
                    count.put(uid, count.getOrDefault(uid, 0) + 1);
                }
            }
        }

        int[] answer = new int[id_list.length];

        // ❻ 각 아이디별 메일을 받은 횟수를 순서대로 정리
        for (int i = 0; i < id_list.length; i++) {
            answer[i] = count.getOrDefault(id_list[i], 0);
        }

        return answer;
    }
}
