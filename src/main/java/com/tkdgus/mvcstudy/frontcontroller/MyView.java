package com.tkdgus.mvcstudy.frontcontroller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, resp);
    }

    public void render(Map<String, Object> model,HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {
        modelToReqAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, resp);
    }

    private void modelToReqAttribute(Map<String, Object> model,HttpServletRequest request) {
        model.forEach((s, o) ->  request.setAttribute(s, o));
    }

}
