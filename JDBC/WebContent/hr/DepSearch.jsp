<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 검색 페이지</title>
</head>
<body>
<h2>찾으시는 부서번호를 입력하세요.</h2>
<form action="/JDBC/Emp.do">
부서 번호 : <input type=text name=depId>
<input type=hidden name=action value=depSearch>
&nbsp; <input type=submit value=검색>
</form>
</body>
</html>