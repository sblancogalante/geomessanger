package uy.edu.um.laboratoriotic.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {

	public static void main(String[] args) {

		try {
			BufferedReader br;

			/** For writing output to socket. */
			PrintWriter pw;

			Socket oSocket = new Socket("127.0.0.1", 10999);
			
			br = new BufferedReader(new InputStreamReader(
					oSocket.getInputStream()));

			pw = new PrintWriter(oSocket.getOutputStream(), true);

			pw.write("LUIS");
			pw.flush();			
			
			while (true) {
			
				String sUserWindowToResfresh = br.readLine(); // Esperando por nuevas notificaciones.
				
				//notify windows
				
			}
			
			//oSocket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

