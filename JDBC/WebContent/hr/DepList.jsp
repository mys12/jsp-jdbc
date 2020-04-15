<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "lab.web.vo.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 목록 페이지</title>
</head>
<body>
<h2>모든 부서 정보</h2>
<table>
<tr>
<td>부서 번호</td>
<td>부서 이름</td>
<td>매니저</td>
<td>지역</td>
</tr>
<tr>
<c:forEach var="dep" items="depList">
<td>${dep.departmentId}</td>
<td>${dep.departmentName}</td>
<td>${dep.managerId}</td>
<td>${dep.locationId}</td>
</tr>
</c:forEach>
</table>
</body>
</html>