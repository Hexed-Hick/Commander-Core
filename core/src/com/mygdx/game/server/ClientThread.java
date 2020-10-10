package com.mygdx.game.server;
import java.io.*;
import java.net.*;

public class ClientThread extends Thread {
		protected Socket socket;
		private DataInputStream input;
		private DataOutputStream out;
		Boolean received = true;
		int playerNumber;
	
	public ClientThread(Socket socket, int playerNumber) {
		System.out.println("Child client started.");
		this.socket = socket;
		this.playerNumber = playerNumber;
		try {
			this.input = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run()
	{
		System.out.println("Client running.");
		String message = "";
		Boolean isRunning = true;
		while(isRunning)
		{
			
				try {
					System.out.println("Child reading...");
								message = input.readUTF();
								System.out.println("Client " + playerNumber + " > " +  message);
								received = true;
				}catch (IOException e)
				{
								e.printStackTrace();
				}
			
	}
	}
	
	public void sendData(String message)
	{
	//Upon action in game, sendData will call the client to send a message to the Server.
		
		try {
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(message);
			out.flush();
			received = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
	
	public void receiveData()
	{
		
		
	}

}
