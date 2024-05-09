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
				
			}
			
			// close JDBC objects
			
			
			return students;
		}
		finally {
			
		}
		

	}
	
	
	
}
