package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import characterPack.Archer;
import characterPack.Knight;
import characterPack.Musketeer;
import characterPack.Priest;
import characterPack.character;

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
Archer archerPlayer;
Knight knightPlayer;
Priest priestPlayer;
Musketeer gunPlayer;
ArrayList<character> playerList;
character selectedPlayer;
InputMultiplexer multi;
ArrayList<ArrayList<character>> turnList;
Boolean nextFound;
Boolean turnOver;
int mapHeight;
int mapWidth;
String currentCharacterID;
int currentX;
int currentY;
MoveToAction move;

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
		game.tiles = new ArrayList<ArrayList<Actor1>>();
		blank = new ArrayList<Actor1>();
		blankCharacterArray = new ArrayList<character>();
		moveTo = new MoveToAction();
		font = new BitmapFont();
		//archerPlayer = new Archer(game);
		//knightPlayer = new Knight(game);
		//priestPlayer = new Priest(game);
		//gunPlayer = new Musketeer(game);
		game.playerList = new ArrayList<character>();
		/*game.playerList.add(archerPlayer);
		game.playerList.add(knightPlayer);
		game.playerList.add(priestPlayer);
		game.playerList.add(gunPlayer);*/
		game.turnList = new ArrayList<ArrayList<character>>();
		nextFound = false;
		turnOver = false;
		mapHeight = 20;
		mapWidth = 20;
		move = new MoveToAction();
		//Adds team1 players to playerList for turn management.
		for(int i = 0; i < game.team1.size(); i++)
		{
			game.playerList.add(game.team1.get(i));
		}
		//adds team2 players to playerList for turn management.
		for(int i = 0; i < game.team2.size(); i++)
		{
			game.playerList.add(game.team2.get(i));
		}
		
		
		//game.cam = new OrthographicCamera();
		//viewport = new StretchViewport(1920, 1080, cam);
		//viewport.apply();
		camX = game.viewport.getScreenWidth()/2;
		camY = game.viewport.getScreenHeight()/2;
		//cam.position.set(camX, camY, 0);
		//NESTED LOOPS CREATING RED HIGHLIGHT GRID AND ARRAYLIST FOR HIGHLIGHT ACTORS.
		for(int i = 0; i < 20; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				if(i%2 == 0)
				{
					highlight = new Actor1(game, outline, x + (i*45), y + (51 * j), i, j, game.batch, game.selectedPlayer );
					game.tiles.add(blank);
					game.tiles.get(i).add(highlight);
					stage.addActor(highlight);
				}
				else
				{
					highlight = new Actor1(game, outline, x + (i*45), (y + 25) + (51*j), i, j, game.batch, game.selectedPlayer );
					game.tiles.add(blank);
					game.tiles.get(i).add(highlight);
					stage.addActor(highlight);
				}
			}
		}
		for(int i = 0; i < game.playerList.size(); i++)
		{
			stage.addActor(game.playerList.get(i));
		}
		
		//adds seven levels (array list <character>s) of speed to the turnList.
		for(int i = 0; i < 7; i++)
		{
			game.turnList.add(blankCharacterArray);
			
		}
		for(int i = 0; i < game.playerList.size(); i++)
		{
			
			//Adds characters to relative turnList arrays based on speed.
			if (game.playerList.get(i).getSpeed() == 1)
					{
				game.turnList.get(0).add(game.playerList.get(i));
					}
			if (game.playerList.get(i).getSpeed() == 2)
			{
		game.turnList.get(1).add(game.playerList.get(i));
			}
			if (game.playerList.get(i).getSpeed() == 3)
			{
		game.turnList.get(2).add(game.playerList.get(i));
			}
			if (game.playerList.get(i).getSpeed() == 4)
			{
		game.turnList.get(3).add(game.playerList.get(i));
			}
			if (game.playerList.get(i).getSpeed() == 5)
			{
		game.turnList.get(4).add(game.playerList.get(i));
			}
			if (game.playerList.get(i).getSpeed() == 6)
			{
		game.turnList.get(5).add(game.playerList.get(i));
			}
			
		}
		
	
		
		//Setting spawn points
		
		for(int i = 0; i < game.team1.size(); i++)
		{
			if(i%2 == 0)
			{
			game.team1.get(i).setBounds(i*45, 25, game.team1.get(i).getSkin().getWidth(), game.team1.get(i).getSkin().getHeight());
			game.team1.get(i).setxC(i);
			game.team1.get(i).setyC(0);
			game.team1.get(i).setfxC(i * 45);
			game.team1.get(i).setfyC(0);
			}
			else
			{
				game.team1.get(i).setBounds(i*45, 25, game.team1.get(i).getSkin().getWidth(), game.team1.get(i).getSkin().getHeight());
				game.team1.get(i).setxC(i);
				game.team1.get(i).setyC(0);
				game.team1.get(i).setfxC(i * 45);
				game.team1.get(i).setfyC(25);
			}
		}
		for(int i = 0; i < game.team2.size(); i++)
		{
			System.out.println("Creating team 2 spawns.");
			if(i%2 == 0)
			{//x + (i*45), y + (51 * j)
			game.team2.get(i).setBounds((19 - i) *45, (19*51) + 25, game.team2.get(i).getSkin().getWidth(), game.team2.get(i).getSkin().getHeight());
			game.team2.get(i).setxC(20 - i);
			game.team2.get(i).setyC(20);
			game.team2.get(i).setfxC((19 - i) *45);
			game.team2.get(i).setfyC((19 * 51) + 25);
			}
			else
			{
				game.team2.get(i).setBounds((19-i) * 45, (19*51), game.team2.get(i).getSkin().getWidth(), game.team2.get(i).getSkin().getHeight());
				game.team2.get(i).setxC(19 - i);
				game.team2.get(i).setyC(19);
				game.team2.get(i).setfxC((19 - i) * 45);
				game.team2.get(i).setfyC((19 * 51));
			}
		}
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();  
		Gdx.input.setInputProcessor(stage);
		game.cam.update();
		//game.socketClient.receiveData();
		
		//Updating tile actors with current information. 
		for(int i = 0; i < game.playerList.size(); i++)
		{
			
			//Unselects player that has just moved or has been unselected.
			if(game.selectedPlayer != null)
			{
			if(game.selectedPlayer.getSelected() == false)
			{
				game.selectedPlayer = null;
			}
			}
			//Checks for selected character.
			if(game.playerList.get(i).getSelected() == true)
			{
				//Designates selected character.
				game.selectedPlayer = game.playerList.get(i);
				for(int x = 0; x < game.tiles.size(); x++)
				{
					for(int w = 0; w < game.tiles.get(x).size(); w++)
					{
						
						game.tiles.get(x).get(w).setSelected(game.selectedPlayer);
						game.tiles.get(x).get(w).setCamX(x1);
						game.tiles.get(x).get(w).setCamY(y1);
					}
				}
				
			}
		}
		//IF STATEMENTS FOR CAMERA MOVEMENT USING ARROW KEYS.
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
		    //y = y + 4;
			y1 = y1 - 4;
			//game.socketClient.sendData("Player is moving down.");
			for(int i = 0; i < game.tiles.size(); i++)
			{
				for(int j = 0; j < game.tiles.get(i).size(); j++)
				{
					
					game.tiles.get(i).get(j).moveBy(0, .010f);
				}
			}
			
			for(int i = 0; i < game.playerList.size(); i++)
			{
				game.playerList.get(i).moveBy(0, 4f);
			}
			
			
			camY = camY - 4;
			game.cam.position.set(camX, camY, 0);
			game.cam.update();
			//batch.setProjectionMatrix(cam.combined);
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			//y = y - 4;
			y1 = y1 + 4;
			//game.socketClient.sendData("Player is moving up.");
			for(int i = 0; i < game.tiles.size(); i++)
			{
				for(int j = 0; j < game.tiles.get(i).size(); j++)
				{
					game.tiles.get(i).get(j).moveBy(0, -.010f);
				}
			}
			
			for(int i = 0; i < game.playerList.size(); i++)
			{
				game.playerList.get(i).moveBy(0, -4f);
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
			//game.socketClient.sendData("Player is moving left.");
			for(int i = 0; i < game.tiles.size(); i++)
			{
				for(int j = 0; j < game.tiles.get(i).size(); j++)
				{
					game.tiles.get(i).get(j).moveBy(.01f, 0);
				}
			}
			for(int i = 0; i < game.playerList.size(); i++)
			{
				game.playerList.get(i).moveBy(4f, 0);
			}
			
			camX = camX - 4;
			game.cam.position.set(camX, camY, 0);
			game.cam.update();
			//batch.setProjectionMatrix(cam.combined);
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			//x = x - 4;
			x1 = x1 + 4;
		//	game.socketClient.sendData("Player is moving right.");
			for(int i = 0; i < game.tiles.size(); i++)
			{
				for(int j = 0; j < game.tiles.get(i).size(); j++)
				{
					game.tiles.get(i).get(j).moveBy(-.01f, 0);
				}
			}
			
			for(int i = 0; i < game.playerList.size(); i++)
			{
				game.playerList.get(i).moveBy(-4f, 0);
			}
			
			camX = camX + 4;
			game.cam.position.set(camX, camY, 0);
			game.cam.update();
			//batch.setProjectionMatrix(cam.combined);
		}
		
		
		//Updates turnlist based on character's new speed.
		
		for(int i = 0; i < game.turnList.size(); i++)
		{
			for(int j = 0; j < game.turnList.get(i).size(); j++)
			{
				if(game.turnList.get(i).get(j).getSpeed() != i)
				{
					temp = game.turnList.get(i).get(j);
					game.turnList.get(i).remove(j);
					game.turnList.get(temp.getSpeed() -1).add(temp);
				}
			}
		}
		//If all players have gone this turn, turnOver = true..
		for(int i = 0; i < game.turnList.size(); i++ )
		{
			turnOver = true;
			for(int j = 0; j < game.turnList.get(i).size(); j++)
			{
				if(game.turnList.get(i).get(j).getGoneThisTurn() == false)
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
			for(int i = 0; i < game.playerList.size(); i++)
			{
				game.playerList.get(i).setTurn(false);
			}
		}
		
		//Finds the next character to act in the turnList
		for(int i = game.turnList.size() - 1; i >= 0; i--)
		{
			nextFound = false;
			for(int j = 0; j < game.turnList.get(i).size(); j++)
			{
				if(game.turnList.get(i).get(j).getGoneThisTurn() == false)
				{
					game.turnList.get(i).get(j).setNext(true);
					//System.out.println("It is " + game.turnList.get(i).get(j).getName() + "'s turn.");
					nextFound = true;
					break;
				}
				else
				{
					game.turnList.get(i).get(j).setNext(false);
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
		
		//NESTED LOOPS FOR DRAWING MAP game.tiles.
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
		
		for(int i = 0; i < game.tiles.size(); i++)
		{
			for(int j = 0; j < game.tiles.get(i).size(); j++)
			{
				if(game.tiles.get(i).get(j).getAlpha() == 1)
				{
					game.batch.draw(game.tiles.get(i).get(j).getSkin(), x + game.tiles.get(i).get(j).getfX(), y + game.tiles.get(i).get(j).getfY());
					font.draw(game.batch, game.tiles.get(i).get(j).getType(), x1 + Gdx.input.getX(), y1 + (Gdx.input.getY() + ((game.viewport.getWorldHeight()/2) - Gdx.input.getY())*2));
				}
			}
		}
		if(game.selectedPlayer != null)
		{
		if(game.selectedPlayer.getDisplayImage() != null)
		{
		game.batch.draw(game.selectedPlayer.getDisplayImage(), x1, y1 );
		}
		}
		//LOOP DRAWING PLAYERS
		for(int i = 0; i < game.playerList.size(); i++)
		{
			game.batch.draw(game.playerList.get(i).getSkin(),  game.playerList.get(i).getX(), game.playerList.get(i).getY());
			if(game.playerList.get(i).getMouseOver() == true)
			{
				font.draw(game.batch, game.playerList.get(i).getType(), x1 + Gdx.input.getX() - 85, y1 + (Gdx.input.getY() + ((game.viewport.getWorldHeight()/2) - Gdx.input.getY())*2));
			}
		}
		
		//ONLINE INPUT INTERPRETATION.
		
		if(game.newDirection)
		{
			System.out.println("Game screen acting on new direction.");
			System.out.println(game.currentDirection.substring(0, 1));
			if(game.currentDirection.substring(0, 1).equals("2"))
			{
				currentCharacterID = game.currentDirection.substring(1, 3);
				currentX = Integer.valueOf(game.currentDirection.substring(3, 5));
				currentY = Integer.valueOf(game.currentDirection.substring(5));
				
				for(int i = 0; i < game.playerList.size(); i++)
				{
					if(game.playerList.get(i).getID().equals(currentCharacterID))
					{
						for(int x = 0; x < game.tiles.size(); x++)
						{
							for(int z = 0; z < game.tiles.get(x).size(); z++)
							{
								System.out.println("Current Tile X: " + game.tiles.get(x).get(z).getX());
								System.out.println("Current Tile Y: " + game.tiles.get(x).get(z).getY());
								System.out.println("Acting X: " + currentX);
								System.out.println("Acting Y: " + currentY);
								if((game.tiles.get(x).get(z).getX() == currentX) && (game.tiles.get(x).get(z).getY() == currentY))
								{
									System.out.println("Moving character " + currentCharacterID + " to X: " + currentX + " Y: " + currentY);
									move.setPosition(game.tiles.get(x).get(z).fxC - game.tiles.get(x).get(z).camX, game.tiles.get(x).get(z).fyC - game.tiles.get(x).get(z).camY);
									move.setDuration(1f);
									game.playerList.get(i).setfxC(game.tiles.get(x).get(z).getfX());
									game.playerList.get(i).setfyC(game.tiles.get(x).get(z).getfY());
									game.playerList.get(i).setxC(currentX);
									game.playerList.get(i).setyC(currentY);
									System.out.println("New character coords: " + game.playerList.get(i).getXc() + " , " +  game.playerList.get(i).getYc());
									System.out.println("New Character Screen Location: X " + game.playerList.get(i).getX() + " , Y " + game.playerList.get(i).getY());
									System.out.println("Supposed to be: X " + game.tiles.get(x).get(z).getfX() + " , Y " +  game.tiles.get(x).get(z).getfY());
									game.playerList.get(i).addAction(move);
									game.playerList.get(i).setTurn(true);
									game.playerList.get(i).setNext(false);
									game.playerList.get(i).setSelected(false);
									currentCharacterID = "";
									game.newDirection = false;
									game.currentDirection = "";
									System.out.println("Directions complete.");
									break;
								}
							}
						}
					/*	System.out.println("Moving character " + currentCharacterID + " to X: " + currentX + " Y: " + currentY);
						move.setPosition(game.tiles.get(currentX).get(currentY).fxC - game.tiles.get(currentX).get(currentY).camX, game.tiles.get(currentX).get(currentY).fyC - game.tiles.get(currentX).get(currentY).camY);
						move.setDuration(1f);
						game.playerList.get(i).setfxC(game.tiles.get(currentX).get(currentY).getfX());
						game.playerList.get(i).setfyC(game.tiles.get(currentX).get(currentY).getfY());
						game.playerList.get(i).setxC(currentX);
						game.playerList.get(i).setyC(currentY);
						System.out.println("New character coords: " + game.playerList.get(i).getXc() + " , " +  game.playerList.get(i).getYc());
						System.out.println("New Character Screen Location: X " + game.playerList.get(i).getX() + " , Y " + game.playerList.get(i).getY());
						System.out.println("Supposed to be: X " + game.tiles.get(currentX).get(currentY).getfX() + " , Y " +  game.tiles.get(currentX).get(currentY).getfY());
						game.playerList.get(i).addAction(move);
						game.playerList.get(i).setTurn(true);
						game.playerList.get(i).setNext(false);
						game.playerList.get(i).setSelected(false);
						currentX = 0;
						currentY = 0;
						currentCharacterID = "";
						game.newDirection = false;
						game.currentDirection = "";*/
					}
				}
				
			}
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
