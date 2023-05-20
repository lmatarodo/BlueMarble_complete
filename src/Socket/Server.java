package Socket;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	private static ArrayList<Long> ranking = new ArrayList<Long>();
	
	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket socket = null;
		try {
	         ss = new ServerSocket(5000);
	      } catch (IOException e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }
	      System.out.println("Record server is ready");
		
		
		while(true) {
			try {
				socket = ss.accept();
				System.out.println("new connection");
				
				// input
		         DataInputStream dis = new DataInputStream(socket.getInputStream());
		         ranking.add(dis.readLong());
		         ranking.add(dis.readLong());

		         
		         DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		         String str = new String();
	        	 str = str.concat("Winner: Player ");
	        	 str = str.concat(Long.toString(ranking.get(0)));
	        	 str = str.concat(" MONEY: ");
	        	 str = str.concat(Long.toString(ranking.get(1)));
		         System.out.println(str);
		         ranking.clear();
		         dos.writeUTF(str);
		         
		         dis.close();
		         dos.close();
		         socket.close();
				
	         } catch (IOException e) {
	            // TODO: handle exception
	            e.printStackTrace();
	         }
			
		}
		
	}

}