package characterPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.SpawnPoint;

public class character extends Actor {
	
	
	int speed;
	int health;
	Sprite sprit;
	SpriteBatch batch;
	BitmapFont Bfont;
	Boolean selected;
	SpawnPoint spawn;
	float fxC;
	float fyC;
	int xC;
	int yC;
	int alpha;
	String sType;
	Boolean mouseOver;
	String type;
	Boolean goneThisTurn;
	Boolean isNext;
	Sprite large;
	
	
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
}
