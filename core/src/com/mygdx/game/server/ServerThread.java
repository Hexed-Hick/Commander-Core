package com.mygdx.game.server;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**

 */
public class ServerThread extends Thread{

    protected Socket clientSocket = null;
    DataInputStream in;
    DataOutputStream out;
    Boolean received = false;
    ArrayList<Socket> connections = new ArrayList<Socket>();
    
    public ServerThread(Socket clientSocket, int playerNumber) {
        this.clientSocket = clientSocket;
        try {
        	in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
			out = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
        }catch(IOException e)
        {
        	e.printStackTrace();
        }
    }
    
    public void run() {
      
    
    Boolean running = true;
	while(running)
	{
		System.out.println("SERVER > Thread is running.");
		String line = "";
		
		try {
		
			line = in.readUTF();
			//System.out.println(line);
				out.writeUTF(line);
				out.flush();
				System.out.println("SERVER > Server has replied");
				
			}catch(IOException e) {
				e.printStackTrace();
			}
    }
    }
    
    public void sendData(String message)
    {
    	try {
			out.writeUTF(message);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public DataInputStream getIn()
    {
    	return in;
    }
    public DataOutputStream getOut()
    {
    	return out;
    }
    
    public void addSocketList(ArrayList<Socket> players)
    {
    	connections = players;
    }
    public void changeOutput(DataOutputStream newStream)
    {
    	out = newStream;
    }
}