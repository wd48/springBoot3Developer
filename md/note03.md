# SpringBoot3 구조

`계층 => 개념의 영역`   
`컨트롤러, 서비스, 리포지토리 => 실제 구현의 영역`

#### 프리젠테이션 계층
- HTTP request 받아 비즈니스 계층으로 전송하는 역할
- 컨트롤러

#### 비즈니스 계층
- 모든 비즈니스 로직 처리 : 서비스를 만들기 위한 로직
- 데이터 처리, 예외 처리, 프로세스 구현 로직 등
- 서비스가 비즈니스 계층의 역할을 한다

#### 퍼시스턴스 계층
- 모든 DB 관련 로직 처리 : DAO (Database Access Object) 사용할 수 있음
- 리포지토리가 퍼시스턴스 계층의 역할을 한다