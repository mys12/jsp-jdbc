<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "lab.web.vo.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<c:forEach var="emp" items="${list}">
<tr>
<td>${emp.employeeId}</td>
<td>${emp.firstName}</td>
<td><a href="/JDBC/Emp.do?action=search&empId=${emp.employeeId}">${emp.lastName}</td>
<td>${emp.email}</td>
<td>${emp.phoneNumber}</td>
<td>${emp.hireDate}</td>
<td>${emp.jobId}</td>
<td>${emp.salary}</td>
<td>${emp.commissionPct}</td>
<td>${emp.managerId}</td>
<td>${emp.departmentId}</td>
</tr> 
</c:forEach>
</table>
</body>
</html>