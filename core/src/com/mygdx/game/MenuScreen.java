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
	//Each menuButton object is created. These are actor objects with click input listeners. Each one should set a new screen of some sort.
	MenuButton startButton;
	MenuButton hostButton;
	MenuButton exitButton;
	MenuButton settingsButton;
	MenuButton soundButton;
	MenuButton connectButton;
	MenuButton resolutionButton;
	
	// Each of the texture sprites for the buttons.
	Texture startButtonSkin;
	Texture hostButtonSkin;
	Texture connectButtonSkin;
	Texture settingsButtonSkin;
	Texture soundButtonSkin;
	Texture resolutionButtonSkin;
	Texture exitButtonSkin;

	// The textfield that takes IP and Port as input and textField string is the default text message in the ip field?
	TextField input;
	String textField;
	
	MyGdxGame game;
	Stage menuStage;
	boolean startGame;
	//if hosting, player = 1, team = 1, initiate server and client
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
		//show() is exactly like the create() method for children of the thread class. This initializes all variables, so that it does not have to take place in the Run() method.
		batch = new SpriteBatch();
		menuStage = new Stage(game.viewport, batch);
		// Creating texture sprites out of file names from the assets. Root file address not necessary.
		startButtonSkin = new Texture("START.png");
		hostButtonSkin = new Texture("HOST.png");					//start server to be connected too
		exitButtonSkin = new Texture("EXIT.png");
		settingsButtonSkin = new Texture("SETTINGS.png");			//make settings class that takes you to settings screen
		soundButtonSkin = new Texture("SOUND.png");
		connectButtonSkin = new Texture("CONNECT.png");				//allow for connection to another server
		resolutionButtonSkin = new Texture("RESOLUTION.png");
		
		// Creating each menu button, which is composed of a sprite, x coordinate, y coordinate, and a String designating what type of button it is. Some serious chad brain code here.
		startButton = new MenuButton(startButtonSkin, game.viewport.getScreenWidth()/2 - 100 , 600 , "start");
		hostButton = new MenuButton(hostButtonSkin,game.viewport.getScreenWidth()/2  - 100, 500, "host");
		connectButton = new MenuButton(connectButtonSkin, game.viewport.getScreenWidth()/2  - 100, 400, "connect");
		settingsButton = new MenuButton(settingsButtonSkin, game.viewport.getScreenWidth()/2  - 100, 300, "settings");
		exitButton = new MenuButton(exitButtonSkin, game.viewport.getScreenWidth()/2  - 100, 200,"exit");

		// Adding each button to the stage, so that it can be drawn and also accept user input. (i.e. clicking).
		menuStage.addActor(startButton);
		menuStage.addActor(hostButton);
		menuStage.addActor(connectButton);
		menuStage.addActor(settingsButton);
		menuStage.addActor(exitButton);
	    
		
		startGame = false;
	}

	@Override
	public void render(float delta) {
		// The following draws a black backdrop behind the game world. This keeps the pixels from clipping onto the background and creating infinite headache visuals.
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Must begin a batch before it can draw to the screen. 
		game.batch.begin();
		//Drawing each menu button actor by calling their "skin" (texture or sprite) and its FLOAT x/y coordinates.
		game.batch.draw(startButton.getSkin(), startButton.getX() , startButton.getYC());
		game.batch.draw(hostButton.getSkin(), hostButton.getX(), hostButton.getYC());
		game.batch.draw(connectButton.getSkin(), connectButton.getX(), connectButton.getYC());
		game.batch.draw(settingsButton.getSkin(), settingsButton.getX(), settingsButton.getYC());
		game.batch.draw(exitButton.getSkin(), exitButton.getX() , exitButton.getYC());
		//Must end the batch for other elements to access the screen.
		game.batch.end();
		//ACT allows the menustage to draw and accept input from actors.
		menuStage.act();
		menuStage.draw();
		
		//The following line assigns the user input to the menuStage. No other stages (ui, or otherwise) could be utilized here. For the game, to split input between UI and gameworld, we must use a multiplexer.
		// 																																										TO DO LATER ^^^^^^^^^^^^^^^^^^^^
		Gdx.input.setInputProcessor(menuStage);
		
		//If the user presses startButton, then startGame will be set to true, and it will set the game screen to the character screen. All of the buttons here follow suit. I
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
