package com.mygdx.game;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.server.Client;
import com.mygdx.game.server.Server;

public class MenuScreen extends ScreenAdapter {

	SpriteBatch batch;
	Sprite sprite;
	
	MenuButton startButton;
	MenuButton hostButton;
	MenuButton exitButton;
	MenuButton settingsButton;
	MenuButton soundButton;
	MenuButton connectButton;
	MenuButton resolutionButton;
	
	Texture startButtonSkin;
	Texture hostButtonSkin;
	Texture connectButtonSkin;
	Texture settingsButtonSkin;
	Texture soundButtonSkin;
	Texture resolutionButtonSkin;
	Texture exitButtonSkin;

	TextField input;
	String textField;
	
	MyGdxGame game;
	Stage menuStage;
	boolean startGame;
	boolean isHosting;
	boolean isConnecting;
	TextField userInputIP;
	TextField userInputPort;
	String ip;
	String port;
	int portNum;
	
	public MenuScreen(MyGdxGame game)
	{
			this.game = game;
	}
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		menuStage = new Stage(new ScreenViewport(), batch);
		startButtonSkin = new Texture("START.png");
		hostButtonSkin = new Texture("HOST.png");					//start server to be connected too
		exitButtonSkin = new Texture("EXIT.png");
		settingsButtonSkin = new Texture("SETTINGS.png");			//make settings class that takes you to settings screen
		soundButtonSkin = new Texture("SOUND.png");
		connectButtonSkin = new Texture("CONNECT.png");				//allow for connection to another server
		resolutionButtonSkin = new Texture("RESOLUTION.png");
		
		startButton = new MenuButton(startButtonSkin, 860, 800, "start");
		hostButton = new MenuButton(hostButtonSkin, 860, 700, "host");
		connectButton = new MenuButton(connectButtonSkin,860,600,"connect");
		settingsButton = new MenuButton(settingsButtonSkin,860,500,"settings");
		exitButton = new MenuButton(exitButtonSkin,860,400,"exit");

		menuStage.addActor(startButton);
		menuStage.addActor(hostButton);
		menuStage.addActor(connectButton);
		menuStage.addActor(settingsButton);
		menuStage.addActor(exitButton);
	    

		startGame = false;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(startButton.getSkin(), startButton.getXC(), startButton.getYC());
		game.batch.draw(hostButton.getSkin(), hostButton.getXC(), hostButton.getYC());
		game.batch.draw(connectButton.getSkin(), connectButton.getXC(), connectButton.getYC());
		game.batch.draw(settingsButton.getSkin(), settingsButton.getXC(), settingsButton.getYC());
		game.batch.draw(exitButton.getSkin(), exitButton.getXC(), exitButton.getYC());
		game.batch.end();
		menuStage.act();
		menuStage.draw();
		
		Gdx.input.setInputProcessor(menuStage);
		
		if(startButton.startGame())
		{
			game.setScreen(new GameScreen(game));
		}
		if(exitButton.exitGame())
		{
			System.exit(0);
		}
		if(hostButton.isHosting)
		{
		//	game.setScreen(new GameScreen(game));
			if(game.socketClient == null && game.socketServer == null)
			{
			game.socketServer = new Server(game, 0);
			game.socketClient = new Client(this.game, "localhost", game.socketServer.getPort(), 1);
			game.socketServer.start();
			game.socketClient.start();
			}
			
			TextFieldStyle style = new TextFieldStyle();
		    style.fontColor = Color.RED;
		    style.font = new BitmapFont();
		    if(input == null)
		    {
			try {
				textField = "IP Address: " + InetAddress.getLocalHost() + " Port: " + game.socketServer.getPort();
				input = new TextField(textField, style);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }
			if(input != null)
			{
		    input.setPosition(300, 300);
		    menuStage.addActor(input);

		    input.getText();
			}

		}
		if(connectButton.isConnecting)
		{
			//game.isConnecting = true;
			//game.setScreen(new GameScreen(game));
			
			TextFieldStyle style = new TextFieldStyle();
		    style.fontColor = Color.RED;
		    style.font = new BitmapFont();

			if(userInputIP == null && userInputPort == null)
			{
		    userInputIP = new TextField("IP Address:", style);
		    userInputPort = new TextField("Port:", style);
		    userInputIP.setPosition(300, 300);
		    userInputPort.setPosition(300, 280);
		    menuStage.addActor(userInputIP);
		    menuStage.addActor(userInputPort);
			}
		    
		   // ip = userInputIP.getText();
		   // portNum = Integer.parseInt(userInputPort.getText());
		    
		    if(connectButton.connect)
		    {
		    	  ip = userInputIP.getText();
				    portNum = Integer.parseInt(userInputPort.getText());
			game.socketClient = new Client(this.game, ip, portNum, 2);
			game.socketClient.start();
			game.setScreen(new GameScreen(game));
		    }
		    

		}
		if(settingsButton.settings())
		{
			game.setScreen(new SettingsScreen(game));
		}
		
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public void dispose() {
		
	}

}
