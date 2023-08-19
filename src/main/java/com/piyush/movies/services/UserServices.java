package com.piyush.movies.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Service;


import com.piyush.movies.entities.User;

@Service
public class UserServices {

	public String checkUser(String username, String password) {

		String status = "";

		// ---------------------------------------------------------------------creating
		// connection with database
		Connection con;
		java.sql.PreparedStatement pst;
		ResultSet rs;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // loading the jdbc driver
			con = DriverManager.getConnection(
					"jdbc:mysql://b1u92pbhura4174wgghm-mysql.services.clever-cloud.com:3306/b1u92pbhura4174wgghm?user=u47xuprrwteh0ves&password=e0WAyH8UlUDHOLd6AZIy"); // connection
																																										// string

			pst = con.prepareStatement("select * from Users where username=? and password=?;");

			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			
			
			if (rs.next()) {
			    String userType = rs.getString("usertype");
			    
			    if (userType.equals("admin")) {
			        status = "Admin.jsp";
			    } else if (userType.equals("user")) {
			        status = "User.jsp";
			    } else {
			        status = "Failed.jsp";
			    }
			} else {
			    status = "Failed.jsp";
			}
			

		} catch (Exception e) {
			status = "Failed";
		}

		return status;

	}

	public String addNewUser(User u) {

		String status = "";
		// ---------------------------------------------------------------------creating
		// connection with database
		Connection con;
		java.sql.PreparedStatement pst;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // loading the jdbc driver
			con = DriverManager.getConnection(
					"jdbc:mysql://b1u92pbhura4174wgghm-mysql.services.clever-cloud.com:3306/b1u92pbhura4174wgghm?user=u47xuprrwteh0ves&password=e0WAyH8UlUDHOLd6AZIy"); // connection
																																										// string

			pst = con.prepareStatement("INSERT INTO Users (username, password, age, gender, usertype) VALUES (?,?,?,?,default);");

			pst.setString(1, u.getUsername());
			pst.setString(2, u.getPassword());
			pst.setInt(3, u.getAge());
			pst.setString(4, u.getGender());


			pst.executeUpdate();
			con.close();
			status = "Success";

		} catch (Exception e) {
			status = "Failed";
		}

		return status;
	}
	
	public ArrayList<User> generateUserReport() {
		ArrayList<User> list = new ArrayList<>();
		User obj;

		Connection con;
		java.sql.Statement st;
		ResultSet rs;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // loading the jdbc driver
			con = DriverManager.getConnection(
					"jdbc:mysql://b1u92pbhura4174wgghm-mysql.services.clever-cloud.com:3306/b1u92pbhura4174wgghm?user=u47xuprrwteh0ves&password=e0WAyH8UlUDHOLd6AZIy"); // connection
																																										// string

			st = con.createStatement();
			rs = st.executeQuery("select * from Users;");

			while (rs.next()) {
				obj = new User();
				obj.setUserId(rs.getInt("user_id"));
				obj.setUsername(rs.getString("username"));
				obj.setPassword(rs.getString("password"));
				obj.setAge(rs.getInt("age"));
				obj.setGender(rs.getString("gender"));
				
				list.add(obj);
			}

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return list;

	}
	


	public boolean changePassword(String username, String newPassword) {
		Connection con;
		java.sql.Statement st;
		ResultSet rs;
	    try {
	        // Load JDBC driver and establish connection
	    	Class.forName("com.mysql.cj.jdbc.Driver"); // loading the jdbc driver
			con = DriverManager.getConnection(
					"jdbc:mysql://b1u92pbhura4174wgghm-mysql.services.clever-cloud.com:3306/b1u92pbhura4174wgghm?user=u47xuprrwteh0ves&password=e0WAyH8UlUDHOLd6AZIy"); // connection
																																										// string

	        // Prepare update query
	        String updateQuery = "UPDATE Users SET password = ? WHERE username = ?";
	        PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
	        preparedStatement.setString(1, newPassword);
	        preparedStatement.setString(2, username);

	        // Execute the update
	        int rowsAffected = preparedStatement.executeUpdate();

	        // Close resources
	        preparedStatement.close();
	        con.close();

	        return rowsAffected > 0; // Password update was successful if rowsAffected > 0
	    } catch (Exception e) {
			System.out.println(e);
		}
	        return false; // Password update failed
	    }
	

}
