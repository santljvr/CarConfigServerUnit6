package dbconnector;
import model.Automobile;
import dbconnector.Dbsetup;

public class Operation {
	
	Automobile auto = new Automobile();
	Dbsetup db1 = new Dbsetup();
		
	public void createRecords(Automobile auto1)
	
	{
		auto = auto1;
    	System.out.println("now we are creating a new entry in existing table.. ");
    	System.out.println("make: "+ auto.getAutoname());
    	System.out.println("price:  "+ auto.getBaseprice());
    	System.out.println("model: " + auto.getModel());
    	
    	String make = auto.getAutoname();
    	String model = auto.getModel();
    	Float price = auto.getBaseprice();
  
    	/* with below queries we are creating new reocrds which is coming from proxyautomoible class in automboile table*/
    	String insertquery= "INSERT INTO automobile() VALUES (NULL,'"+make+"', '"+model+"', '"+price+"')";
    	/*below insert will depend upon the automobile reocords available in the automobile table*/
    	String insertquery2="INSERT INTO optionset() VALUES (NULL,'colors', (select auto_id from automobile where automobile.auto_id=2))";
    	/*below insert will depend upon the optionset reocords available in the optionset table*/
    	String insertquery3="INSERT INTO options() VALUES (NULL,'blue', '200',(select opset_id from optionset where optionset.opset_id=3))";
    	/*other optionset related to auto1*/
    	String insertquery4="INSERT INTO optionset() VALUES (NULL,'breaks', (select auto_id from automobile where automobile.auto_id=1))";
    	String insertquery5="INSERT INTO optionset() VALUES (NULL,'roofs', (select auto_id from automobile where automobile.auto_id=1))";
    	/*options related to breaks and roofs*/
    	String insertquery6="INSERT INTO options() VALUES (NULL,'abs', '50' (select opset_id from optionset where optionset.opset_id=4))";
    	String insertquery7="INSERT INTO options() VALUES (NULL,'moon', '30' (select opset_id from optionset where optionset.opset_id=5))";
    	
    	
    	
    try{ 
    	db1.startDb();
    	db1.viewRecords("before creating new records");
    	db1.insertQuery(insertquery);
    	db1.viewRecords(": after creating  new Automobile Object into DB: ");
    	db1.insertQuery(insertquery2);
    	db1.viewRecords(": after creating: new OptionSet ");
    	db1.insertQuery(insertquery3);
    	db1.viewRecords(": after creating: new option ");
    	db1.insertQuery(insertquery4);
    	db1.viewRecords(": after creating: ");
    	db1.insertQuery(insertquery5);
    	db1.viewRecords(": after creating: ");
    	db1.insertQuery(insertquery6);
    	db1.viewRecords(": after creating: ");
    	db1.insertQuery(insertquery7);
    	db1.viewRecords(": after creating: ");
    	db1.closedb();
    	} catch(Exception e){
    		System.err.println("not able to insert query");
    	}
	}
	
	public void updateRecords(Automobile auto1){
		
		auto = auto1;
    	System.out.println("Objects received by client.... ");
    	System.out.println("make: "+ auto.getAutoname());
    	System.out.println("price:  "+ auto.getBaseprice());
    	System.out.println("model: " + auto.getModel());
    	
    	String make = auto.getAutoname();
    	String model = auto.getModel();
    	Float price = auto.getBaseprice();
  
    	/* with below queries we are updating Tyotoa Make to HYUNDAI Make, Toyota which is coming from proxyautomoible class in automboile table*/
    	String updatequery= "UPDATE automobile SET automobile.make=xx where (automobile.make= '"+make+"')";
    	/* this will update the colors to COLORS*/
    	String updatequery1= "UPDATE optionset os, automobile a SET os.opset_name='lsgtrd' where (os.auto_id = a.auto_id AND os.opset_id=3)";

    	try{ 
        	db1.startDb();
			db1.viewRecords("before Updation! ");
        	db1.updateQuery(updatequery);
        	db1.viewRecords("after Updation! ");
        	db1.updateQuery(updatequery1);        	
        	db1.closedb();
       
    	}
    	catch(Exception e){
    		System.err.println("unable to update records!!!");
    	}
	}
    	public void deleteRecords(Automobile auto1){
    		
    		auto = auto1;
        	System.out.println("auto ojbects received by client.. ");
        	System.out.println("make: "+ auto.getAutoname());
        	System.out.println("price:  "+ auto.getBaseprice());
        	System.out.println("model: " + auto.getModel());
        	
        	String make = auto.getAutoname();
        	String model = auto.getModel();
        	Float price = auto.getBaseprice();
      
        	/* with below queries we are updating Tyotoa Make to HYUNDAI Make, Toyota which is coming from proxyautomoible class in automboile table*/
        	String deletequery= "DELETE FROM automobile where (automobile.make='A')";
        	
        	/* this will update the colors to COLORS*/
        	String deletequery2= "DELETE FROM optionset automobile where (optionset.auto_id = '1')";

        	try{ 
            	db1.startDb();
    			db1.viewRecords("before Deletion! ");
            	db1.deleteQuery(deletequery);
            	db1.viewRecords("after Deletion! ");
            	db1.deleteQuery(deletequery2);
            	db1.viewRecords("after Deletion 2! ");

            	db1.closedb();
           
    	}
        	catch(Exception e){
        		System.err.println("unable to update records!!!");
        	}

	}
    
}
