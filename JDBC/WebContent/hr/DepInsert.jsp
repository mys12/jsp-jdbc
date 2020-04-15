<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "lab.web.vo.*" %>
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 정보 입력 페이지</title>
</head>
<body>
<h2>부서 정보 입력</h2>
<form action="/JDBC/Emp.do" method=post>
<input type=hidden name=action value=insertDep>
<table>
<tr>
<td>부서 번호</td><td><input type=text name=depId></td>
</tr>
<tr>
<td>부서 이름</td><td><input type=text name=depName></td>
</tr>
<tr>
<td>매니저 </td><td><select name=managerId>
<c:forEach var="man" items="${manList}">
<option value="${man.employeeId}">${man.firstName}</option>
</c:forEach>
</select></td>
</tr>
<tr>
<td>도시 이름</td><td><select name=locId>
<c:forEach var="city" items="${cityList}">
<option value="${city.locationId}">${city.city}</option>
</c:forEach>
</select></td>
</tr>
<tr>
<td><input type=submit value=저장> 
<input type=reset value=취소>
</td></tr>
</table>
</form>
</body>
</html>