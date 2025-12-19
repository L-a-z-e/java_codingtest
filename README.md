# Java Coding Test - 코딩 테스트 완벽 정복

**46개의 정선된 Java 알고리즘 문제**로 구성된 코딩 테스트 준비 리포지토리입니다. 기초 배열/정렬부터 고급 그래프/동적 계획법까지 **실전 취업 면접 수준**의 문제들을 다룹니다.

---

## 🎯 저장소 개요

| 항목 | 설명 |
|------|------|
| **총 문제 수** | 46개 |
| **주요 주제** | 배열, 정렬, 완전탐색, 해시, 스택/큐, 트리, 동적계획법 등 |
| **난이도** | 초급 → 고급 (점진적 상향) |
| **학습 기간** | 4-6주 (주당 10-15시간) |
| **추천 대상** | 코딩 테스트 준비, 알고리즘 기초 다지기, 기술 면접 준비 |
| **언어** | Java |

---

## 📊 문제 구성 분석

### 문제별 분류 (카테고리 추정)

```
배열/정렬 (Array & Sorting)
├─ Solution01: 배열 정렬 (Arrays.sort)
├─ Solution02: 수 비교
├─ Solution03: 두 수의 합 (HashSet, 중복 제거)
└─ Solution05: 배열 조작

문자열 (String)
├─ Solution04: 문자열 처리
├─ Solution08: 문자열 분석
└─ Solution09: 문자열 조작

완전탐색 (Brute Force & Combination)
├─ Solution06: 모든 경우의 수
├─ Solution10: 조합 생성
└─ Solution14: 순열/조합

해시/맵 (Hash & HashMap)
├─ Solution12: HashMap 활용
├─ Solution13: 중복 제거
└─ Solution16: 데이터 매핑

스택/큐 (Stack & Queue)
├─ Solution11: 스택 구조
├─ Solution17: 큐 구조
└─ Solution20: 우선순위 큐

시뮬레이션 (Simulation)
├─ Solution07: 좌표 이동 시뮬레이션
├─ Solution19: 규칙 기반 시뮬레이션
└─ Solution23: 복잡한 규칙 처리

동적계획법 (Dynamic Programming)
├─ Solution21: 기본 DP
├─ Solution25: DP 최적화
└─ Solution27: 경로 찾기 DP

그래프/트리 (Graph & Tree)
├─ Solution22: 그래프 탐색
├─ Solution26: 트리 순회
└─ Solution30: 경로 탐색

그 외 (Others)
├─ Solution31-46: 심화 주제
└─ Test.java: 테스트 클래스
```

---

## 🚀 빠른 시작 가이드

### 1단계: 환경 설정

**필요 사항**
- JDK 8 이상
- IntelliJ IDEA 또는 VS Code
- Git

**클론 및 실행**
```bash
# 리포지토리 클론
git clone https://github.com/L-a-z-e/java_codingtest.git
cd java_codingtest

# IDE에서 열기
# IntelliJ IDEA: File → Open → java_codingtest 폴더 선택
# VS Code: code .
```

### 2단계: 문제 풀기

**각 Solution 파일 구조**
```java
public class SolutionXX {
    /**
     * 문제 설명
     * 제약조건
     * 입출력 예시
     */
    
    public static void main(String[] args) {
        // 테스트 케이스
        System.out.println(solution(testInput));
    }
    
    private static returnType solution(inputType) {
        // 풀이 코드
        return result;
    }
}
```

**실행 방법**
```bash
# 특정 문제 실행 (IDE의 Run 기능)
# 또는 커맨드라인
javac src/Solution01.java
java -cp src/ Solution01

# 전체 컴파일
javac src/*.java
```

### 3단계: 검증 및 최적화

```bash
# 시간 복잡도 측정
long start = System.currentTimeMillis();
// 풀이 실행
long end = System.currentTimeMillis();
System.out.println("시간: " + (end - start) + "ms");
```

---

## 📚 문제별 상세 가이드

### 초급 (Solution 01-10): 기초 알고리즘

#### Solution 01: 배열 정렬 ⭐

**개념**
- Arrays.sort() 사용
- 시간 복잡도: O(n log n)

