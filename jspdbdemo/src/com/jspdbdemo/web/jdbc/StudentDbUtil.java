package com.jspdbdemo.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
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

	public void addStudent(Student theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get the connection
			myConn = dataSource.getConnection();			

			// create the SQL
			String sql = "insert into student "
					+ "(first_name, last_name, email)"
					+ "values (?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the parameter values for the student
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());

			// execute the SQL insert
			myStmt.execute();
		}
		finally {
			// clean up the JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Student getStudent(String theStudentId) throws Exception {
		
		Student theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			// convert student id to int
			studentId  = Integer.parseInt(theStudentId);		
			
			// get the connection
			myConn = dataSource.getConnection();			

			// create the SQL
			String sql = "select * from student where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set the parameter values for the student
			myStmt.setInt(1, studentId);

			// execute the SQL insert
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				// use the studentId during construction
				theStudent = new Student(studentId, firstName, lastName, email);
			}
			else {
				throw new Exception("Could not find student id: " + studentId);
			}
			return theStudent;		
		}
		finally {
			// clean up the JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void updateStudent(Student theStudent) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			// get the connection
			myConn = dataSource.getConnection();			

			// create the SQL
			String sql = "update student "
					+ "set first_name=?, last_name=?, email=? "
					+ "where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set the parameter values for the student
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());

			// execute the SQL update
			myStmt.execute();
		}
		finally {
			// clean up the JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteStudent(String studentId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		int id = Integer.parseInt(studentId);	
		try {
			// get the connection
			myConn = dataSource.getConnection();			

			// create the SQL
			String sql = "delete from student "
					+ "where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set the parameter values for the student
			myStmt.setInt(1, id);

			// execute the SQL update
			myStmt.execute();
		}
		finally {
			// clean up the JDBC objects
			close(myConn, myStmt, null);
		}
		
	}

	public List<Student> searchStudents(String theSearchName)  throws Exception {
        List<Student> students = new ArrayList<>();
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        try {
            
            // get connection to database
            myConn = dataSource.getConnection();
            
            //
            // only search by name if theSearchName is not empty
            //
            if (theSearchName != null && theSearchName.trim().length() > 0) {
                // create sql to search for students by name
                String sql = "select * from student where lower(first_name) like ? or lower(last_name) like ?";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
                // set params
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
                myStmt.setString(2, theSearchNameLike);
                
            } else {
                // create sql to get all students
                String sql = "select * from student order by last_name";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
            }
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            while (myRs.next()) {
                
                // retrieve data from result set row
                int id = myRs.getInt("id");
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");
                
                // create new student object
                Student tempStudent = new Student(id, firstName, lastName, email);
                
                // add it to the list of students
                students.add(tempStudent);            
            }
            
            return students;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }
}
