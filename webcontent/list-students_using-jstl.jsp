<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Tracker App</title>
<style>
	body {
		font-family: Arial, sans-serif;
		background-color: #f2f2f2;
	}
	
	#wrapper {
		background-color: #fff;
		padding: 20px;
		margin: 20px;
		border: 1px solid #ccc;
		border-radius: 5px;
	}
	
	#header {
		text-align: center;
		margin-bottom: 20px;
	}
	
	table {
		width: 100%;
		border-collapse: collapse;
	}
	
	th, td {
		padding: 10px;
		text-align: left;
		border-bottom: 1px solid #ccc;
	}
	
	.add-student-button {
		background-color: #4CAF50;
		color: white;
		padding: 10px 20px;
		border: none;
		border-radius: 5px;
		cursor: pointer;
	}
	
	.add-student-button:hover {
		background-color: #45a049;
	}
	
	a {
		color: #007bff;
		text-decoration: none;
	}
	
	a:hover {
		text-decoration: underline;
	}
</style>
</head>
<body>    

	<div id="wrapper">
		<div id="header">
			<h2>Student Tracker App</h2>
		</div>
	</div>


	<div id="container">
		<div id="content">
		
			<input type="button" value="Add Student" onclick="window.location='add-student-form.jsp'; return false;"  class="add-student-button" />
			
			<table>

				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>

				<c:forEach var="student" items="${STUDENT_LIST}">
				
				<!-- set up a link for each student  -->
					<c:url var="studentLink" value="StudentControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="studentId" value="${student.getId()}" />
                    </c:url>
						<tr>
							<td>${ student.getFirstName()}</td>
							<td>${student.getLastName()}</td>
							<td>${student.getEmail()}</td>
							<td><a href="${tempLink }">Update</a></td>

							<td><a
								href="StudentControllerServlet?command=DELETE&studentId=${student.getId()}">Delete</a></td>
						</tr>
				</c:forEach>
				
			</table>
		
		</div>
	</div>
	
	<c:out value="${STUDENT_LIST}" />


</body>
</html>