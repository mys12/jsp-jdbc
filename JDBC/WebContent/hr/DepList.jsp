<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "lab.web.vo.*" %>
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
<%ArrayList<DeptVO> depList = (ArrayList<DeptVO>)request.getAttribute("deptList"); %>
<tr>
<%for(DeptVO dep : depList) {%>
<td><%=dep.getDepartmentId() %></td>
<td><%=dep.getDepartmentName() %></td>
<td><%=dep.getManagerId() %></td>
<td><%=dep.getLocationId() %></td>
</tr>
<%} %>
</table>
</body>
</html>