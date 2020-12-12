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
import com.mygdx.game.gameThreads.TileUpdater;
import com.mygdx.game.server.Client;
import com.mygdx.game.server.Server;

import characterAbilities.Ability;
import characterPack.character;

public class MyGdxGame extends Game implements Runnable {
	public SpriteBatch batch;
	int screen;
	Texture GT1;
	Texture FMGT;
	Texture archer;
	Texture outline;
	Texture knight;
	public Stage stage;
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
	//ArrayList representing tile map. Actual tile graphics are drawn directly from sprites based on map size. "tiles" array is merely an array of the tile ACTORS, whose visual is the red outline.
	//The red outline object ,whether visible or not, is handling all user interaction with tiles. 
	public ArrayList<ArrayList <Actor1>> tiles;
	//Blank arrays used as templates to avoid nullPointer errors.
	ArrayList<Actor1> blank;
	ArrayList<character> blankCharacterArray;
	character temp;
	//Viewport is an object that represents a virtual screen space. When drawing pixels to the game space, the viewport can help us to maintain a constant standard. Shrink the viewport = shrink the resolution
	// Without having to alter our drawing methods too much. Can stretch and also works well with the camera object, which represents our view perspective and allows us to move around the game space without
	//physically changing the location of our game objects. (We are moving, instead of moving the game around)
	Viewport viewport;
	Camera cam;
	//Template moveTo action, for which we can add specific distance and timespan values to cause ACTORS to physically move around the game space.
	MoveToAction moveTo;
	//This is a special font object that allows users to click on the text and type in their own input. There are methods to retrieve the user's input and use it however is needed. In our
	//case, we are using it to find a specific IP and port. (THE OFFICIAL GAME PORT IS 41795 TO AVOID CONFLICTS.)
	BitmapFont font;
								//character archerPlayer;
								//character knightPlayer;
	
	//Playerlist that adds the game's characters as they are selected. This playerlist contains ALL character's from both teams, and allows us to figure out who goes next, who has or hasn't gone the current turn
	//and to find game characters easily. This list is used often in the Interpreter.
	public ArrayList<character> playerList;
	
	//The selectedPlayer object is whichever character you have most recently clicked on. When a character is registered as selected, you can now move them by clicking a tile, and having them selected
	//Should highlight the tiles around them that are in range.
	public character selectedPlayer;
	//This will be used later to split input between game space and UI.
	InputMultiplexer multi;
	//This is a constantly changing turnList that checks the character's speed every render frame or after each turn has ended, and reorganizes the characters by current speed. It cycles backwards.
	// Characters in the 6th row have 6 speed points, and should move first. Characters in the 1st row have 1 speed, and should move last.
	ArrayList<ArrayList<character>> turnList;
	//If nextFound, then the loop that searches for the character that goes next can stop searching.
	boolean nextFound;
	//If turnOver, then all characters are reset to hasGoneThisTurn == false, and the next turn can begin.
	boolean turnOver;
	//If hosting, then the player's client will initiate a server and a serverClient to communicate with the other player. An IP and port will be printed to send to the other player to join.
	boolean hosting;
	//If joining, then the player's client will only initiate a serverClient and prompt them to connect to a server.
	boolean joining;
	//The lists containing the characters specific to each team. This is how the game checks if the player has the privilege to see or move a character.
	public ArrayList<character> team1;
	public ArrayList<character> team2;
	//The currentDirection is the current message sent from the server. It will be deleted as soon as the Interpreter object has parsed through it.
	public String currentDirection;
	//If newDirection, then an interpreter will initialize and handle the currentDirection, deleting the direction and setting newDirection back to false.
	public boolean newDirection;
	//These ID's are using in the interpreter to handle instructions more efficiently.
	public String currentCharacterID1;
	String currentChaacterID2;
	public int currentX;
	public int currentY;
	public int player;
	public Actor1 currentTile;
	
	//Object that parses through multiplayer server instructions.
	public Interpreter interpreter;
	
	//Multiplayer server objects
	 Client socketClient;
	 Server socketServer;
	 boolean isHosting;
	 boolean isConnecting;
	 ArrayList<Actor1> acceptableTiles;
	 
	 //List of tiles that should currently be highlighted, aside from the currently selected tile. (Showing moveable or attackable tiles).
	 ArrayList<Actor1> tempTiles = new ArrayList<Actor1>();
	 public ArrayList<Ability> abilities;
	 public TileUpdater updater;
	
