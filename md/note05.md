# ORM (object-relational mapping)
- 자바의 객체와 데이터베이스를 연결하는 프로그래밍 기법
- 데이터베이스의 값을 객체처럼 사용할 수 있다

| 장점                                                                                                                                                    | 단점                                                             |
|:------------------------------------------------------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------|
| SQL을 직접 작성하지 않고 사용하는 언어로 DB 접근 가능<br/>객체지향적 코드 작성 가능 (비즈니스 로직 집중)<br/>DB 시스템에 대한 종속성 줄어듦 (mysql, postgreSql 전환 등)<br/>매핑 정보가 명확하여 ERD 의존도 낮음, 유지보수 용이 | 프로젝트 복잡성이 커지면 사용 난이도가 올라감<br/>복잡하고 무거운 쿼리는 ORM으로 해결 불가할 경우도 있음 |

# JPA, 하이버네이트
## JPA
- 자바에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스
- 자바 객체와 DB를 연결해 데이터 관리
- 객체 지향 도메인 모델과 데이터베이스의 다리 역할
## 하이버네이트
- JPA 인터페이스를 구현한 구현체이자 자바용 ORM 프레임워크
- 자바 객체를 통해 DB 종류에 상관없이 데이터베이스를 자유롭게 쓰게하도록 하는 목적이 있음
- 내부적으로 JDBC API 사용

### 엔티티
- DB 테이블과 매핑되는 객체
- 본질적으로 자바 객체이므로 일반 객체와 다르지 않다
- 하지만 DB의 테이블과 직접 연결된다는 특징때문에 별도의 명칭으로 부르는 것

### 엔티티 매니저
- 엔티티를 관리해 DB와 애플리케이션 사이에서 객체를 생성, 수정, 삭제하는 등의 역할을 한다
- @PersistenceContext 또는 @Autowired 사용해서 엔티티 매니저는 사용한다

## 영속성 컨텍스트
- 엔티티를 관리하는 가상의 공간 : 1차 캐시, 쓰기 지연, 변경 감지, 지연 로딩
- 모두 데이터베이스의 접근을 최소화해 성능을 높일 수 있다

| 종류    | 설명                                                                                                                                       |
|:------|:-----------------------------------------------------------------------------------------------------------------------------------------|
| 1차 캐시 | 영속성 컨텍스트는 내부에 1차 캐시를 가지고 있다 (캐시의 키 : Id 달린 기본키 식별자, 엔티티)<br/>엔티티 조회 시 1차 캐시에서 데이터 조회 : 값이 있으면 반환, 값이 없으면 DB 조회                           |
| 쓰기 지연 | 트랜잭션을 커밋하기 전까지 DB에 실제로 쿼리를 보내지 않고 모았다가,<br/>트랜잭션을 커밋하면 모았던 쿼리를 한번에 실행하는 것                                                                |
| 변경 감지 | 트랜잭션을 커밋하면 1차 캐시에 저장되어 있는 엔티티의 값과 현재 엔티티의 값 비교,<br/>변경된 값이 있으면 변경 사항을 감지해 변경된 값을 데이터베이스에 자동으로 반영한다.<br/>쓰기 지연과 마찬가지로 적당한 묶음으로 쿼리 요청 가능하다 |
| 지연 로딩 | 쿼리로 요청한 데이터를 애플리케이션에 바로 로딩하는 것이 아닌 필요할 때 쿼리를 날려 데이터를 조회하는 것.                                                                             |
