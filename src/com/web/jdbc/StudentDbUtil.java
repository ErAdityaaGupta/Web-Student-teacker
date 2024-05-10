package com.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			String sql = "SELECT * FROM student ORDER BY last_name";
			
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


	public void addStudent(Student theStudent) {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
			//get db connection
			myConn = dataSource.getConnection();
			
			//create sql for 
			String sql = "INSERT INTO student" + "(first_name, last_name, email)" + "VALUES (?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			//set the param values for the student
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			
			//execute sql insert
			myStmt.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(myConn, null, myStmt);
		}
	}


	public Student getStudent(String theStudentId) {
		
		Student theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		int studentId;
		
		try {
			// convert student id to int
			studentId = Integer.parseInt(theStudentId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected student
			String sql = "SELECT * FROM student WHERE id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, studentId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				// use the studentId during construction
				theStudent = new Student(studentId, firstName, lastName, email);
			} else {
				throw new Exception("Could not find student id: " + studentId);
			}

			return theStudent;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(myConn, myRs, myStmt);
		}
		
		
		return theStudent;
	}


	public void updateStudent(Student theStudent) {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql update statement
			String sql = "UPDATE student " + "SET first_name=?, last_name=?, email=? " + "WHERE id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());

			// execute sql statement
			myStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(myConn, null, myStmt);
		}
		
	}
	
	
	
}
