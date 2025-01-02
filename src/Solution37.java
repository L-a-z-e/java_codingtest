import java.util.ArrayDeque;

public class Solution37 {
    /**
     * 문제
     *  검은색 벽, 흰색 길
     *  캐릭터 동, 서, 남, 북 한 칸씩 이동 가능
     *  게임 맵이 maps로 주어질 때
     *  우리팀 캐릭터가 상대팀 진영에 도착하기위해 지나가야하는 길의 최소 개수를 반환하는 solution() 작성
     *  도착 불가능하다면 -1 반환
     *
     * 제약조건
     *  maps n x m 2차원 배열
     *  maps는 0 과 1로 이루어져있고 0은 벽 1은 길
     *  1 <= n,m <= 100
     *  n, m 둘다 1인 경우는 없음
     *  처음 캐릭터는 (1, 1) 위치에 있고 상대팀 진영은 (n, m) 위치에 있음
     *
     * 입출력 예시
     *  int[][] maps = {{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,1}, {0,0,0,0,1}};
     *  answer = 11
     *
     *  int[][] maps2 = {{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,0}, {0,0,0,0,1}};
     *  answer = -1
     *
     */
    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,1}, {0,0,0,0,1}};
        int[][] maps2 = {{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,0}, {0,0,0,0,1}};
        System.out.println(solution(maps));
        System.out.println(solution(maps2));
    }

    // ❶ 이동할 수 있는 방향을 나타내는 배열 rx, ry 선언
    private static final int[] rx = {0, 0, 1, -1};
    private static final int[] ry = {1, -1, 0, 0};

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static int solution(int[][] maps) {
        // ❷ 맵의 크기를 저장하는 변수 선언
        int N = maps.length;
        int M = maps[0].length;

        // ❸ 최단 거리를 저장할 배열 생성
        int[][] dist = new int[N][M];

        // ❹ bfs 탐색을 위한 큐 생성
        ArrayDeque<Node> queue = new ArrayDeque<>();

        // ❺ 시작 정점에 대해서 큐에 추가, 최단 거리 저장
        queue.addLast(new Node(0, 0));
        dist[0][0] = 1;

        // ❻ queue가 빌 때까지 반복
        while (!queue.isEmpty()) {
            Node now = queue.pollFirst();

            // ❼ 현재 위치에서 이동할 수 있는 모든 방향
            for (int i = 0; i < 4; i++) {
                int nr = now.r + rx[i];
                int nc = now.c + ry[i];

                // ❽ 맵 밖으로 나가는 경우 예외처리
                if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                    continue;

                // ❾ 벽으로 가는 경우 예외처리
                if (maps[nr][nc] == 0)
                    continue;

                // ➓ 이동한 위치가 처음 방문하는 경우, queue에 추가하고 거리 갱신
                if (dist[nr][nc] == 0) {
                    queue.addLast(new Node(nr, nc));
                    dist[nr][nc] = dist[now.r][now.c] + 1;
                }
            }
        }

        // 목적지까지 최단 거리 반환, 목적지에 도달하지 못한 경우에는 -1 반환
        return dist[N - 1][M - 1] == 0 ? -1 : dist[N - 1][M - 1];
    }
}
