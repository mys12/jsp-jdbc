<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<h2>로그인</h2>
<%=request.getAttribute("message")==null ? "" : request.getAttribute("message")%>
<form action="/JDBC/Login.do" method="post">
<br>
아이디 : <input type=text name=userId><br>
비밀번호 : <input type=password name=pw><br>
<input type=submit value=로그인>
</form>
</body>
</html>