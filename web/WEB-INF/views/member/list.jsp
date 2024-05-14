<%--
  Created by IntelliJ IDEA.
  User: 고서현
  Date: 2024-05-14
  Time: 오후 5:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Member List</title>
</head>
<body>
    <h1>회원 리스트 페이지</h1>
    <table>
        <thead>

        <tr>
            <th>id</th>
            <th>email</th>
            <th>password</th>
            <th>name</th>
            <th>age</th>
            <th>phone</th>
            <th>조회</th>
            <th>삭제</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${memberList}" var="member">
        <tr>
            <td>${member.id}</td>
            <td>${member.email}</td>
            <td>${member.password}</td>
            <td>${member.name}</td>
            <td>${member.age}</td>
            <td>${member.phone}</td>
            <td><button>조회</button></td>
            <td><button>삭제</button></td>

        </tr>
        </c:forEach>

        </tbody>
    </table>
</body>
</html>
