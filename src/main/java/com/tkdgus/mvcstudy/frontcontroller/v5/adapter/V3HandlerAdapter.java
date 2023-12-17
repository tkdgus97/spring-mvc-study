package com.tkdgus.mvcstudy.frontcontroller.v5.adapter;

import com.tkdgus.mvcstudy.frontcontroller.ModelView;
import com.tkdgus.mvcstudy.frontcontroller.MyView;
import com.tkdgus.mvcstudy.frontcontroller.v3.ControllerV3;
import com.tkdgus.mvcstudy.frontcontroller.v5.MyHandlerAdapter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class V3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler)
        throws ServletException, IOException {
        ControllerV3 controllerV3 = (ControllerV3) handler;


        Map<String, String> paramMap = createParamMap(req);
        return controllerV3.process(paramMap);
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();

        req.getParameterNames().asIterator().forEachRemaining(s -> paramMap.put(s, req.getParameter(s)));
        return paramMap;
    }
}
