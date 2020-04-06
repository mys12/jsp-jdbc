<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="lab.web.vo.EmpVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 정보 페이지</title>
</head>
<body>
<%EmpVO emp = (EmpVO)request.getAttribute("emp"); %>
사원번호 : <%=emp.getEmployeeId() %> <br>
이름 : <%=emp.getFirstName() %><br>
성 : <%=emp.getLastName() %><br>
이메일 : <%=emp.getEmail() %><br>
전화번호 : <%=emp.getPhoneNumber() %><br>
입사일 : <%=emp.getHireDate() %><br>
직무 : <%=emp.getJobId() %><br>
급여 : <%=emp.getSalary() %><br>
보너스율 : <%=emp.getCommissionPct() %><br>
매니저 : <%=emp.getManagerId() %><br>
부서 : <%=emp.getDepartmentId() %><br>
</body>
</html>