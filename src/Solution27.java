import java.util.Arrays;
import java.util.HashMap;

public class Solution27 {
    /**
     * 다단계 판매
     *  모든 판매원은 판매로 발생한 이익의 10%ㄲ를 계산해서 추천인에게 배분하고 나머지는 가짐
     *  10%를 계산할때는 원 단위에서 자르고 10%가 1원 미만인 경우 이득분배하지 않고 자신이 가짐
     *  각 판매원의 이름을 담은 배열 enroll
     *  각 판매원을 다단계 조직에 참여시킨 다른 판매원의 이름을 referral
     *  판매량 집계 데이터의 판매원 이름 배열 seller
     *  판매량 집계 데이터의 판매수량 배열 amount
     *  각 판매원이 얻은 이익금을 나열한 배열을 반환하는 solution() 작성
     *
     * 제약조건
     *  1<= enroll <= 10,000
     *  referral 길이는 enroll 과 동일
     *  referral[i] 는 enroll[i]를 가입시킨 사람
     *  추천 없이 조직에 참여한 사람은 referral[i] = "-"
     *  enroll은 조직에 참여한 순서에 따름
     *  1 <= seller <= 100,000
     *  amount.length = seller.length
     *  1 <= amount <= 100
     *  1개 판매 이익금은 100원
     *
     * 입출력 예시
     *  String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
     *  String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
     *  String[] seller = {"young", "john", "tod", "emily", "mary"};
     *  int[] amount = {12, 4, 2, 5, 10};
     *  int[] result = {360, 958, 108, 450, 18, 180, 1080};
     */
    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        System.out.println(Arrays.toString(solution(enroll, referral, seller, amount)));
    }

    /**
     * 문제 분석
     * enroll - referral 밑에서부터 올라와야하는 구조
     * 트리구조? X
     *
     * 문제 분할
     * 1. 연결 관계 정립할 방안 찾기
     * 2. 제일 하위레벨부터 올라가면서 10%씩 위로 올림
     */
//    public static int[] solution2(String[] enroll, String[] referral, String[] seller, int[] amount) {
//
//      // 1 연결 관계 정립
//      HashMap<String, String> enrollReferralMap = new HashMap<>();
//      for (int i = 0; i < enroll.length; i++) {
//          enrollReferralMap.put(enroll[i], referral[i]);
//      }
//
//      // 2 하위부터 올라가면서 10%
//      HashMap<String, Integer> sellerAmountMap = new HashMap<>();
//      for (int i = 0; i < seller.length; i++) {
//          String sellerName = seller[i];
//          // 순서가 보장되려면 enroll에서 먼저 나온 사람의 seller 배열에서 뒤에 있어야 계산이 편한지? -> O
//          // 어떻게 정렬할 것인지 각각 본인 이름에 넣어두고 enroll기준으로 뒤에서부터 돌리면 합산이 편할 듯
//          sellerAmountMap.put(sellerName, sellerAmountMap.getOrDefault(sellerName, 0) + amount[i]);
//      }
//
//      HashMap<String, Integer> enrollProfitMap = new HashMap<>();
//
//      // 이름으로 result의 몇번째인지를 구할 수 있어야 함
//      // 그럼 그냥 sellerProfitMap을 쓰는게 나은건지? 새로 Map을 만들지?
//
//      for (int i = enroll.length - 1; i < 0; i--) {
//          // refferal 없는 경우 <-> 없는 경우
//        if (enrollReferralMap.get(enroll[i]).equals("-")) {
//            if(sellerAmountMap.containsKey(enroll[i])){
//                enrollProfitMap.put(enroll[i], enrollProfitMap.getOrDefault(sellerAmountMap.get(enroll[i]) * 100, 0));
//            }
//            else {
//                enrollProfitMap.put(enroll[i], 0);
//            }
//        }
//        else {
//            if(sellerAmountMap.containsKey(enroll[i])){
//                enrollProfitMap.put(enroll[i], enrollProfitMap.getOrDefault(enrollProfitMap.get(enroll[i]) + sellerAmountMap.get(enroll[i]) * 90, 0);
//                enrollProfitMap.
//            }
//        }
//      }
//
//    }

    // 풀이
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        // ❶ parent 해시맵. key는 enroll의 노드, value는 referral의 노드로 구성됨
        HashMap<String, String> parent = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            parent.put(enroll[i], referral[i]);
        }

        // ❷ total 해시맵 생성
        HashMap<String, Integer> total = new HashMap<>();

        // ❸ seller 배열과 amount 배열을 이용하여 이익 분배
        for (int i = 0; i < seller.length; i++) {
            String curName = seller[i];
            // ❹ 판매자가 판매한 총 금액 계산
            int money = amount[i] * 100;
            // ❺ 판매자부터 차례대로 상위 노드로 이동하며 이익 분배
            while (money > 0 && !curName.equals("-")) {
                // ❻ 현재 판매자가 받을 금액 계산(10%를 제외한 금액)
                total.put(curName, total.getOrDefault(curName, 0) + money - (money / 10));
                curName = parent.get(curName);
                // ❼ 10% 를 제외한 금액 계산
                money /= 10;
            }
        }

        // ❽ enroll 배열의 모든 노드에 대해 해당하는 이익을 배열로 반환
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = total.getOrDefault(enroll[i], 0);
        }
        return answer;
    }
}
