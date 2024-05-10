<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import = "java.util.*, com.web.jdbc.*"%>
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
	th {
		background-color: #f2f2f2;
	}
	a {
		color: #007bff;
		text-decoration: none;
	}
</style>
</head>

<%
	// get students from the request object (sent by the servlet)
	List<Student> students = (List<Student>) request.getAttribute("STUDENT_LIST");
%>

<body>    

	<div id="wrapper">
		<div id="header">
			<h2>Student Tracker App</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th></th>
				</tr>

				<%
					for(Student student: students){
				%>
				<tr>
					<td><%=student.getFirstName()%></td>
					<td><%=student.getLastName()%></td>
					<td><%=student.getEmail()%></td>
					<td>
						<a href="StudentControllerServlet?command=DELETE&studentId=<%=student.getId()%>">Delete</a>
					</td> 
				</tr>
				<%
					}
				%>

			</table>
		
		</div>
	</div>

</body>
</html>
