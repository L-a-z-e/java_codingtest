import java.util.HashMap;

public class Solution20 {
    /**
     * 문제
     *  원하는 제품의 문자열 배열 want
     *  원하는 제품의 수량 정수 배열 number
     *  마트에서 할일하는 제품을 나타내는 문자열 배열 discount (1일 1개씩만 할인)
     *  회원가입을해서 원하는 제품을 모두 할인 받을 수 있는 날짜가 몇번이나 존재하는지 일수를 반환
     *
     * 제약조건
     *  1 <= want = number <= 10
     *  number의 총합 10
     *  10 <= discount <= 100,000
     *  want와 discount 문자열 12 이하
     *
     * 입출력 예시
     * want = {"banana", "apple", "rice", "pork", "pot"};
     * number = {3, 2, 2, 2, 1};
     * discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
     * result = 3
     */
    public static void main(String[] args) {
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3, 2, 2, 2, 1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};

        System.out.println(solution(want, number, discount));
        System.out.println(solution2(want, number, discount));
    }

    // 풀이
    public static int solution(String[] want, int[] number, String[] discount) {
        // ❶ want, number배열의 값을 해시맵에 저장
        HashMap<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        int answer = 0; // ❷ 총 일수를 계산할 변수 초기화

        // ❸ 특정일 i에 회원가입 시 할인받을 수 있는 품목 체크
        for (int i = 0; i < discount.length - 9; i++) {
            // ❹ i일 회원가입 시 할인받는 제품 및 개수를 담을 해시맵
            HashMap<String, Integer> discount10d = new HashMap<>();

            // ❺ i일 회원가입 시 할인받는 제품 및 개수로 해시맵 구성
            for (int j = i; j < i + 10; j++) {
                if (wantMap.containsKey(discount[j])) {
                    discount10d.put(discount[j], discount10d.getOrDefault(discount[j], 0) + 1);
                }
            }

            // ❻ 할인하는 상품의 개수가 원하는 수량과 일치하면 정답 변수에 1 추가
            if (discount10d.equals(wantMap))
                answer++;
        }


        return answer;
    }

    /**
     * 시간복잡도? 100,000 -> O(NlogN) 정도까지는 가능할 듯
     * 문제의 핵심은 discount 배열에서 특정 인덱스 기준 10개를 잘랐을 때 원하는 물품 * 수량 과 일치하는지 확인하면 됨
     * discount가 N만큼 있으면 10개씩 끊어서 반복을 돌리면 10N 만큼 시간이 소요됨 제일 간단한 해결책
     */
    public static int solution2(String[] want, int[] number, String[] discount) {
        HashMap<String, Integer> itemNameQuantityMap = new HashMap<>();
        int result = 0;

        for (int i = 0; i < want.length; i++) {
            itemNameQuantityMap.put(want[i], number[i]);
        }

        for (int i = 0; i <= discount.length - 10; i++) {
            HashMap<String, Integer> discountItemMap = new HashMap<>();
            for (int j = i; j < i + 10 ; j++) {
                if (itemNameQuantityMap.containsKey(discount[j])) {
                    discountItemMap.put(discount[j], discountItemMap.getOrDefault(discount[j], 0) + 1);
                }
                else
                    break;
            }
            if (discountItemMap.equals(itemNameQuantityMap))
                result++;
        }

        return result;

    }

}
