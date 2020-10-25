package com.mygdx.game.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.UnknownHostException;

import com.badlogic.gdx.Game;
import com.mygdx.game.MyGdxGame;


public class Client extends Thread {
	
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream out;
	private MyGdxGame game;
	private Boolean isConnected = false;
	private ClientThread child;
	Boolean received = true;
	
	public Client(MyGdxGame game, String ipAddress, int port, int playerNumber)
	{

		
		this.game = game;
		try {
			//Establishes socket to SERVER  with IP and PORT provided in argument.
		this.socket =new Socket(ipAddress, port);
		//Establishes INPUT and OUTPUT reading from/to socket streams.
		input = new DataInputStream(socket.getInputStream());
		this.child = new ClientThread(this.game, this.socket, playerNumber);
		child.start();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
	/*	Boolean isRunning =true;
		while(isRunning)
		{
			String message = "";
			try {
				if(!received) {
				message = input.readUTF();
				System.out.println(message);
				received = true;
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			}*/
		}
	
	public void sendData(String message)
	{
	//Upon action in game, sendData will call the client to send a message to the Server.
			child.sendData(message);
			
	}
	
	public void receiveData()
	{

		String message = "";
		try {
			input = new DataInputStream(socket.getInputStream());
			message = input.readUTF();
			System.out.println(message);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Boolean isConnected()
	{
		return isConnected;
	}
	
}
