package org.xyd.springbootdeveloper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName    : org.xyd.springbootdeveloper
 * fileName       : TestControllerTest
 * author         : user
 * date           : 2024-07-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-09        user       최초 생성
 */
@SpringBootTest
@AutoConfigureMockMvc
class TestControllerTest {
    /**
     * @SpringBootTest
     * 메인 애플리케이션 클래스에 추가하는 애너테이션인 @SpringBootApplication이 있는 클래스를 찾고 그 클래스에 포함되어 있는
     * 빈을 찾은 다음 테이스트용 애플리케이션 컨텍스트라는 것을 만든다
     *
     * @AutoConfigureMockMvc
     * MockMvc를 생성하고 자동으로 구성하는 애너테이션.
     * MockMvc는 애플리케이션을 서버에 배포하지 않고도 테스트용 MVC 환경을 만들어 요청 및 전송, 응답 기능을 제공하는 유틸리티 클래스
     * 컨트롤러를 테스트할 때 사용되는 클래스
     *
     * @BeforeEach
     * 테스트를 실행하기 전에 실행하는 메서드에 적용하는 애너테이션
     *
     * @AfterEach
     * 테스트를 실행한 이후에 실행하는 메서드에 적용하는 애너테이션
     */

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSteUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    public void cleanUp(){
        memberRepository.deleteAll();;
    }

    @DisplayName("getAllMembers: 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {
        // given : 맴버를 저장한다
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        // when : 맴버 리스트를 조회하는 API 호출
        final ResultActions result = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

        // then : 응답 코드가 200 OK이고, 반환받은 값 중에 0번째 요소의 id와 name이 저장된 값과 같은지 확인한다
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }

}