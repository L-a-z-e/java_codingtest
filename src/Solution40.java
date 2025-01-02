import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution40 {
    /**
     * 문제
     *  N개의 마을로 이루어진 나라
     *  양방향으로 통행 가능한 도로
     *  걸리는 시간은 각 도로마다 다름
     *  음식 주문 받을 수 있는 N개의 마을 중 배달 시간이 K 이하인 곳으로만 음식배달을 제한하려할 때
     *  마을 개수가 N
     *  각 마을을 연결하는 도로 정보가 road
     *  음식 배달이 가능한 시간 K
     *  음식 주문을 받을 수 있는 마을의 개수를 반환하는 solution() 작성
     *
     * 제약조건
     *  1 <= N <= 50
     *  1 <= road <= 2000
     *  road의 각 원소는 마을을 연결하는 각 도로의 정보 나타냄
     *  road는 길이가 3인 배열 (a, b, c)
     *  a,b 는 두 마을의 번호, c는 지나가는데 걸리는 시간
     *  a,b를 연결하는 도로는 여러 개가 있을 수 있음
     *  한 도로의 정보가 중복해서 주어지진 않음
     *  1 <= K <= 500,000
     *  임의의 두 마을 간에 항상 이동 가능한 경로가 있음
     *  1번 마을에 있는 음식점이 K시간 이하로 배달할 수 있는 마을의 개수 반환
     *
     * 입출력 예시
     *  int N = 5;
     *  int[][] road = {{1,2,1}, {2,3,3}, {5,2,2}, {1,4,2}, {5,3,1}, {5,4,2}};
     *  int K = 3;
     *  result = 4
     *
     *  int N2 = 6;
     *  int[][] road = {{1,2,1}, {1,3,2}, {2,3,2}, {3,4,3}, {3,5,2}, {3,5,3}, {5,61}};
     *  int K2 = 4;
     *  result = 4
     *
     */
    public static void main(String[] args) {
        int N = 5;
        int[][] road = {{1,2,1}, {2,3,3}, {5,2,2}, {1,4,2}, {5,3,1}, {5,4,2}};
        int K = 3;
        int N2 = 6;
        int[][] road2 = {{1,2,1}, {1,3,2}, {2,3,2}, {3,4,3}, {3,5,2}, {3,5,3}, {5,6,1}};
        int K2 = 4;

        System.out.println(solution(N, road, K));
        System.out.println(solution(N2, road2, K2));
    }

    // 풀이
    private static class Node {
        int dest, cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static int solution(int N, int[][] road, int K) {
        // ❶ 인접 리스트를 저장할 ArrayList 배열 초기화
        ArrayList<Node>[] adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        // ❷ road 정보를 인접 리스트로 저장
        for (int[] edge : road) {
            adjList[edge[0]].add(new Node(edge[1], edge[2]));
            adjList[edge[1]].add(new Node(edge[0], edge[2]));
        }

        int[] dist = new int[N + 1];
        // ❸ 모든 노드의 거리 값을 무한대로 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);

        // ❹ 우선순위 큐를 생성하고 시작 노드를 삽입
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.add(new Node(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.dest] < now.cost)
                continue;

            // ❺ 인접한 노드들의 최단 거리를 갱신하고 우선순위 큐에 추가
            for (Node next : adjList[now.dest]) {
                if (dist[next.dest] > now.cost + next.cost) {
                    dist[next.dest] = now.cost + next.cost;
                    pq.add(new Node(next.dest, dist[next.dest]));
                }
            }
        }

        int answer = 0;

        // ❻ dist 배열에서 K 이하인 값의 개수를 구하여 반환
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) answer++;
        }

        return answer;
    }
}
