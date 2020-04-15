<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="lab.web.vo.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 정보 페이지</title>
</head>
<body>
사원번호 : ${emp.employeeId} <br>
이름 : ${emp.firstName}<br>
성 : ${emp.lastName}<br>
이메일 : ${emp.email}<br>
전화번호 : ${emp.phoneNumber}<br>
입사일 : ${emp.hireDate}<br>
직무 : ${emp.jobTitle}(${emp.jobId})<br>
급여 : ${emp.salary}<br>
보너스율 : ${emp.commissionPct}<br>
매니저 : ${emp.managerName}({emp.managerId})<br>
부서 : ${emp.departmentName}(${emp.departmentId})<br>

<a href="/JDBC/Emp.do?action=update&empId=${emp.employeeId}">정보 수정</a>
&nbsp; <a href="/JDBC/Emp.do?action=delete&empId=${emp.employeeId}">정보 삭제</a>
</body>
</html>