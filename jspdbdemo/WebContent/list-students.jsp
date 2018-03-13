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
	

		<table>
		
			<tr>
				<td>		
					<!-- Add button -->		
					<input type="button" value="Add Student"
					onclick="window.location.href='add-student-form.jsp'; return false;"
					class="add-student-button" />
				</td>	
				<td>
					<!--  add a search box -->
		            <form action="StudentControllerServlet" method="GET">
		                <input type="hidden" name="command" value="SEARCH" />
		                <input type="submit" value="Search" class="add-student-button" />
		                <input type="text" name="theSearchName" />
		            </form>
				</td>	
				<td>
		          <!-- Reset List button -->		
		          <form action="StudentControllerServlet" method="GET">
			          <input type="hidden" name="command" value="SEARCH" />
			          <input type="submit" value="Reset" class="add-student-button" />
		           </form>
				</td>	
			</tr>
		</table>
		
		<br/>		
		
		<table>
			<tr>
				<th>First name</th>	
				<th>Last name</th>				
				<th>Email</th>	
				<th>Action</th>					
			</tr>
			
			<c:forEach var="tempStudent" items="${STUDENT_LIST }">

				<!--  setup a link for each student -->
				<c:url var="updateLink" value="StudentControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="studentId" value="${tempStudent.id}" />
				</c:url>
				<c:url var="deleteLink" value="StudentControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="studentId" value="${tempStudent.id}" />
				</c:url>			
				
				<tr>
					<td> ${tempStudent.firstName}</td>	
					<td> ${tempStudent.lastName}</td>					
					<td> ${tempStudent.email}</td>	
					<td> 
						<a href="${updateLink}">Update</a>
						|
						<a href="${deleteLink}"
						onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
						Delete</a>
					</td>						
				</tr>
	
			</c:forEach>
		</table>	
		</div>
	</div>

</body>
</html>