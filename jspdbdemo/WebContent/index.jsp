<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Java with JSP, JSTL and JDBC Student App Demo</title>
	<link type="text/css" rel="stylesheet" href="css/intro-page.css" />
	
<script>
	function openWin() {
		var w = 700;		
		var h = 600;
		var sW = window.screen.width /2;
		var sH = window.screen.height /2;	
		var mW = sW - w / 2; 
		var mH = sH - h / 2;	
	    window.open("StudentControllerServlet",
	    		"_blank", 
 	    		"toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, copyhistory=no, width=" + w + ", height=" + h + ", left=" + mW + ", top=" + mH);
	}
	window.onload = openWin;
</script>

</head>
<body>
<!-- <div id="intro-page">
	<H3>Java with JSP, JSTL and JDBC Student App Demo</H3>
	The <b>Student App Demo</b> is an MVC application that demonstrates the use of Java with:
	<ul>
		<li>JSP (Java Server Pages)</li>
		<li>JSTL (Java Server Tag Libraries)</li>
		<li>JDBC (Java Database Connector)</li>
		<li>Use of a mySQL database</li>
	</ul>
	<br>
	The application allows to create, edit, delete, and search through the Student records stored in a mySQL database.
	<br><br>
	The application is deployed on Apache Tomcat 8.5, on a CentOs 7 server.
	<br><br>
	The source code is available at: <a href="https://github.com/horiaalmasan/jspdbdemo" target="_blank">github.com/horiaalmasan/jspdbdemo</a> 
	<br><br>
	LinkedIn profile at: <a href="https://www.linkedin.com/in/horiaalmasan" target="_blank">linkedin.com/in/horiaalmasan</a> 
	<br><br>
	<form>
	<input type="button" value="RUN" onclick="openWin()" class="run-button">
	</form>
	
	</div>
 -->
 </body>
</html>