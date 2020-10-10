package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

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


	
	MyGdxGame game;
	Stage menuStage;
	boolean startGame;
	boolean isHosting;
	boolean isConnecting;
	
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
		if(hostButton.startGame())
		{
			game.isHosting = true;
			game.setScreen(new GameScreen(game));
			
			TextFieldStyle style = new TextFieldStyle();
		    style.fontColor = Color.RED;
		    style.font = new BitmapFont();
		    
		    TextField textfield = new TextField("Hosting in progress", style);
		    textfield.setPosition(300, 300);
		    menuStage.addActor(textfield);

		    textfield.getMessageText();

		}
		if(connectButton.startGame())
		{
			game.isConnecting = true;
			game.setScreen(new GameScreen(game));
			
			TextFieldStyle style = new TextFieldStyle();
		    style.fontColor = Color.RED;
		    style.font = new BitmapFont();

			
		    TextField textfield = new TextField("Joinable IP:", style);
		    textfield.setPosition(300, 300);
		    menuStage.addActor(textfield);
		    
		    textfield.getMessageText();

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
