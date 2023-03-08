package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 { //컨트롤러가 뷰를 반환하는 특징이 있다.

    MyView process(HttpServletRequest request, HttpServletResponse response)
            throws SecurityException, IOException;
}
