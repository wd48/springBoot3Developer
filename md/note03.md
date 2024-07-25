# SpringBoot3 구조

`계층 => 개념의 영역`   
`컨트롤러, 서비스, 리포지토리 => 실제 구현의 영역`

#### 프리젠테이션 계층 Presentation
- HTTP request 받아 비즈니스 계층(business layer)으로 전송하는 역할
- 컨트롤러(controller)

#### 비즈니스 계층 Business
- 모든 비즈니스 로직 처리 : 서비스를 만들기 위한 로직
- 데이터 처리, 예외 처리(Exception), 프로세스 구현 로직 등   
`퍼시스턴스 계층 persistence layer`에서 제공하는 서비스 사용가능, 권한 부여, 유효성 검사 등 
- 서비스가 비즈니스 계층의 역할을 한다

#### 퍼시스턴스 계층 Persistence
- 모든 DB 관련 로직 처리 (스토리지 관련) : DAO (Database Access Object) 사용할 수 있음
- 리포지토리가 퍼시스턴스 계층의 역할을 한다


### 요청-응답 과정

1. 포스트맨에서 톰캣에 /test GET 요청   
스프링 부트 내로 request 이동
2. DispatcherServlet이 URL 분석 & 요청처리 가능한 컨트롤러 찾음   
TestController 에 '/test' path에 대한 GET 요청 처리가 가능한 getAllMembers() 메소드 가지고 있음   
DispatcherServlet은 TestController에게 /test GET 요청 전달
3. getAllMembers() 메소드와 해당 요청이 매칭   
`비즈니스 계층` 과 `퍼시스턴스 계층`을 통하면서 필요한 데이터 가져옴
4. View Resolver(뷰 리졸버)   
템플릿 엔진을 사용해 HTML 문서 생성, JSON, XML 등의 데이터 생성
5. 결과 members를 return, 데이터를 포스트맨에서 볼 수 있게된다