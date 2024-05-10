package com.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_suudent_tracker")
	
	private DataSource dataSource; 
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util and pass in the conn pool  
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		}
		catch(Exception e) {
			throw new ServletException(e);
		}		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// list the students in the mvc fashion 
		try {
			
			// read the command parameter
			
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing the students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch(theCommand) {
			case "LIST":
				listStudents(request, response);
				break;
				
			case "ADD":
				addStudent(request, response);
				break;
				
			case "LOAD":
				loadStudent(request, response);
				break;
				
			case "UPDATE":
				updateStudent(request, response);
				break;
				
			default:
				listStudents(request, response);
			}
			
			
			
			listStudents(request,response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) {
		// read student info from form data
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		// create a new student object
		Student theStudent = new Student(id, firstName, lastName, email);

		// perform update on database
		try {
			studentDbUtil.updateStudent(theStudent);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// send back to the list of students page 
		try {
			listStudents(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// read student id from form data
		
		String theStudentId = request.getParameter("studentId");
		
		// get student from the database(db util)
		
		Student theStudent = studentDbUtil.getStudent(theStudentId);
		
		// place student in the request attribute
		
		request.setAttribute("THE_STUDENT", theStudent);
		
		// send to jsp page: update-student-form.jsp
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
		
		
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read the student info from the form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// create a new student object
		Student theStudent = new Student(firstName, lastName, email);
		
		// add the student to the database
		studentDbUtil.addStudent(theStudent);
		
		// send back to main page(the student list)
		listStudents(request,response);
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get students from db util
		List<Student> students = studentDbUtil.getStudents();
		// add those students to the request object
		request.setAttribute("STUDENT_LIST", students);
		// send it to the jsp page(view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students_using-jstl.jsp");
		dispatcher.forward(request, response);
	}

}
