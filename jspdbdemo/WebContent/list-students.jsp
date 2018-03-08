<%@ page import="java.util.*, com.jspdbdemo.web.jdbc.*" %>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
	<title>Students List DB App Demo</title>
	
	<link type="text/css" rel="stylesheet" href="/css/style.css">
	 
</head>

<%
	// get the students from the request object (sent by servlet)
	List<Student> theStudents = 
		(List<Student>) request.getAttribute("STUDENT_LIST");
%>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>The ALM University</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
	
		<table>
			<tr>
				<th>First name</th>	
				<th>Last name</th>				
				<th>Email</th>	
			</tr>
			<% for (Student tempStudent : theStudents) { %>
				<tr>
					<td> <%= tempStudent.getFirstName() %></td>	
					<td> <%= tempStudent.getLastName() %></td>					
					<td> <%= tempStudent.getEmail() %></td>	
				</tr>
			<% } %>

		</table>	
		
		</div>
	</div>

</body>
</html>