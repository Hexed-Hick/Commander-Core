package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

MyGdxGame game;
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
ArrayList<ArrayList <Actor1>> tiles;
ArrayList<Actor1> blank;
ArrayList<character> blankCharacterArray;
character temp;
Viewport viewport;
Camera cam;
MoveToAction moveTo;
BitmapFont font;
character archerPlayer;
character knightPlayer;
ArrayList<character> playerList;
character selectedPlayer;
InputMultiplexer multi;
ArrayList<ArrayList<character>> turnList;
Boolean nextFound;
Boolean turnOver;

public GameScreen(MyGdxGame newGame)
{
	game = newGame;
}
	@Override
	public void show() {
		stage = new Stage(new ScreenViewport(), game.batch);
		GT1 = new Texture("GRASS TILE1.png");
		FMGT = new Texture("FM - GRASS TILE.png");
		archer = new Texture("ARCHER.png");
		outline = new Texture("HIGHLIGHT.png");
		knight = new Texture("KNIGHT.png");
		tiles = new ArrayList<ArrayList<Actor1>>();
		blank = new ArrayList<Actor1>();
		blankCharacterArray = new ArrayList<character>();
		moveTo = new MoveToAction();
		font = new BitmapFont();
		archerPlayer = new character(0, 0, 2, 100, archer, game.batch, "Archer");
		knightPlayer = new character(45, 25, 1, 200, knight, game.batch, "Knight");
		playerList = new ArrayList<character>();
		playerList.add(archerPlayer);
		playerList.add(knightPlayer);
		turnList = new ArrayList<ArrayList<character>>();
		nextFound = false;
		turnOver = false;
		
		
		
		
		//creates characters
		
		//game.cam = new OrthographicCamera();
		//viewport = new StretchViewport(1920, 1080, cam);
		//viewport.apply();
		
		
		
		camX = 960;
		camY = 540;
		//cam.position.set(camX, camY, 0);
		//NESTED LOOPS CREATING RED HIGHLIGHT GRID AND ARRAYLIST FOR HIGHLIGHT ACTORS.
		for(int i = 0; i < 20; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				if(i%2 == 0)
				{
					highlight = new Actor1(outline, x + (i*45), y + (51 * j), i, j, game.batch, selectedPlayer );
					tiles.add(blank);
					tiles.get(i).add(highlight);
					stage.addActor(highlight);
				}
				else
				{
					highlight = new Actor1(outline, x + (i*45), (y + 25) + (51*j), i, j, game.batch, selectedPlayer );
					tiles.add(blank);
					tiles.get(i).add(highlight);
					stage.addActor(highlight);
					
				}
			}
		}
		for(int i = 0; i < playerList.size(); i++)
		{
			stage.addActor(playerList.get(i));
		}
		
		//adds seven levels (array list <character>s) of speed to the turnList.
		for(int i = 0; i < 7; i++)
		{
			turnList.add(blankCharacterArray);
			
		}
		for(int i = 0; i < playerList.size(); i++)
		{
			
			//Adds characters to relative turnList arrays based on speed.
			if (playerList.get(i).getSpeed() == 1)
					{
				turnList.get(0).add(playerList.get(i));
					}
			if (playerList.get(i).getSpeed() == 2)
			{
		turnList.get(1).add(playerList.get(i));
			}
			if (playerList.get(i).getSpeed() == 3)
			{
		turnList.get(2).add(playerList.get(i));
			}
			if (playerList.get(i).getSpeed() == 4)
			{
		turnList.get(3).add(playerList.get(i));
			}
			if (playerList.get(i).getSpeed() == 5)
			{
		turnList.get(4).add(playerList.get(i));
			}
			if (playerList.get(i).getSpeed() == 6)
			{
		turnList.get(5).add(playerList.get(i));
			}
			
		}
		new Thread(this.game).start();
		game.socketServer = new Server(game);
		game.socketServer.start();
		game.socketClient = new Client(this.game, "localHost");
		game.socketClient.start();
		game.socketClient.sendData("ping".getBytes());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();  
		Gdx.input.setInputProcessor(stage);
		game.cam.update();
		//Updating tile actors with current information. 
		for(int i = 0; i < playerList.size(); i++)
		{
			
			//Checks for selected character.
			if(playerList.get(i).getSelected() == true)
			{
				//Designates selected character.
				selectedPlayer = playerList.get(i);
				for(int x = 0; x < tiles.size(); x++)
				{
					for(int w = 0; w < tiles.get(x).size(); w++)
					{
						
						tiles.get(x).get(w).setSelected(selectedPlayer);
						tiles.get(x).get(w).setCamX(x1);
						tiles.get(x).get(w).setCamY(y1);
					}
				}
				
			}
		}
		//IF STATEMENTS FOR CAMERA MOVEMENT USING ARROW KEYS.
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
		    //y = y + 4;
			y1 = y1 - 4;
			for(int i = 0; i < tiles.size(); i++)
			{
				for(int j = 0; j < tiles.get(i).size(); j++)
				{
					
					tiles.get(i).get(j).moveBy(0, .010f);
				}
			}
			
			for(int i = 0; i < playerList.size(); i++)
			{
				playerList.get(i).moveBy(0, 4f);
			}
			
			
			camY = camY - 4;
			game.cam.position.set(camX, camY, 0);
			game.cam.update();
			//batch.setProjectionMatrix(cam.combined);
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			//y = y - 4;
			y1 = y1 + 4;
			for(int i = 0; i < tiles.size(); i++)
			{
				for(int j = 0; j < tiles.get(i).size(); j++)
				{
					
					tiles.get(i).get(j).moveBy(0, -.010f);
				}
			}
			
			for(int i = 0; i < playerList.size(); i++)
			{
				playerList.get(i).moveBy(0, -4f);
			}
			camY = camY + 4;
			game.cam.position.set(camX, camY, 0);
			game.cam.update();
			//batch.setProjectionMatrix(cam.combined);
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT))
		{
			//x = x + 4;
			x1 = x1 - 4;
			for(int i = 0; i < tiles.size(); i++)
			{
				for(int j = 0; j < tiles.get(i).size(); j++)
				{
					
					tiles.get(i).get(j).moveBy(.01f, 0);
				}
			}
			
			for(int i = 0; i < playerList.size(); i++)
			{
				playerList.get(i).moveBy(4f, 0);
			}
			
			camX = camX - 4;
			game.cam.position.set(camX, camY, 0);
			game.cam.update();
			//batch.setProjectionMatrix(cam.combined);
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			//x = x - 4;
			game.socketClient.sendData("ping".getBytes());
			x1 = x1 + 4;
			for(int i = 0; i < tiles.size(); i++)
			{
				for(int j = 0; j < tiles.get(i).size(); j++)
				{
					
					tiles.get(i).get(j).moveBy(-.01f, 0);
				}
			}
			
			for(int i = 0; i < playerList.size(); i++)
			{
				playerList.get(i).moveBy(-4f, 0);
			}
			
			camX = camX + 4;
			game.cam.position.set(camX, camY, 0);
			game.cam.update();
			//batch.setProjectionMatrix(cam.combined);
		}
		
		
		//Updates turnlist based on character's new speed.
		
		for(int i = 0; i <turnList.size(); i++)
		{
			for(int j = 0; j < turnList.get(i).size(); j++)
			{
				if(turnList.get(i).get(j).getSpeed() >= i)
				{
					temp = turnList.get(i).get(j);
					turnList.get(i).remove(j);
					turnList.get(temp.getSpeed() -1).add(temp);
				}
			}
		}
		//If all players have gone this turn, turnOver = true..
		for(int i = 0; i < turnList.size(); i++ )
		{
			turnOver = true;
			for(int j = 0; j < turnList.get(i).size(); j++)
			{
				if(turnList.get(i).get(j).getGoneThisTurn() == false)
				{
					turnOver = false;
					break;
				}
			}
			if(turnOver == false)
			{
				break;
			}
		}
		//if turnOver = true, then all characters set to HasGoneThisTurn = false.
		if(turnOver == true)
		{
			for(int i = 0; i < playerList.size(); i++)
			{
				playerList.get(i).setTurn(false);
			}
		}
		
		//Finds the next character to act in the turnList
		for(int i = turnList.size() - 1; i >= 0; i--)
		{
			nextFound = false;
			for(int j = 0; j < turnList.get(i).size(); j++)
			{
				if(turnList.get(i).get(j).getGoneThisTurn() == false)
				{
					turnList.get(i).get(j).setNext(true);
					nextFound = true;
					break;
				}
				else
				{
					turnList.get(i).get(j).setNext(false);
				}
			}
			if(nextFound == true)
			{
				break;
			}
		}
		
		
		stage.act();
		//System.out.println(cam.combined);
		game.batch.setProjectionMatrix(game.cam.combined);
		game.batch.begin();
		
		//NESTED LOOPS FOR DRAWING MAP TILES.
		for(int i = 0; i < 20; i++)
		{
			// Each row is started with i value
			for(int j = 0; j < 20; j++)
			{
				//each column is filled with j loop
				if(i%2 == 0)
				{
					game.batch.draw(GT1,  x + (i*45), y + (51 * j));
				}
				else
				{
					game.batch.draw(FMGT,x + (i*45), (y + 25) + (51*j));
				}
			}
		}
		//NESTED LOOP FOR CHECKING IF MOUSE OVER TILE. IF TRUE, HIGHLIGHT TILE.
		
		for(int i = 0; i < tiles.size(); i++)
		{
			for(int j = 0; j < tiles.get(i).size(); j++)
			{
				if(tiles.get(i).get(j).getAlpha() == 1)
				{
					game.batch.draw(tiles.get(i).get(j).getSkin(), x + tiles.get(i).get(j).getfX(), y + tiles.get(i).get(j).getfY());
					font.draw(game.batch, tiles.get(i).get(j).getType(), x1 + Gdx.input.getX(), y1 + (Gdx.input.getY() + ((game.viewport.getWorldHeight()/2) - Gdx.input.getY())*2));
				}
			}
		}
		
		//LOOP DRAWING PLAYERS
		for(int i = 0; i < playerList.size(); i++)
		{
			game.batch.draw(playerList.get(i).getSkin(),  playerList.get(i).getX(), playerList.get(i).getY());
			if(playerList.get(i).getMouseOver() == true)
			{
				font.draw(game.batch, playerList.get(i).getType(), x1 + Gdx.input.getX() - 85, y1 + (Gdx.input.getY() + ((game.viewport.getWorldHeight()/2) - Gdx.input.getY())*2));
			}
		}
		
		if(game.isHosting = true)
		{
			System.out.println("Hosting game");
		}
		
		if(game.isConnecting = true)
		{
			System.out.println("Connecting game");
		}
	
		game.batch.end();
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		game.batch.dispose();
		GT1.dispose();
		FMGT.dispose();
		
	}

}
