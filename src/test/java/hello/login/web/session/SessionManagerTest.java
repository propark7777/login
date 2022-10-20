package hello.login.web.session;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import hello.login.domain.member.Member;
import javax.servlet.http.HttpServletResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class SessionManagerTest {
    SessionManager sessionManager = new SessionManager();

    @Test
    void createSession() {

        //세션생성
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member,response);

        //요청한 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //세션조회
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        //세션만료
        sessionManager.expire(request);
        Object expire = sessionManager.getSession(request);
        assertThat(expire).isNull();
    }

}