**풀이 핵심**
```java
public static int[] solution(int[] arr) {
    Arrays.sort(arr);
    return arr;
}
```

**학습 포인트**
- 배열의 기본 조작
- 정렬의 시간 복잡도 이해
- clone()으로 원본 보호

---

#### Solution 03: 두 수의 합 ⭐⭐

**개념**
- 중첩 반복문으로 모든 쌍 찾기
- HashSet으로 중복 제거
- Stream API로 정렬 및 변환

**풀이**
```java
public static int[] solution(int[] numbers) {
    HashSet<Integer> set = new HashSet<>();
    
    // 모든 쌍 (i, j)에 대해 합 계산
    for (int i = 0; i < numbers.length - 1; i++) {
        for (int j = i + 1; j < numbers.length; j++) {
            set.add(numbers[i] + numbers[j]);  // 자동 중복 제거
        }
    }
    
    // Stream으로 정렬 및 배열 변환
    return set.stream()
        .sorted()
        .mapToInt(Integer::intValue)
        .toArray();
}
```

**시간/공간 복잡도**
- 시간: O(n²) + O(m log m) = O(n²)
- 공간: O(n²) - HashSet 크기

---

#### Solution 07: 좌표 이동 시뮬레이션 ⭐⭐

**개념**
- 2D 좌표계 움직임
- 방문 경로 추적
- 양방향 경로 처리

**핵심 알고리즘**
```java
private static final HashMap<Character, int[]> direction = new HashMap<>();

static {
    direction.put('U', new int[]{0, 1});   // Up
    direction.put('D', new int[]{0, -1});  // Down
    direction.put('L', new int[]{-1, 0});  // Left
    direction.put('R', new int[]{1, 0});   // Right
}

public static int solution(String dirs) {
    int x = 5, y = 5;  // 좌표 범위: -5 ~ 5 (11x11 그리드)
    HashSet<String> paths = new HashSet<>();
    
    for (char dir : dirs.toCharArray()) {
        int[] offset = direction.get(dir);
        int nx = x + offset[0];
        int ny = y + offset[1];
        
        // 경계 체크
        if (nx < -5 || nx > 5 || ny < -5 || ny > 5) continue;
        
        // 양방향 경로 추가 (A→B, B→A는 같은 경로)
        paths.add(x + " " + y + " " + nx + " " + ny);
        paths.add(nx + " " + ny + " " + x + " " + y);
        
        x = nx;
        y = ny;
    }
    
    return paths.size() / 2;  // 양방향이므로 2로 나눔
}
```

**학습 포인트**
- HashMap으로 방향 매핑
- Set으로 경로 추적
- 2D 좌표 관리

---

### 중급 (Solution 11-25): 핵심 자료구조

#### 스택/큐 패턴
```java
// Solution 11: Stack 사용
Stack<Integer> stack = new Stack<>();
stack.push(value);
int top = stack.pop();

// Solution 17: Queue 사용
Queue<Integer> queue = new LinkedList<>();
queue.offer(value);
int front = queue.poll();
```

#### 해시맵 패턴
```java
// Solution 12: HashMap으로 빈도수 세기
HashMap<Integer, Integer> map = new HashMap<>();
for (int num : numbers) {
    map.put(num, map.getOrDefault(num, 0) + 1);
}
```

#### 동적계획법 기초
```java
// Solution 21: DP 배열 활용
int[] dp = new int[n];
dp[0] = base_case;

for (int i = 1; i < n; i++) {
    dp[i] = dp[i-1] + calculation;  // 이전 상태를 이용해 현재 상태 계산
}

return dp[n-1];
```

---

### 고급 (Solution 26-46): 심화 알고리즘

#### 그래프 탐색
```java
// BFS/DFS 기본 구조
void dfs(int node, boolean[] visited) {
    visited[node] = true;
    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            dfs(neighbor, visited);
        }
    }
}
```

#### 복잡한 시뮬레이션
```java
// Solution 23+: 복잡한 규칙 처리
// - 여러 조건 분기
// - 상태 변화 관리
// - 특수 케이스 처리
```

---

