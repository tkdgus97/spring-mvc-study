<%@ page import="com.tkdgus.mvcstudy.domain.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
성공
<ul>
    <%--  MvcMemberSaveServlet에서 request.setAttribute에 member로 저장했기 때문에 가능  --%>
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>

    <li>id=<%= ((Member)request.getAttribute("member")).getId()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>