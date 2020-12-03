package characterPack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.Actor1;
import com.mygdx.game.MyGdxGame;

public class Priest extends character {
	String name;
	int speed;
	int health;
	Sprite sprit;
	SpriteBatch batch;
	BitmapFont Bfont;
	Boolean selected;
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
	Texture model = new Texture("PRIEST.png");
	Sprite large;
	public int team;
	public String characterID;
	Actor1 currentTile;

	MyGdxGame game;
	
	public Priest(final MyGdxGame game) {
		super(game);
		this.game = game;
		name = "Priest";
		sprit = new Sprite(model);
		large = new Sprite(model);
		large.setScale(3);
		goneThisTurn = false;
		health = 125;
		speed = 4;
		batch = game.batch;
		Bfont = new BitmapFont();
		selected = false;
		fxC = 90;
		fyC = 0;
		setBounds(90, 0, sprit.getWidth(), sprit.getHeight());
		setTouchable(Touchable.enabled);
		alpha = 1;
		sType = "Priest" + "\nHealth: " + health + "\nSpeed: "+ speed;
		mouseOver = false;
		isNext = false;
		characterID = "02";
		
		addListener(new InputListener()
		{

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				
				mouseOver = true;
				System.out.println("Mouseover");
				super.enter(event, x, y, pointer, fromActor);
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
			
				mouseOver = false;
				super.exit(event, x, y, pointer, toActor);
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(game.player == team)
				{
				if(selected == false)
				{
				selected = true;
				}
				else
				{
					selected = false;
				}
				}
				
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				
				
				super.touchUp(event, x, y, pointer, button);
			}
			
			
			
			
			
			
			
			
			
		});
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
		public String getName()
		{
			return name;
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
		public String getID()
		{
			return characterID;
		}
		public Actor1 getCurrentTile()
		{
			return currentTile;
		}
		public void setCurrentTile(Actor1 tile) {
			currentTile = tile;
		//	System.out.println("CHARACTER " + this.getType() + " IS NOW ASSOCIATED WITH TILE " + tile.getXCoord() + ", " + tile.getYCoord());
		}
	}
