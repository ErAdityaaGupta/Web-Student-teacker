package com.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	
	private DataSource dataSource;

	public StudentDbUtil(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}
	
	
	public List<Student> getStudents() throws Exception {
		
		List <Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection 
			myConn = dataSource.getConnection();
			
			// create sql statement 
			String sql = "Select * from students oder by last_name";
			
			myStmt = myConn.createStatement();
			
			// execute querry
			myRs = myStmt.executeQuery(sql);  
			
			// process result set
			while(myRs.next()) {
				// retrive data from result set 
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				// create new student object
				Student tempStudent = new Student(id,firstName,lastName,email);
				
				// add this object to the list of students
				students.add(tempStudent);
			}
			return students;
		}
		finally {
			// close JDBC objects
			close(myConn,myRs,myStmt);
		}
	}

	private void close(Connection myConn, ResultSet myRs, Statement myStmt) {
		try {
			if(myConn != null) {
				myConn.close();
			}
			if(myRs != null) {
				myRs.close();
			}
			if(myStmt != null) {
				myStmt.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
