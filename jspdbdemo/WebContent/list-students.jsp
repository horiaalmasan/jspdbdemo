<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
	<title>Students List DB App Demo</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
	 
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>The ALM University</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
	
	<!-- button -->
	
		<input type="button" value="Add Student"
			onclick="window.location.href='add-student-form.jsp'; return false;"
			class="add-student-button"
		/>
		
		<table>
			<tr>
				<th>First name</th>	
				<th>Last name</th>				
				<th>Email</th>	
			</tr>
			
			<c:forEach var="tempStudent" items="${STUDENT_LIST }">
				<tr>
					<td> ${tempStudent.firstName}</td>	
					<td> ${tempStudent.lastName}</td>					
					<td> ${tempStudent.email}</td>	
				</tr>
	
			</c:forEach>
		</table>	
		
		</div>
	</div>

</body>
</html>