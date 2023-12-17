package com.tkdgus.mvcstudy.frontcontroller.v2.controller;

import com.tkdgus.mvcstudy.domain.Member;
import com.tkdgus.mvcstudy.domain.MemberRepository;
import com.tkdgus.mvcstudy.frontcontroller.MyView;
import com.tkdgus.mvcstudy.frontcontroller.v1.ControllerV1;
import com.tkdgus.mvcstudy.frontcontroller.v2.ControllerV2;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberListControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        request.setAttribute("members", members);

        return new MyView("/WEB-INF/views/members.jsp");
    }
}
