package com.mygdx.game;

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


public class Server extends Thread {
	
	private DatagramSocket socket;
	private Game game;
	
	public Server(MyGdxGame game)
	{
		this.game = game;
		try {
		this.socket = new DatagramSocket(1024);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void run()
	{
		
		while(true)
		{
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String message = new String(packet.getData());
					if(message.trim().equalsIgnoreCase("ping"));
					{
						sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
					}
			System.out.println("CLIENT > " + new String(packet.getData()));
		}
	}
	
	public void sendData(byte[] data, InetAddress ipAddress, int port)
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
	try {
		socket.send(packet);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
}
