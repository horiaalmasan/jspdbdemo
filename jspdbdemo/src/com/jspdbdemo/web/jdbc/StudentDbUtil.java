package com.jspdbdemo.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	
	private DataSource dataSource;
	
	
	public StudentDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Student> getStudents() throws Exception {
		
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try {
		// get a connection
		myConn = dataSource.getConnection();
		
		// create a SQL statement
		String sql = "select * from student order by last_name";
		
		myStmt = myConn.createStatement();
		
		// execute the query
		myRs = myStmt.executeQuery(sql);
		
		
			// process the result set
			while (myRs.next()) {
				
				// retrieve data from the result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
					
				// create a new student object
				Student tempStudent = new Student(id, firstName, lastName, email);
				
				// add it to the list of students
				students.add(tempStudent);
			}
			return students;
		}
		finally {
				// close the JDBC objects
				close(myConn, myStmt, myRs);
		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();		// doesn't really close it, puts it back in the connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
	
}
