import java.util.Arrays;

public class Solution05 {

    /**
     * 2차원 행렬 arr1, arr2 를 입력받아 arr1에 arr2를 곱한 결과를 반환하는 solution() 함수
     *
     * 제약조건
     *  arr1, arr2의 행과 열의 길이는 2 이상 100 이하
     *  arr1, arr2의 데이터는 -10 이상 20 이하 자연수
     *  곱할 수 있는 배열만 주어짐
     * 입출력 예시
     *  arr1 - [[1, 4], [3, 2], [4, 1]]
     *  arr2 - [[3, 3,], [3,3]]
     *  return - [[15, 15], [15,15], [15,15]]
     */
    public static void main(String[] args) {
        int[][] arr1 = new int[][] {{1, 4}, {3, 2}, {4, 1}};
        int[][] arr2 = new int[][] {{3, 3}, {3, 3}};

        System.out.println(Arrays.deepToString(solution2(arr1, arr2)));
    }

    public static int[][] solution2(int[][] arr1, int[][] arr2) {
        int r1 = arr1.length;
        int c1 = arr1[0].length;
        int r2 = arr2.length;
        int c2 = arr2[0].length;

        int[][] answer = new int[r1][c2];

        // A(i * n) * B(n * j) => Cij = ΣAik*Bkj
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        return answer;
    }

    public static int[][] solution(int[][] arr1, int[][] arr2) {
        // ❶ 행렬 arr1과 arr2의 행과 열의 수
        int r1 = arr1.length;
        int c1 = arr1[0].length;
        int r2 = arr2.length;
        int c2 = arr2[0].length;

        // ❷ 결과를 저장할 2차원 배열 초기화
        int[][] answer = new int[r1][c2];

        // ❸ 첫 번째 행렬 arr1의 각 행과 두 번째 행렬 arr2의 각 열에 대해
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                // ❹ 두 행렬의 데이터를 곱해 결과 리스트에 더해줌
                for (int k = 0; k < c1; k++) {
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return answer;
    }
}
