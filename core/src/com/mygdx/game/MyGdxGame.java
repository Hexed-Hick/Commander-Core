package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.gameThreads.Interpreter;
import com.mygdx.game.server.Client;
import com.mygdx.game.server.Server;

import characterPack.character;

public class MyGdxGame extends Game implements Runnable {
	public SpriteBatch batch;
	int screen;
	Texture GT1;
	Texture FMGT;
	Texture archer;
	Texture outline;
	Texture knight;
	Stage stage;
	Actor1 highlight;
	int Ay = -530;
	int Ax = -960;
	int y = 0;
	int x = 0;
	int x1 = 0;
	int y1 = 0;
	int camX;
	int camY;
	Random rng = new Random();
	public ArrayList<ArrayList <Actor1>> tiles;
	ArrayList<Actor1> blank;
	ArrayList<character> blankCharacterArray;
	character temp;
	Viewport viewport;
	Camera cam;
	MoveToAction moveTo;
	BitmapFont font;
	character archerPlayer;
	character knightPlayer;
	public ArrayList<character> playerList;
	character selectedPlayer;
	InputMultiplexer multi;
	ArrayList<ArrayList<character>> turnList;
	boolean nextFound;
	boolean turnOver;
	boolean hosting;
	boolean joining;
	public ArrayList<character> team1;
	public ArrayList<character> team2;
	public String currentDirection;
	public boolean newDirection;
	public String currentCharacterID1;
	String currentChaacterID2;
	public int currentX;
	public int currentY;
	public int player;
	
	public Interpreter interpreter;
	
	 Client socketClient;
	 Server socketServer;
	 boolean isHosting;
	 boolean isConnecting;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		cam = new OrthographicCamera();
		viewport = new StretchViewport(1366, 768, cam);
		viewport.apply();
		

		setScreen(new MenuScreen(this));
		
		camX = viewport.getScreenWidth()/2;
		camY = viewport.getScreenHeight()/2;
		cam.position.set(camX, camY, 0);
		player = 2;
	}

	
	
	@Override
	public void dispose () {
		batch.dispose();
		socketClient.stop();
		socketServer.stop();
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
