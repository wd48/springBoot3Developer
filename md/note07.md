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

### Summary
- @Controller : 반환값으로 view를 찾아 보여주는 애너테이션
- 탬플릿 엔진 : 데이터를 넘겨받아 HTML에 데이터를 넣어 동적인 웹페이지를 만들어주는 도구
- thymeleaf : 스프링의 대표적인 템플릿 엔진. 컨트롤러에서 모델을 통해 데이터를 설정하면, 모델은 뷰에서 사용할 수 있게 데이터를 전달해준다.