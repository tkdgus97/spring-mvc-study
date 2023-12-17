package com.tkdgus.mvcstudy.frontcontroller.v2;

import com.tkdgus.mvcstudy.frontcontroller.MyView;
import com.tkdgus.mvcstudy.frontcontroller.v2.controller.MemberFormControllerV2;
import com.tkdgus.mvcstudy.frontcontroller.v2.controller.MemberListControllerV2;
import com.tkdgus.mvcstudy.frontcontroller.v2.controller.MemberSaveControllerV2;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerV1Map = new HashMap<>();

    public FrontControllerV2() {
        controllerV1Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV1Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV1Map.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String uri = req.getRequestURI();

        //다형성 활용
        ControllerV2 controller = controllerV1Map.get(uri);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView mv = controller.process(req, resp);
        mv.render(req, resp);
    }
}
