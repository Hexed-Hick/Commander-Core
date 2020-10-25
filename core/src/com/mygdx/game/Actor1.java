package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import characterPack.character;

public class Actor1 extends Actor {

	float alph;
	Sprite sprit;
	SpriteBatch render;
	int xC;
	int yC;
	float fxC;
	float fyC;
	boolean clicked;
	String stype;
	BitmapFont type;
	int advantage;
	int movement;
	character selected;
	MoveToAction move = new MoveToAction();
	int camX;
	int camY;
	MyGdxGame game;
	
	public Actor1(final MyGdxGame game, Texture texture, int x, int y, int xchord, int ychord, SpriteBatch b, character currentChar)
	{
		sprit = new Sprite(texture);
		setBounds(x, y, sprit.getWidth(), sprit.getHeight());
		setTouchable(Touchable.enabled);
		xC = xchord;
		yC = ychord;
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
			//	System.out.println(xC + ", " + yC );
				alph = 1;
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
				}
				super.exit(event, x, y, pointer, toActor);
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Click");
				if(selected != null)
				{
				//if(selected.getSpeed() >= (Math.abs(selected.getXc() - xC)) && selected.getSpeed() >= Math.abs((selected.getYc() - yC)) )
			//	{
					if(selected.getNext() == true)
					{
						System.out.println("Moving.");
					move.setPosition(fxC - camX, fyC - camY);
					move.setDuration(1f);
					selected.setfxC(fxC);
					selected.setfyC(fyC);
					selected.setxC(xC);
					selected.setyC(yC);
					selected.addAction(move);
					selected.setTurn(true);
					selected.setNext(false);
					selected.setSelected(false);
					
					}
				}
				//else
				//{
				//	System.out.println("Not close enough.");
				//}
				//}
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
	
	public float getX()
	{
		return xC;
	}
	
	public float getY()
	{
		return yC;
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
	
}
	

