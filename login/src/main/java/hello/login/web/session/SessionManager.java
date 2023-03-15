package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 세션 관리
 */
@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "mySessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();
    //id 비번 저장

    /**
     * 세션 생성
     * 1- sessionId 생성(임의의 추정 불가능한 랜덤 값)
     * 2- 세션 저장소에 sessionId와 보관할 값 저장
     * 3- sessionId로 응답 쿠키를 생성해서 클라이언트에 전달
     */
    public void createSession(Object value, HttpServletResponse response) {

        //1세션 id를 생성하고, 값을 세션에 저장
        String sessionId = UUID.randomUUID().toString();
        //자바가 제공해주는 UUID를 사용하면 랜덤 ID값을 얻을 수 있다.
        sessionStore.put(sessionId, value); // value는 랜덤값

        //2쿠키 생성
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        //쿠키 네임은 쓸데가 많으므로 상수로(대문자) //단축키 ctrl + alt + C
        response.addCookie(mySessionCookie);

    }

    /**
     * 3세션 조회
     */
    public Object getSession(HttpServletRequest request) {
        //먼저 쿠키 찾기

        /*
    1)
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie :
                cookies) {
            if (cookie.getName().equals(SESSION_COOKIE_NAME)) {
                return sessionStore.get(cookie.getValue());
            }
        }
        처음 이 상태에서 쿠키를 찾는 부분만 별도로 떼어낼 예정
        아래에 계속

    2-1)
        public Cookie findCookie(HttpServletRequest request, String cookisName){
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
             return null;
            }
            return Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(cookieName))
                    .findAny()
                    .orElse(null);

    2-2)
        public Cookie findCookie(HttpServletRequest request, String cookisName){
            if (request.getCookies() == null) { // 코드 합치기 ctrl + alt + N
             return null;
            }
            return Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(cookieName))
                    .findAny()
                    .orElse(null);

    1)을 아래의 코드로 수정
        */
    // 수정된 1)
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie == null) {
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());
    }

    /**
     * 세션 만료
     */
    public void expire(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
        }
    }

    //세션 만료까지 만든 다음 Test를 만든다. SessionManagerTest 클래스 만들기.
    //Test 폴더의 web 안 session폴더에 만들기


    //수정된 2)
    public Cookie findCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) {
            return null;
        }

        // null이 아니면 stream으로 바꾸면서
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
                //쿠키이름이 있으면 반환 아니면 null
    }

}