## 🛠 주요 Java 기법 및 패턴

### 1. 시간 복잡도 분석

```java
// O(1) - 상수시간
int value = array[0];

// O(n) - 선형시간
for (int i = 0; i < n; i++) { }

// O(n²) - 제곱시간
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) { }
}

// O(log n) - 로그시간
while (n > 0) { n /= 2; }

// O(n log n) - 선형로그시간 (정렬)
Arrays.sort(array);
```

### 2. Collections 활용

```java
// List - 삽입 순서 유지
List<Integer> list = new ArrayList<>();
list.add(value);
list.get(index);
list.remove(index);

// Set - 중복 제거
Set<Integer> set = new HashSet<>();
set.add(value);
set.contains(value);

// Map - 키-값 매핑
Map<String, Integer> map = new HashMap<>();
map.put(key, value);
map.get(key);
map.getOrDefault(key, defaultValue);

// Queue - FIFO
Queue<Integer> queue = new LinkedList<>();
queue.offer(value);  // add와 같음
queue.poll();        // remove와 같음

// Stack - LIFO
Stack<Integer> stack = new Stack<>();
stack.push(value);
stack.pop();
```

### 3. Stream API 활용

```java
// 필터링 및 변환
int[] result = set.stream()
    .filter(x -> x > 0)           // 필터
    .sorted()                      // 정렬
    .mapToInt(Integer::intValue)  // 매핑
    .toArray();                    // 배열로 변환

// 빈도수 구하기
List<Integer> list = Arrays.asList(1, 2, 2, 3, 3, 3);
Map<Integer, Long> frequency = list.stream()
    .collect(Collectors.groupingBy(Function.identity(), 
                                   Collectors.counting()));
```

### 4. 객체 생성 및 정렬

```java
// 커스텀 클래스
class Person implements Comparable<Person> {
    String name;
    int age;
    
    @Override
    public int compareTo(Person other) {
        return this.age - other.age;  // 나이 기준 오름차순
    }
}

// 정렬
Person[] people = new Person[n];
Arrays.sort(people);  // compareTo 기준으로 정렬

// 또는 Comparator 사용
Arrays.sort(people, (a, b) -> a.age - b.age);
```

### 5. 재귀와 백트래킹

```java
// 조합 생성
void combination(int[] arr, int r, int start, 
                 int[] result, int depth) {
    if (depth == r) {
        // 조합 완성
        process(result);
        return;
    }
    
    for (int i = start; i < arr.length; i++) {
        result[depth] = arr[i];
        combination(arr, r, i + 1, result, depth + 1);
    }
}

// 순열 생성
void permutation(int[] arr, boolean[] used, 
                 int[] result, int depth) {
    if (depth == arr.length) {
        // 순열 완성
        process(result);
        return;
    }
    
    for (int i = 0; i < arr.length; i++) {
        if (!used[i]) {
            used[i] = true;
            result[depth] = arr[i];
            permutation(arr, used, result, depth + 1);
            used[i] = false;
        }
    }
}
```

---

## 💡 풀이 전략

### 1단계: 문제 이해
- 입출력 형식 파악
- 제약조건 확인 (크기 제한, 값 범위)
- 예시 케이스 따라가보기

### 2단계: 접근 방법 선택
```
복잡도 확인
├─ n ≤ 100: O(n³) 가능
├─ n ≤ 1,000: O(n²) 필요
├─ n ≤ 100,000: O(n log n) 필요
└─ n ≤ 1,000,000: O(n) 필수

문제 특성 파악
├─ 배열/정렬: Arrays.sort 고려
├─ 중복 제거: Set 사용
├─ 빈도수: Map 사용
├─ 탐색: BFS/DFS
└─ 최적화: DP
```

### 3단계: 구현 및 테스트
```java
// 기본 구조
1. 입력값 검증
2. 예시 케이스 테스트
3. 엣지 케이스 테스트
4. 성능 측정
5. 코드 리팩토링
```

### 4단계: 최적화
- 불필요한 연산 제거
- 자료구조 선택 재검토
- 알고리즘 시간복잡도 개선

---

## 📈 학습 로드맵

