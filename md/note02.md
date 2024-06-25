# SpringBoot3 & Java

- 자바 17버전 이용

---
1. 텍스트 블록 (파이썬처럼 """  """)
2. formatted() 메소드 : 값을 파씽하기위한 메소드
3. 레코드 : private final로 정의, 데이터 전달을 목적으로 객체
4. 패턴 매칭 : instanceof 간략한 사용 가능
5. 자료형에 맞는 case 처리
6. Servlet,JPA 네임스페이스 Jakarta 대체    
   패키지 네임스페이스가 javax.* > jakarta.* 로 변경
7. GraalVM 기반의 스프링 네이티브 공식 지원

---
#### SpringBootApplication.java
![](/md/upload/SpringBootApplication.png)

#### @SpringBootConfiguration
- 스프링 부트 관련 설정 annotation
- @Configuration을 상속하여 만듦

#### @ComponentScan
- 사용자가 등록한 Bean을 읽고 등록하는 annotation
- @Component 가진 클래스들을 찾아 Bean으로 등록하는 역할

| Annotation name              | desc     |
|:-----------------------------|:---------|
| @Configuration               | 설정 파일 등록 |
| @Repository                  | ORM 매핑   |
| @Controller, @RestController | 라우터      |
| @Service                     | 비즈니스 로직  |

#### @EnableAutoConfiguration
- 스프링 부트에서 자동 구성을 활성화하는 annotation
- 스프링 부트 서버가 실행될 때 스프링 부트의 메타 파일을 읽고, 정의된 설정들을 자동으로 구성하는 역할 수행
