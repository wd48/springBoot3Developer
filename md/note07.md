# 화면 구성하기 : thymeleaf

### 타임리프 표현식과 문법

- 표현식

    | 표현식   | 설명                               |
    |:------|:---------------------------------|
    | ${..} | 변수의 값                            |
    | #{..} | 속성 파일 값                          |
    | @{..} | URL                              |
    | *{..} | 선택한 변수, th:object 에서 선택한 객체에 접근함 |

- 문법

  |    표현식     |        설명         |                   예제                    |
  |:----------:|:-----------------:|:---------------------------------------:|
  |  th:text   |    텍스트 표현 시 사용    |         th:text=${person.name}          |
  |  th:each   |    컬렉션 반복 시 사용    |      th:each="person : ${persons}"      |
  |   th:if    |  조건이 true인 때만 표시  |        th:if="${person.age}>=20"        |
  | th:unless  | 조건이 false인 때만 표시  |      th:unless="${person.age}>=20"      |
  |  th:href   |       이동 경로       | th:href="@{/persons(id=${person.id})}"  |
  |  th:with   |     변수값으로 지정      |      th:with="name=${person.name}"      |
  | th:object  |    선택한 객체로 지정     |          th:object="${person}"          |
