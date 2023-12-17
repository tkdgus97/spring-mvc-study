package com.tkdgus.mvcstudy.frontcontroller.v3.controller;

import com.tkdgus.mvcstudy.domain.Member;
import com.tkdgus.mvcstudy.domain.MemberRepository;
import com.tkdgus.mvcstudy.frontcontroller.ModelView;
import com.tkdgus.mvcstudy.frontcontroller.v3.ControllerV3;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String userNmme = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("username"));

        Member m = new Member(userNmme, age);
        memberRepository.save(m);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", m);
        return mv;
    }
}
