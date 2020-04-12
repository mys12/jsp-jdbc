<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "lab.web.vo.*" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 <%=request.getAttribute("message")%> 페이지</title>
</head>
<body>
<h2>정보 <%=request.getAttribute("message")%></h2>
<form action="/JDBC/Emp.do" method=post>
<input type=hidden name=action value=<%=request.getAttribute("action")%>>
<%EmpVO emp = (EmpVO)request.getAttribute("emp");%>
<table>
<%if(emp==null) {%>
<tr>
<td> 사원 아이디 : </td> <td><input type=text name=empId></td>
</tr>
<tr>
<td> 이름 , 성 : </td> <td><input type=text name=firstName>
<input type=text name=lastName></td>
</tr>
<tr>
<td> 이메일 : </td> <td><input type=text name=email></td>
</tr>
<tr>
<td> 연락처 : </td> <td><input type=text name=phoneNumber></td>
</tr>
<tr>
<td> 입사일 : </td> <td><input type=date name=hireDate></td>
</tr>
<%ArrayList<JobVO> jobList = (ArrayList<JobVO>)request.getAttribute("jobList"); %>
<tr>
<td>직무 </td><td><select name=jobId>
<%for(JobVO job : jobList) { %>
<option value=<%=job.getJobId()%>><%=job.getJobTitle()%></option>
<%} %>
</select></td></tr>
<tr>
<td>급여 </td><td><input type=text name=salary></td>
</tr>
<tr>
<td>보너스율 </td><td><input type=number min=0 max=1 step=0.05 name=commissionPct></td>
</tr>
<%ArrayList<EmpVO> manList = (ArrayList<EmpVO>)request.getAttribute("manList"); %>
<tr>
<td>매니저 </td><td><select name=managerId>
<%for(EmpVO man : manList) { %>
<option value=<%=man.getEmployeeId()%>><%=man.getFirstName() %></option>
<% }%>
</select></td>
</tr>
<%ArrayList<DeptVO> deptList = (ArrayList<DeptVO>)request.getAttribute("deptList"); %>
<tr>
<td>부서</td><td><select name=departmentId>
<%for(DeptVO dept : deptList) { %>
<option value=<%=dept.getDepartmentId()%>><%=dept.getDepartmentName() %></option>
<% } %>
</select></td></tr>
<%} else { %>
<tr>
<td> 사원 아이디 : </td> <td><input type=text name=empId value=<%=emp.getEmployeeId()%> readonly></td>
</tr>
<tr>
<td> 이름 , 성 : </td> <td><input type=text name=firstName value=<%=emp.getFirstName()%>>
<input type=text name=lastName value=<%=emp.getLastName()%>></td>
</tr>
<tr>
<td> 이메일 : </td> <td><input type=text name=email value=<%=emp.getEmail()%> readonly></td>
</tr>
<tr>
<td> 연락처 : </td> <td><input type=text name=phoneNumber value=<%=emp.getPhoneNumber()%>></td>
</tr>
<tr>
<td> 입사일 : </td> <td><input type=date name=hireDate value=<%=emp.getHireDate()%>></td>
</tr>
<%ArrayList<JobVO> jobList = (ArrayList<JobVO>)request.getAttribute("jobList"); %>
<tr>
<td>직무 </td><td><select name=jobId>
<%for(JobVO job : jobList) { %>
<option value=<%=job.getJobId()%> <%=job.getJobId().equals(emp.getJobId()) ? "selected" : "" %>><%=job.getJobTitle()%></option>
<%} %>
</select></td></tr>
<tr>
<td>급여 </td><td><input type=text name=salary value=<%=emp.getSalary()%>></td>
</tr>
<tr>
<td>보너스율 </td><td><input type=number min=0 max=1 step=0.05 name=commissionPct value=<%=emp.getCommissionPct()%>></td>
</tr>
<%ArrayList<EmpVO> manList = (ArrayList<EmpVO>)request.getAttribute("manList"); %>
<tr>
<td>매니저 </td><td><select name=managerId>
<%for(EmpVO man : manList) { %>
<option value=<%=man.getEmployeeId()%> <%=man.getEmployeeId()==emp.getManagerId() ? "selected" : "" %>><%=man.getFirstName() %></option>
<% }%>
</select></td>
</tr>
<%ArrayList<DeptVO> deptList = (ArrayList<DeptVO>)request.getAttribute("deptList"); %>
<tr>
<td>부서</td><td><select name=departmentId>
<%for(DeptVO dept : deptList) { %>
<option value=<%=dept.getDepartmentId()%> <%=dept.getDepartmentId()==emp.getDepartmentId() ? "selected" : "" %>><%=dept.getDepartmentName() %></option>
<% } %>
</select></td></tr>
<%} %>
<tr>
<td><input type=submit value=<%=request.getAttribute("message")%>> 
<input type=reset value=취소>
</td></tr>
</table>
</form>
</body>
</html>