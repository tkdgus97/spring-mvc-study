package com.tkdgus.mvcstudy.frontcontroller.v1.controller;


import com.tkdgus.mvcstudy.frontcontroller.v1.ControllerV1;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MemberFormController implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";

        //컨트롤러에서 view로 이동시 사용
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
