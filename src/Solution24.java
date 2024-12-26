import java.util.*;
import java.util.stream.Collectors;

public class Solution24 {
    /**
     * 문제
     *  메뉴 구성
     *  단품메뉴 -> 코스요리 형태로 재구성
     *  각 손님들이 주문할 때 가장 많이 함께 주문한 단품 메뉴를 코스 요리 메뉴로 구성
     *  단품 조합 기준으로
     *  손님들이 주문한 단품 메뉴들이 문자열 형식으로 담긴 배열 orders
     *  추가하고 싶어하는 코스요리를 구성하는 단품 메뉴들의 개수가 담긴 배열 course
     *  새로 추가하게 될 코스 요리의 메뉴 구성을 문자열 형태로 배열에 담아 반환하는 solution 작성
     *
     * 제약조건
     *  2 <= orders.length <= 20
     *  orders 배열 각 원소 크기 2 이상 10 이하, 알파벳 대문자 + unique
     *  1 <= course.length <= 10
     *  course 원소 2 이상 10 이하 자연수 오름차순, unique
     *  result 각 코스의 메뉴 구성을 문자열 형식으로 배열에 담아 asc
     *  만약 가장 많이 함께 주문된 메뉴 구성이 여러 개라면 모두 배열에 담아 반환
     *
     * 입출력 예시
     *  String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
     *  int[] course = {2, 3, 4};
     *  String[] result = {"AC", "ACDE", "BCFG", "CDE"};
     */
    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};

        System.out.println(Arrays.toString(solution(orders, course)));
    }

    /**
     * 문제 분할정복
     * 주어진 것 orders , course
     * result -> course 에 각 원소가 구성할 메뉴 수고 orders 에서 단품 조합 추출 -> 오름차순으로 출력
     *
     * 문제 분할
     * 1. course 배열 순회하며 개수 지정
     * 2. orders 배열에서 각 원소마다 지정된 개수만큼 뽑아서 동일한 요소 있는지 확인
     * 3. result 배열에 담고 사전순 오름차순 반환
     *
     * 시간 복잡도 및 자료 구조
     *  Map 사용해서  key - course 원소  / value - orders 에서 원소수만큼 조합으로 뽑은 메뉴중 중복 2회 이상인 것
     *  중복 2회 이상인 것 산출 -> Map에 key는 조합으로해서 몇 번 추가되는지 count -> 2이상만 나중에 필터링
     *  순서? key 값 기준으로 -> 중복2회인거 먼저 산출하고 그다음 최종적으로 맵에 course - 조합(2회이상) 으로 추가
     *
     *  조합 뽑는 방법?
     *
     *
     */
//    public static String[] combine(String[] order, int count) {
//        // 메뉴 정렬
//        String[] sortedOrder = Arrays.stream(order).sorted().toArray(String[]::new);
//        ArrayList<String> combinedMenu = new ArrayList<>();
//
//        // 1번째 요소 정함
//        // 2번째 요소는 그 다음 리스트에서 맨앞빼고 다시 반복 이부분부터 다시 고민 필요할 듯
//        for (int i = 0; i < count; i++){
//            // count 만큼 재귀 호출해서 문자열 더한 조합 생성
//            selectMenu(combinedMenu, i, count-i);
//        }
//
//    }
//
//    public static selectMenu(ArrayList<String> combinedmenu, int index, )
//
//    public static String[] solution2(String orders, int[] course) {
//
//        HashMap<Integer, HashMap<Integer, String[]>> courseMap = new HashMap<>();
//
//        for (int count : course) {
//            for (int i = 0; i < orders.length(); i++) {
//
//            }
//        }
//
//    }

    private static HashMap<Integer, HashMap<String, Integer>> courseMap;

    public static String[] solution(String[] orders, int[] course) {
        // 해시맵 초기화
        courseMap = new HashMap<>();
        for (int i : course) {
            courseMap.put(i, new HashMap<>());
        }

        // ❶ 코스를 배열로 만들고 오름차순 정렬해서 가능한 모든 메뉴 구성을 구함
        for (String order : orders) {
            char[] orderArray = order.toCharArray();
            Arrays.sort(orderArray);
            combinations(0, orderArray, "");
        }

        ArrayList<String> answer = new ArrayList<>();

        // ❷ 모든 코스 후보에 대해서
        for (HashMap<String, Integer> count : courseMap.values()) {
            count.values()
                    .stream()
                    .max(Comparator.comparingInt(o -> o)) // ❸ 가장 빈도수가 높은 코스를 찾음
                    .ifPresent(cnt -> count.entrySet() // ❹ 코스에 대한 메뉴 수가 가능할 때만
                            .stream()
                            // ❺ 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만
                            .filter(entry -> cnt.equals(entry.getValue()) && cnt > 1)
                            // ❻ 코스 메뉴만 answer 리스트에 추가
                            .forEach(entry -> answer.add(entry.getKey())));
        }

        Collections.sort(answer); // ❼ 오름차순으로 정렬
        return answer.toArray(new String[0]);
    }

    // 만들 수 있는 모든 조합을 재귀 함수를 이용해서 구현
    public static void combinations(int idx, char[] order, String result) {
        // 필요한 코스 메뉴의 수와 일치하는 것만 저장
        if (courseMap.containsKey(result.length())) {
            HashMap<String, Integer> map = courseMap.get(result.length());
            // 해당 코스의 수를 1증가
            map.put(result, map.getOrDefault(result, 0) + 1);
        }

        for (int i = idx; i < order.length; i++) {
            combinations(i + 1, order, result + order[i]);
        }
    }

}
