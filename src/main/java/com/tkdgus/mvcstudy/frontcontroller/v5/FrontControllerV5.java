package com.tkdgus.mvcstudy.frontcontroller.v5;

import com.tkdgus.mvcstudy.frontcontroller.ModelView;
import com.tkdgus.mvcstudy.frontcontroller.MyView;
import com.tkdgus.mvcstudy.frontcontroller.v3.controller.MemberFormControllerV3;
import com.tkdgus.mvcstudy.frontcontroller.v3.controller.MemberListControllerV3;
import com.tkdgus.mvcstudy.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.tkdgus.mvcstudy.frontcontroller.v4.ControllerV4;
import com.tkdgus.mvcstudy.frontcontroller.v4.controller.MemberFormControllerV4;
import com.tkdgus.mvcstudy.frontcontroller.v4.controller.MemberListControllerV4;
import com.tkdgus.mvcstudy.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.tkdgus.mvcstudy.frontcontroller.v5.adapter.V3HandlerAdapter;
import com.tkdgus.mvcstudy.frontcontroller.v5.adapter.V4HandlerAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5 extends HttpServlet {
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new V3HandlerAdapter());
        handlerAdapters.add(new V4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        //핸들러 매핑 정보를 통해 핸들러 받아옴
        Object handler = getHandler(req);

        if (handler == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //핸들러 어댑터 가져옴 없을 경우 예외
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        //핸들러를 통해 처리한 후 modelview 받아옴
        ModelView mv = adapter.handle(req, resp, handler);

        String viewName = mv.getViewName();
        MyView myView = viewResolver(viewName);

        myView.render(req, resp);
    }

    private Object getHandler(HttpServletRequest req) {
        String uri = req.getRequestURI();
        return handlerMappingMap.get(uri);
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName +".jsp");
    }
    
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.support(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("핸들러 어댑터를 찾을 수 없습니다.");
    }

}
