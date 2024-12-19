import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution06 {

    /**
     * 실패율 구하는 코드 완성
     * 실패율 = 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어의 수
     * 전체 스테이지 개수가 N, 게임을 이용하는 사용자가 현재 멈춰 있는 스테이지 번호가 담긴 배열 stages
     *
     * 제약조건
     *  N은 1 이상 500 이하 자연수
     *  stages의 길이는 1 이상 200,000 이하
     *  stages에는 1 이상 N + 1 이하의 자연수
     *  N+1은 N 스테이지까지 클리어한 사용자를 나타냄
     *  실패율이 같은 스테이지가 있으면 작은 번호의 스테이지가 먼저 오면 됨
     *  스테이지에 도달한 유저가 없는 경우 실패율은 0으로 정의
     *
     * 입출력 예시
     *  N = 5
     *  stages = {2, 1, 2, 6, 2, 4, 3, 3}
     *  result = {3, 4, 2, 1, 5}
     */
    public static void main(String[] args) {
//        int N = 5;
//        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};

        int N = 100;
        int[] stages = {1, 22, 90, 85, 47, 3, 67, 55, 100, 99, 2, 33, 76, 44, 11, 88, 45, 60, 32, 77, 46, 23, 89, 12, 35, 54, 80, 19, 39, 99, 10, 21, 73, 18, 66, 5, 82, 79, 30, 4, 93, 50, 62, 71, 17, 78, 37, 34, 68, 65, 83, 20, 72, 13, 38, 64, 26, 61, 6, 24, 33, 8, 49, 87, 16, 48, 25, 14, 75, 42, 41, 40, 34, 3, 57, 9, 69, 86, 70, 63, 88, 15, 59, 94, 84, 95, 97, 92, 56, 45, 96, 91};


        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(solution(N, stages)));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        System.out.println(Arrays.toString(solution2(N, stages)));
        end = System.currentTimeMillis();
        System.out.println(end - start);

        System.out.println(Arrays.toString(solution3(N, stages)));
    }

    /**
     * 1. 시간복잡도 최대? 20,0000 개 입력이 있으므로 O(N^2) 보다 낮게해야함
     * 2. 실패율을 정의함
     * 3. 각 스테이지별 실패율을 정렬 후 반환함
     */
    public static int[] solution3(int N, int[] stages) {
        // 1. 시간복잡도 nlogn 까지 가능

        // 2. 실패율 정의 : k번째 스테이지에 머무르고 있는 사람 / k번째 스테이지에 도달한 적이 있던 사용자
        //    도달한 적이 있던 사용자를 구하는 방식? 이전스테이지들에 머무르고 있는 사람 수 제외
        //    N + 1 번쨰 스테이지에 있는 사람은? 전체 스테이지 통과한 사람인데 N + 1 번째는 반환할 이유가 없음
        //    1 스테이지를 1번에 저장하려면 배열을 뒤로 한 칸씩 미루는게 더 직관적

        int reachedChallenger = stages.length;
        int failedchallenger[] = new int[N + 2];

        for (int stage : stages) {
            failedchallenger[stage]++;
        }

        HashMap<Integer, Double> failRateMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            if (failedchallenger[i] == 0)
                failRateMap.put(i, 0.0);
            else {
                failRateMap.put(i, failedchallenger[i] / (double) reachedChallenger);
                reachedChallenger -= failedchallenger[i];
            }
        }

        // 각 스테이지별 실패율을 정렬 후 반환함
        return failRateMap.entrySet().stream().sorted(Map.Entry.<Integer, Double>comparingByValue(Comparator.reverseOrder()).thenComparing(Map.Entry::getKey)).map(Map.Entry::getKey).mapToInt(Integer::intValue).toArray();
    }

    public static int[] solution2(int N, int[] stages) {
        int[] result = new int[N];

        // 도전자 수
        int totalChallenger = stages.length;

        // 한 스테이지에 남아있는 인원
        HashMap<Integer, Integer> stayInStageMap = new HashMap<>();

        // 해당 스테이지까지 도달한 적이 있는 인원
        HashMap<Integer, Integer> totalInStageMap = new HashMap<>();

        // 해당 스테이지에서 머무르고 있는 인원
        HashMap<Integer, Double> failureRateInStageMap = new HashMap<>();

        // 전체 스테이지수 + 1 만큼 반복 (마지막까지 클리어한 인원은 어떻게 처리?)
        for(int i = 1; i <= (N+1); i++) {
            // 초기값 - 0
            stayInStageMap.put(i,0);

            // 초기값 - 전체인원
            totalInStageMap.put(i,totalChallenger);

            // 초기값 - 0
            failureRateInStageMap.put(i, 0.0);
        }

        // stages 배열에서 각 스테이지에 남아있는 인원 / 총 인원 -> 마지막 N+1 번째 스테이지는?
        for (int challenger : stages) {
            stayInStageMap.put(challenger, stayInStageMap.get(challenger)+1);

            for (int j = challenger + 1; j <= N; j++ ) {
                totalInStageMap.put(j, totalInStageMap.get(j)-1);
            }

        }

        for (int i = 1; i <= N; i++) {
            failureRateInStageMap.put(i, totalInStageMap.get(i) != 0 ? (double)stayInStageMap.get(i) / (double)totalInStageMap.get(i) : 0.0);
        }

        return failureRateInStageMap.entrySet().stream().filter(entry -> entry.getKey() <= N).sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue())).mapToInt(HashMap.Entry::getKey).toArray();

    }

    // 복잡성과 가독성이 상대적으로 많이 떨어지며 HashMap을 사용하여 데이터를 저장하고 검색하는 과정에서 추가적인 오버헤드 발생 가능 시간복잡도는 O(N+M) 으로 비슷함

    public static int[] solution(int N, int[] stages) {
        // ❶ 스테이지별 도전자 수를 구함 -> N + 2 인 이유 -> 0은 숫자맞추기 위함 + N+1번째 사용하려고
        int[] challenger = new int[N + 2];
        for (int i = 0; i < stages.length; i++) {
            challenger[stages[i]] += 1;
        }

        // ❷ 스테이지별 실패한 사용자 수 계산 -> 왜 total 이 그냥 length인지? 위랑 동일한 방식 -> 총원 - 현재스테이지 인원 제외
        HashMap<Integer, Double> fails = new HashMap<>();
        double total = stages.length;

        // ❸ 각 스테이지를 순회하며, 실패율 계산
        for (int i = 1; i <= N; i++) {
            if (challenger[i] == 0) { // ❹ 도전한 사람이 없는 경우, 실패율은 0
                fails.put(i, 0.);
            }
            else {
                fails.put(i, challenger[i] / total); // ❺ 실패율 구함
                total -= challenger[i]; // ❻ 다음 스테이지 실패율을 구하기 위해 현재 스테이지의 인원을 뺌
            }
        }

        // ❼ 실패율이 높은 스테이지부터 내림차순으로 정렬
        return fails.entrySet().stream().sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue())).mapToInt(HashMap.Entry::getKey).toArray();
    }
}
