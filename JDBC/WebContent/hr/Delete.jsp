<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="lab.web.vo.EmpVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 삭제 페이지</title>
</head>
<body>
<h2>정보를 정말 삭제하시겠습니까?</h2>
<form action="/JDBC/Emp.do" method=post>
<input type=hidden name=action value=<%=request.getAttribute("action")%>>
<%EmpVO emp = (EmpVO)request.getAttribute("emp");%>
<input type=hidden name=empId value=<%=emp.getEmployeeId()%>>
<input type=submit name=delete value="삭제">
&nbsp; <a href="/JDBC/Emp.do?action=list">취소</a>
</form>
</body>
</html>