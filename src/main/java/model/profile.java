package model;

import java.sql.*;

public class profile {
	//a common method to connect to the DB
	
	private Connection connect()
	 {
		Connection con = null;
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");
			 
			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "root");
		 }
			 catch (Exception e)
			 {e.printStackTrace();}
			 return con;
	}
	
	//insert method
	public String insertProfile( String name, String address, String email, String contact)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 
			 // create a prepared statement
			 
			 String query = " insert into profilemanagement (`uid`,`name`,`address`,`email`,`contact`)" + " values (?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, address);
			 preparedStmt.setString(4, email);
			 preparedStmt.setString(5, contact);
			 
			 //execute the statement
			 
			 preparedStmt.execute();
			 con.close();
			 output = "Inserted succesfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while inserting the new data";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}
	//view method
			public String readProfile()
			{
					String output = "";
					 try
					 {
						 Connection con = connect();
						 if (con == null)
						 {return "Error while connecting to the database for reading."; }
						 
						 // Prepare the html table to be displayed
						 
						 output = "<table border='1'><tr><th>UID</th>" +"<th>NAME</th>" +"<th>ADDRESS</th>" +
								 "<th>EMAIL</th>" +"<th>CONTACT NO </th>" +"<th>Update</th>"+"<th>Remove</th></tr>";
					
						 String query = "select * from profileManagement";
						 Statement stmt = con.createStatement();
						 ResultSet rs = stmt.executeQuery(query);
						 
						 // iterate through the rows in the result set
						 
						 while (rs.next())
						 {
							 String uid = Integer.toString(rs.getInt("uid"));
							 String name = rs.getString("name");
							 String address = rs.getString("address");
							 String email = rs.getString("email");
							 String contact = rs.getString("contact");
							
							 // Add into the html table
							 
							 output += "<td>" + uid + "</td>";
							 output += "<td>" + name + "</td>";
							 output += "<td>" + address + "</td>";
							 output += "<td>" + email + "</td>";
							 output += "<td>" + contact + "</td>";
							
							 
							 // buttons
							 
							
						 output += "<td><form method='post' action='updateProfile.jsp'>"
							 		+ "<input name='btnUpdate' type='submit' value='Update'class='btn btn-secondary'>"
							 		+ "<input name='uid' type='hidden' value='" + uid + "'>" + "</form></td>"
									 + "<td><form method='post' action='viewProfile.jsp'>"
									 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
									 + "<input name='uid' type='hidden' value='" + uid + "'>" + "</form></td></tr>";
						 }
						 con.close();
						 
						 // Complete the html table
						 
						 output += "</table>";
					 }
					 catch (Exception e)
					 {
						 output = "Error while Viewing the profile.";
						 System.err.println(e.getMessage());
					 }
					 return output;
			}
			
			//update
			public String updateProfile(String uid,String name, String address, String email, String contact)
			{
					 String output = "";
					 try
					 {
						 Connection con = connect();
						 if (con == null)
						 {return "Error while connecting to the database for updating."; }
						 
						 // create a prepared statement
						 
						 String query = "UPDATE profileManagement SET name=?,address=?,email=?,contact=? WHERE uid=?";
						 PreparedStatement preparedStmt = con.prepareStatement(query);
						 
						 // binding values
						 
						 preparedStmt.setString(1, name);
						 preparedStmt.setString(2, address);
						 preparedStmt.setString(3, email);
						 preparedStmt.setString(4, contact);
						 preparedStmt.setInt(5, Integer.parseInt(uid));
					
						 
						 
						 // execute the statement
						 
						 preparedStmt.execute();
						 con.close();
						 output = "Updated successfully";
					 }
					 catch (Exception e)
					 {
						 output = "Error while updating the profile.";
						 System.err.println(e.getMessage());
					 }
					 return output;
			}
		
			//delete method

			public String deleteProfile(String uid)
			{
					 String output = "";
					 try
					 {
						 Connection con = connect();
						 if (con == null)
						 {return "Error while connecting to the database for deleting."; }
						 // create a prepared statement
						 String query = "delete from profileManagement where uid=?";
						 PreparedStatement preparedStmt = con.prepareStatement(query);
						 // binding values
						 preparedStmt.setInt(1, Integer.parseInt(uid));
						 // execute the statement
						 preparedStmt.execute();
						 con.close();
						 output = "Deleted successfully";
					 }
					 catch (Exception e)
					 {
						 output = "Error while deleting the profile.";
						 System.err.println(e.getMessage());
					 }
					 return output;
			}
	
	
			 
	
}
		 		
