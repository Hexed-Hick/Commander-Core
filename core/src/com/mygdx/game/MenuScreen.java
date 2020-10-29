package com.mygdx.game;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.badlogic.gdx.Gdx;
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
		menuStage = new Stage(game.viewport, batch);
		startButtonSkin = new Texture("START.png");
		hostButtonSkin = new Texture("HOST.png");					//start server to be connected too
		exitButtonSkin = new Texture("EXIT.png");
		settingsButtonSkin = new Texture("SETTINGS.png");			//make settings class that takes you to settings screen
		soundButtonSkin = new Texture("SOUND.png");
		connectButtonSkin = new Texture("CONNECT.png");				//allow for connection to another server
		resolutionButtonSkin = new Texture("RESOLUTION.png");
		
		startButton = new MenuButton(startButtonSkin, game.viewport.getScreenWidth()/2 - 100 , 600 , "start");
		hostButton = new MenuButton(hostButtonSkin,game.viewport.getScreenWidth()/2  - 100, 500, "host");
		connectButton = new MenuButton(connectButtonSkin, game.viewport.getScreenWidth()/2  - 100, 400, "connect");
		settingsButton = new MenuButton(settingsButtonSkin, game.viewport.getScreenWidth()/2  - 100, 300, "settings");
		exitButton = new MenuButton(exitButtonSkin, game.viewport.getScreenWidth()/2  - 100, 200,"exit");

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
		game.batch.draw(startButton.getSkin(), startButton.getX() , startButton.getYC());
		game.batch.draw(hostButton.getSkin(), hostButton.getX(), hostButton.getYC());
		game.batch.draw(connectButton.getSkin(), connectButton.getX(), connectButton.getYC());
		game.batch.draw(settingsButton.getSkin(), settingsButton.getX(), settingsButton.getYC());
		game.batch.draw(exitButton.getSkin(), exitButton.getX() , exitButton.getYC());
		game.batch.end();
		menuStage.act();
		menuStage.draw();
		
		Gdx.input.setInputProcessor(menuStage);
		
		if(startButton.startGame())
		{
			game.setScreen(new CharacterScreen(game));
		}
		if(exitButton.exitGame())
		{
			System.exit(0);
		}
		if(hostButton.isHosting)
		{
			game.player = 1;
		//	game.setScreen(new GameScreen(game));
			if(game.socketClient == null && game.socketServer == null)
			{
			game.socketServer = new Server(game, 41795);
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
		    game.player = 2;

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
			game.setScreen(new CharacterScreen(game));
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
