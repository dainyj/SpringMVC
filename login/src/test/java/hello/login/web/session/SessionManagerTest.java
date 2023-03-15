package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.*;

class SessionManagerTest { // test 앞에 public 없어도 됨.

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {

        //세션 생성
        //creat에 member를 넣어서 테스트 해볼 예정인다. HttpServletResponse가 필요 그런데 이거 인터페이스임.
        //가짜로 테스트 할 수 있도록 MockHttpServletResponse가 제공됨.
        //sessionManager에 member와 response를 넣는다.
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, response);
        //여기까지 웹브라우저 요청에 응답.


        //요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());
        //mySessionId = 123123123-12312-qweqwe 예를 들어 이렇게 되어있다고 가정

        //세션 조회
        //클라이언트에서 서버로 넘어왔을 때 세션이 조회 되는가 확인
        //실행했을 때 오류가 없이 초록색이 떴는가로 확인
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        //세션 만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
        
        //실행했을 때 오류 없는 초록색이 뜨면 성공

    }
}
