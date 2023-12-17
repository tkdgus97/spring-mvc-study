package com.tkdgus.mvcstudy.frontcontroller.v3;

import com.tkdgus.mvcstudy.frontcontroller.ModelView;
import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
