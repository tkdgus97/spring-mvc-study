package com.tkdgus.mvcstudy.frontcontroller.v4;

import com.tkdgus.mvcstudy.frontcontroller.ModelView;
import com.tkdgus.mvcstudy.frontcontroller.MyView;
import com.tkdgus.mvcstudy.frontcontroller.v3.ControllerV3;
import com.tkdgus.mvcstudy.frontcontroller.v4.controller.MemberFormControllerV4;
import com.tkdgus.mvcstudy.frontcontroller.v4.controller.MemberListControllerV4;
import com.tkdgus.mvcstudy.frontcontroller.v4.controller.MemberSaveControllerV4;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerV1Map = new HashMap<>();

    public FrontControllerV4() {
        controllerV1Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV1Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV1Map.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String uri = req.getRequestURI();

        //다형성 활용
        ControllerV4 controller = controllerV1Map.get(uri);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(req);

        req.getParameterNames().asIterator().forEachRemaining(s -> paramMap.put(s, req.getParameter(s)));
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);

        MyView myView = viewResolver(viewName);

        myView.render(model, req, resp);
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
