package driver;

import driver.Operations;

public class Dbtest {
	
	public static void main(String args[]) {

		Operations op = new Operations();

		
		System.out.println(" WELCOME TO DB OF CAR CONFIGURATION ");
		
		op.connectdb();
		System.out.println("reading existing records.");
		op.readingrecords();
		System.out.println("reading inserted records.");
		op.insertRecords();
		op.readingrecords();
		System.out.println("reading after updation of records.");
		op.updaterecords();
		op.readingrecords();
		System.out.println("reading after deletion of records.");
		op.deleterecords();
		op.readingrecords();

		
		
		
		}
}
	    	 
