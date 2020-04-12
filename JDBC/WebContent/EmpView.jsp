<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="lab.web.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 정보 페이지</title>
</head>
<body>
<%EmpDetailVO emp = (EmpDetailVO)request.getAttribute("emp"); %>
사원번호 : <%=emp.getEmployeeId() %> <br>
이름 : <%=emp.getFirstName() %><br>
성 : <%=emp.getLastName() %><br>
이메일 : <%=emp.getEmail() %><br>
전화번호 : <%=emp.getPhoneNumber() %><br>
입사일 : <%=emp.getHireDate() %><br>
직무 : <%=emp.getJobTitle() %>(<%=emp.getJobId() %>)<br>
급여 : <%=emp.getSalary() %><br>
보너스율 : <%=emp.getCommissionPct() %><br>
매니저 : <%=emp.getManagerName() %>(<%=emp.getManagerId() %>)<br>
부서 : <%=emp.getDepartmentName() %>(<%=emp.getDepartmentId() %>)<br>

<a href="/JDBC/Emp.do?action=update&empId=<%=emp.getEmployeeId()%>">정보 수정</a>
&nbsp; <a href="/JDBC/Emp.do?action=delete&empId=<%=emp.getEmployeeId()%>">정보 삭제</a>
</body>
</html>