	@Override
	public void create () {
		//These initialize each of the objects necessary for drawing sprites to the screen and handling resolution and camera functions. 
		//THE BATCH is the object that draws the individual sprites.
		batch = new SpriteBatch();
		//THE FONT handles user text input and text display
		font = new BitmapFont();
		//CAM AND VIEWPORT SEE LINE 49
		cam = new OrthographicCamera();
		viewport = new StretchViewport(1366, 768, cam);
		viewport.apply();
		

		setScreen(new MenuScreen(this));
		camX = viewport.getScreenWidth()/2;
		camY = viewport.getScreenHeight()/2;
		cam.position.set(camX, camY, 0);
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
/*	public ArrayList<Actor1> getAdjacentTiles(Actor1 tile)
	{
		ArrayList<Actor1> tiles = new ArrayList<Actor1>();
		Boolean even = false;
		System.out.println("RUNNING getAdjacentTiles.exe.    Tile X: " + tile.getXCoord() + " Tile Y: " + tile.getYCoord());
		if(tile.getXCoord()%2 == 0) {
			even = true;
		}
		if(even) {
						if(tile.getXCoord() + 1 < this.tiles.size() ) {
						tiles.add(this.tiles.get(tile.getXCoord() + 1).get(tile.getYCoord()));
			}
						if( tile.getYCoord() + 1 < this.tiles.size()) {
						tiles.add(this.tiles.get(tile.getXCoord()).get(tile.getYCoord() + 1 ));
						}
						if( tile.getXCoord() > 0) {
						tiles.add(this.tiles.get(tile.getXCoord() - 1).get(tile.getYCoord()));
						}
						if(tile.getYCoord() > 0) {
						tiles.add(this.tiles.get(tile.getXCoord()).get(tile.getYCoord() - 1));
						}
						if( tile.getXCoord() + 1 < this.tiles.size() && tile.getYCoord() > 0) {
						tiles.add(this.tiles.get(tile.getXCoord() + 1).get(tile.getYCoord() - 1));
						}
						if( tile.getXCoord() > 0 && tile.getYCoord() > 0) {
						tiles.add(this.tiles.get(tile.getXCoord() -1).get(tile.getYCoord() - 1));
						}
					
					

		}
		if(!even) {
						if(tile.getXCoord() > 0 && tile.getYCoord() + 1 < this.tiles.size()) {
						tiles.add(this.tiles.get(tile.getXCoord() - 1).get(tile.getYCoord() + 1));
						}
						if( tile.getYCoord() + 1 < this.tiles.size()) {
						tiles.add(this.tiles.get(tile.getXCoord()).get(tile.getYCoord() + 1));
						}
						if( tile.getXCoord() + 1 < this.tiles.size() && tile.getYCoord() + 1 < this.tiles.size()) {
						tiles.add(this.tiles.get(tile.getXCoord() + 1).get(tile.getYCoord() +1));
						}
						if( tile.getXCoord() > 0) {
						tiles.add(this.tiles.get(tile.getXCoord() - 1).get(tile.getYCoord()));
						}
						if(tile.getYCoord() > 0) {
						tiles.add(this.tiles.get(tile.getXCoord()).get(tile.getYCoord() - 1));
						}
						if(tile.getXCoord() + 1 < this.tiles.size()) {
						tiles.add(this.tiles.get(tile.getXCoord() + 1).get(tile.getYCoord()));
						}
					
					
				
			
		}
		
		return tiles;
	}
	
	public void updateTempTiles(Actor1 tile, int distance)
	{
		if(selectedPlayer != null)
		{
			
			if(distance <= selectedPlayer.getSpeed())
			{
				if(distance == 0)
				{
				//	System.out.println("Update TempTiles step 1.");
					tile = this.tiles.get(this.selectedPlayer.getXc()).get(this.selectedPlayer.getYc());
					System.out.println("Running updateTempTiles.exe.     Tile X: " + tile.getXCoord() + " Tile Y: " + tile.getYCoord());
					distance++;
					ArrayList<Actor1> tiles = getAdjacentTiles(tile);
					for(int i = 0; i < tiles.size(); i++)
					{
						this.updateTempTiles(tiles.get(i), distance);
					}
				}
				else
				{
					//System.out.println("UpdateTempTiles step " + distance + ".");
					tile.setAlpha(1);
					tile.acceptable = true;
					this.tiles.get(tile.getXCoord()).get(tile.getYCoord()).setAlpha(1);
					ArrayList<Actor1> tiles = getAdjacentTiles(tile);
					distance++;
					//if(distance < this.selectedPlayer.getSpeed())
					//{
					for(int i = 0; i < tiles.size(); i++)
					{
						this.updateTempTiles(tiles.get(i), distance);
					}
					//}
				}
				
			}
		}
		else
		{
			
		}
	}
	
	public void resetTiles() {
		for(int i = 0; i < this.tiles.size(); i++) {
			for(int j = 0; j < this.tiles.get(i).size(); j++) {
				if(!this.tiles.get(i).get(j).mouseOver)
				this.tiles.get(i).get(j).setAlpha(0);
			}
		}
	}*/

}
