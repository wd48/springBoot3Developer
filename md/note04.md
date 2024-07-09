![](/md/upload/img_pjt04_1.png)
- @BeforeAll 메소드 실행
- 테스트 케이스 수만큼(@Test) @BeforeEach - @Test - @AfterEach 생명주기로 진행
- 모든 테스트 케이스까 끝나면 @AfterAll 메소드 실행 후 종료

@BeforeAll 클래스 레벨 설정   
ㅣ   
@BeforeEach 메서드 레벨 설정   
ㅣ   
@Test 테스트 실행   
ㅣ   
@AfterEach 메서드 레벨 정리 (테스트 개수만큼 반복 to @BeforeEach)   
ㅣ   
@AfterAll 클래스 레벨 정리