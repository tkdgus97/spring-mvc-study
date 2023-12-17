package com.tkdgus.mvcstudy.frontcontroller.v2.controller;

import com.tkdgus.mvcstudy.domain.Member;
import com.tkdgus.mvcstudy.domain.MemberRepository;
import com.tkdgus.mvcstudy.frontcontroller.MyView;
import com.tkdgus.mvcstudy.frontcontroller.v1.ControllerV1;
import com.tkdgus.mvcstudy.frontcontroller.v2.ControllerV2;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);

        System.out.println("member = " + member);

        memberRepository.save(member);
        //Model에 데이터를 보관한다.ㅍ
        request.setAttribute("member", member);

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