### Week 1-2: 기초 개념 (Solution 01-10)
```
Day 1-2:   배열, 정렬, 탐색
Day 3-4:   문자열 처리
Day 5:     완전탐색, 조합
Day 6-7:   기초 문제 복습 및 심화
Day 8-10:  추가 기초 문제
Day 11-14: 복합 기초 문제
```

### Week 3-4: 핵심 자료구조 (Solution 11-25)
```
Day 1-2:   스택/큐
Day 3-4:   해시/맵
Day 5-6:   동적계획법 기초
Day 7-8:   시뮬레이션
Day 9-14:  자료구조 응용 문제
```

### Week 5-6: 심화 알고리즘 (Solution 26-46)
```
Day 1-2:   그래프 알고리즘
Day 3-4:   트리/탐색 심화
Day 5-6:   동적계획법 심화
Day 7-10:  복합 문제 풀이
Day 11-14: 모의고사 및 복습
```

---

## ❓ 자주 하는 실수

### 1. 시간 초과 (Time Limit Exceeded)

❌ **잘못된 예**
```java
// O(n²) 알고리즘인데 n이 10⁵인 경우
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        // 10억 번의 반복!
    }
}
```

✅ **올바른 예**
```java
// O(n log n) 정렬 사용
Arrays.sort(arr);
// 또는 O(n) 해시맵
HashMap<Integer, Integer> map = new HashMap<>();
```

### 2. 배열 범위 오류 (Index Out of Bounds)

❌ **잘못된 예**
```java
for (int i = 0; i <= arr.length; i++) {  // arr.length는 범위 밖!
    System.out.println(arr[i]);
}
```

✅ **올바른 예**
```java
for (int i = 0; i < arr.length; i++) {  // 0부터 length-1까지
    System.out.println(arr[i]);
}
```

### 3. Null Pointer Exception

❌ **잘못된 예**
```java
String str = null;
int len = str.length();  // NullPointerException!
```

✅ **올바른 예**
```java
String str = null;
if (str != null) {
    int len = str.length();
}
// 또는
int len = str == null ? 0 : str.length();
```

### 4. 원본 배열 변경

❌ **잘못된 예**
```java
int[] original = {3, 1, 2};
Arrays.sort(original);  // 원본이 변경됨!
```

✅ **올바른 예**
```java
int[] original = {3, 1, 2};
int[] sorted = original.clone();  // 복사본 생성
Arrays.sort(sorted);
```

---

## 🧪 테스트 작성

### 기본 테스트 구조

```java
public class Solution01Test {
    
    @Test
    public void testBasicCase() {
        int[] input = {1, -5, 2, 4, 3};
        int[] expected = {-5, 1, 2, 3, 4};
        int[] result = Solution01.solution(input);
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void testEdgeCase() {
        // 최소 크기
        int[] input = {2, 1};
        int[] expected = {1, 2};
        assertArrayEquals(expected, Solution01.solution(input));
    }
    
    @Test
    public void testLargeInput() {
        // 성능 테스트
        int[] input = new int[100000];
        for (int i = 0; i < 100000; i++) {
            input[i] = 100000 - i;
        }
        
        long start = System.currentTimeMillis();
        Solution01.solution(input);
        long time = System.currentTimeMillis() - start;
        
        System.out.println("Time: " + time + "ms");
        assertTrue(time < 1000);  // 1초 이내
    }
}
```

---

## 📊 채용시험 난이도 분포

```
쉬움 (Easy):   Solution 01-15   (35%)
중간 (Medium): Solution 16-35   (45%)
어려움 (Hard): Solution 36-46   (20%)

실전 팁:
- 실전 시험에서는 보통 3-4개 문제 출제
- 쉬운 문제 1개 + 중간 문제 2-3개 + 어려운 문제 0-1개
- 70% 정도 정답률로도 합격 가능
```

---

## 🎯 학습 목표 달성 체크리스트

