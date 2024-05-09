<%--
  Created by IntelliJ IDEA.
  User: 고서현
  Date: 2024-04-30
  Time: 오후 5:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <h1>회원 가입</h1>
    <form action="/member/save" method="post">
        <input type="text" name="email" id="email" placeholder="이메일">
        <input type="text" name="password" id="password" placeholder="패스워드">
        <input type="text" name="name" id="name" placeholder="이름">
        <input type="text" name="age" id="age" placeholder="나이">
        <input type="text" name="phone" id="phone" placeholder="전화번호">
        <input type="submit" value="회원 가입">

    </form>
</head>
<body>

</body>
</html>
