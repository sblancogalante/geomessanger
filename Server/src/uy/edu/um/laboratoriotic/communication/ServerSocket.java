package uy.edu.um.laboratoriotic.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class ServerSocket {

	private Map<String, Socket> colMap = new HashMap<String, Socket>();
	
	public void notifyClients(String sToUser, String sFromUser) {
		
		try {
			
			Socket oSocket = colMap.get(sToUser);
			BufferedReader br;

		    /** For writing output to socket. */
		    PrintWriter pw;
		    
	        br = new BufferedReader(
	                new InputStreamReader(
	                		oSocket.getInputStream()
	                                     )
	                              );
	       
	       pw = new PrintWriter(oSocket.getOutputStream(), true);
	       
	       pw.write(sFromUser);
	       pw.flush();
	       
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	public void aceptarConexiones() {
		 
			BufferedReader br;

		    /** For writing output to socket. */
		    PrintWriter pw;
			try {
				
				java.net.ServerSocket oServerSocket = new java.net.ServerSocket(7899);
				
				while (true) {
					
					Socket oSocket = oServerSocket.accept();
					
		            br = new BufferedReader(
		                    new InputStreamReader(
		                    		oSocket.getInputStream()
		                                         )
		                                  );
		           
		           pw = new PrintWriter(oSocket.getOutputStream(), true);
		           
		           String sUser = br.readLine();
		           
		           colMap.put(sUser, oSocket);
					
				}
				

	           //sSystem.out.println(sHola);
				
//	           oSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	public static void main(String[] args) {
	   
		
		
		
	}
	
}
