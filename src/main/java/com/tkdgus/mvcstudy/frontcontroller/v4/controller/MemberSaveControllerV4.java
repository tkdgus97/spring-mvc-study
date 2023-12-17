package com.tkdgus.mvcstudy.frontcontroller.v4.controller;

import com.tkdgus.mvcstudy.domain.Member;
import com.tkdgus.mvcstudy.domain.MemberRepository;
import com.tkdgus.mvcstudy.frontcontroller.ModelView;
import com.tkdgus.mvcstudy.frontcontroller.v3.ControllerV3;
import com.tkdgus.mvcstudy.frontcontroller.v4.ControllerV4;
import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String userNmme = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member m = new Member(userNmme, age);
        memberRepository.save(m);

        model.put("member", m);
        return "save-result";
    }
}
