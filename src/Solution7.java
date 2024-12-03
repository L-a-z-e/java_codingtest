import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Solution7 {

    /**
     * U,D,R,L <-> 위, 아래, 오른쪽, 왼쪽 1칸 이동
     * 캐릭터 (0, 0) 시작
     * 좌표평면은 x, y 축 -5 ~ 5
     * 처음 걸어본 길의 길이를 구해 반환하는 solution 함수
     * 제약사항
     *  dirs는 String형 U, D, R, L만 주어짐
     *  경계를 넘어가는 명령은 무시
     *  dirs의 길이는 500 이하 자연수
     * 입출력 예시
     *  dirs - ULURRDLLU
     *  answer - 7
     */
    public static void main(String[] args) {
        String dirs = "ULURRDLLULUDDRRDURLDRLDRLDURLRULLULR";
        System.out.println(solution(dirs));
        System.out.println(solution2(dirs));

    }

    /**
     * 문제 분석
     *  처음본 길이를 구하려면 이전에 갔던 길인지를 판단하고 아니면 +1 맞으면 0
     *  정의되어야하는 것 ? 이전에 갔던 길
     *  (1,1) -> (2,1) 로 갔으면 그 다음에는 (2,1) -> (1,1) 이전에 갔던 길
     *  1. 시작 좌표 (0, 0) 으로부터 지나친 지점의 값의 쌍을 저장 -> 그뒤 정렬, 경계를 넘어가는 값은 무시
     *  2. 어떻게 이전에 왔던 길인지 확인할 수 있는지? (0,0) - (1,0) - (1,1) - (2,1) - (1,1) - (1,0) 순서쌍을 정렬? 아니면 두개 순서를 뒤집어서 같은 쌍들을 제거?
     *      제거하면 (0,0) 기존위치 제거하고 1쌍이 나옴 -> 이 숫자를 구해도 답이 되는건지?
     *  3. 예를 들어서 (0,0) (0,1) (1,1) (1,0) (2,0) (2,-1) (1,-1) (1,0) 이렇게 움직이면 이미 갔던 길은 없는데 저 논리에 따르면 6이 나와야되므로 상상으로 추론 X
     *  4. 이전에 왔던 길이라 함은 (0, 0) (1, 0) (0,0) 에서 0,0의 U 가 들어오면 1,0 의 D 와 동일함 그럼 어떤 값이 들어왔을 때 움직인 거리 + 1을 하기 위해서는
     *      Map에 두가지 방향으로 값이 존재하지 않는 경우 이동거리 + 1을 해주는 방향이 적절해보임
     *      예를 들어 (1,1) U 가 들어왔다면 -> (1,2) D 도 같이 넣는 방식 그래서 실제 현재 좌표와 움직인 동선을 기록하는 것이 동시에 이루어져야 함
     *  5. 좌표계를 넘어가는 경우 (5, n) 에서 R이 들어오는경우 현상 유지이므로 길이체크나 움직인 거리에 영향 없음
     */

    public static int solution2(String dirs) {

        int result = 0;
        class Location {
            int x;
            int y;

            public Location(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public void up() {
                if(y < 5)
                    y += 1;
            }

            public void down() {
                if(y > -5)
                    y -= 1;
            }

            public void left() {
                if(x > -5)
                    x -= 1;
            }

            public void right() {
                if(x < 5)
                    x += 1;
            }

            @Override
            public int hashCode() {
                return Objects.hash(x,y);
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (!(obj instanceof Location)) return false;
                Location location = (Location) obj;
                return this.x == location.x && this.y == location.y;
            }
        }

        class LocationAndDirection {
            Location location;
            char direction;

            public LocationAndDirection(Location location, char direction) {
                this.location = location;
                this.direction = direction;
            }

            public Location getLocation() {
                return location;
            }

            public void setLocation(Location location) {
                this.location = location;
            }

            public char getDirection() {
                return direction;
            }

            public void setDirection(char direction) {
                this.direction = direction;
            }

            @Override
            public int hashCode() {
                return Objects.hash(location,direction);
            }

            @Override
            public boolean equals(Object obj) {
                if(this == obj) return true;
                if(!(obj instanceof LocationAndDirection)) return false;
                LocationAndDirection locationAndDirection = (LocationAndDirection) obj;
                return this.location.equals(locationAndDirection.getLocation()) && this.direction == locationAndDirection.getDirection();
            }
        }

        HashSet<LocationAndDirection> visitedPath = new HashSet<>();

        Location location = new Location(0, 0);
        char reverseDirection = 'A';

        for (char c : dirs.toCharArray()) {

            Location beforeLocation = new Location(location.getX(), location.getY());
            char direction = c;


            switch (c) {
                case 'U':
                    location.up();
                    reverseDirection = 'D';
                    break;

                case 'D':
                    location.down();
                    reverseDirection = 'U';
                    break;

                case 'L':
                    location.left();
                    reverseDirection = 'R';
                    break;

                case 'R':
                    location.right();
                    reverseDirection = 'L';
                    break;
            }

            LocationAndDirection locationAndDirection = new LocationAndDirection(beforeLocation, c);
            LocationAndDirection beforeLocationAndDirection = new LocationAndDirection(location, reverseDirection);

            if(!visitedPath.contains(beforeLocationAndDirection)) {
                visitedPath.add(beforeLocationAndDirection);
                if(!visitedPath.contains(locationAndDirection)) {
                    result += 1;
                    visitedPath.add(locationAndDirection);
                }
            }

        }


        return result;
    }


    // ❶ 좌표평면을 벗어나는지 체크하는 메소드
    private static boolean isValidMove(int nx, int ny) {
        return 0 <= nx && nx < 11 && 0 <= ny && ny < 11;
    }

    // ❷ 다음 좌표 결정을 위한 HashMap 생성
    private static final HashMap<Character, int[]> location = new HashMap<>();

    private static void initLocation() {
        location.put('U', new int[]{0, 1});
        location.put('D', new int[]{0, -1});
        location.put('L', new int[]{-1, 0});
        location.put('R', new int[]{1, 0});
    }

    public static int solution(String dirs) {
        initLocation();
        int x = 5, y = 5;
        HashSet<String> answer = new HashSet<>(); // ❸ 겹치는 좌표는 1개로 처리하기 위함
        // ❹ 주어진 명령어로 움직이면서 좌표 저장
        for (int i = 0; i < dirs.length(); i++) {
            int[] offset = location.get(dirs.charAt(i));
            int nx = x + offset[0];
            int ny = y + offset[1];
            if (!isValidMove(nx, ny)) // ❺ 벗어난 좌표는 인정하지 않음
                continue;
            // ❻ A에서 B로 간 경우 B에서 A도 추가해야 함(총 경로의 개수는 방향성이 없음)
            answer.add(x + " " + y + " " + nx + " " + ny);
            answer.add(nx + " " + ny + " " + x + " " + y);
            // ❼ 좌표를 이동했으므로 업데이트
            x = nx;
            y = ny;
        }

        return answer.size() / 2;
    }
}
