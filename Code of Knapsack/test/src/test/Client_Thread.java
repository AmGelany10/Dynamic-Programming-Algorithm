package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client_Thread extends Thread{
	Socket connectionSocket;
	int ID;
	String clientSentence;
	String capitalizedSentence;
	DataOutputStream outToClient;
	BufferedReader inFromClient;

	//We have to implement a constructor here
	public Client_Thread(Socket ClientSocket,int ID)
	{
		connectionSocket = ClientSocket;
		ID = this.ID;
	}

	public void run(){
		try {
			inFromClient = new BufferedReader(new
					InputStreamReader(connectionSocket.getInputStream()));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		while(true){
			try {
				clientSentence = inFromClient.readLine();
					System.out.println("client sentance: "+ clientSentence);
				if(clientSentence.equalsIgnoreCase("Close Socket"))
				{
					try {
						connectionSocket.close();
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block

					}

				}

				capitalizedSentence = clientSentence.toUpperCase() + '\n';

			} catch (IOException e1) {
				// TODO Auto-generated catch block

			} 
			try {
				outToClient.writeBytes(capitalizedSentence);
			} catch (IOException e) {
				// TODO Auto-generated catch block

			} 
		}
		System.out.println("Connection Socket is closed @Server");
	}
}
