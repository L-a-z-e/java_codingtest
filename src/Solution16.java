import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Solution16 {
    /**
     * 문제
     *  각 기능 진도 100%일 때 서비스에 반영 가능
     *  개발 속도는 모두 다르고 뒤의 기능은 앞의 기능이 배포될 때 함께 배포가능
     *  배포 순서대로 작업 진도가 적힌 progresses
     *  작업 개발 속도 speeds 가 주어질 때
     *  각 배포마다 몇 개의 기능이 배포되는지를 반환하는 solution 함수 작성
     *
     * 제약조건
     *  progresses, speeds 는 100개 이하
     *  작업 진도는 100 미만의 자연수
     *  작업 속도는 100 이하의 자연수
     *  배포는 하루에 한 번만 할 수 있음
     *
     * 입출력 예시
     *  progresses - {93, 30, 55}
     *  speeds - {1, 30, 5}
     *  return - {2, 1}
     *
     *  progresses - {95, 90, 99, 99, 80, 99}
     *  speeds - {1, 1, 1, 1, 1, 1}
     *  return - {1, 3, 2}
     */
    public static void main(String[] args) {
        int[] progresses1 = {93, 30, 55};
        int[] speeds1 = {1, 30, 5};
        int[] progresses2 = {95, 90, 99, 99, 80, 99};
        int[] speeds2 = {1, 1, 1, 1, 1, 1};

        System.out.println(Arrays.toString(solution(progresses1, speeds1)));
        System.out.println(Arrays.toString(solution(progresses2, speeds2)));
    }

    /**
     * Queue 에 작업진행률 넣고
     * 첫번째가 진행률 + 속도 * 날짜 계산했을때 100 이상인지 확인하고 100이하인 건까지 poll() 후 개수 List에 add
     * Queue.isEmpty() == true 까지 반복
     */
    public static int[] solution(int[] progresses, int[] speeds) {

        Queue<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> result = new ArrayList<>();
        int day = 1;
        int index = 0;

        // 1. Queue에 작업진행률 add
        for (int i = 0; i < progresses.length; i++) {
            queue.add(progresses[i]);
        }

        // 2. queue.isEmpty()까지 반복
        while (!queue.isEmpty()) {
            int count = 0;

            for (int i = index; i < speeds.length; i++ ){
                if (queue.peek() + speeds[index] * day >= 100) {
                    queue.poll();
                    index++;
                    count++;
                }
                else {
                    break;
                }
            }

            if (count > 0) {
                result.add(count);
            }
            day++;
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
