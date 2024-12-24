import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution22 {
    /**
     * 문제
     *  장르별로 가장 많이 재생된 노래 2개씩 모아 베스트 앨범 출시
     *  속한 노래가 많이 재생된 장르 먼저 수록
     *  장르 내에서 많이 재생된 노래 먼저 수록
     *  장르 내에서 재생 횟수가 같으면 고유 번호가 낮은 노래를 먼저 수록
     *  노래의 장르 나타내는 문자열 배열 genres
     *  노래 재생 횟수 나타내는 정수배열 plays 주어짐
     *  베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 반환하는 solution 작성
     * 제약조건
     *  genres[i] 는 고유 번호가 i인 노래의 장르
     *  plays[i]는 고유 번호가 i인 노래가 재생된 횟수
     *  1 <= genres.length, plays.length <= 10,000
     *  장르 종류는 100개 미만
     *  장르에 속한 곡이 하나라면 하나만 선택
     *  모든 장르는 재생된 횟수가 다름
     *
     * 입출력 예시
     *  String[] genres = {"classic", "pop", "classic", "classic", "pop"};
     *  int[] plays = {500, 600, 150, 800, 2500};
     *  result = {4, 1, 3, 0};
     */
    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        System.out.println(Arrays.toString(solution(genres, plays)));
//        System.out.println(Arrays.toString(solution2(genres, plays)));
    }

    /**
     * 문제 분석
     * 입력 10000개이하 이므로 O(N^2) 까지 사용가능
     * 1. 장르별로 최대 2개 선택 (개별곡 order by 횟수, 고유번호)
     * 2. 장르별 재생횟수 높은 순으로 출력
     * 보류
     */
//    public static int[] solution2(String[] genres, int[] plays) {
//      // 장르 목록, 재생횟수 Map 의 Value별로 정렬이 가능한가?
//        HashMap<String, ArrayList<Integer>> genresPlaysMap = new HashMap<>();
//        for (int i = 0; i < genres.length; i++){
//            if (genresPlaysMap.containsKey(genres[i])){
//                genresPlaysMap.get(genres[i]).add(plays[i]);
//            }
//            ArrayList<Integer> playsList = new ArrayList<>();
//            playsList.add(plays[i]);
//            genresPlaysMap.put(genres[i], playsList);
//        }
//
//        HashMap<String, Integer> genresTotalPlays = genresPlaysMap.entrySet().stream()
//                .collect(Collectors.toMap(
//                        entry -> entry.getKey(),
//                        entry -> entry.getValue().stream().mapToInt(Integer::intValue).sum()
//                ));
//
//    }

    // 풀이
    public static int[] solution(String[] genres, int[] plays) {
        HashMap<String, ArrayList<int[]>> genreMap = new HashMap<>();
        HashMap<String, Integer> playMap = new HashMap<>();

        // ❶ 장르별 총 재생 횟수와 각 곡의 재생 횟수 저장
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            if (!genreMap.containsKey(genre)) {
                genreMap.put(genre, new ArrayList<>());
                playMap.put(genre, 0);
            }
            genreMap.get(genre).add(new int[]{i, play});
            playMap.put(genre, playMap.get(genre) + play);
        }

        ArrayList<Integer> answer = new ArrayList<>();

        // ❷ 총 재생 횟수가 많은 장르순으로 내림차순 정렬
        Stream<Map.Entry<String, Integer>> sortedGenre = playMap.entrySet()
                .stream()
                .sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));

        // ❸ 각 장르 내에서 노래를 재생 횟수 순으로 정렬해 최대 2곡까지 선택
        sortedGenre.forEach(entry -> {
            Stream<int[]> sortedSongs = genreMap.get(entry.getKey()).stream()
                    .sorted((o1, o2) -> Integer.compare(o2[1], o1[1]))
                    .limit(2);
            sortedSongs.forEach(song -> answer.add(song[0]));
        });

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
