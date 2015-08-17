package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Operations {
	
	
	Connection          connection;
    PreparedStatement   statement, statement2, statement3;
    ResultSet           resultSet, resultSet2, resultSet3;
	
	Operations(){
		super();	
	}
 
    
    protected void connectdb(){
    try {
    	// Load the MySQL JDBC driver
      System.out.println(".. welcome to database utility inside server..");
      Class.forName("com.mysql.jdbc.Driver") ;
      System.out.println("MySQL JDBC driver loaded ok.");
      
      connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/Automobile?"
    	        + "user=santosh&password=santosh123");
      System.out.println("Connected with host:port/database");
    
    }
    catch (Exception e) {
        System.err.println("Exception: "+e.getMessage());
      }
	}
    
    protected void readingrecords(){
    
    /* reading records*/
    
    	
	  System.out.println(" reading records in Automobile db : ..\n");
	  
	  try{
	  
	  statement = connection.prepareStatement("select * from automobile");
	  statement2 = connection.prepareStatement("select * from optionset");
	  statement3 = connection.prepareStatement("select * from options");
       resultSet = statement.executeQuery();
       resultSet2 = statement2.executeQuery();
       resultSet3 = statement3.executeQuery();
       /*printing   values of automobile, optionset and option tables on console*/
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

    } catch(SQLException e){
    	System.err.println(e);
    	System.out.println("unable to read records..");
    	}
    }
 
    
    /*insert queries*/ 

    protected void insertRecords(){
           
       /*inserting into automobile table*/
       
       String insertquery= "INSERT INTO automobile() VALUES (NULL,'GM', 'COBALT', '1600')";
       
       /*inserting into optionset table*/
       
    	String insertquery2="INSERT INTO optionset() VALUES (NULL,'roofs', (select auto_id from automobile where automobile.auto_id=2))";

        
    	String insertquery3="INSERT INTO options() VALUES (NULL,'sunroof', '200',(select opset_id from optionset where optionset.opset_id=3))";
    	
    	/* inserting records*/
    	
        try {
    	
    	statement=connection.prepareStatement(insertquery);
    	statement.executeUpdate(); 
	    statement=connection.prepareStatement(insertquery2);
	    statement.executeUpdate(); 	
		statement=connection.prepareStatement(insertquery3);
		statement.executeUpdate(); 	
		
        }
		catch (SQLException ex) {
			System.err.println(ex);
			System.out.println("unable to insert...");
		} 
    }
    
    
    protected void updaterecords(){
 
        //now updating options records
        
    	String updatequery= "UPDATE options o, optionset os, automobile a SET o.option_name='red' where (o.option_name='white' AND o.opset_id=os.opset_id AND os.auto_id=a.auto_id)";
        try{      
        statement=connection.prepareStatement(updatequery);
		  	statement.executeUpdate(); 
		 
        }
		  	
		catch (SQLException ex) {
			System.err.println(ex);
			System.out.println("unable to update....");	
		}
    }
    
    
    protected void deleterecords() {
        
        //now deleting records from options
        
    	String deletequery= "DELETE FROM automobile where automobile.model='COBALT'";
    	try{
		  statement=connection.prepareStatement(deletequery);
		  statement.executeUpdate(); 
		 
    	}
	  catch(SQLException e){
			System.err.println(e);
			System.out.println("error while deleting the records..");
	  }
    }
    
    
    protected void closedb(){
    	/* now  and closing*/
   try{ 	
    System.out.println("now closing and commiting db");
    connection.commit();
	  resultSet.close();
	  statement.close();
	  connection.close();
    }
   catch(SQLException e){
	   
	   System.err.println(e);
	   System.out.println("error during closing the connection..");
	   
	   }
   }
}
