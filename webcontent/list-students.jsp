<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.util.*, com.web.jdbc.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Tracker App</title>
</head>

	<%
	// get students from the request object (sent by the servlet)
			List<Student> students = (List<Student>) request.getAttribute("STUDENT_LIST");
	%>

<body>    

	<div id = "wrapper">
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
					<!--  <a href="StudentControllerServlet?command=DELETE&studentId=<%=student.getId()%>">Delete</a></td>  -->
				</tr>
				<%
                    }
                %>

			</table>
		
		</div>
	</div>

</body>
</html>