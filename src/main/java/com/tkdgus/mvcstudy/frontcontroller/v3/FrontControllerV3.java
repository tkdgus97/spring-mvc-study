package com.tkdgus.mvcstudy.frontcontroller.v3;

import com.tkdgus.mvcstudy.frontcontroller.ModelView;
import com.tkdgus.mvcstudy.frontcontroller.MyView;
import com.tkdgus.mvcstudy.frontcontroller.v3.controller.MemberFormControllerV3;
import com.tkdgus.mvcstudy.frontcontroller.v3.controller.MemberListControllerV3;
import com.tkdgus.mvcstudy.frontcontroller.v3.controller.MemberSaveControllerV3;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerV1Map = new HashMap<>();

    public FrontControllerV3() {
        controllerV1Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV1Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV1Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String uri = req.getRequestURI();

        //다형성 활용
        ControllerV3 controller = controllerV1Map.get(uri);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(req);

        req.getParameterNames().asIterator().forEachRemaining(s -> paramMap.put(s, req.getParameter(s)));

        ModelView mv = controller.process(paramMap);
        String viewName = mv.getViewName();

        MyView myView = viewResolver(viewName);

        myView.render(mv.getModel(), req, resp);
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();

        req.getParameterNames().asIterator().forEachRemaining(s -> paramMap.put(s, req.getParameter(s)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName +".jsp");
    }
}
