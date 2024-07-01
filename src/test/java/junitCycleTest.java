import org.junit.jupiter.api.*;

public class junitCycleTest {
    // 전체 테스트를 시작하기 전에 1회 실행, 메소드는 static으로 선언
    // DB 연결, 테스트 환경 초기화 시 사용
    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }
    
    // 테스트 케이스 시작하기 전에 매번 실행
    // 테스트 메소드에서 사용하는 객체르 초기화, 테스트에 필요한 값을 미리 넣을 때
    // 각 인스턴스에 대해 메서드를 호출해야 하므로 메소드는 static 이 아니어야 함
    @BeforeEach
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }
    
    @Test
    public void test1() {
        System.out.println("test1");
    }
    
    @Test
    public void test2() {
        System.out.println("test2");
    }
    
    @Test
    public void test3() {
        System.out.println("test3");
    }

    // 전체 테스트를 마치고 종료하기 전에 한 번만 실행
    // DB 연결을 종료할 때, 공통적으로 사용하는 자원을 해제할 때 사용
    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }
    
    // 각 테스트 케이스를 종료하기 전 매번 실행
    // 테슽 이후 특정 데이터를 삭제해야 하는 경우
    @AfterEach
    public void afterEach() {
        System.out.println("@AfterEach");
    }
}