package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import characterPack.character;

public class Actor1 extends Actor {

	float alph;
	Sprite sprit;
	SpriteBatch render;
	public int txC;
	public int tyC;
	public float fxC;
	public float fyC;
	boolean clicked;
	String stype;
	BitmapFont type;
	int advantage;
	int movement;
	character selected;
	MoveToAction move = new MoveToAction();
	public int camX;
	public int camY;
	MyGdxGame game;
	String moveMessage;
	int SUPPOSEDX;
	public Boolean mouseOver;
	public Boolean acceptable;
	
	public Actor1(final MyGdxGame game, Texture texture, int x, int y, int xchord, int ychord, SpriteBatch b, character currentChar)
	{
		sprit = new Sprite(texture);
		setBounds(x, y, sprit.getWidth(), sprit.getHeight());
		setTouchable(Touchable.enabled);
		txC = xchord;
		tyC = ychord;
		fxC = x;
		fyC = y;
		alph = 0;
		render = b;
		clicked = false;
		stype = "";
		selected = currentChar;
		camX = 0;
		camY = 0;
		this.game = game;
		mouseOver = false;
		acceptable = false;
		
		//if(sprit.equals(new Sprite(new Texture("GRASS TILE1.png"))))
		//{
		advantage = 1;
		movement = 1;
			stype = "Plains\n     + " + movement +  " movement\n     +" + advantage + " advantage";
		//}
		type = new BitmapFont();
		
		addListener(new InputListener()
		{

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				System.out.println(txC + ", " + tyC );
				alph = 1;
				mouseOver = true;
				//render.begin();
				//type.draw(render, stype, Gdx.input.getX(), Gdx.input.getY());
				//render.end();
				super.enter(event, x, y, pointer, fromActor);
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				System.out.println("Off");
				if(clicked == false) {
				alph = 0;
				mouseOver = false;
				}
				super.exit(event, x, y, pointer, toActor);
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Click");
				for(int i = 0 ; i <  game.updater.getAdjacentTiles(game.tiles.get(txC).get(tyC)).size(); i++ )
				{
					System.out.println("This tile is " +  txC + ", "+ tyC);
					System.out.println("Its adjacent tiles are: ");
					System.out.println(game.updater.getAdjacentTiles(game.tiles.get(txC).get(tyC)).get(i).getXCoord() + ", " + game.updater.getAdjacentTiles(game.tiles.get(txC).get(tyC)).get(i).getYCoord());
				}
				if(selected != null)
				{
					if(selected.getNext() == true)
					{
						//if(selected.getSpeed() >= (Math.abs(selected.getXc() - txC)) && selected.getSpeed() >= Math.abs((selected.getYc() - tyC)) && !(selected.getXc() == tyC && selected.getYc() == txC))
						if(acceptable)
						{
						System.out.println("Moving.");
					move.setPosition(fxC - camX, fyC - camY);
					move.setDuration(1f);
					selected.setfxC(fxC);
					selected.setfyC(fyC);
					selected.setxC(txC);
					selected.setyC(tyC);
					selected.addAction(move);
					selected.setTurn(true);
					selected.setNext(false);
					selected.setSelected(false);
					selected.setCurrentTile(game.tiles.get(txC).get(tyC));
					System.out.println("Local character moved to: X " + fxC + ", Y " + fyC);
					if(txC < 10 && tyC > 9) 
							{
					moveMessage = "2" + selected.getID() + "0" + Integer.toString(txC) +  Integer.toString(tyC);
							}
							else if (txC > 9 && tyC < 10)
							{
								moveMessage = "2" + selected.getID() + Integer.toString(txC) + "0" + Integer.toString(tyC);
							}
							else if (txC > 9 && tyC > 9)
							{
								moveMessage = "2" + selected.getID() + Integer.toString(txC) + Integer.toString(tyC);
							}
							else if (txC < 10 && tyC < 10)
							{
								moveMessage = "2" + selected.getID() + "0" + Integer.toString(txC) + "0" + Integer.toString(tyC);
							}
					game.socketClient.sendData(moveMessage);
					moveMessage = "";
							}
				else
				{
					System.out.println("Not close enough.");
				}
					}
					}
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				clicked = false;
				alph = 0;
				super.touchUp(event, x, y, pointer, button);
			}
			
			
			
			
			
			
			
			
		});
		
	}

	public float getAlpha()
	{
		return alph;
	}
	
	public Sprite getSkin()
	{
		return sprit;
	}
	
	public int getXCoord()
	{
		return txC;
	}
	
	public int getYCoord()
	{
		return tyC;
	}
	
	public float getfY()
	{
		return fyC;
	}
	
	public float getfX()
	{
		return fxC;
	}

	public void moveBy(int x, int y) {
		fxC = fxC + x;
		fyC = fyC + y;
		super.moveBy(x, y);
	}

	public void moveTo(int i, int j) {
		fxC = fxC + i;
		fyC = fyC + j;
		
	}
	
	public String getType()
	{
		return stype;
	}
	
	
	public void setSelected(character select)
	{
		selected = select;
	}
	
	public void setCamX(int camOffX)
	{
		camX = camOffX;
	}
	
	public void setCamY(int camOffY)
	{
		camY = camOffY;
	}
	
	public int getCamX() {
		return camX;
	}
	public int getCamY()
	{
		return camY;
	}
	
	public void setAlpha(int value)
	{
		alph = value;
	}
	
	public void setXCoord(int coord) {
		txC = coord;
	}
	public void setYCoord(int coord) {
		tyC = coord;
	}
	
	
}
	

