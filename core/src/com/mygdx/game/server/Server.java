package com.mygdx.game.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.UnknownHostException;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.mygdx.game.MyGdxGame;


public class Server extends Thread {
	
	private Socket socket;
	private int port;
	private ServerSocket server;
	private DataInputStream in;
	MyGdxGame game;
	private int connectedPlayers = 0;
	private DataOutputStream out;
	int runTime;
	Boolean firstTime = true;
	ServerThread player1;
	ServerThread player2;
	ArrayList<Socket> connections = new ArrayList<Socket>();
	
	public Server(MyGdxGame game, int port)
	{
		this.game = game;
		this.port = port;
		try {
			System.out.println("Server started.");
		server = new ServerSocket(this.port);
		System.out.println("Established on port: " + server.getLocalPort());
		System.out.println("Your server IP is: " + InetAddress.getLocalHost());
		System.out.println("Waiting for client...");
	}catch(IOException e)
		{
		e.printStackTrace();
		}
	}
	
	
	public void run() {
		
		while(true)
		{
		System.out.println("Server running.");
		try {
			socket = server.accept();
			connectedPlayers++;
			System.out.println("Client accepted at: " + socket.getInetAddress());
			if(connectedPlayers == 1)
			{
			player1 = new ServerThread(socket, connectedPlayers);
			player1.start();
			}
			if(connectedPlayers == 2)
			{
				player2 = new ServerThread(socket, connectedPlayers);
				player2.start();
			}
            connections.add(socket);
            if(connectedPlayers == 2)
            {
            	DataOutputStream temp = player1.getOut();
            	player1.changeOutput(player2.getOut());
            	
            	player2.changeOutput(temp);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
         System.out.println("Connected players: " + connectedPlayers);
		}
	}


	/*public void run()
	{
		System.out.println("Server running.");
			try {
				socket = server.accept();
				connectedPlayers++;
				System.out.println("Client accepted at: " + socket.getInetAddress());
				ServerThread st = new ServerThread(socket);
	            connections.add(st);
	            if(connectedPlayers == 1)
	            {
	            	player1 = st;
	            }
	            else if(connectedPlayers == 2)
	            {
	            	player2 = st;
	            }
	            System.out.println("Connected players: " + connectedPlayers);
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		System.out.println("Closing connection.");
	}*/
	/*public void sendData(byte[] data, InetAddress ipAddress, int port)
	{
	try {
		socket.send(packet);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}*/
	
	public int getPort()
	{
		return server.getLocalPort();
	}
	
	public ServerThread getPlayer1Thread()
	{
		return player1;
	}
	
	public ServerThread getPlayer2Thread()
	{
		return player2;
	}
	
	public void sendToAll(String message)
	{
		try {
			player1.getOut().writeUTF(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			player2.getOut().writeUTF(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
