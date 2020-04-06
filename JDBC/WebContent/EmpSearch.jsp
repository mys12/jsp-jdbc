<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 페이지</title>
</head>
<body>
<h2>찾으시는 사원의 사원번호를 입력하세요.</h2>
<form action="/JDBC/Emp.do">
사원 번호 : <input type=text name=empId>
<input type=hidden name=action value=search>
&nbsp; <input type=submit value=검색>
</form>
</body>
</html>