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


public class Client extends Thread {
	
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Game game;
	
	public Client(Game game, String ipAddress)
	{
		this.game = game;
		try {
		this.socket = new DatagramSocket();
		this.ipAddress = InetAddress.getByName(ipAddress);
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
			System.out.println("SERVER > " + new String(packet.getData()));
		}
	}
	
	public void sendData(byte[] data)
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
	try {
		socket.send(packet);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
}
