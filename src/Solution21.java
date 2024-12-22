import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Solution21 {
    /**
     * 문제
     *  채팅방에 들어오고 나가거나 닉네임을 변경한 기록이 담긴 문자열 배열 record가 매개변수로 주어질 때
     *  모든 기록이 처리된 다음 최종으로 방을 개설한 사람이 보는 메시지를 문자열 배열 형태로 반환하는 solution 작성
     *
     * 제약 조건
     *  record 는 1 이상 100,000 이하 문자열 배열
     *  모든 유저는 [userid] 로 구분
     *  [userid] 가 [nickname] 으로 채팅방에 입장
     *  - Enter userid nickname
     *  [userid] 가 채팅방 퇴장
     *  - Leave userid
     *  [userid]가 [nickname]으로 닉네임 변경
     *  - Change userid nickname
     *  첫 단어는 Enter, Leave, Change 중 하나
     *  각 단어는 공백으로 구분, 알파벳 대문자, 소문자, 숫자로만 이루어짐
     *  유저 아이디와 닉네임은 알파벳 대소문자 구별함
     *  유저 아이디와 닉네임 길이는 1이상 10이하
     *  잘못된 입력은 주어지지 않음
     *
     * 입출력 예시
     * record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
     * result = {"Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."};
     */
    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        System.out.println(Arrays.toString(solution(record)));
        System.out.println(Arrays.toString(solution2(record)));

    }

    /**
     * 문제 분할
     * 최종 사용자가 누군지 판단
     * 최종 사용자 기준으로 마지막 메시지까자 출력
     * 시간복잡도 계산 -> nlogn 까지 가능 정렬은 필요없는 듯
     * 닉네임 변경 처리
     * 자료구조는 뭘로 처리할건지?
     *
     * -> 문제를 잘못 이해한건지 문제를 쓴 사람의 잘못인지 최종으로 방을 개설한 사람이 아니라 각 명령이 끝난 뒤 최종 출력메시지 구하는 것
     *
     */
    private static String[] solution2(String[] record) {
        // 최종사용자 판단
        // Enter 다음 Leave 가 없는 최초의 사람
        HashMap<String, String> userIdNickNameMap = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();

        // 명령 + userid Map
        for (String s : record) {
            String[] cmd = s.split(" ");
            if(cmd[0].equals("Enter") || cmd[0].equals("Change")){
                userIdNickNameMap.put(cmd[1], cmd[2]);
            }
        }

        // 출력할 메시지
        HashMap<String, String> msgMap = new HashMap<>();
        msgMap.put("Enter", "님이 들어왔습니다.");
        msgMap.put("Leave", "님이 나갔습니다.");

        for (String s : record) {
            String[] cmd = s.split(" ");
            if(!cmd[0].equals("Change")) {
                result.add(userIdNickNameMap.get(cmd[1]) + msgMap.get(cmd[0]));
            }
        }

        return result.toArray(new String[0]);
    }

    // 풀이
    private static String[] solution(String[] record) {
        // Enter/Leave 메세지를 저장할 해시맵 생성
        HashMap<String, String> msg = new HashMap<>();
        msg.put("Enter", "님이 들어왔습니다.");
        msg.put("Leave", "님이 나갔습니다.");

        HashMap<String, String> uid = new HashMap<>();

        // ❶ record의 각 줄을 하나씩 처리
        for (String s : record) {
            String[] cmd = s.split(" ");
            if (cmd.length == 3) { // ❷ Enter 또는 Change인 경우
                uid.put(cmd[1], cmd[2]);
            }
        }

        // 답을 저장할 answer List 생성
        ArrayList<String> answer = new ArrayList<>();

        // ❸ record의 각 줄을 하나씩 처리
        for (String s : record) {
            String[] cmd = s.split(" ");
            // ❹ 각 상태에 맞는 메세지를 answer에 저장
            if (msg.containsKey(cmd[0])) {
                answer.add(uid.get(cmd[1]) + msg.get(cmd[0]));
            }
        }

        return answer.toArray(new String[0]);
    }
}
