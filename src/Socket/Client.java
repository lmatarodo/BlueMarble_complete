package Socket;

import java.io.*;
import java.net.*;
import java.util.*;

class Test extends Thread{
	private long ID;
	private long record;
	private String str;
	
	public Test(long ID,long record) {
		this.ID = ID;
		this.record = record;
		str = new String();
		
	}
	
	public String returnRecord() {
		return this.str;
	}
	
   public void run() {
      try {
    	  Socket soc = new Socket("localhost", 5000);
    	  DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
    	  dos.writeLong(ID+1);
    	  dos.writeLong(record);
    	  
    	  DataInputStream dis = new DataInputStream(soc.getInputStream());
    	  this.str = dis.readUTF();
//    	  System.out.println("readUTF : " + str);
    	  

    	  dos.close();
    	  dis.close();
    	  soc.close();

         
      } catch (IOException e) {
         // TODO: handle exception
         e.printStackTrace();
      }
   }
}



public class Client {
	private long ID;
	private long record;
	private String str;
	
	public Client(long id,long record) {
		this.ID = ID;
		this.record = record;
		str = new String();
	}
	
	public String returnRecord() {
		return this.str;
	}

   public void start() {
	   Test test = new Test(ID,record);
	   test.start();
	   
	   while(true) {
		   if(test.returnRecord().length() != 0) {
			   break;
		   }
		   System.out.print("");
	   }
	   
	   
	   
	   this.str = test.returnRecord();
//	   System.out.println("client : " + str);
	   
   }
}