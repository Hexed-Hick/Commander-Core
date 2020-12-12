package characterPack;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Actor1;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.SpawnPoint;

import characterAbilities.Ability;

public class character extends Actor {
	
	
	//THIS IS THE BASIC TEMPLATE FOR THE GAME'S CHARACTERS. EACH CHARACTER HAS A UNIQUE CLASS WITH CONSTANT VALUES, SO WE DO NOT HAVE TO SPECIFICALLY SET EACH VALUE UPON CREATION.
	//THIS CLASS CONTAINS ALL OF THE BASIC CHARACTER DATA AND METHODS THAT ALLOW US TO USE THEM PROPERLY. PLEASE BECOME FAMILIAR WITH THIS CLASS.
	
	//SPEED represents the turn priority of a character, as well as the distance they are able to travel. 
	int speed;
	//Self explanitory. 100 health is the average, health below 100 will be weaker characters, above will be tank characters.
	int health;
	//The sprite is the .PNG image within the assetts file
	Sprite sprit;
	//This will be initialized within each character to be the spriteBatch from the MyGdxGame OBJECT.
	SpriteBatch batch;
	//This is a new bitmap font that will draw itself to the screen when hovering over a character. This displays character's class and current stats.
	BitmapFont Bfont;
	// The game code will iterate through each character and check if they are selected. If SELECTED, then the abilities and stats will be provided on the screen, and clicking on a tile will cause the character
	//to move, if the tile is within distance.
	Boolean selected;
	//This may be implemented later, with a coordinate object so that coordinates can be stored and interpreted all at once, instead of manually reading and setting each x, y coordinate individually.
	SpawnPoint spawn;
	//The FLOAT value coordinates that correlate to the pixel location on screen.
	float fxC;
	float fyC;
	//The INTEGER value coordinates that represent the current TILE MAP position.
	int xC;
	int yC;
	//Only draw sprites with alpha value = 1
	int alpha;
	//sType = string type. This is a string that is initiated to indicate the TYPE of character the object is. (Archer sType = "Archer")
	String sType;
	//If mouseOver, draw character stats to mouse location on screen.
	Boolean mouseOver;
	//type is a formatted block of stats and sType that is printed to mouse location.
	String type;
	//If goneThisTurn, then the character will not have its next turn until the next round.
	Boolean goneThisTurn;
	//if isNext, the user can preform an action with that character. Upon completion, goneThisTurn = true.
	Boolean isNext;
	//A second sprite to display a selected character in later in game GUI.
	Sprite large;
	//Integer value representing which team the character belongs to. team = 1 when player is hosting. team = 2 when player is connecting.
	public int team;
	// Unique ID representing the class of character. This is useful for using in server data intepretation. 
	public String characterID;
	
	public Actor1 currentTile;
	
	public Ability ability1;
	public Ability ability2;
	public Ability ability3;
	
	public character(MyGdxGame game)
	{
	}
	//returns true if character is selected by clicking.
	public boolean getSelected()
	{
		return selected;
	}
	
	//Sets X coordinate in terms of gameboard.
	public void setxC(int x)
	{
		xC = x;
	}
	//Sets Y coordinate in terms of gameboard.
	public void setyC(int y) {
		yC = y;
	}
	public int getAlpha()
	{
		return alpha;
	}
	public float getX()
	{
		return fxC;
	}
	public float getY()
	{
		return fyC;
	}
	
	public Sprite getSkin()
	{
		return sprit;
	}
	public Boolean getMouseOver()
	{
		return mouseOver;
	}
	
	public String getType()
	{
		return sType;
	}
	public void moveBy(int x, int y) {
		fxC = fxC + x;
		fyC = fyC + y;
		super.moveBy(x, y);
	}
	
	public int getSpeed()
	{
		return speed;
	}
	public int getYc()
	{
		return yC;
	}
	public int getXc()
	{
		return xC;
	}
	public void setfxC(float num)
	{
		fxC = num;
	}
	public void setfyC(float num)
	{
		fyC = num;
	}
	public boolean getGoneThisTurn()
	{
		return goneThisTurn;
	}
	public void setTurn(boolean turn)
	{
		goneThisTurn = turn;
	}
	public boolean getNext()
	{
		return isNext;
	}
	public void setNext(boolean next)
	{
		isNext = next;
	}
	
	public void setSpawn(SpawnPoint spawn)
	{
		this.spawn = spawn;
		xC = this.spawn.getX();
		yC = this.spawn.getY();
	}
	
	public int getSpawnX()
	{
		return this.spawn.getX();
	}
	public int getSpawnY()
	{
		return this.spawn.getY();
	}
	
	public Sprite getDisplayImage()
	{
		return large;
	}
	
	public void setSelected(Boolean select)
	{
		selected = select;
	}
	
	public int getTeam()
	{
		return team;
	}
	public void setTeam(int player)
	{
		team = player;
	}
	public String getID() {
		return characterID;
	}
	public Actor1 getCurrentTile()
	{
		return currentTile;
	}
	public void setCurrentTile(Actor1 tile) {
		currentTile = tile;
		System.out.println("CHARACTER " + this.getType() + " IS NOW ASSOCIATED WITH TILE " + tile.getXCoord() + ", " + tile.getYCoord());
	}
	public Ability getAbility1()
	{
		return ability1;
	}
	
	public Ability getAbility2() {
		return ability2;
	}
	
	public Ability getAbility3() {
		return ability3;
	}
}
