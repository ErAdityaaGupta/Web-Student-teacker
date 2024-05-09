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

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	
	private DataSource dataSource; 
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
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
			listStudents(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get students from db util
		List<Student> students = studentDbUtil.getStudents();
		// add those students to the request object
		request.setAttribute("STUDENT_LIST", students);
		// send it to the jsp page(view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}

}
