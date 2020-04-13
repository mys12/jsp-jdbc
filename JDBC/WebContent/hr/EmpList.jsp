<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "lab.web.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 페이지</title>
</head>
<body>
<h2>모든 회원 정보</h2>
<table>
<tr>
<td>사원 정보</td>
<td>이름</td>
<td>성</td>
<td>이메일</td>
<td>연락처</td>
<td>입사일</td>
<td>직무</td>
<td>월급</td>
<td>보너스율</td>
<td>매니저</td>
<td>부서</td>
</tr>
<% ArrayList<EmpVO> list = (ArrayList<EmpVO>)request.getAttribute("list");%>
<tr>
<%for(EmpVO emp : list) { %>
<td><%=emp.getEmployeeId() %></td>
<td><%=emp.getFirstName() %></td>
<td><a href="/JDBC/Emp.do?action=search&empId=<%=emp.getEmployeeId()%>"><%=emp.getLastName() %></td>
<td><%=emp.getEmail() %></td>
<td><%=emp.getPhoneNumber() %></td>
<td><%=emp.getHireDate() %></td>
<td><%=emp.getJobId() %></td>
<td><%=emp.getSalary() %></td>
<td><%=emp.getCommissionPct() %></td>
<td><%=emp.getManagerId() %></td>
<td><%=emp.getDepartmentId() %></td>
<tr>
<% } %> 
</table>
</body>
</html>