```
배열/정렬
[_] Arrays.sort() 사용법
[_] 이진 탐색 (Binary Search)
[_] 문제에 맞는 정렬 조건 선택

해시/맵
[_] HashMap 기본 사용법
[_] HashSet으로 중복 제거
[_] 빈도수 계산

스택/큐
[_] Stack 구현 및 활용
[_] Queue와 Deque의 차이
[_] 우선순위 큐 (PriorityQueue)

동적계획법
[_] DP 테이블 설계
[_] 점화식 유도
[_] 메모이제이션 vs 타뷸레이션

그래프
[_] BFS/DFS 구현
[_] 경로 찾기
[_] 최단 경로 알고리즘

고급 주제
[_] 분할 정복 (Divide & Conquer)
[_] 탐욕 알고리즘 (Greedy)
[_] 백트래킹

완성도: __/20 영역
```

---

## 💬 Java 팁과 트릭

### 문자열 처리
```java
// 문자열을 문자 배열로
char[] chars = "hello".toCharArray();

// 문자 배열을 문자열로
String str = new String(chars);

// 역순 문자열
String reversed = new StringBuilder("hello").reverse().toString();

// 특정 문자 개수
long count = "hello".chars().filter(ch -> ch == 'l').count();
```

### 수학 연산
```java
// 최대/최소값
int max = Math.max(a, b);
int min = Math.min(a, b);

// 절댓값
int abs = Math.abs(-5);  // 5

// 거듭제곱
int power = (int) Math.pow(2, 3);  // 8

// 제곱근
int sqrt = (int) Math.sqrt(16);  // 4

// 나머지
int mod = 10 % 3;  // 1
```

### 숫자 변환
```java
// 문자를 숫자로
int num = Character.getNumericValue('5');
int num2 = Integer.parseInt("123");

// 숫자를 문자로
char ch = Character.forDigit(5, 10);
String str = String.valueOf(123);

// 진법 변환
String binary = Integer.toBinaryString(10);    // "1010"
String hex = Integer.toHexString(255);         // "ff"
int fromBinary = Integer.parseInt("1010", 2);  // 10
```

---

## 🚨 주의사항

1. **메모리 제한**: 보통 256MB
   - 배열 크기: int[10⁷] ≈ 40MB
   - 큰 배열은 메모리 효율적으로 처리

2. **시간 제한**: 보통 1-3초
   - 1초 ≈ 10⁸ 연산
   - O(n²) 알고리즘: n ≤ 10⁴ 안전

3. **정확도**: 부동소수점 오류 주의
   - 정수 계산 권장
   - 필요 시 BigDecimal 사용

4. **오버플로우**: Integer 범위 초과 주의
   - int: -2³¹ ~ 2³¹-1
   - long: -2⁶³ ~ 2⁶³-1
   - 필요 시 long 사용

---

## 📖 추가 학습 자료

### 온라인 플랫폼
- **LeetCode**: 영문 코딩 테스트 (https://leetcode.com)
- **프로그래머스**: 한국 코딩 테스트 (https://programmers.co.kr)
- **백준**: 알고리즘 문제 (https://www.acmicpc.net)

---

## 📝 사용 가이드

### IDE 설정 (IntelliJ IDEA)

1. **프로젝트 열기**
   - File → Open → java_codingtest 폴더

2. **Run Configuration**
   - Edit Configurations
   - Java Application 추가
   - Main class: Solution01

3. **디버깅**
   - 코드에 중단점(Breakpoint) 설정
   - Debug 모드로 실행
   - 변수 값 확인

### 커맨드라인 사용법

```bash
# 단일 파일 컴파일 및 실행
javac src/Solution01.java
java -cp src/ Solution01

# 모든 파일 컴파일
javac src/*.java

# 특정 클래스 실행
java -cp src/ Solution03
```

---

## ✨ 팁과 권장사항

### 효율적인 학습 방법

1. **단계별 학습**
   - 이전 문제를 충분히 이해한 후 다음 진행
   - 무리하지 말 것

2. **다양한 풀이 방법**
   - 한 문제를 여러 방법으로 풀어보기
   - 시간/공간 복잡도 비교

3. **코드 리뷰**
   - 다른 사람의 풀이 참고
   - 더 나은 방법 학습

4. **정기적 복습**
   - 1주일 후 다시 풀기
   - 2주일 후 다시 풀기
   - 한 달 후 다시 풀기
