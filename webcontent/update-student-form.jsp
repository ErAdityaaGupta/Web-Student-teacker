<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Student </title>
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h1>Add New Student</h1>
		</div>
	</div>
	
	<div id="container">	
		<form action="StudentCotrollerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE"> 
			
			<input type="hidden" name="studentId" value="${THE_STUDENT.id}">
			<table>
				<tr>
					<td>First Name:</td>
					<td><input type="text" name="firstName"
								value="${THE_STUDENT.firstName}"></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" name="lastName"
								value="${THE_STUDENT.lastName }"></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email"
								value="${THE_STUDENT.email }"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Add Student"></td>
				</tr>
			</table>
		</form>
		
		<div style = "clear:both"></div>
		
		<p>
		    <a href="list-students_using-jstl.jsp">View Student List</a>
		</p>
		
	</div>
</body>
</html>