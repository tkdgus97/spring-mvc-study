package com.tkdgus.mvcstudy.frontcontroller.v1;

import com.tkdgus.mvcstudy.frontcontroller.v1.controller.MemberFormController;
import com.tkdgus.mvcstudy.frontcontroller.v1.controller.MemberListController;
import com.tkdgus.mvcstudy.frontcontroller.v1.controller.MemberSaveController;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerV1 extends HttpServlet {
    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    public FrontControllerV1() {
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormController());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveController());
        controllerV1Map.put("/front-controller/v1/members", new MemberListController());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String uri = req.getRequestURI();

        //다형성 활용
        ControllerV1 controller = controllerV1Map.get(uri);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(req, resp);
    }
}
