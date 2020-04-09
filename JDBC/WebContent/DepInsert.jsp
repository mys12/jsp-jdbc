<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="lab.web.vo.*" %>
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
<td> 부서 번호 : </td> <td><input type=text name=depId></td>
</tr>
<tr>
<td> 부서 이름 : </td> <td><input type=text name=departmentName></td>
</tr>
<%ArrayList<EmpVO> manList = (ArrayList<EmpVO>)request.getAttribute("manList"); %>
<tr>
<td> 매니저 : </td> <td><select name=managerId>
<%for(EmpVO man : manList) { %>
<option value=<%=man.getEmployeeId()%>><%=man.getFirstName()%></option>
<% } %>
</select></td></tr>
<%ArrayList<LocationsVO> cityList = (ArrayList<LocationsVO>)request.getAttribute("cityList");%>
<tr>
<td> 지역 : </td> <td><select name =locationId>
<%for(LocationsVO city : cityList) { %>
<option value=<%=city.getLocationId()%>><%=city.getCity()%></option>
<% } %>
</select> </td></tr>
<tr>
<td><input type=submit value=저장> 
<input type=reset value=취소>
</td></tr>
</table>
</form>
</body>
</html>