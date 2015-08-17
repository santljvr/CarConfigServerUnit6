package dbconnector;
import java.sql.*;
import java.util.Vector;
		public class Dbsetup {
			
		    Connection          connection;
		    PreparedStatement   statement, statement2, statement3;
		    ResultSet           resultSet, resultSet2, resultSet3;
			
		  public void startDb() {
			 	
		    try {
		// Load the MySQL JDBC driver
		      System.out.println(".. welcome to database utility inside server..");
		      Class.forName("com.mysql.jdbc.Driver") ;
		      System.out.println("MySQL JDBC driver loaded ok.");
		      
		      connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/Automobile?"
		    	        + "user=santosh&password=santosh123");
		      System.out.println("Connected with host:port/database");
		    } catch (Exception e) {
		      System.err.println("Exception: "+e.getMessage());
		    }
		     
		  }
		  
		   public void viewRecords(String operation){
			   
			   try{
					  System.out.println(operation +" records in Automobile db : ..\n");
					  statement = connection.prepareStatement("select * from automobile");
					  statement2 = connection.prepareStatement("select * from optionset");
					  statement3 = connection.prepareStatement("select * from options");
				       resultSet = statement.executeQuery();
				       resultSet2 = statement2.executeQuery();
				       resultSet3 = statement3.executeQuery();
				      
				/*printing  new values of automobile*/
				    while(resultSet.next())
				 
					System.out.println("autombile table: "+resultSet.getString(1)+" "+resultSet.getString(2)
					+ " "+resultSet.getString(3)+" "+resultSet.getString(4)+"\n");      
					     
				   
				  /*printing  new values of optionset*/
			       
			         while(resultSet2.next())
			 
				      System.out.println("optionset table: "+resultSet2.getString(1)+" "+resultSet2.getString(2)
				      + " "+resultSet2.getString(3)+"\n");
				  
				     /*printing new values of options*/
				       
			         while(resultSet3.next())
			 
				      System.out.println("option table: "+resultSet3.getString(1)+" "+resultSet3.getString(2)
				      + " "+resultSet3.getString(3)+" "+resultSet3.getString(4)+"\n");
			
				  }				      
				     catch (Exception e) {
				      System.err.println("Exception: "+e.getMessage());
				    } 
		   }
		  
		  public void insertQuery(String query){
		    	
			  try {
				  	statement=connection.prepareStatement(query);
				  	statement.executeUpdate(); 	
				}
				catch (SQLException ex) {
					System.err.println(ex);
				} 
			 		  
		  }
		  
		  public void updateQuery(String query){
			  
			  try {
				  	statement=connection.prepareStatement(query);
				  	statement.executeUpdate(); 
				  	
				  	
				}
				catch (SQLException ex) {
					System.err.println(ex);
					
				}
			  
		  }
		  
		  public void deleteQuery(String query){
			  try{
				  statement=connection.prepareStatement(query);
				  statement.executeUpdate(); 	
			  }
			  catch(SQLException e){
					System.err.println(e);
			  }
		  }
		 
		  
		  public void closedb() throws SQLException{
			  System.out.println("now closing db");
			  resultSet.close();
			  statement.close();
			  connection.close();
		  }
	    	  
	}


