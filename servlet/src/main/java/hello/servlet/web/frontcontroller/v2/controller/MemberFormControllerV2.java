package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    public static final String WEB_INF_VIEWS_NEW_FORM_JSP = "/WEB-INF/views/new-form.jsp";

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        return new MyView("/WEB-INF/views/new-form.jsp");
        //dispatcher.forward()를 생성, 호출 하지 않고 MyView 객체 생성후 뷰 이름 넣고 반환
    }
}
